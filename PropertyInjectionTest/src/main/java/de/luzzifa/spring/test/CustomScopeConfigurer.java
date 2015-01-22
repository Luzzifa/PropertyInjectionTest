package de.luzzifa.spring.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;


public class CustomScopeConfigurer implements BeanFactoryPostProcessor, Ordered
{
  private int order = Ordered.LOWEST_PRECEDENCE;

  @Override
  public int getOrder()
  {
    return this.order;
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException
  {
    beanFactory.registerScope("custom", new CustomScope());
  }

}
