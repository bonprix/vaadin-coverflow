package org.vaadin.addons.coverflow.demo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.coverflow.CoverFlow;

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
		urls.add("http://www.sprachreisen.de/uploads/media/123rf_9921964_neuseeland_01.jpg");
		urls.add("http://www.sprachreisen.de/uploads/media/123rf_9921964_neuseeland_01.jpg");
		urls.add("http://www.sprachreisen.de/uploads/media/123rf_9921964_neuseeland_01.jpg");
		urls.add("http://www.sprachreisen.de/uploads/media/123rf_9921964_neuseeland_01.jpg");
		urls.add("http://www.sprachreisen.de/uploads/media/123rf_9921964_neuseeland_01.jpg");
		urls.add("http://www.sprachreisen.de/uploads/media/123rf_9921964_neuseeland_01.jpg");
		urls.add("http://www.sprachreisen.de/uploads/media/123rf_9921964_neuseeland_01.jpg");
		urls.add("http://www.sprachreisen.de/uploads/media/123rf_9921964_neuseeland_01.jpg");
		urls.add("http://www.sprachreisen.de/uploads/media/123rf_9921964_neuseeland_01.jpg");
		urls.add("http://www.sprachreisen.de/uploads/media/123rf_9921964_neuseeland_01.jpg");

		// Initialize our new UI component
		final CoverFlow coverFlow = new CoverFlow(urls);
		coverFlow.setWidth(600, Unit.PIXELS);
		coverFlow.setHeight(600, Unit.PIXELS);

		// Show it in the middle of the screen
		final VerticalLayout layout = new VerticalLayout();
		layout.setStyleName("demoContentLayout");
		layout.setSizeFull();
		layout.addComponent(coverFlow);
		layout.setComponentAlignment(coverFlow, Alignment.MIDDLE_CENTER);
		setContent(layout);

	}
}
