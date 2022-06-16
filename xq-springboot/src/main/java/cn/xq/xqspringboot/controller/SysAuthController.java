package cn.xq.xqspringboot.controller;

import utils.PageUtil;
import utils.ResultUtil;
import component.vo.PageVo;
import component.vo.Result;
import cn.xq.xqspringboot.entity.SysAuth;
import cn.xq.xqspringboot.service.ISysAuthService;
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
@Api(tags = "系统权限管理接口")
@RequestMapping("/xq/sysAuth")
@Transactional
public class SysAuthController {

    @Autowired
    private ISysAuthService iSysAuthService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    public Result<SysAuth> get(@PathVariable String id) {

        SysAuth sysAuth = iSysAuthService.getById(id);
        return new ResultUtil<SysAuth>().setData(sysAuth);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部数据")
    public Result<List<SysAuth>> getAll() {

        List<SysAuth> list = iSysAuthService.list();
        return new ResultUtil<List<SysAuth>>().setData(list);
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取")
    public Result<IPage<SysAuth>> getByPage(PageVo page) {

        IPage<SysAuth> data = iSysAuthService.page(PageUtil.initMpPage(page));
        return new ResultUtil<IPage<SysAuth>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "编辑或更新数据")
    public Result<SysAuth> saveOrUpdate(SysAuth sysAuth) {

        if (iSysAuthService.saveOrUpdate(sysAuth)) {
            return new ResultUtil<SysAuth>().setData(sysAuth);
        }
        return new ResultUtil<SysAuth>().setErrorMsg("操作失败");
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delAllByIds(@RequestParam String[] ids) {

        for (String id : ids) {
            iSysAuthService.removeById(id);
        }
        return ResultUtil.success("批量通过id删除数据成功");
    }
}
