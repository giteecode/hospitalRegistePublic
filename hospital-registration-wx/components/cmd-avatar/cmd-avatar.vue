<template>
	<view class="cmd-avatar" :class="setShapeSizeClass" :style="setIconTextStyle+setNumSizeStyle" @tap="$_click">
		<image v-if="src" class="cmd-avatar-img" mode="aspectFit" :src="src" @load="$_imageLoad" @error="$_imageError"></image>
		<cmd-icon v-if="icon && !src && !text" :type="icon" :size="setIconSize" :color="make.color"></cmd-icon>
		<text v-if="text && !src && !icon" v-text="text"></text>
	</view>
</template>

<script>
	import cmdIcon from '../cmd-icon/cmd-icon.vue'

	/**  
	 * 头像组件  
	 * @description 用于展示用户头像、数字号、单字符。  
	 * @tutorial https://ext.dcloud.net.cn/plugin?id=176  
	 * @property {String,Number} text 头像显示文字 - 建议取首位text[0]，长度小于3位  
	 * @property {String} icon 头像显示图标icon - 列如：home、add  
	 * @property {String} src 头像图片地址 - 仅支持相对路径、绝对路径，支持 base64 码  
	 * @property {String,Number} size 头像显示大小 - sm，lg，md，任意Number值  
	 * @property {String} shape 头像形状 - 圆形：circle，矩形：square  
	 * @property {Object} make 自定义头像样式 - 颜色、背景、边距，列如：{'margin-right': '8px'}  
	 * @event {Function} click 头像组件点击事件  
	 * @event {Function} load 图片载入成功事件  
	 * @event {Function} error 图片载入错误事件  
	 * @example <cmd-avatar text='CMD'></cmd-avatar>  
	 */
	export default {
		name: 'cmd-avatar',

		components: {
			cmdIcon
		},

		props: {
			/**
			 * 大小 sm md lg Number
			 */
			size: {
				validator: val => {
					return typeof val === 'number' || ['sm', 'lg', 'md'].includes(val);
				},
				default: 'md'
			},
			/**
			 * 形状 circle圆形 square矩形
			 */
			shape: {
				validator: val => ['circle', 'square'].includes(val),
				default: 'circle'
			},
			/**
			 * 样式属性style
			 */
			make: {
				type: Object,
				default: () => {
					return {
						'color': ''
					};
				}
			},
			/**
			 * 图片网络地址
			 */
			src: {
				type: String,
				default: ''
			},
			/**
			 * 图标type名称
			 */
			icon: {
				type: String,
				default: ''
			},
			/**
			 * 文本名字建议取首位text[0],长度小于3位
			 */
			text: {
				type: [String, Number],
				default: ''
			}
		},

		computed: {
			// 形状大小
			setShapeSizeClass() {
				let classList = []
				if (['circle', 'square'].includes(this.shape)) {
					classList.push(`cmd-avatar-${this.shape}`)
				}
				if (['sm', 'lg', 'md'].includes(this.size)) {
					classList.push(`cmd-avatar-${this.size}`)
				}
				return classList
			},
			// 自定义大小
			setNumSizeStyle() { 
				return typeof this.size === 'number' ?
					`width:${this.size}px;` +
					`height:${this.size}px;` +
					`font-size:${(this.size / 2)}px;` +
					`line-height:${this.size}px;` :'';
			},
			// 图标文本样式
			setIconTextStyle() {
				let styleString = '';
				for (let it in this.make) {
					styleString += `${it}:${this.make[it]};`;
				}
				return styleString;
			},
			// 图标大小
			setIconSize() {
				if (typeof this.size === 'number') {
					return this.size / 2;
				}
				if (this.size === 'sm') {
					return uni.upx2px(64) / 2;
				}
				if (this.size === 'lg') {
					return uni.upx2px(128) / 2;
				}
				return uni.upx2px(96) / 2;
			}
		},

		methods: {
			// 当图片载入完毕时
			$_imageLoad(e) {
				this.$emit('load', e);
			},
			// 当错误发生时
			$_imageError(e) {
				this.$emit('error', e);
			},
			// 点击事件
			$_click(e) {
				this.$emit('click', e)
			}
		}
	}
</script>

<style>
	.cmd-avatar {
		color: #fff;
		text-align: center;
		border-radius: 6upx;
		background: #eee;
		box-shadow: 0 2px 1px -1px rgba(0, 0, 0, .2), 0 1px 1px 0 rgba(0, 0, 0, .14), 0 1px 3px 0 rgba(0, 0, 0, .12);
		overflow: hidden;
	}

	.cmd-avatar-img {
		width: 100%;
		height: 100%;
	}

	.cmd-avatar-lg {
		width: 128upx;
		height: 128upx;
		font-size: 64upx;
		line-height: 128upx;
	}

	.cmd-avatar-sm {
		width: 64upx;
		height: 64upx;
		font-size: 32upx;
		line-height: 64upx;
	}

	.cmd-avatar-md {
		width: 96upx;
		height: 96upx;
		font-size: 48upx;
		line-height: 96upx;
	}

	.cmd-avatar-square {
		border-radius: 6upx;
		background-clip: border-box;
		overflow: hidden;
	}

	.cmd-avatar-circle {
		border-radius: 50%;
		background-clip: border-box;
		overflow: hidden;
	}
</style>
