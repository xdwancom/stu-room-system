package com.springweb.controller;

import com.springweb.annotation.Log;
import com.springweb.pojo.Result;
import com.springweb.pojo.Stu;
import com.springweb.service.stuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 学生管理Controller
 */
@Slf4j
@Log
@CrossOrigin(origins = {"*", "null"})
@RequestMapping("/stu")//处理指定请求
@RestController//请求处理类,自动将方法返回值转换为json响应返回
public class stuController {
    @Autowired//ioc容器管理，依赖注入
    private stuService stuService;

    /**
     * 根据id批量删除学生
     */
    @DeleteMapping("/ids/{ids}")
    public Result deletebyid(@PathVariable List<Integer> ids){
        return stuService.removeByIds(ids)?Result.success():Result.error("删除失败");
    }

    /**
     *id已存在则更新，无则新增学生，post请求，body请求体，json格式
     */
    @PostMapping
    public Result add_or_update(@RequestBody Stu stu){
        return stuService.add_or_update(stu);
    }

    /**
     * 条件查询，post请求，body请求体，json格式，返回新表
     */
    @PostMapping("/search")
    public Result search(@RequestBody Stu stu){
        log.info("查询学生: {}" , stu);
        return Result.success(stuService.search(stu));
    }

    /**
     * 按照年级/院系优先分配宿舍
     */
    @GetMapping("/assign/{method}")
    public Result grade_assign(@PathVariable String method){
        return stuService.assign(method);
    }

    @PostMapping("/upload")
    public Result upload(MultipartFile file)throws Exception {
        log.info("文件上传: {}", file);
        log.info("原文件名: {}", file.getOriginalFilename());
        return Result.success(stuService.upload(file));
    }
}
