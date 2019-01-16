# 报名助手数据库

```sql
-- 用户信息表
create table `user_info`(
    `user_id` varchar(64) not null comment '用户openid',
    `user_name` varchar(512) not null comment '用户姓名',
    `user_icon` varchar(512) comment '用户头像',
    `user_phonenumber` varchar(32) comment '用户手机号',
    primary key( `user_id` )  
) character set = utf8;

-- 用户建议表
create table `user_suggestion`(
    `suggestion_id` int not null auto_increment comment '建议编号',
    `user_id` varchar(64) not null comment '用户openid',
    `suggestion_content` varchar(1024) not null comment '建议内容',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    primary key( `suggestion_id` )
) character set = utf8;

-- 活动信息表
create table `activity_info`(
    `activity_id` int not null auto_increment comment '活动编号',
    `activity_type` int not null comment '活动类型',
    `activity_subject` varchar(512) not null comment '活动主题',
    `activity_content` varchar(1024) not null comment '活动内容',
    `activity_signstartdate` datetime not null comment '活动报名开始时间',
    `activity_signenddate` datetime not null comment '活动报名结束时间',
    `activity_startdate` datetime not null comment '活动开始时间',
    `activity_enddate` datetime not null comment '活动结束时间',
    `activity_fee` int comment '活动费用',
    `activity_peoplelimit` int not null comment '活动总人数',
    `activity_peopleregistered` int not null default 0 comment '活动已报名人数',
    `activity_address` varchar(1024) not null comment '活动地址',
    `activity_addressname` varchar(1024) not null comment '活动地址名称',
    `activity_longitude` decimal(20,10) not null comment '活动地址经度',
    `activity_latitude`  decimal(20,10) not null comment '活动地址纬度',
    `activity_organizer` varchar(512) not null comment '活动组织者姓名',
    `activity_organizerphonenumber` varchar(32) comment '活动组织者手机号',
    `activity_organizerid` varchar(64) not null comment '活动组织者openid',
    `activity_valid` int not null default 0 comment '活动有效性',
    primary key( `activity_id` )
) character set = utf8;

-- 用户收藏活动信息表
create table `activity_collect`(
    `usercollect_id` int not null auto_increment comment '收藏编号',
    `usercollect_userid` varchar(64) not null comment '活动收藏者openid',
    `usercollect_activityid` int not null comment '收藏活动编号',
    primary key( `usercollect_id` )
) character set = utf8;

-- 活动参与人表
create table `activity_member`(
    `activity_joinid` int not null auto_increment comment '活动参与人编号',
    `activity_userid` varchar(64) not null comment '活动参与人openid',
    `activity_activityid` int not null comment '活动编号',
    primary key( `activity_joinid` )
) character set = utf8;

-- 管理员表
create table `manager_info`(
    `manager_id` int not null auto_increment comment '管理员编号',
    `manager_nickname` varchar(64) not null comment '管理员昵称',
    `manager_loginno`  varchar(64) not null unique comment '管理员账号',
    `manager_password` varchar(64) not null comment '管理员密码',
    primary key( `manager_id` )
) character set = utf8;
```

