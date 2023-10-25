<template>
	<view :class="setBodyClass" :style="setBackgroundColor+bodyHeight">
		<slot></slot>
	</view>
</template>

<script>
	/**  
	 * 导航栏内容页组件  
	 * @description 针对使用底部导航栏组件 cmd-bottom-nav 或顶部导航栏组件 cmd-nav-bar 时。  
	 * @tutorial https://ext.dcloud.net.cn/plugin?id=207  
	 * @property {String} type 使用导航栏类型 - 默认top，支持：top、bottom、top-bottom  
	 * @property {String} background-color 内容区背景颜色 - 默认白色  
	 * @example <cmd-page-body type="top-bottom"></cmd-page-body>  
	 */
	export default {
		name: 'cmd-page-body',

		props: {
			/**
			 * 使用导航栏类型,默认top，top、bottom、top-bottom
			 */
			type: {
				type: String,
				default: 'top'
			},
			/**
			 * 内容区背景颜色,默认#ffffff
			 */
			backgroundColor: {
				type: String,
				default: ''
			}
		},

		data() {
			return {
				bodyHeight: 0
			}
		},

		computed: {
			/**
			 * 内容区样式根据导航类型设置
			 */
			setBodyClass() {
				let bodyClass = ['cmd-page-body', 'cmd-page-body-top-bottom']
				if (this.type == 'top') {
					bodyClass.splice(1)
					bodyClass.push('cmd-page-body-top');
				}
				if (this.type == 'bottom') {
					bodyClass.splice(1)
					bodyClass.push('cmd-page-body-bottom');
				}
				return bodyClass;
			},
			/**
			 * 内容区背景颜色
			 */
			setBackgroundColor() {
				let backgroundColor = "background: #ffffff;";
				if (this.backgroundColor) {
					backgroundColor = `background: ${this.backgroundColor};`;
				}
				return backgroundColor;
			}
		},

		mounted() {
			// 初始默认内容高度 
			let windowHeight = uni.getSystemInfoSync().windowHeight;
			// #ifndef H5
			windowHeight -= uni.getSystemInfoSync().statusBarHeight;
			// #endif
			// #ifdef MP
			windowHeight -= 5;
			// #endif
      if (this.type == 'top') {
      	windowHeight -= uni.upx2px(88);
      }
      if (this.type == 'bottom') {
      	windowHeight -= uni.upx2px(118);
      }
      if (this.type == 'top-bottom') {
      	windowHeight -= uni.upx2px(206);
      }
			this.bodyHeight = `min-height:${windowHeight}px`;
		},

	}
</script>

<style>
	.cmd-page-body {
		position: absolute;
		top: 0;
		left: 0;
		right: 0;
		background: #ffffff;
	}

	.cmd-page-body-top-bottom {
		padding-bottom: 118upx;
		padding-top: 88upx;
		top: var(--status-bar-height);
	}

	.cmd-page-body-bottom {
		padding-bottom: 118upx;
	}

	.cmd-page-body-top {
		padding-top: 88upx;
		top: var(--status-bar-height);
	}
</style>
