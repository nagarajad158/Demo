package com.aem.geeks.core.models.impl;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.aem.geeks.core.models.Multimedia;
import com.day.cq.commons.DownloadResource;
import com.day.cq.dam.api.Asset;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
		adapters = Multimedia.class,
		resourceType = MultimediaImpl.RESOURCE_TYPE,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultimediaImpl implements Multimedia {
	
	final protected static String RESOURCE_TYPE="aemgeeks/components/content/multi-media";
	
	@ValueMapValue(name= DownloadResource.PN_REFERENCE)
	private String imageRef;
	
	@ValueMapValue
	private String altText;
	
    @SlingObject
    private Resource resource;

    @SlingObject
    private SlingHttpServletRequest request;
    
    @SlingObject
    private ResourceResolver resolver;
    
    private String mimeType;
    
    @PostConstruct
    protected void init() {
	    if (imageRef != null) {
	    	Resource resource = resolver.getResource(imageRef);
	    	Asset asset = resource.adaptTo(Asset.class);
	    	mimeType = asset.getMimeType().toString();	
	    }
    }

	@Override
	public String getImageRef() {
		return imageRef;
	}

	@Override
	public String getAltText() {
		return altText;
	}
	
	@Override
	public String getMimeTypeImage() {
		return mimeType.startsWith("image") ? mimeType : null;
	}
	
	@Override
	public String getMimeTypeVideo() {
		return mimeType.startsWith("video") ? mimeType : null;
	}

}
