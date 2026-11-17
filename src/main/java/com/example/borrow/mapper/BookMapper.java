package com.example.borrow.mapper;

import com.example.borrow.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {

    /** 按书名关键字查，title 为空就查全部。 */
    List<Book> selectByTitle(@Param("title") String title);

    /** 按主键查一本，查不到返回 null。 */
    Book selectById(@Param("id") Long id);

    /** 新增，主键回填到入参对象的 id 上。 */
    int insert(Book book);

    /** 按主键更新书名、作者、库存。 */
    int update(Book book);

    /**
     * 库存减 1。返回 0 表示没扣到（库存已经是 0，或者书不存在）。
     * 判断和扣减在同一条 SQL 里完成，并发下不会超卖。
     */
    int decreaseStock(@Param("id") Long id);

    /** 还书时库存加 1。 */
    int increaseStock(@Param("id") Long id);
}
