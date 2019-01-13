<template>
  <div>
    <!--activityType: '',-->
    <!--activitySubject: '',-->
    <!--activityContent: '',-->
    <!--activitySignstartdate: '',-->
    <!--activitySignenddate: '',-->
    <!--activitySignstarttime: '',-->
    <!--activitySignendtime: '',-->
    <!--activityStartdate: '',-->
    <!--activityEnddate: '',-->
    <!--activityFee: 0,-->
    <!--activityPeoplelimit: 0,-->
    <!--activityAddress: '',-->
    <!--activityAddressname: '选择地点',-->
    <!--activityLongitude: 0,-->
    <!--activityLatitude: 0,-->
    <!--activityOrganizer: '',-->
    <!--activityOrganizerphonenumber: '',-->
    <!--activityOrangizerid: '',-->

    <input type="text" id="act_sub" placeholder="请输入标题" v-model="activitySubject"><br>
    <textarea id="act_con" placeholder="请输入详细信息" v-model="activityContent"></textarea>
    <span id="act_type">请选择活动类型</span>
    <radio-group @change="handleRadioChange">
      <label for="type1"><input type="radio" name="type" value="1" id="type1">
      Type 1</label>
      <label for="type2"><input type="radio" name="type" value="2" id="type2">
      Type 2</label>
      <label for="type3"><input type="radio" name="type" value="3" id="type3">
      Type 3</label>
      <label for="type4"><input type="radio" name="type" value="4" id="type4">
      Type 4</label>
    </radio-group>
    <p class="date-time" id="sign-start">报名开始时间
      <view>
        <picker mode="date" start="2013-09-01" end="2020-09-01" @change="handlechange1" id="date-pick-1" class="date-pick" :value="activitySignstartdate">
          {{activitySignstartdate}}
        </picker>
      </view>
      <view>
        <picker mode="time" start="00:00" end="23:59" v-model="activitySignstarttime" @change="handlechange2" id="time-pick-1" class="time-pick" :value="activitySignstarttime">
          {{activitySignstarttime}}
        </picker>
      </view>
    </p>
    <p class="date-time" id="sign-end">报名截止时间
      <view>
        <picker mode="date" start="2013-09-01" end="2020-09-01" @change="handlechange3" id="date-pick-2"  class="date-pick"  :value="activitySignenddate">
          {{activitySignenddate}}
        </picker>
      </view>
      <view>
        <picker mode="time" start="00:00" end="23:59" v-model="activitySignstarttime" @change="handlechange4" id="time-pick-2" class="time-pick"  :value="activitySignendtime">
          {{activitySignendtime}}
        </picker>
      </view>
    </p>
    <p class="date-time" id="act-start">活动开始时间
      <view>
        <picker mode="date" start="2015-09-01" end="2017-09-01" @change="handlechange5" class="date-pick" id="date-pick-3"  :value="activityStartdate">
          {{activityStartdate}}
        </picker>
      </view>
      <view>
        <picker mode="time" start="00:00" end="23:59" v-model="activitySignstarttime" @change="handlechange6" class="time-pick" id="time-pick-3" :value="activityStarttime">
          {{activityStarttime}}
        </picker>
      </view>
    </p>
    <p class="date-time" id="act-end">活动截止时间
      <view>
        <picker mode="date" start="2015-09-01" end="2017-09-01" @change="handlechange7" class="date-pick" id="date-pick-4" :value="activityEnddate">
          {{activityEnddate}}
        </picker>
      </view>
      <view>
        <picker mode="time" start="00:00" end="23:59" v-model="activitySignstarttime" @change="handlechange8" class="time-pick" id="time-pick-4" :value="activityEnddate">
          {{activityEndtime}}
        </picker>
      </view>
    </p>
    <p id="act_fee"><span>报名费用</span><span id="fee"><input type="digit" maxlength="10" value=0 v-model="activityFee">元</span></p>
    <p id="act_hc"><span>人数限制</span><span id="hc"><input type="number" maxlength="4" placeholder="填写报名人数" v-model="activityPeoplelimit">人</span></p>
    <p id="act_addr"><span>活动地点</span><button @tap="handlechooseloca"><span>{{activityAddressname}}</span><img src="../../../static/images/loca.png" alt=""></button></p>
    <p id="act_man_name"><span>发起人姓名</span><input type="text" placeholder="请填写发起人姓名" v-model="activityOrganizer"></p>
    <p id="act_man_phone"><span>发起人电话</span><input type="text" maxlength="11" placeholder="请填写发起人电话" v-model="activityOrganizerphonenumber"></p>
    <button id="sub" @tap="handleSubmit">确 认</button>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        userIdMd5: '',
        activityType: '',
        activitySubject: '',
        activityContent: '',
        activitySignstartdate: new Date().getFullYear() + '-' +this.formatTime(new Date().getMonth()+1)+ '-' +this.formatTime(new Date().getDate()),
        activitySignenddate: new Date().getFullYear() + '-' +this.formatTime(new Date().getMonth()+1)+ '-' +this.formatTime(new Date().getDate()),
        activitySignstarttime: '00:00',
        activitySignendtime: '23:59',
        activityStartdate: new Date().getFullYear() + '-' +this.formatTime(new Date().getMonth()+1)+ '-' +this.formatTime(new Date().getDate()),
        activityEnddate: new Date().getFullYear() + '-' +this.formatTime(new Date().getMonth()+1)+ '-' +this.formatTime(new Date().getDate()),
        activityStarttime: '00:00',
        activityEndtime: '23:59',
        activityFee: 0,
        activityPeoplelimit: 0,
        activityAddress: '',
        activityAddressname: '选择地点',
        activityLongitude: 0,
        activityLatitude: 0,
        activityOrganizer: '',
        activityOrganizerphonenumber: '',
        activityOrangizerid: '',
      }
    },
    computed: {
      activitySignStart() {
        return this.activitySignstartdate + ' ' + this.activitySignstarttime + ':00'
      }
    },
    methods: {
      formatTime(e) {
        if (parseInt(e)<10)
          return '0'+e
        else
          return e
      },
      handlechange1(e) {
        // console.log(e)
        this.activitySignstartdate = e.mp.detail.value
        console.log(this.activitySignstartdate)

      },
      handlechange2(e) {
        this.activitySignstarttime = e.mp.detail.value
        console.log(this.activitySignstarttime)
      },
      handlechange3(e) {
        this.activitySignenddate = e.mp.detail.value
        console.log(this.activitySignenddate)
      },
      handlechange4(e) {
        this.activitySignendtime = e.mp.detail.value
        console.log(this.activitySignendtime)
      },
      handlechange5(e) {
        this.activityStartdate = e.mp.detail.value
        console.log(this.activityStartdate)
      },
      handlechange6(e) {
        this.activityStartime = e.mp.detail.value
        console.log(this.activityStarttime)
      },
      handlechange7(e) {
        this.activityEnddate = e.mp.detail.value
        console.log(this.activityEnddate)
      },
      handlechange8(e) {
        this.activitySignendtime = e.mp.detail.value
        console.log(this.activityEndtime)
      },
      handlechooseloca() {
        let that = this
        wx.chooseLocation({
          success(res){
            console.log(res)
            that.activityAddressname = res.name
            that.activityAddress = res.address
            that.activityLatitude = res.latitude
            that.activityLongitude = res.longitude
          }
        })
        this.a++
      },
      handleSubmit() {
        console.log(this.activitySubject)
        console.log(this.activityContent)
        console.log(this.activityType)
        console.log(this.activitySignstartdate)
        console.log(this.activitySignenddate)
        console.log(this.activitySignstarttime)
        console.log(this.activitySignendtime)
        console.log(this.activityStartdate)
        console.log(this.activityEnddate)
        console.log(this.activityStarttime)
        console.log(this.activityEndtime)
        console.log(this.activityFee)
        console.log(this.activityPeoplelimit)
        console.log(this.activityLatitude)
        console.log(this.activityLongitude)
        console.log(this.activityAddressname)
        console.log(this.activityAddress)
        console.log(this.activityOrganizer)
        console.log(this.activityOrganizerphonenumber)
        let data2send = {
          'userIdMd5': 'jiguochang',
          'activityType': this.activityType,
          'activitySubject': this.activitySubject,
          'activityContent': this.activityContent,
          'activitySignstartdate': this.activitySignstartdate + ' ' +this.activitySignstarttime+':00',
          'activitySignenddate': this.activitySignenddate + ' ' +this.activitySignendtime+':00',
         /* 'activitySignstarttime': this.activitySignstarttime,
          'activitySignendtime': this.activitySignendtime,*/
          'activityStartdate': this.activityStartdate + ' ' + this.activityStarttime+':00',
          'activityEnddate': this.activityEnddate + ' ' +this.activityEndtime+':00',
          /*'activityStarttime': this.activityStarttime,
          'activityEndtime': this.activityEndtime,*/
          'activityFee': this.activityFee,
          'activityPeoplelimit': this.activityPeoplelimit,
          'activityAddress': this.activityAddress,
          'activityAddressname': this.activityAddressname,
          'activityLongitude': this.activityLongitude,
          'activityLatitude': this.activityLatitude,
          'activityOrganizer': this.activityOrganizer,
          'activityOrganizerphonenumber': this.activityOrganizerphonenumber,
        }
        console.log(data2send.activitySignstartdate)
        console.log(data2send.activitySignenddate)
        console.log(data2send.activityStartdate)
        console.log(data2send.activityEnddate)
        /* 发送 */
        this.$fly.interceptors.request.use((request) => {
          request.headers = {
            'Content-Type': 'application/json'
          };
        })
        wx.showLoading({
          mask: true,
          title: '加载中'
        })
        this.$fly.get({
          method: 'POST',
          url: 'http://activity103.mynatapp.cc/miniapp/activityinfo/save',/*contentType: 'application/json;charset=utf-8',*/
          body: JSON.stringify(data2send)
        }).then(function(res){
          console.log(res.data)
        })

      },
      handleRadioChange(e){
        this.activityType = parseInt(e.mp.detail.value)
      },
    }
  }
</script>

<style>
  .con {
    height: 100%;
  }

  .tab-box {
    background-color: #eeeeee;
    height: 100 rpx;
    display: flex;
    justify-content: space-around;
    align-items: center;
    color: #000000;
  }

  .tab {
    box-sizing: border-box;
    border-bottom: 5 rpx solid #cccccc;
  }

  .tab-active {
    border-bottom: 5 rpx solid #ff0000;
  }

  swiper {
    height: calc(100% - 140rpx);
    /*box-sizing: border-box;*/
    padding: 20 rpx 0;
    background-color: #eeeeee;
  }

  swiper-item {
    height: 100%;
    overflow: scroll;
    padding: 30 rpx 0;
  }

  .tab {
    width: 33.3%;
    height: 100%;
    line-height: 100 rpx;
    text-align: center;
  }

  .card {
    width: 500 rpx;
    border: solid 5 rpx #000000;
    margin: 30 rpx auto;
    padding: 20 rpx;
  }

  .card p {
    height: 70 rpx;
    line-height: 70 rpx;
  }

  .date-time {
    display: flex;
    justify-content: space-between;
  }

  #act_sub {
    height: 80rpx;
    padding-left: 25rpx;
    border-bottom: solid 3rpx #eee;
  }
  #act_con {
    width: 100%;
    height: 240rpx;
    padding: 25rpx;
    border-bottom: solid 15rpx #eee;
    box-sizing: border-box;
  }
  #act_type {
    display: block;
    height: 80rpx;
    padding-left: 25rpx;
    line-height: 80rpx;
    font-size: 30rpx;
  }
  radio-group {
    display: flex;
    justify-content: space-around;
    align-items: center;
    height: 80rpx;
    line-height: 80rpx;
    border-bottom: solid 15rpx #eee;
    font-size: 30rpx;
  }
  #sign-start {
    height: 80rpx;
    line-height: 80rpx;
    padding-left: 25rpx;
    font-size: 30rpx;
    border-bottom: solid 3rpx #eee;
  }
  #sign-end {
    height: 80rpx;
    line-height: 80rpx;
    padding-left: 25rpx;
    font-size: 30rpx;
    border-bottom: solid 15rpx #eee;
  }
  #act-start {
    height: 80rpx;
    line-height: 80rpx;
    padding-left: 25rpx;
    font-size: 30rpx;
    border-bottom: solid 3rpx #eee;
  }
  #act-end {
    height: 80rpx;
    line-height: 80rpx;
    padding-left: 25rpx;
    font-size: 30rpx;
    border-bottom: solid 15rpx #eee;
  }
  .date-pick {
    width: 300rpx;
    text-align: right;
  }
  .time-pick {
    width: 200rpx;
    text-align: center;
  }
  #act_fee {
       height: 80rpx;
       line-height: 80rpx;
       padding-left: 25rpx;
       padding-right: 25rpx;
       font-size: 30rpx;
       border-bottom: solid 3rpx #eee;
       display: flex;
       justify-content: space-between;
     }
  #act_fee>input {
    display: inline-block;
    height: 80rpx;
    width: 200rpx;
    text-align: center;
    line-height: 80rpx;
  }
  #fee {
    display: flex;
    align-items: center;
  }
  #fee>input {
    width: 180rpx;
    text-align: right;
    margin-right: 10rpx;
  }
  #act_hc {
    height: 80rpx;
    line-height: 80rpx;
    padding-left: 25rpx;
    font-size: 30rpx;
    border-bottom: solid 3rpx #eee;
    display: flex;
    justify-content: space-between;
    border-bottom: solid 15rpx #eee;
    padding-right: 25rpx;
     }
  #act_hc>input {
    display: inline-block;
    height: 80rpx;
    width: 200rpx;
    text-align: center;
    line-height: 80rpx;
  }
  #hc {
    display: flex;
    align-items: center;
  }
  #hc>input {
    width: 180rpx;
    text-align: right;
    margin-right: 10rpx;
  }
  #act_addr {
    display: flex;
    height: 80rpx;
    line-height: 80rpx;
    padding-left: 25rpx;
    font-size: 30rpx;
    border-bottom: solid 15rpx #eee;
    justify-content: space-between;
  }
  #act_addr>button {
    margin: 0;
    border: none;
    background-color: #ffffff;
    display: inline-block;
    height: 80rpx;
    width: 500rpx;
    line-height: 80rpx;
    font-size: 30rpx;
    box-shadow: none;
    outline: none;
    color: #2C8DF6;
    display: flex;
    text-align: right;
    position: relative;
  }
  #act_addr>button::after{ border: none; }
  #act_addr>button span {
    display: inline-block;
    width: 400rpx;
    text-align: right;
  }
  #act_addr>button img {
    height: 40rpx;
    width: 40rpx;
    margin: 20rpx 0;
    position: absolute;
    right: 14px;
  }
  #act_man_name {
    display: flex;
    height: 80rpx;
    line-height: 80rpx;
    padding-left: 25rpx;
    padding-right: 25rpx;
    font-size: 30rpx;
    border-bottom: solid 3rpx #eee;
    justify-content: space-between;
  }
  #act_man_name>input {
    display: flex;
    height: 80rpx;
    line-height: 80rpx;
    padding-left: 25rpx;
    font-size: 30rpx;
    border-bottom: solid 3rpx #eee;
    justify-content: space-between;
    text-align: right;
  }
  #act_man_phone {
    display: flex;
    height: 80rpx;
    line-height: 80rpx;
    padding-left: 25rpx;
    padding-right: 25rpx;
    font-size: 30rpx;
    border-bottom: solid 15rpx #eee;
    justify-content: space-between;
  }
  #act_man_phone>input {
    display: flex;
    height: 80rpx;
    line-height: 80rpx;
    padding-left: 25rpx;
    font-size: 30rpx;
    border-bottom: solid 3rpx #eee;
    justify-content: space-between;
    text-align: right;
  }
  #sub {
    width: 80%;
    background-color: rgb(44, 141, 246);
    margin: 20rpx auto;
    color: #ffffff;
  }
  #sub:active {
    background-color: rgba(44, 141, 246, 0.8);
  }
  #sub::after{ border: none; }
</style>
