<script>
  export default {
    data() {
      return {
        id: ''
      }
    },
    // methods: {
    //   login: async function() {
    //     let id = await this.getId()
    //     console.log(id)
    //     this.globalData.id = id
    //   },
    //   getId() {
    //     let response
    //     let result
    //     let that = this
    //     wx.login({
    //       success(res) {
    //         console.log('第一次请求数据')
    //         console.log(res)
    //         response = res
    //         that.$fly.get({
    //           method: 'POST',
    //           url: 'http://activity103.mynatapp.cc/wx/wxlogin?code=' + response.code,
    //         }).then(function (res) {
    //           console.log(res.data.data)
    //           result = res.data.data
    //         })
    //       }
    //     })
    //     return result
    //   }
    // },
    created() {
      /* Promise */
      // let p = new Promise()
      // // 调用API从本地缓存中获取数据
      // const my = wx.getStorageSync('my') || []
      // my.unshift(Date.now())
      // wx.setStorageSync('my', my)
      //
      // // console.log('app created and cache my by setStorageSync')
      let response
      let that = this
      wx.login({
        success(res) {
          console.log('第一次请求数据')
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
      // console.log('页面初始化')
      // this.login()
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

  page {
    width: 100%;
    height: 100%;
  }
</style>
