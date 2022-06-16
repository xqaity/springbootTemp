package cn.xq.xqspringboot.controller;

import annotation.DataSourceSwitcher;
import annotation.RepeatSubmit;
import enumtype.DataSourceEnum;
import org.hibernate.annotations.Where;
import org.springframework.validation.BindingResult;
import utils.PageUtil;
import utils.ResultUtil;
import component.vo.PageVo;
import component.vo.Result;
import cn.xq.xqspringboot.entity.Student;
import cn.xq.xqspringboot.service.IStudentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

/**
 * @author xq
 */
@Slf4j
@RestController
@Api(tags = "测试生成学生管理接口")
@RequestMapping("/xq/student")
@Transactional
@RepeatSubmit
public class StudentController {

    @Autowired
    private IStudentService iStudentService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    public Result<Student> get(@Valid @PathVariable String id, BindingResult bindingResult) {
        Student student = iStudentService.getById(id);
        return new ResultUtil<Student>().setData(student);
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    public Result<Student> save() {
        Student studentSave = new Student();
        studentSave.setDelFlag(1);
        iStudentService.save(studentSave);
        return new ResultUtil<Student>().setData(studentSave);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部数据")

    public Result<List<Student>> getAll() {
        List<Student> list = iStudentService.getListStudent();
        return new ResultUtil<List<Student>>().setData(list);
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取")
    public Result<IPage<Student>> getByPage(PageVo page) {

        IPage<Student> data = iStudentService.page(PageUtil.initMpPage(page));
        return new ResultUtil<IPage<Student>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "编辑或更新数据")
    public Result<Student> saveOrUpdate(Student student) {

        if (iStudentService.saveOrUpdate(student)) {
            return new ResultUtil<Student>().setData(student);
        }
        return new ResultUtil<Student>().setErrorMsg("操作失败");
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delAllByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            iStudentService.removeById(id);
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }
}
