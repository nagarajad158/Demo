package com.aem.geeks.core.models.impl;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import com.aem.geeks.core.models.StateItem;
import com.aem.geeks.core.models.StatesList;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = StatesList.class,
        resourceType = StatesListImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class StatesListImpl implements StatesList {
	
	final protected static String RESOURCE_TYPE="aemgeeks/components/content/states-list";
	
	@ChildResource
	private List<StateItem> states;
	

	@Override
	public List<StateItem> getStates() {
		return states;
	}

}
