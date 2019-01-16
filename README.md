# WeiXinMiniProgram
华科软件学院硕士2018年项目实训

### 前端



##### 运行方法
1. cd my-project
2. npm install
3. npm start


##### 项目目录
my-project
- build
- config
- src
  - components
  - pages
    - login
    - home
    - my
    - user
    - newActivity
    - updateActivity
    - launchActivityDetail
    - participateActivityDetail
    - collectActivityDetail
    - randomActivityDetail
    - suggestion
  - utils
  - App.vue
  - app.json
  - main.js
- static
  - images
  - js
- package.json




### 后端



##### 运行方法

1. IntelliJ IDEA 安装lombok插件
2. 安装redis
3. 将doc中建表文件导入数据库中
4. 修改yml相关配置



##### 项目目录

- Check
  - ActivityCheck
- Controller
  - ActivityCollectController
  - ActivityController
  - ActivityParticipateController
  - BackController
  - ManagerInfoController
  - SuggestionController
  - WxController
- Conversion
  - ActivityInfo2BacktoActivityInfo
  - CalculateDistance
  - GetWxInfo
  - InfoMd5
  - TimeTransfer
- Dao
  - ActivityCollect
  - ActivityInfo
  - ActivityMember
  - ManagerInfo
  - UserInfo
  - UserSuggestion
- Enums
  - ActivityInfoStatusEnum
  - BackInfoStatusEnum
  - ManagerInfoStatusEnum
  - WxInfoStatusEnum
- Pojo
  - Activity
    - ActivityChooseInfo2Back
    - ActivityInfo2Back
  - Page
    - BackPage2Back
    - Page2Back
  - Suggestion
    - SuggestionDelete2Back
    - UserSuggestion2Back
  - ManagerInfo2Back
  - MapInfo2Back
  - ResultVO
  - UserInfo2Back
  - WxInfo
- RedisControl
  - RedisClean
- Repository
  - ActivityCollectRepository
  - ActivityInfoRepository
  - ActivityMemberRepository
  - ManagerInfoRepository
  - UserInfoRepository
  - UserSuggestionRepository
- Service
  - impl
    - ActivityCollectServiceImpl
    - ActivityInfoServiceImpl
    - ActivityMemberServiceImpl
    - ManagerInfoServiceImpl
    - UserInfoServiceImpl
    - UserSuggestionServiceImpl
  - ActivityCollectService
  - ActivityInfoService
  - ActivityMemberService
  - ManagerInfoService
  - UserInfoService
  - UserSuggestionService

- Utils
  - HttpClientUtils
  - JsonUtils
- DemoApplication
