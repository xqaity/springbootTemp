package cn.xq.xqspringboot.user.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import utils.PageUtil;
import utils.ResultUtil;
import component.vo.PageVo;
import component.vo.Result;
import cn.xq.xqspringboot.user.entity.SysUser;
import cn.xq.xqspringboot.user.service.ISysUserService;
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
@Api(tags = "系统用户管理接口")
@RequestMapping("/xq/sysUser")
@Transactional
public class SysUserController {

    @Autowired
    private ISysUserService iSysUserService;

    @RequestMapping(value = "/getByUserName/{userName}", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    public Result<SysUser> getByUserName(@PathVariable String userName) {

        SysUser sysUser = iSysUserService.getUserByUserName(userName);
        return new ResultUtil<SysUser>().setData(sysUser);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    public Result<SysUser> get(@PathVariable String id) {

        SysUser sysUser = iSysUserService.getById(id);
        return new ResultUtil<SysUser>().setData(sysUser);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部数据")
    public Result<List<SysUser>> getAll() {

        List<SysUser> list = iSysUserService.list();
        return new ResultUtil<List<SysUser>>().setData(list);
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取")
    public Result<IPage<SysUser>> getByPage(PageVo page) {

        IPage<SysUser> data = iSysUserService.page(PageUtil.initMpPage(page));
        return new ResultUtil<IPage<SysUser>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "编辑或更新数据")
    public Result<SysUser> saveOrUpdate(SysUser sysUser) {
        SysUser sysUser1 = new SysUser();
        sysUser1.setUserName("qiuguobin");
        sysUser1.setPassWord("123123");
        sysUser1.setPermission("view,edit");
        sysUser1.setRole("admin");
        if (iSysUserService.saveOrUpdate(sysUser1)) {
            return new ResultUtil<SysUser>().setData(sysUser1);
        }
        return new ResultUtil<SysUser>().setErrorMsg("操作失败");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "编辑或更新数据")
    public Result<SysUser> save(SysUser sysUser) {
        SysUser sysUser1 = new SysUser();
        sysUser1.setUserName("qiuguobin");
        sysUser1.setPassWord("123123");
        sysUser1.setPermission("view,edit");
        sysUser1.setRole("admin");
        if (iSysUserService.save(sysUser1)) {
            return new ResultUtil<SysUser>().setData(sysUser1);
        }
        return new ResultUtil<SysUser>().setErrorMsg("操作失败");
    }


    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delAllByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            iSysUserService.removeById(id);
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }
}
