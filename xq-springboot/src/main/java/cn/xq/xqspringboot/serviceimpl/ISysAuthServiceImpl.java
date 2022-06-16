package cn.xq.xqspringboot.serviceimpl;

import cn.xq.xqspringboot.mapper.SysAuthMapper;
import cn.xq.xqspringboot.entity.SysAuth;
import cn.xq.xqspringboot.service.ISysAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统权限接口实现
 * @author xq
 */
@Slf4j
@Service
@Transactional
public class ISysAuthServiceImpl extends ServiceImpl<SysAuthMapper, SysAuth> implements ISysAuthService {

    @Autowired
    private SysAuthMapper sysAuthMapper;
}