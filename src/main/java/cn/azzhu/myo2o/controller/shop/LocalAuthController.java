package cn.azzhu.myo2o.controller.shop;

import cn.azzhu.myo2o.entity.LocalAuth;
import cn.azzhu.myo2o.entity.LogInfo;
import cn.azzhu.myo2o.entity.PersonInfo;
import cn.azzhu.myo2o.service.LocalAuthService;
import cn.azzhu.myo2o.service.LogInfoService;
import cn.azzhu.myo2o.service.PersonInfoService;
import cn.azzhu.myo2o.utils.SysConstants;
import com.google.code.kaptcha.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @author azzhu
 * @create 2019-08-25 19:48:51
 */
@Controller
@RequestMapping("/shop")
@Slf4j
public class LocalAuthController {

    @Autowired
    private PersonInfoService personInfoService;

    @Autowired
    private LocalAuthService localAuthService;

    @Autowired
    private LogInfoService logInfoService;

    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping("/login.html")
    public String toLogin() {
        return "shop/login";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @GetMapping("/register.html")
    public String toRegister() {
        return "shop/register";
    }

    @GetMapping("/main.html")
    public String toMain() {
        return "common/main";
    }

    /**
     * 跳转到主页
     * @return
     */
    @GetMapping("index.html")
    public String toIndex() {
        return "shop/index";
    }

    /**
     * 注册用户
     * @param localAuth
     * @return
     */
    @PostMapping("regist")
    public String register(LocalAuth localAuth, Map<String,Object> map) {
        //1.向person_info中插入一条数据
        PersonInfo personInfo = new PersonInfo();
        personInfo.setCreateTime(new Date());
        personInfo.setShopOwnerFlag(1); //店主
        personInfo.setAdminFlag(0);

        int result = personInfoService.addPersonInfo(personInfo);

        if(result > 0) {
            //2.取person_info中的主键，设置LocalAuth的user_id
            Long userId = personInfo.getUserId();
            localAuth.setUserId(userId);

            //3.local_auth插入一条数据
            localAuth.setCreateTime(new Date());
            String password = localAuth.getPassword();
            //TODO  密码加密
            localAuth.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            localAuthService.addLocalAuth(localAuth);

            //4.注册成功---跳转到登录页面
            RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
            //该值其实是保存在 session 中的，并且会在下次重定向请求时删除
            redirectAttributes.addFlashAttribute("msg","注册成功.....");
            return "redirect:login.html";
        }

        //5.注册失败 --- 回到注册页面，给出错误提示
        map.put("msg","注册失败...");
        return "register.html";
    }
    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("code") String code,
                        Map<String,Object> map,
                        HttpSession session, HttpServletRequest request) {
        //1.根据用户名和密码查询用户
        //对密码进行加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LocalAuth loginUser = localAuthService.login(userName, password);

        //2.校验验证码
        //2.1 从Session中获取验证码
        //2.2 校验
        String sessCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(code.equalsIgnoreCase(sessCode)) {
            //校验用户是否找到
            if(loginUser != null) {
                //TODO 3.查询权限菜单 ---- 直接写死,后面加入权限菜单时再补充
                System.out.println(userName+"======="+password);
                //4.放入session
                session.setAttribute(SysConstants.SESSION_USER,loginUser);

                //5.根据user_id查找PersonInfo信息
                PersonInfo personInfo = personInfoService.getPersonInfoById(loginUser.getUserId());
                //放入到Session中,页面中需要显示当前登录人信息
                session.setAttribute(SysConstants.PERSON_INFO,personInfo);

                //TODO 记录登录日志
                // 记录登陆日志 向sys_login_log里面插入数据
                LogInfo logInfo = new LogInfo();
                logInfo.setLogintime(new Date());
                logInfo.setLoginname(loginUser.getUserName() + "-" + personInfo.getName());
                logInfo.setLoginip(request.getRemoteAddr());

                logInfoService.addLogInfo(logInfo);
                //6.登录成功跳转到index.html，失败回到login.html
                return "redirect:/shop/index.html";
            } else {
                //用户名或密码错误
                map.put("msg",SysConstants.USER_ERROR);
                return "shop/login.html";
            }

        } else {
            //验证码错误
            map.put("msg",SysConstants.CODE_ERROR);
            return "shop/login.html";
        }
    }

    /**
     * 退出
     * @param session
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute(SysConstants.SESSION_USER);
        return "redirect:login.html";
    }

    /**
     * 查看个人资料页（在登录的时候，将个人信息放入到了Session中，可能有所不妥）
     * @return
     */
    @GetMapping("user/personInfo.html")
    public String toPersonInfo() {
        return "shop/user/personInfo";
    }
}
