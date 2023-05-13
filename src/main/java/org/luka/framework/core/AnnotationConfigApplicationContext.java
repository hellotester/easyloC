package org.luka.framework.core;

import org.luka.framework.contract.ConfigurableApplicationContext;

import java.util.List;

public class AnnotationConfigApplicationContext implements ConfigurableApplicationContext {
    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public <T> T getBean(String name) {
        return null;
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> requiredType) {
        return null;
    }

    @Override
    public <T> List<T> getBeans(Class<T> requiredType) {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public List<BeanDefinition> findBeanDefinitions(Class<?> type) {
        return null;
    }

    @Override
    public BeanDefinition findBeanDefinition(Class<?> type) {
        return null;
    }

    @Override
    public BeanDefinition findBeanDefinition(String name) {
        return null;
    }

    @Override
    public BeanDefinition findBeanDefinition(String name, Class<?> requiredType) {
        return null;
    }

    @Override
    public Object createBeanAsEarlySingleton(BeanDefinition def) {
        return null;
    }
}
