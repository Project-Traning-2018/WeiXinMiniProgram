import Vue from 'vue'
import App from './App'
import Fly from 'flyio/dist/npm/wx'

let fly = new Fly
Vue.config.productionTip = false
Vue.prototype.$fly = fly

App.mpType = 'app'

const app = new Vue(App)
app.$mount()
Vue.prototype.globalData = getApp().globalData
Vue.prototype.globalData.launActList = {}
Vue.prototype.globalData.partActList = {}
Vue.prototype.globalData.collActList = {}

