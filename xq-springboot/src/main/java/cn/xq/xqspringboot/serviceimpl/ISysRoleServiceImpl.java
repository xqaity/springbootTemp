package cn.xq.xqspringboot.serviceimpl;

import cn.xq.xqspringboot.mapper.SysRoleMapper;
import cn.xq.xqspringboot.entity.SysRole;
import cn.xq.xqspringboot.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统角色接口实现
 * @author xq
 */
@Slf4j
@Service
@Transactional
public class ISysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
}