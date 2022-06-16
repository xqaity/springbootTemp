package cn.xq.xqspringboot.controller;

import utils.PageUtil;
import utils.ResultUtil;
import component.vo.PageVo;
import component.vo.Result;
import cn.xq.xqspringboot.entity.SysRole;
import cn.xq.xqspringboot.service.ISysRoleService;
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
@Api(tags = "系统角色管理接口")
@RequestMapping("/xq/sysRole")
@Transactional
public class SysRoleController {

    @Autowired
    private ISysRoleService iSysRoleService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    public Result<SysRole> get(@PathVariable String id) {

        SysRole sysRole = iSysRoleService.getById(id);
        return new ResultUtil<SysRole>().setData(sysRole);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部数据")
    public Result<List<SysRole>> getAll() {

        List<SysRole> list = iSysRoleService.list();
        return new ResultUtil<List<SysRole>>().setData(list);
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取")
    public Result<IPage<SysRole>> getByPage(PageVo page) {

        IPage<SysRole> data = iSysRoleService.page(PageUtil.initMpPage(page));
        return new ResultUtil<IPage<SysRole>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "编辑或更新数据")
    public Result<SysRole> saveOrUpdate(SysRole sysRole) {

        if (iSysRoleService.saveOrUpdate(sysRole)) {
            return new ResultUtil<SysRole>().setData(sysRole);
        }
        return new ResultUtil<SysRole>().setErrorMsg("操作失败");
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delAllByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            iSysRoleService.removeById(id);
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }
}
