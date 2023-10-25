<template>
	<view class="cmd-cell-item" :class="{'is-disabled': disabled,'no-border': !border }" @tap="$_click" :hover-class="hoverClass">
		<view class="cmd-cell-item-body">
			<view class="cmd-cell-item-left" v-if="slotLeft">
				<slot></slot>
			</view>
			<view class="cmd-cell-item-content">
				<view class="cmd-cell-item-title" v-if="title" v-text="title"></view>
				<view class="cmd-cell-item-brief" v-if="brief" v-text="brief"></view>
			</view>
			<view class="cmd-cell-item-right" v-if="slotRight">
				<slot></slot>
				<view class="cmd-cell-icon-arrow-right">
					<cmd-icon v-if="arrow" type="chevron-right" size="24" color="#C5CAD5"></cmd-icon>
				</view>
			</view>
			<view class="cmd-cell-item-right" v-if="!slotRight">
				<text :class="addon.length > 18 ? 'cmd-cell-addon-text':''">{{addon}}</text>
				<view class="cmd-cell-icon-arrow-right">
					<switch v-if="showSwitch" :color="switchColor" :disabled='disabled' :checked="switchState" @change="$_switch" />
					<cmd-icon v-if="arrow" type="chevron-right" size="24" color="#C5CAD5"></cmd-icon>
				</view>
			</view>
		</view>
		<view v-if="addon2" v-text="addon2" class="cmd-cell-item-children" style="font-size: 24upx;color: #858b9c;"></view>
	</view>
</template>

<script>
	import cmdIcon from '../cmd-icon/cmd-icon.vue'

	/**  
	 * 列表单元组件
	 * @description 列表用于展现并列层级的信息内容，列表可承载功能入口、功能操作、信息展示等功能。  
	 * @tutorial http://ext.dcloud.net.cn/plugin?id=177  
	 * @property {String} title 标题 - 默认：空  
	 * @property {String} brief 标题下描述文本 - 默认：空  
	 * @property {String} addon 附加文本 - 默认：空  
	 * @property {String} addon2 附加文本2 - 在列表单元下显示文字说明，默认：空  
	 * @property {Boolean} arrow 动作箭头标识 - 默认：false  
	 * @property {Boolean} disabled 禁用状态 - 默认：false  
	 * @property {Boolean} border 下边框线列表单元项，默认：true  
	 * @property {Boolean} show-switch 显示开关Switch - 默认：false  
	 * @property {Boolean} switch-state 开关选中状态 - 默认：false  
	 * @property {Boolean} switch-color 开关颜色 - 默认：蓝色，MP绿色  
	 * @property {Boolean} slot-left 插槽左 - 不可同时打开两个插槽，默认：false  
	 * @property {Boolean} slot-right 插槽右 - 不可再使用switch addon，默认：false  
	 * @event {Function} click 列表单元项 点击事件  
	 * @event {Function} switch 列表单元项切换 Switch 触发事件  
	 * @example <cmd-cell-item title="普通条目" addon="附加文案" arrow></cmd-cell-item>
	 */
	export default {
		name: 'cmd-cell-item',

		components: {
			cmdIcon
		},

		props: {
			/**
			 * 标题
			 */
			title: {
				type: String,
				default: ''
			},
			/**
			 * 标题描述文本
			 */
			brief: {
				type: String,
				default: ''
			},
			/**
			 * 附加文本
			 */
			addon: {
				type: String,
				default: ''
			},
			/**
			 * 附加文本2 item下文字说明
			 */
			addon2: {
				type: String,
				default: ''
			},
			/**
			 * 动作箭头标识
			 */
			arrow: {
				type: Boolean,
				default: false
			},
			/**
			 * 是否禁用项
			 */
			disabled: {
				type: Boolean,
				default: false
			},
			/**
			 * 下边框线
			 */
			border: {
				type: Boolean,
				default: true
			},
			/**
			 * 显示开关
			 */
			showSwitch: {
				type: Boolean,
				default: false
			},
			/**
			 * 开关状态 是否被选中
			 */
			switchState: {
				type: Boolean,
				default: false
			},
			/**
			 * 开关颜色
			 */
			switchColor: {
				type: String,
				default: ''
			},
			/**
			 * 插槽左 不可同时打开两个插槽
			 */
			slotLeft: {
				type: Boolean,
				default: false
			},
			/**
			 * 插槽右 不可再使用switch addon
			 */
			slotRight: {
				type: Boolean,
				default: false
			},
			/**
			 * 指定按钮按下去的样式类
			 */
			hoverClass: {
				type: String,
				default: 'cmd-cell-item-hover'
			}
		},

		methods: {
			/**
			 * item项点击事件
			 */
			$_click(e) {
				if (!this.disabled) {
					this.$emit('click', e)
				}
			},
			/**
			 * 开关触发改变事件
			 */
			$_switch(e) {
				if (!this.disabled) {
					this.$emit('switch', e)
				}
			}
		}

	}
</script>

<style>
	.cmd-cell-item {
		position: relative;
	}

	.cmd-cell-item.no-border .cmd-cell-item-body::before {
		display: none;
	}

	.cmd-cell-item-body {
		position: relative;
		display: flex;
		align-items: center;
		justify-content: space-between;
		min-height: 100upx;
		padding-top: 20upx;
		padding-bottom: 20upx;
		margin-left: 20upx;
		margin-right: 20upx;
		box-sizing: border-box;
	}

	.cmd-cell-item-body::before {
		content: '';
		position: absolute;
		z-index: 2;
		background-color: #E2E4EA;
		transform-origin: 100% 50%;
		transform: scaleY(0.5) translateY(100%);
		bottom: 0;
		left: 0;
		right: auto;
		top: auto;
		width: 100%;
		height: 2upx;
		transform-origin: 50% 100%;
	}


	.cmd-cell-item-left {
		flex-shrink: 0;
		margin-right: 32upx;
	}

	.cmd-cell-item-title {
		line-height: 1.2;
	}

	.cmd-cell-item-brief {
		color: #858B9C;
		font-size: 24upx;
		line-height: 1.4;
		margin-top: 8upx;
	}

	.cmd-cell-item-content {
		flex: 1 1 0%;
		color: #111A34;
		font-size: 32upx;
		line-height: 1.2;
	}

	.cmd-cell-item-right {
		flex-shrink: 0;
		margin-left: 12upx;
		display: inline-flex;
		align-items: center;
		justify-content: flex-end;
		color: #858B9C;
		font-size: 32upx;
	}

	.cmd-cell-item-right .cmd-cell-addon-text {
		font-size: 24upx;
	}

	.cmd-cell-item-right .cmd-cell-icon-arrow-right {
		margin-left: 6upx;
		margin-right: -6upx;
		color: #C5CAD5;
	}

	.cmd-cell-item-children {
		padding: 20upx 0;
		margin-left: 20upx;
		margin-right: 20upx;
	}

	.cmd-cell-item.is-disabled .cmd-cell-item-content,
	.cmd-cell-item.is-disabled .cmd-cell-item-title,
	.cmd-cell-item.is-disabled .cmd-cell-item-brief,
	.cmd-cell-item.is-disabled .cmd-cell-item-left,
	.cmd-cell-item.is-disabled .cmd-cell-item-right,
	.cmd-cell-item.is-disabled .cmd-cell-item-children {
		color: #C5CAD5;
	}

	.cmd-cell-item-hover {
		background: transparent;
	}
</style>
