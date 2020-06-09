package com.iwiller.sys.test;

import com.iwiller.sys.domain.SysUser;
import com.iwiller.sys.service.ISysUserService;
import com.iwiller.sys.vo.SysUserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LoginTest {
    @Autowired
    private ISysUserService sysUserService;

    @Test
    public void testLogin(){
        SysUserVo sysUserVo = new SysUserVo();
        sysUserVo.setLoginname("admin");
        sysUserVo.setPwd("123456");
        SysUser login = sysUserService.login(sysUserVo);
        System.out.println(login);
    }


}
