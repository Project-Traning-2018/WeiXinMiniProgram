<template>
  <div class="con">
    <div class="tab-box">

      <!--<view v-for="(title, index) in titles" @tap="handleSelect($event)"  :key="index" :class="[defaultClass, index==activeIndex?specialClass:'']"-->
           <!--&gt;{{title}}-->
      <!--</view>-->

      <div @tap="handleSelect($event)" :class="[defaultClass, tab0==activeIndex?specialClass:'']">
        我发起的
      </div>
      <div @tap="handleSelect($event)" :class="[defaultClass, tab1==activeIndex?specialClass:'']">
        我参与的
      </div>
      <div @tap="handleSelect($event)" :class="[defaultClass, tab2==activeIndex?specialClass:'']">
        我收藏的
      </div>
    </div>
    <swiper :current="current" duration="150" @change="handleChange">
      <swiper-item>
        <div class="card" v-for="(act, index) in actLaunByme" :index="index" @tap="toDetailLaunch">
          <p class="title">{{act.activitySubject}}</p>
          <p><span class="key">地点</span><span class="value">{{act.activityAddressname}}</span></p>
          <p><span class="key">类别</span><span class="value">{{act.activityType}}</span></p>
          <p><span class="key">报名时间</span><span class="value">{{act.activitySignDate}}</span></p>
          <p><span class="key">报名活动</span><span class="value">{{act.activityDate}}</span></p>
        </div>
        <div class="card">
          <p class="title">标题</p>
          <p><span class="key">地点</span><span class="value">55555555555</span></p>
          <p><span class="key">类别</span><span class="value">4565698</span></p>
          <p><span class="key">报名时间</span><span class="value">5465555</span></p>
          <p><span class="key">报名活动</span><span class="value">"1234561"</span></p>
        </div>
        <div class="card">
          <p class="title">标题</p>
          <p>地点</p>
          <p>时间</p>
        </div>
        <div class="card">
          <p class="title">标题</p>
          <p>地点</p>
          <p>时间</p>
        </div>
        <div class="card">
          <p  class="title">标题</p>
          <p>地点</p>
          <p>时间</p>
        </div>
        <div class="card">
          <p class="title">标题</p>
          <p>地点</p>
          <p>时间</p>
        </div>
      </swiper-item>
      <swiper-item>
        <div class="card">
          <p>标题</p>
          <p>地点</p>
          <p>时间</p>
        </div>
      </swiper-item>
      <swiper-item>
        <div class="card">
          <p>标题</p>
          <p>地点</p>
          <p>事件</p>
        </div>
      </swiper-item>
    </swiper>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        titles: ['我发起的',
          '我参与的',
          '我收藏的'],
        activeIndex: 0,   // tab-item当前高亮的index 应时刻与current保持一致
        evnets: [],
        defaultClass: 'tab',
        specialClass: 'tab-active',
        current: 0,       // swiper当前显示item的index
        tab0: 0,
        tab1: 1,
        tab2: 2,
        actLaunByme: [
          {
            activitySubject: '活动1',
            activityAddressname: '活动1',
            activityType: '活动1',
            activitySignDate: '活动1',
            activityDate: '明天'
          },
          {
            activitySubject: '活动2',
            activityAddressname: '活动2',
            activityType: '活动2',
            activitySignDate: '活动2',
            activityDate: '明天'
          },
        ], // 我发起的活动列表
        actPartByme: [], // 我参与的活动列表
        actCollByme: [], // 我收藏的活动列表
      }
    },
    methods: {
      handleChange(e) {
        this.activeIndex = e.mp.detail.current
        this.current = this.activeIndex
        // console.log('current: '+this.current+' activeIndex: '+this.activeIndex)
      },
      handleSelect($event) {
        console.log($event)
        let tapIndex = parseInt($event.mp.target.dataset.eventid/*.charAt(2)*/)
        this.activeIndex = tapIndex
        this.current = tapIndex+''
        console.log('tapIndex: '+tapIndex + ' activeIndex: '+ this.activeIndex+' current: '+this.current)
      },
      toDetailLaunch($event) {
        let index = parseInt($event.currentTarget.dataset.eventid.charAt(2));
        wx.navigateTo({
          url: '../_launchActivityDetail/main?index='+index
        })
      },
      toDetailParticipate($event) {
        let index = parseInt($event.currentTarget.dataset.eventid.charAt(2));
        wx.navigateTo({
          url: '../_participateActivityDetail/main?index='+index
        })
      },
      toDetailCollect($event) {
        let index = parseInt($event.currentTarget.dataset.eventid.charAt(2));
        wx.navigateTo({
          url: '../_collectActivityDetail/main?index='+index
        })
      },
    },
    onLoad() {
      /* 保存对this(Vue实例)的引用 */
      let that = this
      /* 设置加载动画 */
      // wx.showLoading({
      //   mask: true,
      //   title: '加载中'
      // })
      /*promise*/
      this.$fly.get({
        method: 'POST',
        url: 'http://activity103.mynatapp.cc/miniapp/activityinfo/listbyid'+'?userKey='+'jiguochang',/*contentType: 'application/json;charset=utf-8',*/
        // body: JSON.stringify(data2send)
      }).then(function(res){
        console.log(res.data)
        return that.$fly.get({
          method: 'POST',
          url: 'xxx'
        })
      }).then(function (res) {
        
      })
    }
  }
</script>

<style>
  .con {
    height: 100%;
  }

  .tab-box {
    background-color: #eeeeee;
    height: 100rpx;
    display: flex;
    justify-content: space-around;
    align-items: center;
    color: #000000;
  }

  .tab {
    box-sizing: border-box;
    border-bottom: 5rpx solid #cccccc;
  }

  .tab-active {
    border-bottom: 5rpx solid #ff0000;
  }

  swiper {
    height: calc(100% - 140rpx);
    /*box-sizing: border-box;*/
    background-color: #eeeeee;
    height: calc(100% - 100rpx);
  }

  swiper-item {
    height: 100%;
    overflow: scroll;
  }

  .tab {
    width: 33.3%;
    height: 100%;
    line-height: 100rpx;
    text-align: center;
  }

  .card {
    width:600rpx;
    margin:30rpx auto;
    border-radius:20rpx;
    background:#ffffff;
    border: 4rpx solid #dcdee2;
    padding:  20rpx 0;
  }
  .card p {
    height: 70rpx;
    line-height: 70rpx;
    padding: 0 30rpx;
    font-size: 30rpx;
  }

  .card .title {
    font-size: 40rpx;
    border-bottom: 4rpx solid #dcdee2;
    padding: 0rpx 30rpx 20rpx;
    margin-bottom: 20rpx;
  }
  .key {
    display: inline-block;
    width: 160rpx;
  }
</style>
