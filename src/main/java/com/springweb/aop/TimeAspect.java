package com.springweb.aop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect //AOP类
public class TimeAspect {

    @Around("execution(* com.springweb.service.*.*(..))") //切入点表达式
    //@Around("com.itheima.aop.MyAspect1.pt()")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();//1. 记录开始时间
        Object result = joinPoint.proceed();//2. 调用原始方法运行
        long end = System.currentTimeMillis();//3. 记录结束时间,
        log.info(joinPoint.getSignature()+"方法执行耗时: {}ms", end-begin);//计算方法执行耗时
        return result;
    }
}
