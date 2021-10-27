package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author Zero
 */
@Component
@Aspect
public class LogAop {
    // request对象
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    // 开始时间
    private Date visitTime;
    // 访问的类
    private Class clazz;
    // 访问的方法
    private Method method;

    /**
     * 执行前
     * @param joinPoint
     */
    @Before("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        // 当前时间就是访问的时间
        visitTime = new Date();
        // 具体要访问的类
        clazz = joinPoint.getTarget().getClass();
        // 获取访问的方法的名称
        String methodName = joinPoint.getSignature().getName();
        // 获取访问的方法的参数
        Object[] args = joinPoint.getArgs();
        // 获取具体执行的方法的method对象
        if(args == null || args.length == 0){
            // 只能获取无参数的方法
            method = clazz.getMethod(methodName);
        }else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < classArgs.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            clazz.getMethod(methodName, classArgs);
        }
    }

    /**
     * 具体方法执行后
     * @param joinPoint
     * @throws NoSuchMethodException
     */
    @After("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint joinPoint) throws Exception {
        // 获取访问时长
        long time = new Date().getTime() - visitTime.getTime();
        String url = "";
        if(clazz != null && method != null && clazz != LogAop.class && clazz != SysLogController.class){
            // 1. 获取类上的@RequestMapping()
            RequestMapping classAnnotation  = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation != null){
                String[] classValue = classAnnotation.value();
                // 2. 获取方法上的@RequestMapping()
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation != null){
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                    // 获取ip地址
                    String ip = request.getRemoteAddr();
                    // 获取当前操作的用户
                    // 从上下文中获取当前登录的用户
                    SecurityContext context = SecurityContextHolder.getContext();
                    // 注意这个User类是spring security中的，不是自定义的
                    User user = (User)context.getAuthentication().getPrincipal();
                    String username = user.getUsername();
                    // 封装成SysLog对象
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setMethod("[类名] " + clazz.getName() + "[方法名 ]" + method.getName());
                    sysLogService.save(sysLog);
                }
            }
        }

    }

}
