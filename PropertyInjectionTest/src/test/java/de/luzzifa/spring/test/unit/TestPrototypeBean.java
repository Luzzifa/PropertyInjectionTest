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

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import de.luzzifa.spring.test.CustomScope;
import de.luzzifa.spring.test.TestFactory;

/**
 * TODO: pupose of this class
 * <p style="font-style:italic;font-variant:small-caps;font-size:80%">
 * Copyright &copy; Luzzifa.de 2013 All rights reserved.
 * </p>
 * @author Wolfgang Deifel
 */
public class TestPrototypeBean
{
	@Test
	public void test()
	{
		final TestFactory testFactory = new TestFactory();
		
		final HashMap<String, Object> scope1 = new HashMap<String, Object>();
		final HashMap<String, Object> scope2 = new HashMap<String, Object>();
		final HashMap<String, Object> scope3 = new HashMap<String, Object>();
		
		CustomScope.setButes(scope1);
		Object protoBean1 = testFactory.getProtoBean();
		System.out.println("ProtoBean1: "+protoBean1+"@"+protoBean1.hashCode());
		
		CustomScope.setButes(scope2);
		Object protoBean2 = testFactory.getProtoBean();
		System.out.println("ProtoBean2: "+protoBean2+"@"+protoBean2.hashCode());
		
		CustomScope.setButes(scope3);
		Object protoBean3 = testFactory.getProtoBean();
		System.out.println("ProtoBean3: "+protoBean3+"@"+protoBean3.hashCode());
		
		CustomScope.setButes(scope1);
		Object protoBean4 = testFactory.getProtoBean();
		System.out.println("ProtoBean4: "+protoBean4+"@"+protoBean4.hashCode());
		
		CustomScope.setButes(scope3);
		Object protoBean5 = testFactory.getProtoBean();
		System.out.println("ProtoBean5: "+protoBean5+"@"+protoBean5.hashCode());
		
		CustomScope.setButes(scope1);
		Object protoBean6 = testFactory.getProtoBean();
		System.out.println("ProtoBean6: "+protoBean6+"@"+protoBean6.hashCode());
		
		CustomScope.setButes(scope1);
		Object protoBean7 = testFactory.getProtoBean();
		System.out.println("ProtoBean7: "+protoBean7+"@"+protoBean7.hashCode());
		
//		assertNotSame(protoBean1, protoBean2);
//		assertNotSame(protoBean1, protoBean3);
//		assertNotSame(protoBean2, protoBean3);
//		assertSame(protoBean1, protoBean4);
//		assertSame(protoBean3, protoBean5);
	}

}
