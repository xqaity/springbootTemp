package cn.xq.xqspringboot.controller;

import utils.PageUtil;
import utils.ResultUtil;
import component.vo.PageVo;
import component.vo.Result;
import cn.xq.xqspringboot.entity.Teacher;
import cn.xq.xqspringboot.service.ITeacherService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xq
 */
@Slf4j
@RestController
@Api(tags = "测试生成学生管理接口")
@RequestMapping("/xq/teacher")
@Transactional
public class TeacherController {

    @Autowired
    private ITeacherService iTeacherService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    public Result<Teacher> get(@PathVariable String id) {

        Teacher teacher = iTeacherService.getById(id);
        return new ResultUtil<Teacher>().setData(teacher);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部数据")
    public Result<List<Teacher>> getAll() {

        List<Teacher> list = iTeacherService.list();
        return new ResultUtil<List<Teacher>>().setData(list);
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取")
    public Result<IPage<Teacher>> getByPage(PageVo page) {

        IPage<Teacher> data = iTeacherService.page(PageUtil.initMpPage(page));
        return new ResultUtil<IPage<Teacher>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "编辑或更新数据")
    public Result<Teacher> saveOrUpdate(Teacher teacher) {

        if (iTeacherService.saveOrUpdate(teacher)) {
            return new ResultUtil<Teacher>().setData(teacher);
        }
        return new ResultUtil<Teacher>().setErrorMsg("操作失败");
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delAllByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            iTeacherService.removeById(id);
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }
}
