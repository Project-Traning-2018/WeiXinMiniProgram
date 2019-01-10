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
    <swiper :current="current" duration="250" @change="handleChange">
      <swiper-item>
        <div class="card">
          <p>标题</p>
          <p>地点</p>
          <p>事件</p>
        </div>
        <div class="card">
          <p>标题</p>
          <p>地点</p>
          <p>事件</p>
        </div>
        <div class="card">
          <p>标题</p>
          <p>地点</p>
          <p>事件</p>
        </div>
        <div class="card">
          <p>标题</p>
          <p>地点</p>
          <p>事件</p>
        </div>
        <div class="card">
          <p>标题</p>
          <p>地点</p>
          <p>事件</p>
        </div>
      </swiper-item>
      <swiper-item>
        <div class="card">
          <p>标题</p>
          <p>地点</p>
          <p>事件</p>
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
    },

    created() {
      const logs = (wx.getStorageSync('logs') || [])
      this.logs = logs.map(log => formatTime(new Date(log)))
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
    padding: 20rpx 0;
    background-color: #eeeeee;
  }

  swiper-item {
    height: 100%;
    overflow: scroll;
    padding: 30rpx 0;
  }

  .tab {
    width: 33.3%;
    height: 100%;
    line-height: 100rpx;
    text-align: center;
  }

  .card {
    width: 500rpx;
    border: solid 5rpx #000000;
    margin: 30rpx auto;
    padding: 20rpx;
  }

  .card p {
    height: 70rpx;
    line-height: 70rpx;
  }
</style>
