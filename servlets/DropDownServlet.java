package com.aem.geeks.core.servlets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Servlet;

import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.commerce.common.ValueMapDecorator;
import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.day.crx.JcrConstants;

@Component(service = Servlet.class,
property = {
		ServletResolverConstants.SLING_SERVLET_RESOURCE_TYPES + "=" + DropDownServlet.RESOURCE_TYPE,
		ServletResolverConstants.SLING_SERVLET_METHODS + "=GET" })
public class DropDownServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = -4441559664733712400L;

	public static final String RESOURCE_TYPE = "geeks/components/states";

	private static Logger LOGGER = LoggerFactory.getLogger(DropDownServlet.class);

	@SuppressWarnings("rawtypes")
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		try {
			Resource resource = request.getResource();
			LOGGER.debug("Resource : {}", resource);
			if (resource != null) {
				Resource dataSource = resource.getChild("datasource");
				LOGGER.debug("DataSource: {}", dataSource);
				if (dataSource != null) {
					String path = String.valueOf(dataSource.getValueMap().get("rootPath", String.class));
					LOGGER.debug("Path value: {}", path);
					ResourceResolver resourceResolver = request.getResourceResolver();
					List<KeyValue> dropDownList = new ArrayList<>();
					Resource dataResource = resourceResolver.getResource(path);
					Iterator<Resource> iterator = dataResource.listChildren();
					List<Resource> list = new ArrayList<>();
					iterator.forEachRemaining(list::add);
					list.forEach(state -> {
						ValueMap valueMap = state.getValueMap();
						String text = valueMap.get("text", String.class);
						LOGGER.debug("text: {}", text);
						String value = valueMap.get("value", String.class);
						LOGGER.debug("value: {}", value);
						dropDownList.add(new KeyValue(text, value));
					});

					@SuppressWarnings("unchecked")
					DataSource ds = new SimpleDataSource(new TransformIterator(dropDownList.iterator(), input -> {
						KeyValue keyValue = (KeyValue) input;
						ValueMap vm = new ValueMapDecorator(new HashMap<>());
						vm.put("value", keyValue.value);
                        vm.put("text", keyValue.key);
						return new ValueMapResource(resourceResolver, new ResourceMetadata(),
								JcrConstants.NT_UNSTRUCTURED, vm);
					}));
					request.setAttribute(DataSource.class.getName(), ds);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Error in Get Drop Down Values", e);
		}
	}

	private class KeyValue {

        /**
         * key property.
         */
        private String key;
        /**
         * value property.
         */
        private String value;

        /**
         * constructor instance intance.
         *
         * @param newKey   -
         * @param newValue -
         */
        private KeyValue(final String newKey, final String newValue) {
            this.key = newKey;
            this.value = newValue;
        }
    }
}