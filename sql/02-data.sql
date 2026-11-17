-- ============================================================
-- 图书借阅管理系统 · 演示数据
-- 执行方式：mysql -uroot -p < sql/02-data.sql
-- 依赖 01-schema.sql 已经建好库和表
-- 姓名和学号全部是编造的，不要换成真实名单再公开仓库
-- ============================================================

SET NAMES utf8mb4;

USE borrow_demo;

-- 主键写死，方便下面那条借阅记录直接引用
INSERT INTO book (id, title, author, stock) VALUES
    (1, 'Java 核心技术', 'Cay Horstmann', 5),
    (2, '深入理解 Java 虚拟机', '周志明', 2),
    (3, 'Spring Boot 实战', 'Craig Walls', 1),
    (4, '重构：改善既有代码的设计', 'Martin Fowler', 0);

INSERT INTO `user` (id, name, student_no) VALUES
    (1, '张三', '2021001'),
    (2, '李四', '2021002'),
    (3, '王五', '2021003');

-- 一条进行中的借阅：张三借了《深入理解 Java 虚拟机》，还没还
-- 注意 stock 已经是扣过的值（2），这里不要再减
INSERT INTO borrow_record (id, user_id, book_id, borrow_date, return_date, status) VALUES
    (1, 1, 2, '2026-11-15 20:17:57', NULL, 0);
