package org.luka.framework.contract;

import org.luka.framework.core.BeanDefinition;

import java.util.List;

public interface ConfigurableApplicationContext extends AppContextConfig {

    List<BeanDefinition> findBeanDefinitions(Class<?> type);

    BeanDefinition findBeanDefinition(Class<?> type);


    BeanDefinition findBeanDefinition(String name);

    BeanDefinition findBeanDefinition(String name, Class<?> requiredType);

    Object createBeanAsEarlySingleton(BeanDefinition def);

}
