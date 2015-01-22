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

package de.luzzifa.spring.test.unit;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TODO: pupose of this class
 * <p style="font-style:italic;font-variant:small-caps;font-size:80%">
 * Copyright &copy; Luzzifa.de 2013 All rights reserved.
 * </p>
 * @author Wolfgang Deifel
 */
public class TestCustomBeanCreation
{
	private ApplicationContext context;
	
	@Before
	public void createApplicationContext()
	{
		this.context = new ClassPathXmlApplicationContext("spring-test.xml");
	}
	
	@Test
	public void testCreateBean3TimesWorks()
	{
		for (int x=1; x <= 99; ++x)
		{
			Object protoBean = context.getBean("testBean");
			System.out.println("ProtoBean"+x+": "+protoBean+"@"+protoBean.hashCode());
			
		}
	}

}
