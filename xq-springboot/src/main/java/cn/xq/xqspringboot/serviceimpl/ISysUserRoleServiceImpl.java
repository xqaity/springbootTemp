package cn.xq.xqspringboot.serviceimpl;

import cn.xq.xqspringboot.mapper.SysUserRoleMapper;
import cn.xq.xqspringboot.entity.SysUserRole;
import cn.xq.xqspringboot.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统用户角色接口实现
 * @author xq
 */
@Slf4j
@Service
@Transactional
public class ISysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
}