package com.aem.geeks.core.models.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.aem.geeks.core.models.StateItem;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
		adapters = StateItem.class,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class StateItemImpl implements StateItem {
	
	@ValueMapValue
	private String stateName;
	
	@ValueMapValue
	private String stateValue;

	@Override
	public String getStateName() {
		return stateName;
	}

	@Override
	public String getStateValue() {
		return stateValue;
	}

}
