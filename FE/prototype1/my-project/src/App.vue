<script>
  export default {
    data() {
      return {
        id: ''
      }
    },
    created() {
      console.log('应用启动')
    }
  }
</script>

<style>
  .container {
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-between;
    padding: 200 rpx 0;
    box-sizing: border-box;
  }

  * {
    transition: width 2s;
    -moz-transition: width 2s;
    -webkit-transition: width 2s;
    -o-transition: width 2s;
  }

  page {
    width: 100%;
    height: 100%;
  }
</style>
