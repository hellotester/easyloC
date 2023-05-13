package org.luka.framework.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class BeanDefinition {
    // 全局唯一的Bean Name:
    String name;

    // Bean的声明类型:
    Class<?> beanClass;

    // Bean的实例:
    Object instance = null;

    // 构造方法/null:
    Constructor<?> constructor;

    // 工厂方法名称/null:
    String factoryName;

    // 工厂方法/null:
    Method factoryMethod;

    // Bean的顺序:
    int order;

    // 是否标识@Primary:
    boolean primary;

    // init/destroy方法名称:
    String initMethodName;
    String destroyMethodName;

    // init/destroy方法:
    Method initMethod;
    Method destroyMethod;
}
