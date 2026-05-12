# tlias-web-management

`tlias-web-management` 是一个基于 Spring Boot + MyBatis 的教务/人事管理后端项目，当前已经完成主要业务模块开发，可直接作为课程项目或后端练手项目使用。

## 项目简介

项目围绕“部门、员工、班级、学生、统计报表、登录认证、文件上传”等场景展开，覆盖了后端开发中最常见的一批能力，包括：

- RESTful API 设计
- 分层架构设计
- MyBatis 数据持久化
- MySQL 数据库操作
- 统一响应封装
- 参数校验与异常处理
- JWT 登录认证
- 统计报表接口
- 文件上传

## 已实现功能

### 1. 登录认证

- 登录接口
- JWT 令牌生成与解析
- Token 校验过滤器 / 拦截器
- 登录态保护的基础能力

### 2. 部门管理

- 查询所有部门
- 根据 ID 查询部门
- 新增部门
- 修改部门
- 删除部门

### 3. 员工管理

- 员工分页查询
- 员工新增
- 员工修改
- 员工删除
- 员工详情回显
- 员工职位、性别等统计数据接口

### 4. 班级管理

- 班级分页查询
- 班级新增
- 班级修改
- 班级删除
- 班级详情查询
- 班级学生人数统计接口

### 5. 学生管理

- 学生分页查询
- 学生新增
- 学生修改
- 学生删除
- 学生详情查询
- 学生违纪扣分修改
- 学位统计接口

### 6. 报表统计

- 员工职位统计
- 员工性别统计
- 学生学位统计
- 班级学生人数统计

### 7. 文件上传

- 已集成文件上传相关接口
- 可用于头像上传、附件上传等场景扩展

### 8. 操作日志审计

- 基于自定义 `@Log` 注解+AOP 实现接口操作日志自动记录
- 记录操作人、操作时间、类名、方法名、请求参数、返回值、耗时等信息
- 操作日志可持久化到数据库，便于后续审计与问题排查

### 9. 通用基础能力

- 统一返回结果封装 `Result`
- 全局异常处理
- 日志输出 `Slf4j`
- 操作日志自动记录与持久化
- MyBatis 驼峰映射
- 自动填充创建时间、修改时间
- 分页查询支持 `PageHelper`
- 基础工具类封装

## 技术栈

- Java 21
- Spring Boot 4
- Spring Web MVC
- MyBatis
- PageHelper
- MySQL
- JWT
- Lombok

## 项目结构

```text
tlias-web-management
├─ src/main/java/com/itluffy
│  ├─ config            # Web 配置
│  ├─ controller        # 控制层
│  ├─ exception         # 全局异常处理
│  ├─ filter            # 过滤器
│  ├─ interceptor       # 拦截器
│  ├─ mapper            # 数据访问层
│  ├─ pojo              # 实体类 / 请求响应对象
│  ├─ service           # 业务层接口
│  ├─ service/impl      # 业务层实现
│  ├─ utils             # 工具类
│  └─ TliasWebManagementApplication.java
├─ src/main/resources
│  └─ application.yaml  # 项目配置
└─ pom.xml
```

## 运行环境

- JDK 21
- Maven 3.9+
- MySQL 8+
- IDEA / VS Code

## 快速开始

### 1. 准备数据库

先创建数据库，并导入项目对应的表结构和测试数据。

### 2. 修改配置

根据本地环境修改 `src/main/resources/application.yaml` 中的数据库连接信息、JWT 配置等内容。

示例：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/tlias
    username: root
    password: your_password
```

### 3. 启动项目

在项目根目录执行：

```bash
mvn spring-boot:run
```

也可以直接运行启动类：

```text
com.itluffy.TliasWebManagementApplication
```

## 接口概览

### 部门管理

统一前缀：`/depts`

- `GET /depts`：查询所有部门
- `GET /depts/{id}`：根据 ID 查询部门
- `POST /depts`：新增部门
- `PUT /depts`：修改部门
- `DELETE /depts?id=1`：删除部门

### 员工管理

统一前缀：`/emps`

- `GET /emps`：分页查询员工
- `GET /emps/list`：兼容旧版分页请求
- `GET /emps/{id}`：查询员工详情
- `POST /emps`：新增员工
- `PUT /emps`：修改员工
- `DELETE /emps?ids=1&ids=2`：删除员工

### 班级管理

统一前缀：`/clazzs`

- `GET /clazzs`：分页查询班级
- `GET /clazzs/list`：兼容旧版分页请求
- `GET /clazzs/{id}`：查询班级详情
- `POST /clazzs`：新增班级
- `PUT /clazzs`：修改班级
- `DELETE /clazzs/{id}`：删除班级

### 学生管理

统一前缀：`/students`

- `GET /students`：分页查询学生
- `GET /students/{id}`：查询学生详情
- `POST /students`：新增学生
- `PUT /students`：修改学生
- `DELETE /students/{ids}`：批量删除学生
- `PUT /students/violation/{id}/{score}`：修改违纪扣分

### 统计报表

统一前缀：`/report`

- `GET /report/empJobData`：员工职位统计
- `GET /report/empGenderData`：员工性别统计
- `GET /report/studentDegreeData`：学生学位统计
- `GET /report/studentCountData`：班级学生人数统计

## 统一返回格式

项目统一使用 `Result` 作为返回结构，例如：

```json
{
  "code": 1,
  "msg": "success",
  "data": []
}
```

- `code = 1` 表示成功
- `code = 0` 表示失败
- `msg` 表示提示信息
- `data` 表示实际返回数据

## 开发说明

- 项目采用典型的 `Controller` / `Service` / `Mapper` 分层模式
- 数据库字段建议使用下划线命名，Java 实体类使用驼峰命名
- 新增和修改时会自动补全时间字段
- 已接入 JWT 认证与过滤逻辑，适合继续扩展权限控制
- 已具备统一异常处理和通用响应封装，方便后续维护
- 已新增操作日志审计能力，便于追踪关键接口调用

## 可继续扩展的方向

- 更细粒度的权限控制
- 用户注册与角色管理
- 更完整的文件上传与下载
- 前后端联调部署

## 适合谁使用

- 学习 Spring Boot 后端开发的同学
- 想要一个完整课程项目放到 GitHub 的同学
- 需要整理成简历项目或面试项目的人

## 说明

本项目主要用于学习、展示和二次开发，欢迎根据自己的业务需求继续完善。
