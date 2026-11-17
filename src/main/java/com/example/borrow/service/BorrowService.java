package com.example.borrow.service;

import com.example.borrow.entity.BorrowRecord;
import com.example.borrow.mapper.BookMapper;
import com.example.borrow.mapper.BorrowRecordMapper;
import com.example.borrow.common.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BorrowService {

    private final BookMapper bookMapper;

    private final BorrowRecordMapper recordMapper;

    public BorrowService(BookMapper bookMapper, BorrowRecordMapper recordMapper) {  // 构造器注入
        this.bookMapper = bookMapper;                       // 存下图书 Mapper
        this.recordMapper = recordMapper;                   // 存下记录 Mapper
    }

    @Transactional(rollbackFor = Exception.class)
    public BorrowRecord borrow(Long userId,Long bookId) {
        int rows = bookMapper.decreaseStock(bookId);
        if (rows == 0) {
            throw new IllegalStateException("借书失败:库存不足或者不存在");

        }
        BorrowRecord record = new BorrowRecord();

        record.setUserId(userId);
        record.setBookId(bookId);

        recordMapper.insert(record);

        return recordMapper.selectById(record.getId());
    }
        @Transactional(rollbackFor = Exception.class)
        public void returnBook(Long recordId) throws NotFoundException {
            BorrowRecord record=recordMapper.selectById(recordId);
                    if(record == null){
                        throw new NotFoundException("借阅记录不存在");
            }
                    if(record.getStatus()!=null && record.getStatus()==1){
                        throw new IllegalStateException("已经还过了");
                    }
                    int rows = recordMapper.markReturned(recordId);
                    if(rows == 0){
                        throw new IllegalStateException("已经还过了");
                    }
                    bookMapper.increaseStock(record.getBookId());
        }
    }





































