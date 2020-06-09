package com.iwiller.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.iwiller.sys.constants.SysConstants;
import com.iwiller.sys.domain.SysRole;
import com.iwiller.sys.domain.SysUser;
import com.iwiller.sys.mapper.SysRoleMapper;
import com.iwiller.sys.mapper.SysUserMapper;
import com.iwiller.sys.service.ISysUserService;
import com.iwiller.sys.utils.DataGridView;
import com.iwiller.sys.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Override
    public SysUser login(SysUserVo sysUserVo) {
        //明文
        //生成密文
        String pwd = DigestUtils.md5DigestAsHex(sysUserVo.getPwd().getBytes());
        sysUserVo.setPwd(pwd);
        System.out.println("SysUserServiceImpl.login");
        return sysUserMapper.login(sysUserVo);
    }

    @Override
    public DataGridView queryAllUser(SysUserVo sysUserVo) {
        Page<Object> page = PageHelper.startPage(sysUserVo.getPage(),sysUserVo.getLimit());
        List<SysUser> data = this.sysUserMapper.queryAllUser(sysUserVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addUser(SysUserVo sysUserVo) {
        //设置默认密码
        sysUserVo.setPwd(DigestUtils.md5DigestAsHex(SysConstants.USER_DEFAULT_PWD.getBytes()));
        //设置用户类型 都是普通用户类型type=2
        sysUserVo.setType(SysConstants.USER_TYPE_NORMAL);
        this.sysUserMapper.insertSelective(sysUserVo);
    }

    @Override
    public void updateUser(SysUserVo sysUserVo) {
        this.sysUserMapper.updateByPrimaryKeySelective(sysUserVo);
    }

    @Override
    public void deleteUser(Integer userid) {
        //删除用户
        this.sysUserMapper.deleteByPrimaryKey(userid);
        //根据用户id删除sys_role_user里面的数据
        this.sysRoleMapper.deleteRoleUserByUid(userid);
    }

    @Override
    public void deleteBatchUser(Integer[] ids) {
        for (Integer uid :
                ids) {
            this.deleteUser(uid);
        }
    }

    @Override
    public void resetUserPwd(Integer userid) {
        SysUser sysUser = new SysUser();
        sysUser.setUserid(userid);
        sysUser.setPwd(DigestUtils.md5DigestAsHex(SysConstants.USER_DEFAULT_PWD.getBytes()));
        this.sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public DataGridView queryUserRole(Integer userid) {
        //1.查询所有可用的角色
        SysRole sysRole = new SysRole();
        sysRole.setAvailable(SysConstants.AVAILABLE_TRUE);
        List<SysRole> allRole = this.sysRoleMapper.queryAllRole(sysRole);
        //2.根据用户ID查询已拥有的角色
        List<SysRole> userRole = this.sysRoleMapper.queryRoleByUid(SysConstants.AVAILABLE_TRUE,userid);

        List<Map<String,Object>> data = new ArrayList<>();
        for (SysRole r1 :
                allRole) {
            Boolean LAY_CHECKED = false;
            for (SysRole r2 :
                    userRole) {
                if(r1.getRoleid()==r2.getRoleid()){
                    LAY_CHECKED = true;
                }
            }
            Map<String,Object> map = new HashMap<>();
            map.put("roleid",r1.getRoleid());
            map.put("rolename",r1.getRolename());
            map.put("roledesc",r1.getRoledesc());
            map.put("LAY_CHECKED",LAY_CHECKED);
            data.add(map);
        }

        return new DataGridView(data);
    }

    @Override
    public void saveUserRole(SysUserVo sysUserVo) {
        Integer userid = sysUserVo.getUserid();
        Integer[] roleIds = sysUserVo.getIds();
        //根据用户id删除sys_role_user里面的数据
        this.sysRoleMapper.deleteRoleUserByUid(userid);
        //保持关系
        if(roleIds!=null&&roleIds.length>0){
            for (Integer rid:
                 roleIds) {
                this.sysUserMapper.insertUserRole(userid,rid);
            }
        }
    }


}
