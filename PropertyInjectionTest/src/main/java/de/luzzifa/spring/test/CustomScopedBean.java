/**
 * 
 */
package de.luzzifa.spring.test;

import java.util.List;

/**
 * @author wdeifel
 *
 */
public class CustomScopedBean
{
	private List<String> stringList;
	/**
	 * 
	 */
	public CustomScopedBean()
	{
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the stringList
	 */
	public List<String> getStringList()
	{
		return stringList;
	}
	/**
	 * @param stringList the stringList to set
	 */
	public void setStringList(List<String> stringList)
	{
		this.stringList = stringList;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return "PrototypeBean [stringList=" + stringList + "]";
	}

}
