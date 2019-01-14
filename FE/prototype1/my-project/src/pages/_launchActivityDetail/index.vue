<template>
    <div class="detailContainer">
      <p><span class="key">活动名称</span><span class="value">活动名称</span></p>
      <p><span class="key">活动详情</span></p>
      <textarea name="" id=""></textarea>
      <p><span class="key">活动类型</span><span class="value">类型一</span></p>
      <p><span class="key">报名开始时间</span><span class="value time">2018-09-01 11:22:33</span></p>
      <p><span class="key">报名截止时间</span><span class="value time">2018-09-01 11:22:33</span></p>
      <p><span class="key">活动开始时间</span><span class="value time">2018-09-01 11:22:33</span></p>
      <p><span class="key">活动截止时间</span><span class="value time">2018-09-01 11:22:33</span></p>
      <p><span class="key">活动地址</span><span class="value">云苑大酒店</span></p>
      <p><span class="key">报名费用</span><span class="value">30</span></p>
      <p><span class="key">报名人数限制</span><span class="value">30</span></p>
      <p><span class="key">已报名用户</span></p>
      <p><span class="key">活动发起人</span><span class="value">是是是</span></p>
      <p><span class="key">发起人联系方式</span><span class="value">12345678901</span></p>
      <p class="btns"><button id="update">修改</button><button id="delete">删除</button></p>
    </div>
</template>

<script>
    export default {
        name: "index",
        data() {
          return {
            actinfo: {},
            index: 0,
          }
        },
        onLoad(options) {
          this.index = options.index
          // this.actinfo = this.globalData.launActList[this.index]
        }
    }
</script>

<style scoped>
    .detailContainer {
      width:650rpx;
      /*border:4rpx solid #dcdee2;*/
      border-radius:20rpx;
      box-shadow:#dedee2 0 0 10rpx 5rpx;
      position: absolute;
      top: 40rpx;
      margin-left: 50rpx;
      margin-bottom: 40rpx;
      padding: 40rpx 0;
    }
  p {
    height: 80rpx;
    line-height: 80rpx;
    font-size: 32rpx;
    padding: 0 40rpx;
  }
  p .key {
    display: inline-block;
    width: 250rpx;
  }
  p .value {
    display: inline-block;
    margin-left: 40rpx;
  }
  .time {
    font-size: 28rpx
  }
  textarea {
    margin: 0 auto;
    line-height: 70rpx;
    height: 280rpx;
    width: 580rpx;
    font-size: 32rpx;
    padding:  0 20rpx;
    box-sizing: border-box;
    border: solid 4rpx #dcdee2;
    border-radius: 10rpx;
  }
  .btns {
    height: 100rpx;
    display: flex;
    justify-content: space-around;
    align-items: flex-end;
  }
  button {
    width: 40%;
    height: 80rpx;
    line-height: 80rpx;
    font-size: 32rpx;
    color: #ffffff;
  }
  button::after {
    border: none;
  }
  #update {
    background-color: #2db7f5;
  }
  #update:active {
    background-color: rgba(87, 163, 243, 0.5);
  }
  #delete {
    background-color: #f16643;
  }
  #delete:active {
    background-color: rgba(237, 64, 20, 0.5);
  }
</style>
