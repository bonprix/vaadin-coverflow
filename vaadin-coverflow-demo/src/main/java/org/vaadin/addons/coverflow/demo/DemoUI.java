package org.vaadin.addons.coverflow.demo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.coverflow.CoverFlow;
import org.vaadin.addons.coverflow.client.CoverflowStyle;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("MyComponent Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.addons.coverflow.demo.DemoWidgetSet")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(final VaadinRequest request) {

		final List<String> urls = new ArrayList<String>();
		urls.add("http://rfp.laudert.de/previews/assets/prev_l/5/6/9/7/15055697.jpg");
		urls.add("http://rfp.laudert.de/previews/assets/prev_l/6/9/3/5/15056935.jpg");
		urls.add("http://rfp.laudert.de/previews/assets/prev_l/5/4/9/5/15055495.jpg");
		urls.add("http://rfp.laudert.de/previews/assets/prev_l/6/6/2/5/15036625.jpg");
		urls.add("http://rfp.laudert.de/previews/assets/prev_l/8/4/7/4/15038474.jpg");
		urls.add("http://rfp.laudert.de/previews/assets/prev_l/6/6/4/1/15036641.jpg");
		urls.add("http://rfp.laudert.de/previews/assets/prev_l/8/8/3/2/15078832.jpg");
		urls.add("http://rfp.laudert.de/previews/assets/prev_l/8/2/5/5/15078255.jpg");
		urls.add("http://rfp.laudert.de/previews/assets/prev_l/8/8/9/6/15078896.jpg");
		urls.add("http://rfp.laudert.de/previews/assets/prev_l/6/7/8/9/15046789.jpg");
		urls.add("http://rfp.laudert.de/previews/assets/prev_l/1/0/6/7/15051067.jpg");

		// Initialize our new UI component
		final CoverFlow coverFlow = new CoverFlow(urls);
		coverFlow.setWidth(600, Unit.PIXELS);
		coverFlow.setHeight(600, Unit.PIXELS);
		coverFlow.setCoverflowStyle(CoverflowStyle.CAROUSEL);
		coverFlow.setMaxImageSize(800);

		// Show it in the middle of the screen
		final VerticalLayout layout = new VerticalLayout();
		layout.setStyleName("demoContentLayout");
		layout.setSizeFull();
		layout.addComponent(coverFlow);
		layout.setComponentAlignment(coverFlow, Alignment.MIDDLE_CENTER);
		setContent(layout);

	}
}
