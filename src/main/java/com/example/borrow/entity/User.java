package com.example.borrow.entity;

/**
 * 用户实体，对应 user 表。
 * 注意表名是保留字，但类名照常写 User，反引号只出现在 SQL 里。
 */
public class User {

    private Long id;              // 主键，对应 user.id
    private String name;          // 姓名，对应 user.name
    private String studentNo;     // 学号，对应 user.student_no（驼峰转换后是 studentNo）

    public Long getId() { return id; }                        // 读主键
    public void setId(Long id) { this.id = id; }              // 写主键
    public String getName() { return name; }                  // 读姓名
    public void setName(String name) { this.name = name; }    // 写姓名
    public String getStudentNo() { return studentNo; }        // 读学号
    public void setStudentNo(String studentNo) { this.studentNo = studentNo; } // 写学号
}
