package com.iwiller.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.iwiller.sys.constants.SysConstants;
import com.iwiller.sys.domain.SysUser;
import com.iwiller.sys.service.ISysLogLoginService;
import com.iwiller.sys.service.ISysUserService;
import com.iwiller.sys.utils.WebUtils;
import com.iwiller.sys.vo.SysLogLoginVo;
import com.iwiller.sys.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * 用户登录控制器
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysLogLoginService sysLogLoginService;
    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin(){
        return "system/main/login";
    }

    /**
     * 登录方法
     * @param sysUserVo
     * @return
     */
    @RequestMapping("login")
    public String login(SysUserVo sysUserVo, Model model){
        String code = WebUtils.getHttpSession().getAttribute("code").toString();
        if(sysUserVo.getCode().equals(code)){
            SysUser sysUser = this.sysUserService.login(sysUserVo);
            if(sysUser != null){
                //放到session
                WebUtils.getHttpSession().setAttribute("user",sysUser);
                //记录登录日记 向sys_login_log插入数据
                SysLogLoginVo logLoginVo = new SysLogLoginVo();
                logLoginVo.setLogintime(new Date());
                logLoginVo.setLoginname(sysUser.getRealname()+"-"+sysUser.getLoginname());
                logLoginVo.setLoginip(WebUtils.getHttpServletRequest().getRemoteAddr());
                sysLogLoginService.addLogLogin(logLoginVo);
                return "system/main/index";
            }else{
                model.addAttribute("error", SysConstants.USER_LOGIN_ERROR_MSG);
                return "system/main/login";
            }
        }else {
            model.addAttribute("error",SysConstants.USER_LOGIN_CODE_ERROR_MSG);
            return "system/main/login";
        }
    }

    /**
     * 得到登录验证码
     * @param response
     * @param session
     */
    @RequestMapping("getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws Exception {
        //定义图形的验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116,36,5,10);
        session.setAttribute("code",lineCaptcha.getCode());
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(lineCaptcha.getImage(),"JPEG",outputStream);
    }


}
