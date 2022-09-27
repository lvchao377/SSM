package com.atguigu.spring.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author maomao
 * @create 2022-07-24 10:03
 */
// @Aspect表示这个类是一个切面类
@Aspect
// @Component注解保证这个切面类能够放入IOC容器
@Component
@Order(2)
public class LoggerAspect {

    @Pointcut("execution(* com.atguigu.spring.aop.annotation.*.*(..))")
    public void pointCut(){}

    @Before("execution(* com.atguigu.spring.aop.annotation.CalculatorImpl.*(..))")
//    @Before("execution(public int com.atguigu.spring.aop.annotation.CalculatorImpl.add(int,int))")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println("Logger-->前置通知，方法名："+methodName+"，参数："+args);
    }
    @After("pointCut()")
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logger-->后置通知，方法名："+methodName +"执行完毕");
    }
    @AfterReturning(value = "pointCut()",returning = "result")
    public void afterReturningMethod(JoinPoint joinPoint, Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logger-->返回通知，方法名："+methodName+"，结果："+ result);
    }
    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Throwable ex){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logger-->异常通知，方法名："+methodName+"，异常："+ex);
    }
//    @Around("pointCut()")
//    public Object aroundMethod(ProceedingJoinPoint joinPoint){
//        String methodName = joinPoint.getSignature().getName();
//        String args = Arrays.toString(joinPoint.getArgs());
//        Object result = null;
//        try {
//            System.out.println("环绕通知-->目标对象方法执行之前");
//            //目标对象（连接点）方法的执行
//            result = joinPoint.proceed();
//            System.out.println("环绕通知-->目标对象方法返回值之后");
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//            System.out.println("环绕通知-->目标对象方法出现异常时");
//        } finally {
//            System.out.println("环绕通知-->目标对象方法执行完毕");
//        }
//        return result;
//    }
}
