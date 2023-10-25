<template>
	<view>
		<view v-if="hasSlot" @tap="show">
			<slot />
		</view>
		<view class="xp-picker" :style="{'visibility':pickerVisible?'visible':'hidden'}">
			<view class="xp-picker-mask" :class="{'xp-picker-animation':animation}" :style="{'opacity':pickerVisible?0.6:0}"
			 @tap="_cancel"></view>
			<view class="xp-picker-container" :class="{'xp-picker-container--show':pickerVisible,'xp-picker-animation':animation}">
				<view v-if="actionPosition==='top'" class="xp-picker-action">
					<view class="xp-picker-action--cancel" @tap="_cancel">取消</view>
					<view class="xp-picker-action--confirm" @tap="_confirm">确定</view>
				</view>
				<view v-if="isError" class="xp-picker-error" :style="{'height':height+'vh'}">
					<text>Error！please check your configuration</text>
					<text>（请检查你的配置 或 查看控制台错误信息）</text>
				</view>
				<picker-view v-else :style="{'height':height+'vh'}" indicator-style="height:40px;" :value="selected" @change="_change">
					<picker-view-column v-for="(k,i) in modeArr" :key="i" class="xp-picker-column">
						<view class="xp-picker-list-item" v-for="(item,index) in cols[i]" :key="index">
							{{item+units[i]}}
						</view>
					</picker-view-column>
				</picker-view>
				<view v-if="actionPosition==='bottom'" class="xp-picker-btns">
					<button class="xp-button xp-button--cancel" @tap="_cancel">取消</button>
					<button class="xp-button xp-button--confirm" @tap="_confirm">确定</button>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		templateFactory,
		getLocalTime,
		fmtNumber,
		time2Timestamp,
		getDate
	} from "./util.js"
	export default {
		name: 'XpPicker',
		data() {
			return {
				isError: true,
				isConfirm: false,
				pickerVisible: false,
				template: {},
				cols: [],
				selected: []
			}
		},
		props: {
			mode: {
				type: String,
				default: "ymd"
			},
			animation: {
				type: Boolean,
				default: true
			},
			height: {
				type: [Number, String],
				default: 35
			},
			'action-position': {
				type: String,
				default: "top"
			},
			'year-range': {
				type: Array,
				default: () => [2010, null]
			},
			value: {
				type: String,
				default: null
			},
			history: {
				type: Boolean,
				default: false
			}
		},
		watch: {
			mode() {
				this.render()
			}
		},
		computed: {
			hasSlot() {
				return !!this.$slots['default']
			},
			modeArr() {
				return this.mode.split("")
			},
			units() {
				const arr = []
				for (const k in this.template) {
					if (this.mode.indexOf(k) !== -1) arr.push(this.template[k].text)
				}
				return arr
			},
		},
		created() {
			this.render()
		},
		methods: {
			render() {
				this.assert() //检查用户配置
				this.template = templateFactory(this) //生成所需列 默认模板 
				this.initCols() //根据模板 初始化列
				this.initSelected() //设置默认值
			},
			assert() {
				if ("ymdhis".indexOf(this.mode) === -1) {
					throw new Error("render error，illegal 'mode'")
				}
				if (getLocalTime(this.mode) == undefined) {
					throw new Error("render error，the 'mode' is not found")
				}
				if (this.value != null) {
					const arr = this.value.split(/-|:|\s/)
					if (arr.length != this.modeArr.length) {
						throw new Error("render error，because the 'value' cannot be formatted as 'mode'")
					}
				}
				if (this.yearRange.length !== 2) {
					throw new Error("render error，because the length of array 'year-rang' must be 2")
				}
				this.isError = false
			},
			initCols() {
				for (const k of this.mode) {
					const range = this.template[k].range
					this.fillCol(k, ...range)
				}
			},
			initSelected() {
				const v = this.value || getLocalTime(this.mode)
				this.setSelected(v)
			},
			fillCol(k, s, e) {
				const index = this.mode.indexOf(k)
				let arr = []
				for (let i = s; i <= e; i++)
					arr.push(fmtNumber(i))
				this.$set(this.cols, index, arr)
			},
			//dt 时间字符串 如 '2020-02-16'
			setSelected(dt) {
				const arr = dt.split(/-|:|\s/)
				const a = this.cols
				for (let i = 0; i < a.length; i++)
					this.$set(this.selected, i, a[i].indexOf(arr[i]))
			},
			resolveCurrentDt() {
				let str = ""
				for (let i = 0; i < this.selected.length; i++)
					str += this.cols[i][this.selected[i]] + this.units[i]
				let dt = str
					.replace('年', '-')
					.replace('月', '-')
					.replace('日', ' ')
					.replace('时', ':')
					.replace('分', ':')
					.replace('秒', '')
				if (!this.mode.endsWith('s'))
					dt = dt.substring(0, dt.length - 1)
				return dt
			},
			show() {
				if (this.history) {
					if (!this.isConfirm) this.initSelected()
				} else
					this.initSelected()
				this.pickerVisible = true
			},
			_confirm() {
				if (!this.isError) this.$emit('confirm', this._getResult())
				if (!this.isConfirm) this.isConfirm = true
				this.pickerVisible = false
			},
			_getResult() {
				const detail = {
					value: this.resolveCurrentDt()
				}
				const tp = time2Timestamp(detail.value)
				if (!isNaN(tp)) detail.timestamp = tp
				return detail
			},
			_cancel() {
				this.$emit('cancel')
				this.pickerVisible = false
			},
			_change(e) {
				let col;
				const newValue = e.detail.value
				for (let i = 0; i < newValue.length; i++) {
					if (newValue[i] !== this.selected[i]) {
						col = this.modeArr[i]
						break
					}
				}
				this.selected = newValue
				const index = this.mode.indexOf("d")
				if (index !== -1 && (col === 'y' || col === 'm')) {
					const currentDt = this.resolveCurrentDt()
					this.fillCol("d", 1, getDate(currentDt))
				}
			}
		}
	}
</script>

<style scoped lang="scss">
	.xp-picker {
		position: fixed;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;
		z-index: 999;
		font-size: 30rpx;
	}

	.xp-picker-container {
		position: fixed;
		bottom: 0;
		transform: translateY(100%);
		z-index: 999;
		width: 100%;
		background-color: #fff;
		visibility: hidden;

	}

	.xp-picker-container--show {
		transform: translateY(0);
		visibility: visible;
	}

	.xp-picker-mask {
		z-index: 998;
		width: 100%;
		height: 100%;
		background-color: rgb(0, 0, 0);
	}

	.xp-picker-animation {
		transition: all 0.25s;
	}

	.xp-picker-error {
		width: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		color: #ff0000
	}

	.xp-picker-action {
		width: 100%;
		display: flex;
		justify-content: space-between;
		align-items: center;
		height: 90rpx;
		padding: 0 28rpx;
		box-sizing: border-box;
		position: relative;
		font-size: 34rpx;
		border-bottom: 0.5px solid #e5e5e5
	}

	.xp-picker-btns {
		width: 100%;
		display: flex;
		justify-content: space-around;
		align-items: center;
		height: 160rpx;
		padding: 0 20rpx;
		box-sizing: border-box;
		position: relative;
	}

	.xp-button {
		line-height: 2.3;
		font-size: 32rpx;
		margin: 0;
		padding: 0 80rpx;
	}

	.xp-button:after {
		border: none;
	}

	.xp-button--cancel {
		background-color: #f5f5f5;
		color: #000;
	}

	.xp-button--confirm {
		background-color: #47a16e;
		color: #fff;
	}

	.xp-picker-action--cancel {
		opacity: .7;
	}

	.xp-picker-action--confirm {
		color: #007aff;
	}

	.xp-picker-column {
		text-align: center;
		border: none;
		font-size: 34rpx;
	}

	.xp-picker-list-item {
		display: flex;
		justify-content: center;
		align-items: center;
		height: 40px;
	}
</style>
