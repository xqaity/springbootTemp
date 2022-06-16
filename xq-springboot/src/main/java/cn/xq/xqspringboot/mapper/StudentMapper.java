package cn.xq.xqspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.xq.xqspringboot.entity.Student;

import java.util.List;

/**
 * 测试生成学生数据处理层
 * @author xq
 */
public interface StudentMapper extends BaseMapper<Student> {

	public List<Student> getListStudent();
}