<template>
	<view>
		<!-- #ifdef H5 -->
		<transition :name="'cmd-'+name">
			<slot></slot>
		</transition>
		<!-- #endif -->
		<!-- #ifndef H5 -->
		<view :class="'cmd-'+name">
			<slot></slot>
		</view>
		<!-- #endif -->
	</view>
</template>

<script>
	/**  
	 * 动画组件  
	 * @description 复用动画切换组件,可自行拆取动画。  
	 * @tutorial https://ext.dcloud.net.cn/plugin?id=211 
	 * @property {String} name 动画名 - 默认：fade  
	 * @example <cmd-transition name="fade">你好，uni-app</cmd-transition>  
	 */
	export default {
		name: 'cmd-transition',

		props: {
			/**
			 * 使用动画名
			 * 淡入淡出 - fade、fade-up、fade-down、fade-left、fade-right
			 * 滑动 - slide-up、slide-down、slide-left、slide-right
			 * 弹动 bounce
			 * 中部弹出 zoom
			 * 中部弹入 punch
			 * 飞入 fly
			 */
			name: {
				type: String,
				default: 'fade'
			}
		}

	}
</script>

<style>
	/* #ifdef H5 */
	.cmd-bounce-enter-active {
		-webkit-animation: bounce-in .3s linear;
		animation: bounce-in 0.3s linear;
	}

	.cmd-bounce-leave-active {
		-webkit-animation: zoom-out .25s linear;
		animation: zoom-out 0.25s linear;
	}

	.cmd-zoom-enter,
	.cmd-zoom-leave-to {
		opacity: .01;
		-webkit-transform: scale(0.75);
		transform: scale(0.75);
	}

	.cmd-zoom-enter-active {
		-webkit-transition: all 0.3s cubic-bezier(0.215, 0.61, 0.355, 1);
		transition: all 0.3s cubic-bezier(0.215, 0.61, 0.355, 1);
	}

	.cmd-zoom-leave-active {
		-webkit-transition: all .25s linear;
		transition: all 0.25s linear;
	}

	.cmd-punch-enter,
	.cmd-punch-leave-to {
		opacity: .01;
		-webkit-transform: scale(1.35);
		transform: scale(1.35);
	}

	.cmd-punch-enter-active {
		-webkit-transition: all 0.3s cubic-bezier(0.215, 0.61, 0.355, 1);
		transition: all 0.3s cubic-bezier(0.215, 0.61, 0.355, 1);
	}

	.cmd-punch-leave-active {
		-webkit-transition: all .25s linear;
		transition: all 0.25s linear;
	}

	.cmd-slide-up-enter,
	.cmd-slide-up-leave-to {
		-webkit-transform: translate3d(0, 100%, 0);
		transform: translate3d(0, 100%, 0);
	}

	.cmd-slide-up-enter-active {
		-webkit-transition: -webkit-transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: -webkit-transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1), -webkit-transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
	}

	.cmd-slide-up-leave-active {
		-webkit-transition: -webkit-transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: -webkit-transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1), -webkit-transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1);
	}

	.cmd-slide-right-enter,
	.cmd-slide-right-leave-to {
		-webkit-transform: translate3d(-100%, 0, 0);
		transform: translate3d(-100%, 0, 0);
	}

	.cmd-slide-right-enter-active {
		-webkit-transition: -webkit-transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: -webkit-transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1), -webkit-transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
	}

	.cmd-slide-right-leave-active {
		-webkit-transition: -webkit-transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: -webkit-transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1), -webkit-transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1);
	}

	.cmd-slide-left-enter,
	.cmd-slide-left-leave-to {
		-webkit-transform: translate3d(100%, 0, 0);
		transform: translate3d(100%, 0, 0);
	}

	.cmd-slide-left-enter-active {
		-webkit-transition: -webkit-transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: -webkit-transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1), -webkit-transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
	}

	.cmd-slide-left-leave-active {
		-webkit-transition: -webkit-transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: -webkit-transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1), -webkit-transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1);
	}

	.cmd-slide-down-enter,
	.cmd-slide-down-leave-to {
		-webkit-transform: translate3d(0, -100%, 0);
		transform: translate3d(0, -100%, 0);
	}

	.cmd-slide-down-enter-active {
		-webkit-transition: -webkit-transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: -webkit-transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1), -webkit-transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
	}

	.cmd-slide-down-leave-active {
		-webkit-transition: -webkit-transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: -webkit-transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1);
		transition: transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1), -webkit-transform 0.25s cubic-bezier(0.165, 0.84, 0.44, 1);
	}

	.cmd-fade-enter,
	.cmd-fade-leave-to {
		opacity: 0.01;
	}

	.cmd-fade-enter-active {
		-webkit-transition: opacity 0.3s cubic-bezier(0.215, 0.61, 0.355, 1);
		transition: opacity 0.3s cubic-bezier(0.215, 0.61, 0.355, 1);
	}

	.cmd-fade-leave-active {
		-webkit-transition: opacity .25s linear;
		transition: opacity 0.25s linear;
	}

	.cmd-fade-up-enter,
	.cmd-fade-up-leave-to {
		opacity: .01;
		-webkit-transform: translate3d(0, 20%, 0);
		transform: translate3d(0, 20%, 0);
	}

	.cmd-fade-up-enter-active {
		-webkit-transition: all 0.3s cubic-bezier(0.215, 0.61, 0.355, 1);
		transition: all 0.3s cubic-bezier(0.215, 0.61, 0.355, 1);
	}

	.cmd-fade-up-leave-active {
		-webkit-transition: all .25s linear;
		transition: all 0.25s linear;
	}

	.cmd-fade-down-enter,
	.cmd-fade-down-leave-to {
		opacity: .01;
		-webkit-transform: translate3d(0, -20%, 0);
		transform: translate3d(0, -20%, 0);
	}

	.cmd-fade-down-enter-active {
		-webkit-transition: all 0.3s cubic-bezier(0.215, 0.61, 0.355, 1);
		transition: all 0.3s cubic-bezier(0.215, 0.61, 0.355, 1);
	}

	.cmd-fade-down-leave-active {
		-webkit-transition: all .25s linear;
		transition: all 0.25s linear;
	}

	.cmd-fade-right-enter,
	.cmd-fade-right-leave-to {
		opacity: .01;
		-webkit-transform: translate3d(-20%, 0, 0);
		transform: translate3d(-20%, 0, 0);
	}

	.cmd-fade-right-enter-active {
		-webkit-transition: all 0.3s cubic-bezier(0.215, 0.61, 0.355, 1);
		transition: all 0.3s cubic-bezier(0.215, 0.61, 0.355, 1);
	}

	.cmd-fade-right-leave-active {
		-webkit-transition: all .25s linear;
		transition: all 0.25s linear;
	}

	.cmd-fade-left-enter,
	.cmd-fade-left-leave-to {
		opacity: .01;
		-webkit-transform: translate3d(20%, 0, 0);
		transform: translate3d(20%, 0, 0);
	}

	.cmd-fade-left-enter-active {
		-webkit-transition: all 0.3s cubic-bezier(0.215, 0.61, 0.355, 1);
		transition: all 0.3s cubic-bezier(0.215, 0.61, 0.355, 1);
	}

	.cmd-fade-left-leave-active {
		-webkit-transition: all .25s linear;
		transition: all 0.25s linear;
	}

	.cmd-fly-enter-active {
		-webkit-animation: fly-in .6s;
		animation: fly-in .6s;
		-webkit-animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
		animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
	}

	.cmd-fly-leave-active {
		-webkit-animation: zoom-out .25s;
		animation: zoom-out 0.25s;
	}

	@-webkit-keyframes fly-in {
		0% {
			opacity: .5;
			-webkit-transform: scale(0.5) translate3d(0, 0.5rem, 0);
			transform: scale(0.5) translate3d(0, 0.5rem, 0);
		}

		45% {
			opacity: 1;
			-webkit-transform: scale(1.05) translate3d(0, -0.5rem, 0);
			transform: scale(1.05) translate3d(0, -0.5rem, 0);
		}

		to {
			-webkit-transform: scale(1) translateZ(0);
			transform: scale(1) translateZ(0);
		}
	}


	@keyframes fly-in {
		0% {
			opacity: .5;
			-webkit-transform: scale(0.5) translate3d(0, 0.5rem, 0);
			transform: scale(0.5) translate3d(0, 0.5rem, 0);
		}

		45% {
			opacity: 1;
			-webkit-transform: scale(1.05) translate3d(0, -0.5rem, 0);
			transform: scale(1.05) translate3d(0, -0.5rem, 0);
		}

		to {
			-webkit-transform: scale(1) translateZ(0);
			transform: scale(1) translateZ(0);
		}
	}


	@-webkit-keyframes bounce-in {
		0% {
			-webkit-transform: scale(0.5);
			transform: scale(0.5);
		}

		45% {
			-webkit-transform: scale(1.05);
			transform: scale(1.05);
		}

		80% {
			-webkit-transform: scale(0.95);
			transform: scale(0.95);
		}

		to {
			-webkit-transform: scale(1);
			transform: scale(1);
		}
	}


	@keyframes bounce-in {
		0% {
			-webkit-transform: scale(0.5);
			transform: scale(0.5);
		}

		45% {
			-webkit-transform: scale(1.05);
			transform: scale(1.05);
		}

		80% {
			-webkit-transform: scale(0.95);
			transform: scale(0.95);
		}

		to {
			-webkit-transform: scale(1);
			transform: scale(1);
		}
	}


	@-webkit-keyframes zoom-out {
		to {
			opacity: .01;
			-webkit-transform: scale(0.75);
			transform: scale(0.75);
		}
	}


	@keyframes zoom-out {
		to {
			opacity: .01;
			-webkit-transform: scale(0.75);
			transform: scale(0.75);
		}
	}

	/* #endif */
	/* #ifndef H5 */

	.cmd-fade {
		animation: fade .3s 1;
	}

	.cmd-fade-up {
		animation: fade-up .3s 1;
	}

	.cmd-fade-down {
		animation: fade-down .3s 1;
	}

	.cmd-fade-left {
		animation: fade-left .3s 1;
	}

	.cmd-fade-right {
		animation: fade-right .3s 1;
		animation-fill-mode: forwards;
	}

	.cmd-slide-up {
		animation: slide-up .3s 1;
	}

	.cmd-slide-down {
		animation: slide-down .3s 1;
	}

	.cmd-slide-left {
		animation: slide-left .3s 1;
	}

	.cmd-slide-right {
		animation: slide-right .3s 1;
	}

	.cmd-bounce {
		animation: bounce-in 0.3s linear;
	}

	.cmd-fly {
		animation: fly-in .6s;
		animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
	}

	.cmd-punch {
		animation: punch-in 0.3s;
		animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
	}

	.cmd-zoom {
		animation: zoom-in 0.3s;
		animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
	}

	@keyframes zoom-in {
		from {
			opacity: 0.01;
			transform: scale(0.75);
		}

		to {
			opacity: 1;
			transform: scale(1);
		}
	}

	@keyframes punch-in {
		from {
			opacity: 0.01;
			transform: scale(1.35);
		}

		to {
			opacity: 1;
			transform: scale(1);
		}
	}

	@keyframes fade {
		from {
			opacity: 0.01;
		}

		to {
			opacity: 1;
		}
	}

	@keyframes fade-left {
		from {
			opacity: 0;
			transform: scale(.8);
		}

		to {
			opacity: 1;
			transform: scale(1);
		}
	}

	@keyframes fade-right {
		from {
			transform: scale(1.2);
			opacity: 0;
		}

		to {
			transform: scale(1);
			opacity: 1;
		}
	}

	@keyframes fade-up {
		from {
			transform: translate3d(0, 20%, 0);
			opacity: 0;
		}

		to {
			transform: translate3d(0, 0, 0);
			opacity: 1;
		}
	}

	@keyframes fade-down {
		from {
			transform: translate3d(0, -20%, 0);
			opacity: 0;
		}

		to {
			transform: translate3d(0, 0, 0);
			opacity: 1;
		}
	}

	@keyframes slide-right {
		from {
			transform: translateX(100%);
		}

		to {
			transform: translateX(0);
		}
	}

	@keyframes slide-left {
		from {
			transform: translateX(-100%);
		}

		to {
			transform: translateX(0);
		}
	}

	@keyframes slide-up {
		from {
			transform: translateY(-100%);
			opacity: 0;
		}

		to {
			transform: translateX(0);
			opacity: 1;
		}
	}

	@keyframes slide-down {
		from {
			transform: translateY(100%);
			opacity: 0;
		}

		to {
			transform: translateX(0);
			opacity: 1;
		}
	}

	@keyframes fly-in {
		0% {
			opacity: .5;
			-webkit-transform: scale(0.5) translate3d(0, 0.5rem, 0);
			transform: scale(0.5) translate3d(0, 0.5rem, 0);
		}

		45% {
			opacity: 1;
			-webkit-transform: scale(1.05) translate3d(0, -0.5rem, 0);
			transform: scale(1.05) translate3d(0, -0.5rem, 0);
		}

		to {
			-webkit-transform: scale(1) translateZ(0);
			transform: scale(1) translateZ(0);
		}
	}

	@keyframes bounce-in {
		0% {
			-webkit-transform: scale(0.5);
			transform: scale(0.5);
		}

		45% {
			-webkit-transform: scale(1.05);
			transform: scale(1.05);
		}

		80% {
			-webkit-transform: scale(0.95);
			transform: scale(0.95);
		}

		to {
			-webkit-transform: scale(1);
			transform: scale(1);
		}
	}

	/* #endif */
</style>
