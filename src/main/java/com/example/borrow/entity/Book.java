package com.example.borrow.entity;

/**
 * 图书实体，对应 book 表。字段和列一一对应，只负责装数据，不写业务逻辑。
 */
public class Book {

    private Long id;         // 主键，对应 book.id
    private String title;    // 书名，对应 book.title
    private String author;   // 作者，对应 book.author
    private Integer stock;   // 库存，对应 book.stock

    public Long getId() { return id; }                    // 读主键
    public void setId(Long id) { this.id = id; }           // 写主键
    public String getTitle() { return title; }             // 读书名
    public void setTitle(String title) { this.title = title; }  // 写书名
    public String getAuthor() { return author; }           // 读作者
    public void setAuthor(String author) { this.author = author; } // 写作者
    public Integer getStock() { return stock; }            // 读库存
    public void setStock(Integer stock) { this.stock = stock; }    // 写库存
}
