package cn.azzhu.myo2o.interceptor;

import cn.azzhu.myo2o.annotation.Access;
import cn.azzhu.myo2o.entity.LocalAuth;
import cn.azzhu.myo2o.entity.Menu;
import cn.azzhu.myo2o.service.MenuService;
import cn.azzhu.myo2o.utils.SysConstants;
import cn.azzhu.myo2o.vo.MenuVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author azzhu
 * @create 2019-09-11 15:53:15
 */
@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private MenuService menuService;

    @Autowired
    private StringRedisTemplate redisTemplate;
    // 在调用方法之前执行拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 将handler强转为HandlerMethod, 前面已经证实这个handler就是HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        // 获取出方法上的Access注解
        Access access = method.getAnnotation(Access.class);
        if (access == null) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        } else if(access.menus().length == 0){
            //向menus中添加权限菜单
            // 从参数中获取用户Id
            LocalAuth localAuth = (LocalAuth) request.getSession().getAttribute(SysConstants.SESSION_USER);
            Long userId = localAuth.getUserId();
            // 到数据库权限表中查询用户拥有的权限集合, 与set集合中的权限进行对比完成权限校验
            ObjectMapper mapper = new ObjectMapper();
            //应该先去缓存中取，若缓存中没有，再去数据库中取，取完之后要放入到缓存 menu:uid
            List<Menu> list = null;
            if(!redisTemplate.hasKey(SysConstants.MENU_KEY+localAuth.getUserId())) {
                //根据用户id查询
                list=this.menuService.queryMenuByUserIdForList(new MenuVo(), localAuth.getUserId());
                //放入到redis中
                try {
                    String menuStr = mapper.writeValueAsString(list);
                    redisTemplate.opsForValue().set(SysConstants.MENU_KEY+localAuth.getUserId(),menuStr);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            } else {
                //缓存中
                String menuVal = redisTemplate.opsForValue().get(SysConstants.MENU_KEY+localAuth.getUserId());
                try {
                    //解析json串
                    list = mapper.readValue(menuVal,new TypeReference<List<Menu>>() { });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            String[] menus = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                menus[i] = list.get(i).getTitle();
            }
        } else if (access.menus().length > 0) {
            // 如果权限配置不为空, 则取出配置值
            String[] authorities = access.menus();
            Set<String> authSet = new HashSet<>();
            for (String authority : authorities) {
                // 将权限加入一个set集合中
                authSet.add(authority);
            }

            String role = request.getParameter("role");
            if (!StringUtils.isEmpty(role)) {
                if (authSet.contains(role)) {
                    // 校验通过返回true, 否则拦截请求
                    return true;
                }
            }
        }
        // 拦截之后应该返回公共结果, 这里没做处理
        return false;
    }
}
