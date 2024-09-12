package com.springweb.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.springweb.pojo.Result;
import com.springweb.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override //目标资源方法运行前运行, 返回true: 放行, 返回false, 不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        String url = req.getRequestURL().toString();
        log.info("请求的url:{}",url);//获取请求url，输出日志
        log.info("请求token:{}",req.getHeader("token"));
        String jwt = req.getHeader("token");//获取请求头中的token值（令牌）
        if (true){return true;}
        //2.判断请求url中是否包含login，如果包含，说明是登录操作，放行。
//        if(url.contains("login")){
//            log.info("登录操作, 放行...");
//            return true;
//        }

        //判断令牌是否存在，如果不存在，返回错误结果（未登录）并拒绝放行
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空,返回未登录错误信息");
            //未使用RestController请求处理类，所以使用阿里巴巴fastJSON手动将Result对象转换为json格式返回
            String notLogin = JSONObject.toJSONString(Result.error("NOT_LOGIN"));
            resp.getWriter().write(notLogin);//响应返回错误信息
            return false;
        }

        //解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {//jwt解析失败
            e.printStackTrace();
            log.info("解析令牌失败, 返回未登录错误信息");
            //未使用RestController请求处理类，所以使用阿里巴巴fastJSON手动将Result对象转换为json格式返回
            String notLogin = JSONObject.toJSONString(Result.error("NOT_LOGIN"));
            resp.getWriter().write(notLogin);
            return false;
        }

        //放行
        log.info("令牌合法, 放行");
        return true;
    }

    @Override //目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("目标资源方法运行后运行...");
    }

    @Override //视图渲染完毕后运行, 最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("视图渲染完毕后运行, 最后运行...");
    }
}
