package cn.xq.xqspringboot.service;

import annotation.DataSourceSwitcher;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.xq.xqspringboot.entity.Student;
import enumtype.DataSourceEnum;

import java.util.List;

/**
 * 测试生成学生接口
 * @author xq
 */
public interface IStudentService extends IService<Student> {

	public List<Student> getListStudent();
}