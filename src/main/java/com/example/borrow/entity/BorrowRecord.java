package com.example.borrow.entity;

import java.time.LocalDateTime;

/**
 * 借阅记录实体，对应 borrow_record 表。一条记录就是一次借阅。
 */
public class BorrowRecord {

    private Long id;                       // 主键，对应 borrow_record.id
    private Long userId;                   // 借书人，对应 user_id
    private Long bookId;                   // 借的哪本书，对应 book_id
    private LocalDateTime borrowDate;      // 借出时间，对应 borrow_date
    private LocalDateTime returnDate;      // 归还时间，对应 return_date；null 表示未还
    private Integer status;                // 状态，对应 status：0 借出，1 已还

    public Long getId() { return id; }                                  // 读主键
    public void setId(Long id) { this.id = id; }                        // 写主键
    public Long getUserId() { return userId; }                          // 读借书人
    public void setUserId(Long userId) { this.userId = userId; }        // 写借书人
    public Long getBookId() { return bookId; }                          // 读图书 id
    public void setBookId(Long bookId) { this.bookId = bookId; }        // 写图书 id
    public LocalDateTime getBorrowDate() { return borrowDate; }         // 读借出时间
    public void setBorrowDate(LocalDateTime borrowDate) { this.borrowDate = borrowDate; } // 写借出时间
    public LocalDateTime getReturnDate() { return returnDate; }         // 读归还时间
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; } // 写归还时间
    public Integer getStatus() { return status; }                       // 读状态
    public void setStatus(Integer status) { this.status = status; }     // 写状态
}
