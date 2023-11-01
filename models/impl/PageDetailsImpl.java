package com.aem.geeks.core.models.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import com.aem.geeks.core.models.PageDetails;
import com.day.cq.wcm.api.Page;
@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
		adapters = PageDetails.class,
		resourceType = PageDetailsImpl.RESOURCE_TYPE,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PageDetailsImpl implements PageDetails {
	
	final protected static String RESOURCE_TYPE="aemgeeks/components/page";

	@ScriptVariable(name="currentPage")
	private Page page;
	
	
	@Override
	public String getPageName() {
		return page.getName();
	}
}
