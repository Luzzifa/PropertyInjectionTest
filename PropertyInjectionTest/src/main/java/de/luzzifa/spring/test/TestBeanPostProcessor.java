/**
 * Copyright 2013 Wolfgang Deifel, Luzzifa.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.luzzifa.spring.test;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.TypedStringValue;

/**
 * TODO: pupose of this class
 * <p style="font-style:italic;font-variant:small-caps;font-size:80%">
 * Copyright &copy; Luzzifa.de 2013 All rights reserved.
 * </p>
 * 
 * @author Wolfgang Deifel
 */
public class TestBeanPostProcessor implements InstantiationAwareBeanPostProcessor
{
	private static String[] propertyValues = {
		"XString4711", "XString1", "XString1"
	};
	private static int value = 0;

	/**
	 * 
	 */
	public TestBeanPostProcessor()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
	{
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException
	{
		return bean;
	}

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException
	{
		return null;
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException
	{
		return true;
	}

	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException
	{
		List<PropertyValue> original;
		if (pvs instanceof MutablePropertyValues)
		{
			original = ((MutablePropertyValues) pvs).getPropertyValueList();
		}
		else
		{
			original = Arrays.asList(pvs.getPropertyValues());
		}

		List<PropertyValue> deepCopy = new ArrayList<>(original.size());
	    boolean cloneNecessary = false;
	    for (PropertyValue pv : original)
	    {
	      Object originalValue = pv.getValue();
	      Object convertedValue = ("${stringList}".equals(originalValue)) 
	          ? propertyValues[(++value) % propertyValues.length].substring(1)
	          : originalValue;
	      if (originalValue == convertedValue)
	      {
	        deepCopy.add(pv);
	      }
	      else
	      {
	        final PropertyValue npv = new PropertyValue(pv, convertedValue);
	        deepCopy.add(npv);
	        cloneNecessary = true;
	      }
	    }
	    
	    if (cloneNecessary)
	    {
	      // Set our (possibly massaged) deep copy.
	      pvs = new MutablePropertyValues(deepCopy);
	    }
	    return pvs;
	}

}
