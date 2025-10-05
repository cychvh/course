# 课程管理（课程项目）
-  课程名称： 《软件工程创新实践训练2》
-  开发者：蔡奕成 （在导师的指导下完成）
-  完成时间 2024年12月
## 1. 系统概述

### 1.1 项目背景

一个基于Spring Boot + Vue.js的前后端分离课程管理系统，支持用户管理、课程管理、公告管理等核心功能。

### 1.2 系统架构

- 后端：基于Spring Boot + MyBatis-Plus + Redis构建，提供RESTful API
- 前端：基于Vue.js + Element Plus构建，界面美观易用
- 数据库：MySQL 8.0，包含完整的表结构和关系设计
- 缓存：Redis用于公告缓存，提升系统性能

## 2. 功能模块设计

### 2.1 用户管理模块

- 用户注册、登录

- 角色权限管理（学生、教师、管理员）

- 个人信息管理

### 2.2 课程管理模块

- 课程信息的增删改查

- 听课统计

- 课程教室

### 2.3 公告管理模块

- 公告发布、编辑、删除

- 公告分类管理

- Redis缓存优化
### 2.4系统管理模块
- 数据统计与分析

- 系统日志记录

- 基础数据维护

## 3. 数据库设计

### 3.1 用户表(users)

CREATE TABLE `user`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;
### 3.2 课程表(course)

CREATE TABLE `course`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `coursename` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `coursenum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `courseteacher` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `coursedate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `coursedesc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `courseroom` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;
### 3.3 选课表(course_selections)

CREATE TABLE course_selections (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  student_id BIGINT NOT NULL,
  course_id BIGINT NOT NULL,
  select_time DATETIME NOT NULL,
  status VARCHAR(20) DEFAULT 'SELECTED',
  FOREIGN KEY (student_id) REFERENCES users(id),
  FOREIGN KEY (course_id) REFERENCES courses(id)
);
### 3.4 公告表(announcements)

CREATE TABLE `notice`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `creatdate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

### 3.5 教师听课表(crcount)
  CREATE TABLE `crcount`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教师名称',
  `tk_count` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '听课次数',
  `bt_count` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被听课次数',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updatetime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8662422 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程统计信息' ROW_FORMAT = COMPACT;

### 3.6 课程与用户关联关系表(curelation)
  CREATE TABLE `curelation`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `courseid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

### 3.7 角色权限配置表(power)
  CREATE TABLE `power`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `roleid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `power` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

### 3.8 角色定义表（role）
  CREATE TABLE `role`  (
  `roleid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `powerid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rolename` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`roleid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;



## 4. 接口设计

### 4.1 用户相关接口

1. POST /login  
   - 请求：`{userId, password}`  
   - 响应：`{token, userInfo}`（userInfo包含id、name、roleid等用户信息）

2. POST /saveUser  
   - 请求：`{id, name, sex, mobile, roleid, password, type}`（用户信息，id为空时为新增，非空时为修改）  
   - 响应：`{success, message}`

3. GET /loginOut/{token}  
   - 请求：路径参数`token`  
   - 响应：`{success}`（退出登录，清除token缓存）

4. POST /upPwd  
   - 请求：`{oldPwd, newPwd}` + 请求头`token`  
   - 响应：`{success, message}`（修改密码）

5. GET /findTeacher  
   - 请求：`{pageNum, pageSize, id, name}`（分页查询教师，继承PageReq参数）  
   - 响应：`{pageInfo}`（包含教师列表、总条数、总页数等）

6. GET /findDirector  
   - 请求：`{pageNum, pageSize, id, name}`（分页查询主任，继承PageReq参数）  
   - 响应：`{pageInfo}`（包含主任列表、总条数、总页数等）


### 4.2 课程相关接口

1. GET /queryCourse  
   - 参数：无  
   - 响应：`{courseList}`（课程列表，包含id、coursename、coursenum等信息）

2. POST /saveCourse  
   - 请求：`{id, coursename, coursenum, courseteacher, coursedesc, status}`（id为空时新增，非空时修改）  
   - 响应：`{success, message}`

3. DELETE /delCourse/{id}  
   - 请求：路径参数`id`（课程ID）  
   - 响应：`{success, message}`

4. POST /arranging  
   - 请求：`{id, date1[], date2[], courseroom[]}`（排课信息，date1为日期，date2为时间，courseroom为教室）  
   - 响应：`{success, message}`


### 4.3 选课/听课相关接口

1. POST /listening  
   - 请求：`{id, courseid, name, type}`（听课记录，courseid为课程ID，name为听课人）  
   - 响应：`{success, message}`

2. DELETE /delCurelation/{id}  
   - 请求：路径参数`id`（听课记录ID）  
   - 响应：`{success, message}`

3. GET /queryCurelation  
   - 参数：无  
   - 响应：`{curelationList}`（听课记录列表）


### 4.4 公告相关接口

1. GET /queryNotice  
   - 参数：无  
   - 响应：`{notices}`（公告列表，包含id、title、content、creatdate等）

2. POST /saveNotice  
   - 请求：`{id, title, content, creatdate, userid}`（id为空时新增，非空时修改）  
   - 响应：`{success, message}`

3. DELETE /delNotice/{id}  
   - 请求：路径参数`id`（公告ID）  
   - 响应：`{success, message}`


### 4.5 角色相关接口

1. GET /queryRole  
   - 参数：无  
   - 响应：`{roleList}`（角色列表，包含角色及关联权限信息）

2. PUT /editRole  
   - 请求：`{rolename, power, powerid}`（角色及权限修改信息）  
   - 响应：`{success, message}`



## 5. 安全设计

### 5.1 认证机制

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;

    // 请求之前拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 拿到前端发送的token
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()){
            // token为空
            // 返回一个401响应码
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        // 有token
        // 从redis 拿到value
        Object obj = redisTemplate.opsForValue().get(token);
        if (obj == null){
            // token 失效了
            // 返回一个401响应码
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }else {
            // 已经登录
            return true;
        }
    }
}

### 5.2 数据安全
1.密码加密存储（BCrypt）

2.SQL注入防护（MyBatis参数绑定）

3.XSS攻击防护（输入过滤）

4,CSRF防护（Spring Security）

## 6. 性能优化

### 6.1 前端优化

1. 路由懒加载
2. 组件按需引入
3. 图片资源压缩
4. API请求节流

### 6.2 后端优化

1. 数据库索引优化
2. Redis缓存热点数据
3. 分页查询优化
4. 连接池配置
5. SQL语句性能监控

### 6.3 缓存策略

@Service
public class AnnouncementService {
    
    @Cacheable(value = "announcements", key = "#id")
    public Announcement getById(Long id) {
        return announcementMapper.selectById(id);
    }
    
    @CacheEvict(value = "announcements", key = "#id")
    public void updateAnnouncement(Announcement announcement) {
        announcementMapper.updateById(announcement);
    }
}

## 7. 部署方案

### 7.1 开发环境

\- Node.js >= 14.0.0

\- JDK >= 1.8

\- MySQL >= 8.0

\- Maven >= 3.6

\- Redis >= 5.0

\- Maven >= 3.6

### 7.2 部署步骤

1. 前端部署

  \- npm install

  \- npm run serve

2. 后端部署

\- mvn clean package -DskipTests

### 7.3 配置文件

spring:

  datasource:
  
    url: jdbc:mysql://localhost:3306/course_management

    username: ${DB_USERNAME}
    
    password: ${DB_PASSWORD}
    
  redis:
  
    host: ${REDIS_HOST}
    
    port: ${REDIS_PORT}
    
server:

  port: 8089

## 8. 项目目录结构

src/main/java/

└── com/gec/

├── CourseApplication.java                  // 项目启动类

├── req/                                    // 请求参数实体类目录

│   ├── CourseReq.java                      // 课程相关请求参数

│   ├── RoleReq.java                        // 角色相关请求参数

│   ├── PageReq.java                        // 分页请求参数

│   └── UserQueryReq.java                   // 用户查询请求参数

├── controller/                             // 控制器目录

│   ├── CourseController.java               // 课程相关接口

│   ├── NoteController.java                 // 通知相关接口

│   └── UserController.java                 // 用户相关接口

├── util/                                   // 工具类目录

│   ├── SpringMvcConfig.java                // Spring MVC配置

│   ├── Result.java                         // 统一响应结果封装

│   ├── Constans.java                       // 常量定义

│   ├── SnowFlake.java                      // 雪花算法工具

│   └── LoginInterceptor.java               // 登录拦截器

├── entity/                                 // 数据库实体类目录

│   ├── Course.java                         // 课程实体

│   ├── User.java                           // 用户实体

│   ├── Notice.java                         // 通知实体

│   ├── Power.java                          // 权限实体

│   └── Role.java                           // 角色实体

├── mapper/                                 // MyBatis Mapper接口目录

│   ├── CourseMapper.java                   // 课程数据操作接口

│   ├── UserMapper.java                     // 用户数据操作接口

│   ├── RoleMapper.java                     // 角色数据操作接口

│   ├── NoticeMapper.java                   // 通知数据操作接口

│   └── PowerMapper.java                    // 权限数据操作接口

├── resp/                                   // 响应实体类目录

│   └── UserResp.java                       // 用户响应实体

└── service/                                // 服务层目录

    ├── CourseService.java                  // 课程服务接口
    
    ├── RoleService.java                    // 角色服务接口
    
    ├── NoticeService.java                  // 通知服务接口
    
    ├── PowerService.java                   // 权限服务接口
    
    ├── UserService.java                    // 用户服务接口
    
    └── impl/                               // 服务实现类目录
    
        ├── CourseServiceImpl.java          // 课程服务实现
        
        ├── PowerServiceImpl.java           // 权限服务实现
        
        ├── NoticeServiceImpl.java          // 通知服务实现
        
        ├── UserServiceImpl.java            // 用户服务实现
        
        └── RoleServiceImpl.java            // 角色服务实现
## 9.

本文档详细说明了项目的主要设计内容，包括功能模块、数据库设计、接口设计等关键部分，可作为开发和维护的重要参考。
