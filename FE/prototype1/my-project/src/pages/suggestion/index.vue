<template>
    <view>
      <textarea placeholder="请输入您的意见和建议" id="sug" v-model="content"></textarea>
      <button @tap="handleTap" type="primary">提交</button>
      <p class="show">{{ content }}</p>
    </view>
</template>

<script>
    export default {
        name: "index",
      data(){
          return {
            content: ''
          }
      },
      methods: {
          handleTap() {
            console.log(this.content)
            let data2send = {
              userIdMd5: this.globalData.id,
              suggestion: this.content
            }
            this.$fly.get({
              method: 'POST',
              url: 'http://activity103.mynatapp.cc/suggestion/usersuggestion',/*contentType: 'application/json;charset=utf-8',*/
              body: JSON.stringify(data2send)
            }).then(function(res){
              console.log(res.data.data)
            })
          }
      }
    }
</script>

<style scoped>
  textarea{
    height: 600rpx;
    width: 600rpx;
    display: block;
    border: solid 1rpx #000000;
    margin: 0 auto;
    box-sizing: border-box;
    padding: 20rpx;
  }
  button {
    width: 500rpx;
    margin-top: 50rpx;
  }
  .show {
    height: 100rpx;

  }
</style>
