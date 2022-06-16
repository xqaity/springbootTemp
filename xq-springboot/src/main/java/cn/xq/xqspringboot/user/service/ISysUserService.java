package cn.xq.xqspringboot.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.xq.xqspringboot.user.entity.SysUser;

/**
 * 系统用户接口
 * @author xq
 */
public interface ISysUserService extends IService<SysUser> {
	public SysUser getUserByUserName(String userName);
}