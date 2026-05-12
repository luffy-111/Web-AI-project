package com.itluffy.aop;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;
import com.itluffy.mapper.OperateLogMapper;
import com.itluffy.pojo.OperateLog;
import com.itluffy.pojo.Result;
import com.itluffy.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    /**
     * 操作日志持久化 Mapper。
     */
    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 用于将方法参数、返回值序列化为 JSON 字符串。
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 拦截标注了 {@link com.itluffy.anno.Log} 注解的方法，记录其执行过程中的操作日志。
     * 日志内容包含：操作人、操作时间、目标类名、方法名、方法参数、返回值以及方法执行耗时。
     */
    @Around("@annotation(com.itluffy.anno.Log)")
    public Object recordOperateLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录方法开始执行的时间，用于计算耗时。
        long begin = System.currentTimeMillis();
        Object result = null;
        Throwable throwable = null;

        try {
            // 执行目标方法，并获取返回值。
            result = joinPoint.proceed();
            return result;
        } catch (Throwable ex) {
            // 如果目标方法抛出异常，先保存异常对象，最后继续抛出。
            throwable = ex;
            throw ex;
        } finally {
            // 无论方法正常结束还是异常结束，都记录日志。
            long costTime = System.currentTimeMillis() - begin;
            String methodName = joinPoint.getSignature().getName();
            String className = joinPoint.getTarget().getClass().getName();
            String methodParams = toJson(joinPoint.getArgs());
            String returnValue = throwable == null ? toJson(result) : toJson(Result.error(throwable.getMessage()));

            // 封装操作日志对象。
            OperateLog operateLog = new OperateLog();
            operateLog.setOperateEmpId(getOperateEmpId());
            operateLog.setOperateTime(LocalDateTime.now());
            operateLog.setClassName(className);
            operateLog.setMethodName(methodName);
            operateLog.setMethodParams(methodParams);
            operateLog.setReturnValue(returnValue);
            operateLog.setCostTime(costTime);

            // 日志保存失败不影响业务主流程。
            try {
                operateLogMapper.addOperateLog(operateLog);
            } catch (Exception e) {
                log.error("保存操作日志失败", e);
            }
        }
    }

    /**
     * 从当前请求头中获取 token，并解析出操作人 ID。
     * 如果当前线程没有 HTTP 请求上下文或 token 无效，则返回 null。
     */
    private Integer getOperateEmpId() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return null;
        }
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");
        if (token == null || token.isBlank()) {
            return null;
        }
        try {
            Long id = JwtUtils.getId(token);
            return id == null ? null : id.intValue();
        } catch (Exception e) {
            log.warn("解析token获取操作人失败", e);
            return null;
        }
    }

    /**
     * 将对象转换为 JSON 字符串；如果序列化失败，则退回为对象的字符串表示。
     */
    private String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JacksonException e) {
            return String.valueOf(obj);
        }
    }
}
