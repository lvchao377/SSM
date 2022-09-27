package com.atguigu.proxy;

import com.atguigu.spring.proxy.Calculator;
import com.atguigu.spring.proxy.CalculatorImpl;
import com.atguigu.spring.proxy.CalculatorStaticProxy;
import com.atguigu.spring.proxy.ProxyFactory_mycopy;
import org.junit.Test;

/**
 * @author maomao
 * @create 2022-07-23 23:08
 */
public class ProxyTest {

    @Test
    public void testProxy(){
        CalculatorStaticProxy proxy = new CalculatorStaticProxy(new CalculatorImpl());
        proxy.add(1,3);
        proxy.sub(1,1);

    }
    @Test
    public void testDynamicProxy(){
//        ProxyFactory factory = new ProxyFactory(new CalculatorImpl());
//        Calculator proxy = (Calculator) factory.getProxy();
//        proxy.div(10,2);
        //proxy.div(1,1);

        ProxyFactory_mycopy fa = new ProxyFactory_mycopy(new CalculatorImpl());
        Calculator proxy2 = (Calculator) fa.getProxy();
        proxy2.div(1, 1);
        
    }
}
