package cn.xq.xqspringboot.serviceimpl;

import cn.xq.xqspringboot.mapper.SysRoleAuthMapper;
import cn.xq.xqspringboot.entity.SysRoleAuth;
import cn.xq.xqspringboot.service.ISysRoleAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统角色权限接口实现
 * @author xq
 */
@Slf4j
@Service
@Transactional
public class ISysRoleAuthServiceImpl extends ServiceImpl<SysRoleAuthMapper, SysRoleAuth> implements ISysRoleAuthService {

    @Autowired
    private SysRoleAuthMapper sysRoleAuthMapper;
}