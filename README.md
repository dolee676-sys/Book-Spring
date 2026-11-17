# 图书借阅管理系统

> 基于 Spring Boot 3.5.16 + MyBatis 3.0.5 + MySQL 8 的图书借阅后端

## 功能

- 图书：列表、按书名关键字搜索、查看单本、新增、修改
- 用户：列表、新增（学号唯一，重复返回 400）
- 借阅：借书扣库存、还书写回归还时间、查某本书当前被谁借走、查某个用户的借阅记录
- 借书用条件更新防超卖，还书用状态位防重复归还
- 统一的失败语义：不存在 404、参数或外键错误 400、库存不足或重复归还 409


## 技术栈
- JDK 21 及以上（pom 中编译目标为 21）
- Spring Boot 3.5.16（spring-boot-starter-web）
- MyBatis Spring Boot Starter 3.0.5，SQL 写在 XML 里，接口只声明方法
- MySQL 8（mysql-connector-j）

## 数据库

库名 `borrow_demo`，三张表：

- `book` —— 图书，`stock` 是库存，借出一本减一
- `user` —— 用户，`student_no` 有唯一索引（表名是保留字，SQL 里要写反引号）
- `borrow_record` —— 借阅记录，`status` 0 表示借出中、1 表示已归还，`user_id`/`book_id` 各有一个外键

## 启动

### 1. 建库并导入演示数据

```bash
mysql -uroot -p < sql/01-schema.sql
mysql -uroot -p < sql/02-data.sql
```

`01-schema.sql` 会先删表再建表，可以重复执行，但已有数据会被清空。

### 2. 提供数据库密码

密码不写在代码里，`application.yml` 读的是环境变量，默认为空：

```yaml
password: ${DB_PASSWORD:}
```

不提供这个变量应用照样能启动，但第一次查库会返回 500（连接池是懒加载的）。

### 3. 运行

```bash
DB_PASSWORD=你的密码 mvn spring-boot:run
```

或者在 IDEA 里运行 `BorrowApplication`，Run Configuration 的 Environment variables 里加 `DB_PASSWORD`。

### 4. 验证

默认端口 8080，浏览器打开 <http://localhost:8080/api/books>，返回 4 本书的 JSON 即成功。

## 接口

图书：

- `GET /api/books` —— 图书列表，可选参数 `title` 做关键字模糊查询
- `GET /api/books/{id}` —— 单本详情，不存在返回 404
- `POST /api/books` —— 新增，body：`{"title":"书名","author":"作者","stock":3}`
- `PUT /api/books/{id}` —— 整体更新书名、作者、库存
- `GET /api/books/{id}/borrowers` —— 这本书当前未归还的借阅人列表

用户：

- `GET /api/users` —— 用户列表
- `POST /api/users` —— 新增，body：`{"name":"张三","studentNo":"2021001"}`，学号重复返回 400
- `GET /api/users/{id}/borrows` —— 该用户的全部借阅记录（含书名、借出/归还时间）

借还：

- `POST /api/borrow` —— 借书，body：`{"userId":1,"bookId":2}`，成功返回借阅记录；库存为 0 返回 409
- `PUT /api/return` —— 还书，body：`{"recordId":1}`，成功返回 `{"ok":true}`；重复归还返回 409

状态码约定：200 成功、400 参数错误或用户/图书不存在、404 目标不存在、409 业务冲突、500 未预期错误。







