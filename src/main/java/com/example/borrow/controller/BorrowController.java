package com.example.borrow.controller;

import com.example.borrow.dto.BorrowRequest;
import com.example.borrow.dto.ReturnRequest;
import com.example.borrow.entity.BorrowRecord;
import com.example.borrow.service.BorrowService;
import com.example.borrow.common.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class BorrowController {

    private BorrowService borrowService;

    public BorrowController(BorrowService borrowService){
        this.borrowService = borrowService;
    }

    @PostMapping("/borrow")
    public BorrowRecord borrow(@RequestBody BorrowRequest req){
        return borrowService.borrow(req.getUserId(),req.getBookId());
    }

    @PutMapping("/return")
    public Map<String,Object> returnBook(@RequestBody ReturnRequest req) throws NotFoundException {
        borrowService.returnBook(req.getRecordId());
        return Map.of("ok",true);
    }
}





















