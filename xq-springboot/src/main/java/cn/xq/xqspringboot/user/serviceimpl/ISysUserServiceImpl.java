package cn.xq.xqspringboot.user.serviceimpl;

import cn.xq.xqspringboot.user.mapper.SysUserMapper;
import cn.xq.xqspringboot.user.entity.SysUser;
import cn.xq.xqspringboot.user.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统用户接口实现
 * @author xq
 */
@Slf4j
@Service
@Transactional
public class ISysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserByUserName(String userName) {
        return sysUserMapper.getUserByUserName(userName);
    }
}