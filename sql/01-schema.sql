-- ============================================================
-- 图书借阅管理系统 · 建表脚本
-- 执行方式：mysql -uroot -p < sql/01-schema.sql
-- 会先删表再建表，可以重复执行；已有数据会被清空，注意！
-- ============================================================

SET NAMES utf8mb4;

CREATE DATABASE IF NOT EXISTS borrow_demo
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_0900_ai_ci;

USE borrow_demo;

-- 先删子表（有外键的那张），再删父表，否则删不动
DROP TABLE IF EXISTS borrow_record;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS book;


-- ------------------------------------------------------------
-- 图书表
-- ------------------------------------------------------------
CREATE TABLE book (
    id     BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    title  VARCHAR(128) NOT NULL                COMMENT '书名',
    author VARCHAR(64)  NOT NULL                COMMENT '作者',
    stock  INT          NOT NULL DEFAULT 0      COMMENT '库存，借出一本减一',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '图书';


-- ------------------------------------------------------------
-- 用户表
-- user 是 MySQL 的保留字，表名叫 user 就必须写反引号
-- 学号加 NOT NULL + UNIQUE，是"同一个学号不能注册两次"的最后一道防线；
-- 应用层再判断一次只是为了让接口返回友好的 400，正确性还是靠数据库这条约束
-- ------------------------------------------------------------
CREATE TABLE `user` (
    id         BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    name       VARCHAR(32) NOT NULL                COMMENT '姓名',
    student_no VARCHAR(20) NOT NULL                COMMENT '学号',
    PRIMARY KEY (id),
    UNIQUE KEY uk_user_student_no (student_no)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户';


-- ------------------------------------------------------------
-- 借阅记录表
-- status：0 = 借出中，1 = 已归还
-- return_date 允许 NULL，没归还就没有归还时间
-- 两个外键指向 user / book；借书时 userId 不存在，插入会失败，接口靠这个报 400
-- ON DELETE RESTRICT：有借阅记录的图书不许直接删，避免留下孤儿数据
-- ------------------------------------------------------------
CREATE TABLE borrow_record (
    id          BIGINT   NOT NULL AUTO_INCREMENT COMMENT '主键',
    user_id     BIGINT   NOT NULL                COMMENT '借书人，外键指向 user.id',
    book_id     BIGINT   NOT NULL                COMMENT '所借图书，外键指向 book.id',
    borrow_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '借出时间',
    return_date DATETIME          DEFAULT NULL   COMMENT '归还时间，NULL 表示未还',
    status      TINYINT  NOT NULL DEFAULT 0      COMMENT '0 借出中 / 1 已归还',
    PRIMARY KEY (id),
    KEY idx_borrow_user (user_id),
    KEY idx_borrow_book (book_id),
    CONSTRAINT fk_borrow_user FOREIGN KEY (user_id) REFERENCES `user` (id) ON DELETE RESTRICT,
    CONSTRAINT fk_borrow_book FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE RESTRICT
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '借阅记录';
