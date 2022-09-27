package com.atguigu.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 动态代理
 * @author maomao
 * @create 2022-07-23 23:38
 */

public class ProxyFactory_mycopy {
    private Object target;

    public ProxyFactory_mycopy(Object target){
        this.target = target;
    }

    public Object getProxy(){
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /**
                 * proxy:代理对象
                 * method:代理对象需要实现的方法，即其中需要重写的方法
                 * args:method所对应方法的参数
                 */
                Object result = null;
                try {
                    result = null;
                    System.out.println("动态代理日志 " + method.getName() + ",参数：" + Arrays.toString(args));
                    result = method.invoke(target, args);
                    System.out.println("动态代理日志 " + method.getName() + ",结果：" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("[动态代理][日志] " + method.getName() + "，异常：" + e.getMessage());
                } finally {
                    System.out.println("[动态代理][日志] " + method.getName() + "，方法执行完毕");
                }

                return result;
            }
        };
        return Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    }

}
