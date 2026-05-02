# tlias-web-management

一个基于 **Spring Boot + MyBatis + MySQL** 的部门管理示例项目，主要用于学习后端分层开发、REST 接口设计和数据库访问。

## 项目简介

该项目当前实现了一个简单的部门查询接口，用于从 MySQL 数据库中读取部门信息并以统一响应格式返回。

## 技术栈

- Java 21
- Spring Boot 4
- Spring Web MVC
- MyBatis
- MySQL
- Lombok

## 项目功能

- 查询所有部门列表
- 使用统一响应对象返回数据
- 采用 Controller / Service / Mapper 分层结构

## 项目结构

```text
tlias-web-management/
├── src/
│   ├── main/
│   │   ├── java/com/itluffy/
│   │   │   ├── controller/   # 控制层
│   │   │   ├── service/      # 业务层
│   │   │   ├── mapper/       # 数据访问层
│   │   │   └── pojo/         # 实体类与响应封装
│   │   └── resources/
│   │       └── application.yaml
│   └── test/
├── pom.xml
└── README.md
```

## 接口说明

### 查询所有部门

- **请求方式**：`GET`
- **请求地址**：`/depts`
- **返回结果**：部门列表

示例返回：

```json
{
  "code": 1,
  "msg": "success",
  "data": [
    {
      "id": 1,
      "name": "人事部",
      "createTime": "2026-05-02T10:00:00",
      "updateTime": "2026-05-02T10:00:00"
    }
  ]
}
```

## 本地运行环境

运行前请确保已安装并准备好：

- JDK 21
- Maven 3.9+
- MySQL 8+

## 数据库配置

项目默认连接本地 MySQL 数据库：

- 数据库名：`tlias`
- 地址：`localhost:3306`

请根据自己的环境修改 `src/main/resources/application.yaml` 中的数据库配置。

## 启动步骤

1. 创建数据库 `tlias`
2. 导入部门表及测试数据
3. 修改数据库连接配置
4. 运行 Spring Boot 启动类 `TliasWebManagementApplication`
5. 访问接口：

```bash
GET http://localhost:8080/depts
```

## 代码说明

- `DeptController`：接收 HTTP 请求并返回统一结果
- `DeptService` / `DeptServiceImpl`：处理业务逻辑
- `DeptMapper`：通过 MyBatis 查询数据库
- `Result`：统一响应封装
- `Dept`：部门实体类
