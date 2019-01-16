<template>
  <div class="detailContainer">
    <p><span class="key">活动名称</span><span class="value">{{actinfo.activitySubject}}</span></p>
    <p><span class="key">活动详情</span></p>
    <textarea name="" id="" disabled>{{actinfo.activityContent}}</textarea>
    <p><span class="key">活动类型</span><span class="value">{{actinfo.activityType}}</span></p>
    <p><span class="key">报名开始时间</span><span class="value time">{{processedSignStartDate}}</span></p>
    <p><span class="key">报名截止时间</span><span class="value time">{{processedSignEndDate}}</span></p>
    <p><span class="key">活动开始时间</span><span class="value time">{{processedStartDate}}</span></p>
    <p><span class="key">活动截止时间</span><span class="value time">{{processedEndDate}}</span></p>
    <p id="act_addr"><span>活动地点</span></p>
    <p id="show_addr"><span>{{actinfo.activityAddressname}}</span><img src="../../../static/images/loca.png" alt=""></p>
    <p><span class="key">报名费用</span><span class="value">{{actinfo.activityFee}}</span></p>
    <p><span class="key">报名人数限制</span><span class="value">{{actinfo.activityPeoplelimit}}</span></p>
    <p><span class="key">已报名人数</span><span class="value">{{actinfo.activityPeopleregistered}}</span></p>
    <p><span class="key">活动发起人</span><span class="value">{{actinfo.activityOrganizer}}</span></p>
    <p><span class="key">发起人联系方式</span><span class="value">{{actinfo.activityOrganizerphonenumber}}</span></p>
    <p class="btns"><button id="update" @tap="handleCollect">收藏</button><button id="delete" @tap="handleSignout">退出</button></p>
  </div>
</template>

<script>
    export default {

      data() {
        return {
          actinfo: {},
          index: 0,
        }
      },
      computed: {
        processedSignStartDate() {
          return this.actinfo.activitySignstartdate.replace('T', ' ').substring(0, 16)
        },
        processedSignEndDate() {
          return this.actinfo.activitySignenddate.replace('T', ' ').substring(0, 16)
        },
        processedStartDate() {
          return this.actinfo.activityStartdate.replace('T', ' ').substring(0, 16)
        },
        processedEndDate() {
          return this.actinfo.activityEnddate.replace('T', ' ').substring(0, 16)
        },
      },
      onLoad(options) {
        console.log(options)
        this.index = parseInt(options.index)
        this.actinfo = this.globalData.partActList[this.index]
      },
        methods: {
          handleCollect() {
            let that = this
            wx.showModal({
              title: '提示',
              content: '确认收藏活动',
              success(res) {
                if (res.confirm) {
                  that.$fly.get({
                    method: 'POST',
                    url: 'http://activity103.mynatapp.cc/miniapp/activitycollect/save?userKey='+that.globalData.id+
                    '&activityId='+that.actinfo.activityId,
                  }).then(function(res){
                    console.log(res)
                    if (res.data.msg === '活动收藏成功') {
                      wx.showToast({
                        title: '收藏成功',
                        duration: 1000,
                        mask: true,
                        complete(){
                          wx.switchTab({
                            url: '../my/main'
                          })
                        }
                      })
                    } else {
                      wx.showToast({
                        title: '发布失败，请稍后重试',
                        duration: 1000
                      })
                    }
                  })

                } else {
                  console.log('用户点击了取消')
                }
              }
            })
          },
          handleSignout() {
            let that = this
            wx.showModal({
              title: '提示',
              content: '确认退出活动',
              success(res) {
                if (res.confirm) {
                  that.$fly.get({
                    method: 'POST',
                    url: 'http://activity103.mynatapp.cc/miniapp/activityinfo/participatecancel?userKey='+that.globalData.id+
                      '&activityId='+that.actinfo.activityId,
                  }).then(function(res){
                    console.log(res)
                    if (res.data.msg === '活动取消参加成功') {
                      wx.showToast({
                        title: '退出成功',
                        duration: 1000,
                        mask: true,
                        complete(){
                          wx.switchTab({
                            url: '../my/main'
                          })
                        }
                      })
                    } else {
                      wx.showToast({
                        title: '退出失败，请稍后重试',
                        duration: 1000
                      })
                    }
                  })
                } else {
                  console.log('用户点击了取消')
                }
              }
            })
          },
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
  #show_addr span {
    display: inline-block;
    width: 540rpx;
    text-align: right;
    line-height: 80rpx;
    font-size: 28rpx;
  }
  #show_addr img {
    height: 40rpx;
    width: 40rpx;
    margin: 20rpx 0;
    position: absolute;
    right: 14px;
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

