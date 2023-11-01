package com.aem.geeks.core.models.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.aem.geeks.core.models.Text;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
		adapters = Text.class,
		resourceType = TextImpl.RESOURCE_TYPE,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class TextImpl implements Text {
	
	final protected static String RESOURCE_TYPE="aemgeeks/components/content/text";
	
	@ValueMapValue
	private String text;
	
	@ValueMapValue
	private String id;

	@Override
	public String getText() {
		return text;
	}

	@Override
	public String getId() {
		return id;
	}

}
