package com.springweb.service;
import com.springweb.pojo.Result;
import com.springweb.pojo.Stu;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

/**
 * 学生管理
 */
public interface stuService {
    /**
     * 批量删除学生
     * @param ids
     */
    void deleteid(List<Integer> ids);

    /**
     * 新增学生
     * @param stu
     */
    Result add(Stu stu);

    /**
     * 条件查询学生
     * @param stu
     */
    List<Stu> search(Stu stu);

    /**
     * 更新学生
     */
    Result update(Stu stu);

    /**
     * 按照年级/院系优先分配宿舍
     */
    Result assign(String method);

    Result upload(MultipartFile file) throws IOException;
}
