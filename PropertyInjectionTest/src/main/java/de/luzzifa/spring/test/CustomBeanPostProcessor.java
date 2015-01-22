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

/**
 * TODO: pupose of this class
 * <p style="font-style:italic;font-variant:small-caps;font-size:80%">
 * Copyright &copy; Luzzifa.de 2013 All rights reserved.
 * </p>
 * 
 * @author Wolfgang Deifel
 */
public class CustomBeanPostProcessor implements InstantiationAwareBeanPostProcessor
{
	private static String[] propertyValues = {
		"XString1", "XString2", "XString1", "XString4"
	};
	private static int value = -1;

	/**
	 * 
	 */
	public CustomBeanPostProcessor()
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
		final List<PropertyValue> original = getOriginalPropertyValuesList(pvs);
		final List<PropertyValue> deepCopy = new ArrayList<>(original.size());
	    boolean cloneNecessary = false;
	    
	    for (PropertyValue pv : original)
	    {
	        final Object originalValue = pv.getValue();
			if ("${stringList}".equals(originalValue))
			{
				final Object newPropertyValue = getNewPropertyValue();
				final PropertyValue npv = new PropertyValue(pv, newPropertyValue);
				deepCopy.add(npv);
				cloneNecessary = true;
			}
			else
			{
				deepCopy.add(pv);
			}
	    }
	    
	    if (cloneNecessary)
	    {
	      // Set our (possibly massaged) deep copy.
	      pvs = new MutablePropertyValues(deepCopy);
	    }
	    return pvs;
	}

	/**
	 * return every time a new String object
	 * @return string123
	 */
	private Object getNewPropertyValue()
	{
		return new String(propertyValues[(++value) % propertyValues.length]);
	}
	
	private List<PropertyValue> getOriginalPropertyValuesList(PropertyValues pvs)
	{
		if (pvs instanceof MutablePropertyValues)
		{
			return ((MutablePropertyValues) pvs).getPropertyValueList();
		}
		return Arrays.asList(pvs.getPropertyValues());
	}

}
