package com.springweb.controller;

import com.springweb.annotation.Log;
import com.springweb.pojo.Result;
import com.springweb.pojo.Room;
import com.springweb.service.roomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 宿舍管理Controller
 */
@Slf4j
@Log
@CrossOrigin(origins = {"*", "null"})
@RequestMapping("/room")
@RestController()//请求处理类,自动将方法返回值转换为json响应返回
public class roomController {

    @Autowired//ioc容器管理，依赖注入
    private roomService roomService;

    /**
     * 批量删除宿舍
     */
    @DeleteMapping("/ids/{ids}")
    public Result deleteid(@PathVariable List<String> ids){
        log.info("根据roommsg批量删除宿舍:{}",ids);
        //调用service批量删除宿舍
        roomService.deleteid(ids);
        return Result.success();
    }

    /**
     * 新增宿舍，post请求，body请求体，json格式
     */
    @PostMapping
    public Result add(@RequestBody Room room){
        log.info("新增宿舍: {}" , room);
        //调用service新增宿舍
        return roomService.add(room);
    }

    /**
     * 条件查询，post请求，body请求体，json格式，返回新表
     */
    @PostMapping("/search")
    public Result search(@RequestBody Room room){
        log.info("查询宿舍: {}" , room);
        //调用service查询宿舍
        return Result.success(roomService.search(room));
    }

    /**
     * 更新，post请求，body请求体，json格式
     */
    @PostMapping("/update")
    public Result update(@RequestBody Room room){
        log.info("更新宿舍: {}" , room);
        //调用service更新宿舍
        roomService.update(room);
        return Result.success();
    }
}
