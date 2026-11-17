package com.example.borrow.mapper;

import com.example.borrow.entity.BorrowRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 借阅记录 Mapper。
 */
public interface BorrowRecordMapper {

    /** 插入一条借阅记录（借出），主键回填。 */
    int insert(BorrowRecord record);

    /** 按记录 id 查一条。 */
    BorrowRecord selectById(@Param("id") Long id);

    /**
     * 某用户的全部借阅记录，连同书名一起查出来。
     * 返回 Map 是因为结果里有 bookTitle 这种"实体里没有的字段"，
     * 用 Map 接最省事；正式项目更推荐建一个专门的对象。
     */
    List<Map<String, Object>> selectByUserId(@Param("userId") Long userId);

    /** 某本书当前未还被谁借走，返回 Map（记录 id、用户 id、姓名、借出时间）。 */
    List<Map<String, Object>> selectActiveByBookId(@Param("bookId") Long bookId);

    /**
     * 还书：把状态改成已还并写入归还时间。
     * WHERE 里带 status = 0，重复还书时第二次影响行数为 0，用它来防重复。
     */
    int markReturned(@Param("id") Long id);
}
