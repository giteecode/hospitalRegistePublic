<template>
  <div>
    <svg-icon icon-class="question" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'pipilinDoc',
  data() {
    return {
      url: 'http://doc.pipilin.vip/pipilin-vue'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>