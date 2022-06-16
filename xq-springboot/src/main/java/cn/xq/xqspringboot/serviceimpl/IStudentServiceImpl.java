package cn.xq.xqspringboot.serviceimpl;

import annotation.DataSourceSwitcher;
import cn.xq.xqspringboot.mapper.StudentMapper;
import cn.xq.xqspringboot.entity.Student;
import cn.xq.xqspringboot.service.IStudentService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import enumtype.DataSourceEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 测试生成学生接口实现
 * @author xq
 */
@Slf4j
@Service
@Transactional
public class IStudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @DS("slaver")
    @Override
    @DataSourceSwitcher(DataSourceEnum.SLAVE)
    public List<Student> getListStudent(){
        return studentMapper.getListStudent();
    }
}