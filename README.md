## 项目简介

`tlias-web-management` 是一个课程/练手型 Web 后端项目，主要用于学习和实践：

- Spring Boot Web 接口开发
- MyBatis 数据持久化
- MySQL 数据库交互
- RESTful 风格接口设计
- 统一返回结果封装
- 分层架构设计（Controller / Service / Mapper / POJO）

## 已完成内容

### 部门管理

已完成部门相关的基础 CRUD 接口：

- 查询所有部门
- 根据 ID 查询部门详情
- 新增部门
- 修改部门信息
- 删除部门

### 基础能力

- 统一返回结果类 `Result`
- 日志输出 `Slf4j`
- MyBatis 驼峰命名映射配置
- 新增/修改时自动补全 `createTime`、`updateTime`
- 项目启动类与分层结构已搭建完成

### 员工模块

- 已创建员工管理相关 Controller、Service、Mapper、POJO 等基础文件
- 后续可继续补充员工列表、分页查询、删除、编辑等接口

## 技术栈

- Java 21
- Spring Boot 4
- Spring Web MVC
- MyBatis 4
- MySQL
- Lombok

## 项目结构

```text
tlias-web-management
├─ src/main/java/com/itluffy
│  ├─ controller        # 控制层
│  ├─ service           # 业务层
│  ├─ service/impl      # 业务实现
│  ├─ mapper            # MyBatis Mapper
│  ├─ pojo              # 实体类 / 结果封装类
│  └─ TliasWebManagementApplication.java
├─ src/main/resources
│  └─ application.yaml  # 项目配置
└─ pom.xml
```

## 运行环境

- JDK 21
- Maven 3.9+
- MySQL 8+
- IDEA / VS Code 任意一种开发工具

## 快速开始

### 1. 准备数据库

请先创建名为 `tlias` 的数据库，并导入对应的表结构与测试数据。

> 注意：仓库中的数据库连接信息仅适用于你本地环境，请根据实际情况修改 `src/main/resources/application.yaml`。

### 2. 修改数据库配置

在 `application.yaml` 中配置数据库连接信息：

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

或者直接运行启动类：

```text
com.itluffy.TliasWebManagementApplication
```

## 部门接口说明

统一前缀：`/depts`

### 1. 查询所有部门

- `GET /depts`

### 2. 根据 ID 查询部门

- `GET /depts/{id}`

### 3. 新增部门

- `POST /depts`
- 请求体示例：

```json
{
  "name": "人事部"
}
```

### 4. 修改部门

- `PUT /depts`
- 请求体示例：

```json
{
  "id": 1,
  "name": "市场部"
}
```

### 5. 删除部门

- `DELETE /depts?id=1`

## 接口返回格式

项目统一使用 `Result` 返回：

```json
{
  "code": 1,
  "msg": "success",
  "data": []
}
```

- `code = 1` 表示成功
- `code = 0` 表示失败
- `data` 为返回数据
- `msg` 为提示信息

## 开发说明

- 新增部门时会自动补全 `createTime` 和 `updateTime`
- 修改部门时会自动更新 `updateTime`
- 数据库字段建议使用下划线命名，实体类使用驼峰命名
- 项目当前已具备基础后端骨架，可继续扩展员工管理模块

## 后续可扩展功能

- 员工列表分页查询
- 员工新增 / 编辑 / 删除
- 条件检索
- 统一异常处理
- 登录认证
- 文件上传

