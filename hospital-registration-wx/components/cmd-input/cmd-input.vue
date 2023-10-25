<template>
  <view class="cmd-input">
    <input class="cmd-input-input" :disabled="disabled" :focus="isFocus" :type="type === 'password' ? 'text' : type"
      :password="type==='password' && !showPassword" :value="inputValue" :maxlength="maxlength" :placeholder="placeholder"
      :placeholder-style="setPlaceholderStyle" @input="$_onInput" @focus="$_onFocus" @blur="$_onBlur" @confirm="$_onConfirm" />
    <view v-if="inputValue.length" class="cmd-input-icon">
      <cmd-icon v-if="displayable&&!clearable" type="eye" size="24" :color="showPassword ? '#111a34':'#c5cad5'" @click="$_display"></cmd-icon>
      <cmd-icon v-if="clearable" type="close-circle" size="24" color="#c5cad5" @click="$_clear"></cmd-icon>
    </view>
  </view>
</template>

<script>
  import cmdIcon from '@/components/cmd-icon/cmd-icon.vue'

  /**  
   * 输入框组件  
   * @description 用户可控制是否显示输入框标题，是否出现清除按钮，输入框状态。  
   * @tutorial http://ext.dcloud.net.cn/plugin?id=180  
   * @property {String} type 输入值类型 - 可选： digit idcard number text password  
   * @property {String} placeholder 占位符  
   * @property {Object} placeholder-style 占位符样式  
   * @property {String, Number} maxlength 最大输入长度 - 默认140  
   * @property {String, Number} value 默认初始内容  
   * @property {Boolean} disabled 禁用状态 - 默认:false  
   * @property {Boolean} focus 自动获取焦点 - 默认:false  
   * @property {Boolean} clearable 显示清除按钮 - 默认:false  
   * @property {Boolean} displayable 显示密码可见按钮 - 默认:false  
   * @event {Function} focus 键入聚焦输入框 监听事件  
   * @event {Function} blur 键出移除输入框 监听事件  
   * @event {Function} input 键入输入 监听事件  
   * @event {Function} confirm 输入框提交 监听事件  
   * @example <cmd-input placeholder="聚焦输入默认值并清空输入框" type="text" value="聚焦输入默认值" focus clearable></cmd-input>  
   */
  export default {
    name: 'cmd-input',

    components: {
      cmdIcon
    },

    props: {
      /**
       * 输入类型 digit idcard number text password
       */
      type: {
        type: String,
        default: 'text'
      },
      /**
       * 占位符
       */
      placeholder: {
        type: String,
        default: ''
      },
      /**
       * 占位符样式
       */
      placeholderStyle: {
        type: Object,
        default: () => {
          return {}
        }
      },
      /**
       * 最大输入长度
       */
      maxlength: {
        type: [String, Number],
        default: '',
      },
      /**
       * 是否为禁用状态
       */
      disabled: {
        type: Boolean,
        default: false
      },
      /**
       * 自动获取焦点
       */
      focus: {
        type: Boolean,
        default: false
      },
      /**
       * 默认初始内容
       */
      value: {
        type: [String, Number],
        default: ''
      },
      /**
       * 是否显示清除按钮
       */
      clearable: {
        type: Boolean,
        default: false
      },
      /**
       * 是否显示密码可见按钮
       */
      displayable: {
        type: Boolean,
        default: false
      }
    },

    data() {
      return {
        /**
         * 显示密码明文
         */
        showPassword: false,
        /**
         * 输入框的值
         */
        inputValue: this.value,
        /**
         * 是否聚焦
         */
        isFocus: this.focus
      }
    },

    /**
     * 监听输入值
     */
    watch: {
      value(v) {
        this.inputValue = v;
      }
    },

    computed: {
      /**
       * 设置占位符样式
       */
      setPlaceholderStyle() {
        let placeholderStyle = '';
        for (let it in this.placeholderStyle) {
          placeholderStyle += `${it}:${this.placeholderStyle[it]}`;
        }
        return placeholderStyle;
      }
    },

    methods: {
      /**
       * 清除输入框
       */
      $_clear() {
        this.inputValue = ''
        this.isFocus = true
      },
      /**
       * 密码可见状态
       */
      $_display() {
        this.showPassword = !this.showPassword
      },
      /**
       * 键入聚焦输入框
       */
      $_onFocus(e) {
        this.$emit('focus', e.target.value)
      },
      /**
       * 键出移除输入框
       */
      $_onBlur(e) {
        this.$emit('blur', e.target.value)
      },
      /**
       * 键入输入监听
       */
      $_onInput(e) {
        this.$emit('input', e.target.value)
      },
      /**
       * 输入框提交监听
       */
      $_onConfirm(e) {
        this.$emit('confirm', e.target.value)
      }
    }
  }
</script>

<style>
  .cmd-input {
    display: flex;
    flex-direction: row;
    align-items: center;
  }

  .cmd-input-input {
    flex: 1;
    text-align: left;
    width: 100%;
    height: 90upx;
    color: #111a34;
    font-size: 36upx;
    font-weight: 500;
    line-height: 1.2;
    font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, 微软雅黑, Arial, sans-serif;
    border: none;
    background: 0 0;
    outline: 0;
    box-sizing: border-box;
    appearance: none;
  }

  .cmd-input-icon {
    width: 48upx;
    margin-left: 8upx;
    text-align: center;
    font-size: 36upx;
    font-weight: 500;
    line-height: 1.2;
  }
</style>
