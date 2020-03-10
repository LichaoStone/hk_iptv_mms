package com.haikan.iptv.common.frame.log;

import com.google.gson.Gson;
import com.haikan.iptv.bean.TsysUser;
import com.haikan.iptv.common.util.ServletUtils;
import com.haikan.iptv.common.util.StringUtils;
import com.haikan.iptv.config.shiro.util.ShiroUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * 操作日志记录处理
 * @author fuce 
 * @date: 2018年9月30日 下午1:40:33
 */
@Aspect
@Component
@EnableAsync
public class LogAspect
{
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);


    // 配置织入点
    @Pointcut("@annotation(com.haikan.iptv.common.frame.log.Log)")
    public void logPointCut()
    {
    }

    /**
     * 前置通知 用于拦截操作
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()")
    public void doBefore(JoinPoint joinPoint)
    {
        handleLog(joinPoint, null);
    }

    /**
     * 拦截异常操作
     * 
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e)
    {
        handleLog(joinPoint, e);
    }

    @Async
    protected void handleLog(final JoinPoint joinPoint, final Exception e)
    {
        try
        {
            // 获得注解
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null)
            {
                return;
            }

            // 获取当前的用户
            TsysUser currentUser = ShiroUtils.getUser();
            System.out.println("userName:****"+currentUser.getUsername());

            // *========数据库日志=========*//
            TsysOperLog operLog = new TsysOperLog();
           
            //赋值操作
            String ip = ShiroUtils.getIp();
            System.out.println("ip1************"+ip);
//            HttpServletRequest request = ServletUtils.getRequest();
//            System.out.println("ip2************"+IpUtil.getIpAddr(request));
            //请求的参数
            Object[] args = joinPoint.getArgs();
            //将参数所在的数组转换成json
            System.out.println("args*****"+args);
            //operLog.setOperIp(ip);*/
            // 操作地点
            //operLog.setOperLocation(AddressUtils.getRealAddressByIP(ip));
            // 请求的地址
            operLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
            if (currentUser != null)
            {
//            	//操作人
                operLog.setOperName(currentUser.getUsername());
            }

            if (e != null)
            {
            	//错误日志
            	operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");
            operLog.setOperTime(new Date());
            // 处理设置注解上的参数
            getControllerMethodDescription(controllerLog, operLog);
            // 保存数据库
            //System.out.println("-----------------");
            System.out.println(new Gson().toJson(operLog));
        }
        catch (Exception exp)
        {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * @param log
     * @param operLog
     * @throws Exception
     */
    public void getControllerMethodDescription(Log log, TsysOperLog operLog) throws Exception
    {
        // 设置action动作
       // operLog.setAction(log.action());
        // 设置标题
        operLog.setTitle(log.title());
        // 设置channel
        //operLog.setChannel(log.channel());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData())
        {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(operLog);
        }
    }

    /**
     * 获取请求的参数，放到log中
     * @param operLog
     */
    private void setRequestValue(TsysOperLog operLog)
    {
        Map<String, String[]> map = ServletUtils.getRequest().getParameterMap();
        Gson gson=new Gson();
        String params = gson.toJson(map);
        operLog.setOperParam(StringUtils.substring(params, 0, 255));
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(Log.class);
        }
        return null;
    }
}
