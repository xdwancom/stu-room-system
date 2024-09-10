package com.springweb.pojo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 学生实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stu {
    private Integer unit; //单元楼栋
    private Integer roomid; //宿舍号
    private Integer id; //学号
    private String name; //姓名
    private String gender; //性别
    private Integer age;//年龄
    private String department; //院系
    private Integer grade; //年级
    private String phone; //电话
    private String roommsg; //楼栋-宿舍号
}
