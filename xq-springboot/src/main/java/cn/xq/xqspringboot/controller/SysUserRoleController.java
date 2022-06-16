package cn.xq.xqspringboot.controller;

import utils.PageUtil;
import utils.ResultUtil;
import component.vo.PageVo;
import component.vo.Result;
import cn.xq.xqspringboot.entity.SysUserRole;
import cn.xq.xqspringboot.service.ISysUserRoleService;
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
@Api(tags = "系统用户角色管理接口")
@RequestMapping("/xq/sysUserRole")
@Transactional
public class SysUserRoleController {

    @Autowired
    private ISysUserRoleService iSysUserRoleService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    public Result<SysUserRole> get(@PathVariable String id) {

        SysUserRole sysUserRole = iSysUserRoleService.getById(id);
        return new ResultUtil<SysUserRole>().setData(sysUserRole);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部数据")
    public Result<List<SysUserRole>> getAll() {

        List<SysUserRole> list = iSysUserRoleService.list();
        return new ResultUtil<List<SysUserRole>>().setData(list);
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取")
    public Result<IPage<SysUserRole>> getByPage(PageVo page) {

        IPage<SysUserRole> data = iSysUserRoleService.page(PageUtil.initMpPage(page));
        return new ResultUtil<IPage<SysUserRole>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "编辑或更新数据")
    public Result<SysUserRole> saveOrUpdate(SysUserRole sysUserRole) {

        if (iSysUserRoleService.saveOrUpdate(sysUserRole)) {
            return new ResultUtil<SysUserRole>().setData(sysUserRole);
        }
        return new ResultUtil<SysUserRole>().setErrorMsg("操作失败");
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delAllByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            iSysUserRoleService.removeById(id);
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }
}
