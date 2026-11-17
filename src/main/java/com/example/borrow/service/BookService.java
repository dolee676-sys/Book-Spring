package com.example.borrow.service;

import com.example.borrow.entity.Book;
import com.example.borrow.mapper.BookMapper;
import com.example.borrow.mapper.BorrowRecordMapper;
import com.example.borrow.common.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final BookMapper bookMapper;
    private final BorrowRecordMapper recordMapper;
    public BookService(BookMapper bookMapper,BorrowRecordMapper recordMapper ){
        this.bookMapper = bookMapper;
        this.recordMapper = recordMapper;
    }

    public List<Book> list(String title){
        return bookMapper.selectByTitle(title);
    }

    public Book getById(Long id) throws NotFoundException {
        Book book = bookMapper.selectById(id);
        if(book == null){
            throw new NotFoundException("图书不存在，id=" + id);
        }
        return book;

    }

    public Book create(Book book){
        bookMapper.insert(book);
        return bookMapper.selectById(book.getId());
    }

    public Book update(Long id,Book book) throws NotFoundException {
        getById(id);
        book.setId(id);
        int rows = bookMapper.update(book);
        if (rows == 0) {
            throw new IllegalStateException("更新失败，图书不存在");

        }
        return bookMapper.selectById(id);
    }
        public List<Map<String,Object>> borrowers(Long bookId) throws NotFoundException {
            getById(bookId);
            return recordMapper.selectActiveByBookId(bookId);
        }




}


















