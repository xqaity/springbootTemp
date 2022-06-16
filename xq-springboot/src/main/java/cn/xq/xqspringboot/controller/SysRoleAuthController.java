package cn.xq.xqspringboot.controller;

import utils.PageUtil;
import utils.ResultUtil;
import component.vo.PageVo;
import component.vo.Result;
import cn.xq.xqspringboot.entity.SysRoleAuth;
import cn.xq.xqspringboot.service.ISysRoleAuthService;
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
@Api(tags = "系统角色权限管理接口")
@RequestMapping("/xq/sysRoleAuth")
@Transactional
public class SysRoleAuthController {

    @Autowired
    private ISysRoleAuthService iSysRoleAuthService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    public Result<SysRoleAuth> get(@PathVariable String id) {

        SysRoleAuth sysRoleAuth = iSysRoleAuthService.getById(id);
        return new ResultUtil<SysRoleAuth>().setData(sysRoleAuth);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部数据")
    public Result<List<SysRoleAuth>> getAll() {

        List<SysRoleAuth> list = iSysRoleAuthService.list();
        return new ResultUtil<List<SysRoleAuth>>().setData(list);
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取")
    public Result<IPage<SysRoleAuth>> getByPage(PageVo page) {

        IPage<SysRoleAuth> data = iSysRoleAuthService.page(PageUtil.initMpPage(page));
        return new ResultUtil<IPage<SysRoleAuth>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "编辑或更新数据")
    public Result<SysRoleAuth> saveOrUpdate(SysRoleAuth sysRoleAuth) {

        if (iSysRoleAuthService.saveOrUpdate(sysRoleAuth)) {
            return new ResultUtil<SysRoleAuth>().setData(sysRoleAuth);
        }
        return new ResultUtil<SysRoleAuth>().setErrorMsg("操作失败");
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delAllByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            iSysRoleAuthService.removeById(id);
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }
}
