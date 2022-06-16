package cn.xq.xqspringboot.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.xq.xqspringboot.user.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * 系统用户数据处理层
 * @author xq
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
	public SysUser getUserByUserName(@Param("userName") String userName);
}