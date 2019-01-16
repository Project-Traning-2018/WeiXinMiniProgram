import Vue from 'vue'
import App from './home'
import QQMapWX from '../../../static/js/qqmap-wx-jssdk'

let qqmapsdk = new QQMapWX({
  key: "TL5BZ-LKU3R-YM7WP-W4CQH-UBHJ7-T2F5Y" // 必填
});
const app = new Vue(App)
app.$mount()
Vue.prototype.qqmapsdk = qqmapsdk
