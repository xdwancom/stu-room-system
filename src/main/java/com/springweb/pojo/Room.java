package com.springweb.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 宿舍实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private Integer unit; //单元楼栋,单数男双数女
    private Integer roomid; //宿舍号
    private Integer floor; //楼层
    private Integer bedcount; //床位数
    private String money; //住宿费
    @TableId(value = "roommsg")
    private String roommsg; //楼栋-宿舍号
}
