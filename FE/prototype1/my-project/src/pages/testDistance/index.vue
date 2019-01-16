<template>
  <div>
  <!--form表单-->
  <form @submit="formSubmit">
    <!--输入起点和终点经纬度坐标，格式为string格式-->
    <label>起点坐标:
      <button @tap="handleTap1">选择位置</button>
      <input style="border:1px solid #000;" name="start" v-model="startPoint"></input>
    </label>
    <!--多个终点位置示例：39.984060,116.307520;39.984060,116.507520-->
    <label>终点坐标:
      <button @tap="handleTap2">选择位置</button>
      <input style="border:1px solid #000;" name="dest" v-model="endPoint"></input>
    </label>
    <!--提交表单数据-->
    <button form-type="submit">计算距离</button>
  </form>
  <!--渲染起点经纬度到终点经纬度距离，单位为米-->
  <view v-for="(dis, index) in distance" :key="index">
    <view>起点到终点{{index+1}}的步行距离为{{dis}}米</view>
  </view>
  </div>
</template>

<script>
    export default {
      name: "index",
      data() {
        return {
          startPoint: '',
          endPoint: '',
          content: '',
          distance: []
        }
      },
      methods: {
        handleTap1() {
          let that = this
          wx.chooseLocation({
            success(res) {
              console.log(res)
              that.startPoint = res.latitude+','+res.longitude
            }
          })
        }
        ,
        handleTap2() {
          let that = this
          wx.chooseLocation({
            success(res) {
              console.log(res)
              that.endPoint = res.latitude+','+res.longitude
            }
          })
        }
        ,
        formSubmit(e){
          var that = this;
          console.log(that)
          console.log(e)
          //调用距离计算接口
          that.qqmapsdk.calculateDistance({
            //mode: 'driving',//可选值：'driving'（驾车）、'walking'（步行），不填默认：'walking',可不填
            //from参数不填默认当前地址
            //获取表单提交的经纬度并设置from和to参数（示例为string格式）

            from: e.mp.detail.value.start || '', //若起点有数据则采用起点坐标，若为空默认当前地址
            to: e.mp.detail.value.dest, //终点坐标
            success: function(res) {//成功后的回调
              console.log(res);
              var res = res.result;
              var dis = [];
              for (var i = 0; i < res.elements.length; i++) {
                dis.push(res.elements[i].distance); //将返回数据存入dis数组，
              }
              that.distance = dis
            },
            fail: function(error) {
              console.error(error);
            },
            complete: function(res) {
              console.log(res);
            }
          });
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
