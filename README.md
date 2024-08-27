# [签到App]

## 简介

**[签到App]**
是一款用来辅助教师统计学生到课情况的软件。该软件可以将教师的手机等移动设备变成人脸识别签到系统，系统启动后，学生在手机摄像头前停留即可完成签到，同时系统会用语音提示学生签到成功。教师可以随时查看签到统计信息，包括学生签到时间、班级到课率和未签到学生名单等。

该项目的创新点在于，这个系统不依赖专门的设备，只需要使用教师的手机即可完成签到和数据统计。目前已有的人脸识别签到系统均依赖专门的设备（例如电子班牌），设备的采购、安装和维护成本都是不可忽略的影响因素。直接使用教师的手机来实现人脸识别，可以将成本降至最低，这将有利于人脸识别签到系统在各个学校的推广，进而有效降低缺勤率，推动各大高校教学效果的提高。

## 功能目标

在该软件的早期版本，人脸识别和数据统计均在本地实现。在后续版本，将逐步实现数据上报至服务器，和针对多个班级乃至多个年级的到课情况汇总分析功能。

软件主要功能包括：

- **用户功能**：每个用户即为一位教师，系统早期版本在本地运行，只有一位用户。每个用户可以管理多个班级。
- **班级功能**：班级即为在同一个课堂同时上课的一群学生的集合，签到开始时，教师用户需要指定签到的班级，从而让系统确定哪些人需要签到。一个常规的班级例子如下：

| 属性名  | 属性值                                       |
|------|-------------------------------------------|
| 课程编号 | 000001                                    |
| 名称   | 高等数学A(1)[计算机1-4]                          |
| 任课教师 | XXX                                       |
| 上课学生 | {张三2305010101，李四2305010102，王五2305010103…} |

- **人脸识别签到功能**：签到开始后，教师手持手机或固定手机，使手机摄像头朝向某一方向，学生在摄像头前短暂停留后，系统识别出正在签到的是哪一位学生，并且语音播报签到成功的信息。
- **数据汇总统计功能**：签到过程中或签到结束后，教师可以查看已签到和未签到的学生列表，以及签到率等统计信息。如果需要，还会额外统计哪些学生多次缺到等信息。

- **（远期计划）数据上报和汇总统计功能**
  ：根据实际需求，将学生签到的状况实时或集中上传至服务器，以便于对签到数据进行汇总分析。例如查看多个班级乃至整个年级的签到情况，或者查看某门课程、某位任课老师的课程到课情况，又或者查看某位学生在各门课程中的到课情况等。

## 模块划分

### 用户界面部分

- **登录界面**：用于教师用户登录。在单用户模式下不需要登录功能。

- **用户主菜单**：教师在主菜单选择要使用的功能，主要功能包括签到、数据统计和人像采集。教师点击一个选项后进入相应的功能界面。

- **签到准备菜单**：教师选择“签到”功能后进入该界面，教师可以在此创建或选择要签到的班级，并查看班级信息和学生列表。教师还可以调整签到设置。准备好后，教师点击“开始签到”启动签到系统。
- **人脸识别签到界面**：这是签到系统的核心功能界面，该界面会显示摄像头画面和相关统计信息。学生人脸识别签到成功后，屏幕上会有相关提示。签到结束后，教师在该界面点击“结束签到”按钮结束签到。
- **数据统计界面**：教师选择“数据统计”功能后进入该界面，教师可以在此界面查看相应的签到统计信息。
- **人像采集界面**：教师选择“人像采集”功能后进入该界面，该界面用于录入和管理学生的基本信息和人脸特征数据，这些信息被用于组建班级。

### 业务逻辑部分

待完成
