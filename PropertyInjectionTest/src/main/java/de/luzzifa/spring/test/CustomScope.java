package de.luzzifa.spring.test;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * TODO: pupose of this class
 * 
 * @author Wolfgang Deifel
 * @version $Revision$
 */
public class CustomScope implements Scope
{
	private static final Logger LOG = LoggerFactory.getLogger(CustomScope.class);
	private static Map<String,Object> butes;
	
	/**
	 * @param butes the butes to set
	 */
	public static void setButes(Map<String, Object> butes)
	{
		CustomScope.butes = butes;
	}

	/**
	 * 
	 */
	public CustomScope()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory)
	{
		LOG.debug("CSO get request for [{}] (Factory: [{}])", name, objectFactory);
		synchronized (butes)
		{
			Object scopedObject = butes.get(name);
			if (scopedObject == null) 
			{
				LOG.info("CSO create [{}] (Factory: [{}])", name, objectFactory);
				scopedObject = objectFactory.getObject();
				butes.put(name, scopedObject);
			}
			return scopedObject;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getConversationId()
	{
		LOG.debug("TSO request conversationId");
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerDestructionCallback(String name, Runnable callback)
	{
		LOG.debug("TSO registerDestructionCallback request for [{}]", name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object remove(String name)
	{
		LOG.debug("TSO remove request for [{}]", name);
		if (butes!=null)
		{
			Object scopedObject = butes.get(name);
			if (scopedObject != null) 
			{
				butes.remove(name);
				return scopedObject;
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object resolveContextualObject(String key)
	{
		LOG.debug("TSO resolveContextualObject request for [{}]", key);
		return null;
	}
	
}
