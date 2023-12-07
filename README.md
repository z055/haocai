##耗材管理
###目录树
```
├─Sql                               ---- sql文件
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─haocai
│  │  │          ├─filter           ---- 过滤器
│  │  │          ├─module           ---- 模块
│  │  │          └─utils            ---- 工具
│  │  ├─resources
│  │  │  └─mapper                   ---- xxx mapper.xml
│  │  └─webapp
│  │      └─WEB-INF
│  │          └─layui
│  │              ├─api
│  │              ├─css
│  │              ├─images
│  │              ├─js
│  │              ├─lib
│  │              └─page            ---- 各模块页面
```
#系统管理——api

用户管理

查询所有用户 user/queryAllUser

登录 user/login
入库管理和入库审核——api

####```/ruku/getdata```，当页面加载时，可把数据库里采购审核成功的耗材查询出来,此接口可接收耗材名，耗材类型，时间段，根据时间time1开始时间，time2结束时间

####(时间要string形式接收如2022-05-13) ，耗材属性等作为查询筛选条件

####```/ruku/update```,可修改耗材的价格和数量接收价格或数量或者价格和数量

####```/ruku/kuguan``` 由库管员操作入库，接收ID字符串如"34,35,36,37,38,39"

####```/ruku/shenheSelect``` 审核过程中查询耗材的信息，传递过来的信息status为0代表未审核 1代表审核中 2代表审核成功必须要传入limit和page，根据状态查询审核中###1未审核0审核成功2二级院长审核打回4二级管理员审核打回5属性名是status2，根据时间time1开始时间，time2结束时间

####```/ruku/success```当某一个审查人员选中审核通过时调用此接口，需要传入json的集合,或者单个也应该可以，需要参数耗材ID,审核id审核意见

####```/ruku/delect```可删除耗材信息需要传入参数ID

####```/ruku/opinion``` 查询审核流程已有的审核意见需要参数shenheid

####```/ruku/dahui```审查者审查不通过使用此接口，需要传入值 审核id审核意见，审核逐级审核，最终到库管员手中，他可以做相应修改操作，重新提交申请入库

####入库表status0代表未审核，1代表审核中，2代表审核成功并且成功入库4代表审核被二级院长打回，5代表审核被二级管理员打回

####```/ruku/selectnamebh``` 查询name和耗材编号

####注!shstatus是前端往后端传的审核状态，作为当前审核状态的筛选

####```/ruku/getIdAll```通过id查训相关信息


###入库管理和入库审核-api
###接口 /ruku/getdata，当页面加载时，可把数据库里采购审核成功的耗材查询出来,此接口可接收耗材名，耗材类型，时间段，根据时间time1开始时间，time2结束时间

###(时间要string形式接收如2022-05-13) ，耗材属性等作为查询筛选条件

###接口/ruku/update,可修改耗材的价格和数量接收价格或数量或者价格和数量

###接口/ruku/kuguan 由库管员操作入库，接收ID字符串如"34,35,36,37,38,39"

###接口/ruku/shenheSelect 审核过程中查询耗材的信息，传递过来的信息status为0代表未审核 1代表审核中 2代表审核成功必须要传入limit和page，根据状态查询审核中###1未审核0审核成功2二级院长审核打回4二级管理员审核打回5属性名是status2，根据时间time1开始时间，time2结束时间

###接口/ruku/success当某一个审查人员选中审核通过时调用此接口，需要传入json的集合,或者单个也应该可以，需要参数耗材ID,审核id审核意见

###/ruku/delect可删除耗材信息需要传入参数ID

###/ruku/opinion 查询审核流程已有的审核意见需要参数shenheid

###/ruku/dahui审查者审查不通过使用此接口，需要传入值 审核id审核意见，审核逐级审核，最终到库管员手中，他可以做相应修改操作，重新提交申请入库

###入库表status0代表未审核，1代表审核中，2代表审核成功并且成功入库

###/ruku/selectnamebh 查询name和耗材编号

###注!shstatus是前端往后端传的审核状态，作为当前审核状态的筛选

###审核表status0代表已提交，1代表二级管理员审核成功2代表二级管理员打回，3代表二级院长审核成功4代表二级院长打回

###采购管理-api

###接口/purchase/sel_allnumber,查询采购总数据总条数

###接口/purchase/purchaselist 点击采购显示的数据和名称需要编号和二级学院

###接口/purchase/changepurchaselist 点击修改需要的数据需要编号和二级学院

###接口/purchase/updatepurchase 修改提交 

###接口/purchase/purchasecheck 采购页面提交

###接口/purchase//sel_all 查询采购表和list表所有共同的数据

###接口/purchase//deletepurchase 删除采购  需要对应的id

###接口/purchase/sub_purchase 采购汇总提交审核通过 需要二级学院 审核人姓名  角色

###接口/purchase/sel_teacher  查询功能 教师或其他人 需要耗材名称、类别、属性 审核流程状态 时间 总页数、每页数 二级学院 角色

###接口/purchase/shenhepage 审核页面 需要id

###接口/purchase/sel_allnumber 查所有、总条数

###接口/purchase/shenhetuihui 审核退回 需要二级学院、意见、id、审核人、角色

###采购管理-api

###出库教师提，专业负责人审核，二级院长审核，库管直接出库管理

###/chuku/getDepartment老师提出申请，选择授课学院，根据授课学院查出此学院的所有专业，需要传入参数 老师下拉框选的学院(department)

###/chuku/getCourse根据专业获取此专业下的课程和课程名称等等需要传入参数专业名称(major)

###/chuku/getTuijian根据教师所选的课程和实训名进行推荐耗材需要参数课程(kechengName),和实训名(shixunName)

###/chuku/getAll当用户感觉推荐的耗材不够时点击更多调用此接口

###需要当前用户所选的学院(department)，name(name)(可选传''空字符或者传name为空若是不是空字符则根据name进行模糊查询)

###/chuku/shenqing老师发出出库申请，生成出库申请的记录参数是List<实体类>,具体传参参考如下图 ![image-20220522144938233](C:\Users\sanlian\AppData\Roaming\Typora\typora-user-images\image-20220522144938233.png)

###其中kid为总库存的id

###/chuku/getShAll领导审核时调用此接口主要接收某些筛选条件，如某个时间段，time1(起始时间),time2(终止时间)类###型String(如2020-5-10)，还有专业名，实训名，课程名，申请人姓名等等，还有可以根据当前审核状态查询用shstatus 0代表未审核 1代表审核成功 2代表被自己上一级打回

###/chuku/tongyi当审核通过时调用此接口，后台用List<对象>,需要参数shenheid，审核意见(若是为空默认为审核同意)

###/chuku/dahui当审核打回时调用此接口，后台用List<对象>,需要参数shenheid，审核意见(必填项)

###/chuku/guihuan老师归还时，库管员调用此接口需要传入的参数shenheid,kid(总库存表里的耗材id),耗材价格，归还的数量number(我想的是不一定全部归还)

###/chuku/ delete，删除记录需要传入参数shenheid，只删out表里的记录，删除后前台看不见，但数据库申请记录都保留了

###/chuku/ deleteAll删的干干净净，彻彻底底，毫无痕迹，需要传入参数shenheid

###/chuku/ selectOpinion查询目前所有此审核记录的审核意见需要传入参数shenheid

###/chuku/ xiangqing，领导审查时可根据此接口查看老师申请的耗材详细信息

###/chuku/ Teacher此接口返回此老师的申请记录需要传参某些筛选条件，如某个时间段，time1(起始时间),time2(终止时###间)类型String(如2020-5-10)，还有专业名，实训名，课程名，申请人姓名等等，还有可以根据当前审核状态查询

###/chuku/ updatehc老师修改申请的耗材信息，如修改的数量,添加新的耗材接收参数如下图![image-20220522144909910](C:\Users\sanlian\AppData\Roaming\Typora\typora-user-images\image-20220522144909910.png) number传的是老师上次选的耗材数量，allNumber传的是老师要把耗材数量改为多少
