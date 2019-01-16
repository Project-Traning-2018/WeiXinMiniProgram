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
          <p><span class="key">地点</span><span class="value"></span></p>
          <p class="value">{{act.activityAddressname}}</p>
          <p><span class="key">类别</span><span class="value">{{act.activityType}}</span></p>
          <p><span class="key">报名时间</span><span class="value"></span></p>
          <p class="value">{{act.processedSignDate}}</p>
          <p><span class="key">报名活动</span><span class="value"></span></p>
          <p class="value">{{act.processedDate}}</p>
        </div>
      </swiper-item>
      <swiper-item>
        <div class="card" v-for="(act, index) in actPartByme" :index="index" @tap="toDetailParticipate">
          <p class="title">{{act.activitySubject}}</p>
          <p><span class="key">地点</span><span class="value"></span></p>
          <p class="value">{{act.activityAddressname}}</p>
          <p><span class="key">类别</span><span class="value">{{act.activityType}}</span></p>
          <p><span class="key">报名时间</span><span class="value"></span></p>
          <p class="value">{{act.processedSignDate}}</p>
          <p><span class="key">报名活动</span><span class="value"></span></p>
          <p class="value">{{act.processedDate}}</p>
        </div>
      </swiper-item>
      <swiper-item>
        <div class="card" v-for="(act, index) in actCollByme" :index="index" @tap="toDetailCollect">
          <p class="title">{{act.activitySubject}}</p>
          <p><span class="key">地点</span><span class="value"></span></p>
          <p class="value">{{act.activityAddressname}}</p>
          <p><span class="key">类别</span><span class="value">{{act.activityType}}</span></p>
          <p><span class="key">报名时间</span><span class="value"></span></p>
          <p class="value">{{act.processedSignDate}}</p>
          <p><span class="key">报名活动</span><span class="value"></span></p>
          <p class="value">{{act.processedDate}}</p>
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
        actPartByme: [
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
          {
            activitySubject: '活动3',
            activityAddressname: '活动1',
            activityType: '活动1',
            activitySignDate: '活动1',
            activityDate: '明天'
          },
          {
            activitySubject: '活动3',
            activityAddressname: '活动2',
            activityType: '活动2',
            activitySignDate: '活动2',
            activityDate: '明天'
          },
        ], // 我参与的活动列表
        actCollByme: [
          {
            activitySubject: '活动2',
            activityAddressname: '活动2',
            activityType: '活动2',
            activitySignDate: '活动2',
            activityDate: '明天'
          },
          {
            activitySubject: '活动3',
            activityAddressname: '活动1',
            activityType: '活动1',
            activitySignDate: '活动1',
            activityDate: '明天'
          },
          {
            activitySubject: '活动3',
            activityAddressname: '活动2',
            activityType: '活动2',
            activitySignDate: '活动2',
            activityDate: '明天'
          },
        ], // 我收藏的活动列表
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
          url: '../launchActivityDetail/main?index='+index
        })
      },
      toDetailParticipate($event) {
        let index = parseInt($event.currentTarget.dataset.eventid.charAt(2));
        wx.navigateTo({
          url: '../participateActivityDetail/main?index='+index
        })
      },
      toDetailCollect($event) {
        let index = parseInt($event.currentTarget.dataset.eventid.charAt(2));
        wx.navigateTo({
          url: '../collectActivityDetail/main?index='+index
        })
      },
    },
    onShow() {
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
        url: 'http://activity103.mynatapp.cc/miniapp/activityinfo/listbyid'+'?userKey='+this.globalData.id,/*contentType: 'application/json;charset=utf-8',*/
        // body: JSON.stringify(data2send)
      }).then(function(res){
        that.actLaunByme =  res.data.data
        that.globalData.launActList = res.data.data
        console.log(that.globalData.launActList)
        for (let i = 0;i < that.actLaunByme.length;i++) {
          that.actLaunByme[i].processedSignDate = that.actLaunByme[i].activitySignstartdate.replace('T', ' ').substring(0, 16) + ' - ' +
            that.actLaunByme[i].activitySignenddate.replace('T', ' ').substring(0, 16)
          that.actLaunByme[i].processedDate = that.actLaunByme[i].activityStartdate.replace('T', ' ').substring(0, 16) + ' - ' +
            that.actLaunByme[i].activityEnddate.replace('T', ' ').substring(0, 16)
        }
        return that.$fly.get({
          method: 'POST',
          url: 'http://activity103.mynatapp.cc/miniapp/activityinfo/participatelist?userKey=' + that.globalData.id,
        })
      }).then(function (res) {
        console.log('返回的参加活动列表')
        console.log(res)
        that.actPartByme = res.data.data
        that.globalData.partActList = res.data.data
        for (let i = 0;i < that.actPartByme.length;i++) {
          that.actPartByme[i].processedSignDate = that.actPartByme[i].activitySignstartdate.replace('T', ' ').substring(0, 16) + ' - ' +
            that.actPartByme[i].activitySignenddate.replace('T', ' ').substring(0, 16)
          that.actPartByme[i].processedDate = that.actPartByme[i].activityStartdate.replace('T', ' ').substring(0, 16) + ' - ' +
            that.actPartByme[i].activityEnddate.replace('T', ' ').substring(0, 16)
        }
        return that.$fly.get({
          method: 'POST',
          url: 'http://activity103.mynatapp.cc/miniapp/activitycollect/list?userKey=' + that.globalData.id,
        })
      }).then(function (res) {
        console.log('返回的收藏活动列表')
        console.log(res)
        that.actCollByme = res.data.data
        that.globalData.collActList = res.data.data
        for (let i = 0;i < that.actCollByme.length;i++) {
          that.actCollByme[i].processedSignDate = that.actCollByme[i].activitySignstartdate.replace('T', ' ').substring(0, 16) + ' - ' +
            that.actCollByme[i].activitySignenddate.replace('T', ' ').substring(0, 16)
          that.actCollByme[i].processedDate = that.actCollByme[i].activityStartdate.replace('T', ' ').substring(0, 16) + ' - ' +
            that.actCollByme[i].activityEnddate.replace('T', ' ').substring(0, 16)
        }
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
  .value {
    text-align: right;
    font-size: 28rpx;
  }
</style>
