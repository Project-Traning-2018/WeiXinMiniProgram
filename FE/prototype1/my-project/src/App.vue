<script>
  export default {
    data() {
      return {
        id: ''
      }
    },
    created() {
      // 调用API从本地缓存中获取数据
      const logs = wx.getStorageSync('logs') || []
      logs.unshift(Date.now())
      wx.setStorageSync('logs', logs)

      // console.log('app created and cache logs by setStorageSync')
      let response
      let that = this
      wx.login({
        success(res) {
          console.log(res)
          response = res
          that.$fly.get({
            method: 'POST',
            url: 'http://activity103.mynatapp.cc/wx/wxlogin?code=' + response.code,
          }).then(function (res) {
            console.log(res.data.data)
            that.globalData.id = res.data.data
          })
        }
      })
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

  /* this rule will be remove */
  * {
    transition: width 2s;
    -moz-transition: width 2s;
    -webkit-transition: width 2s;
    -o-transition: width 2s;
  }
</style>
