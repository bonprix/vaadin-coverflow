package org.vaadin.addons.coverflow.demo;

import java.util.ArrayList;
import java.util.Collections;
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
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;

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

		final List<String> verticalUrls = new ArrayList<String>();
		verticalUrls.add("http://rfp.laudert.de/previews/assets/prev_l/6/5/9/3/15076593.jpg");
		verticalUrls.add("http://rfp.laudert.de/previews/assets/prev_l/6/5/9/5/15076595.jpg");
		verticalUrls.add("http://rfp.laudert.de/previews/assets/prev_l/5/2/3/6/15055236.jpg");
		verticalUrls.add("http://rfp.laudert.de/previews/assets/prev_l/5/2/3/7/15055237.jpg");
		verticalUrls.add("http://rfp.laudert.de/previews/assets/prev_l/5/2/3/5/15055235.jpg");
		verticalUrls.add("http://rfp.laudert.de/previews/assets/prev_l/6/9/3/9/15006939.jpg");
		verticalUrls.add("http://rfp.laudert.de/previews/assets/prev_l/2/8/4/4/14232844.jpg");

		final List<String> horizontalUrls = new ArrayList<String>();
		horizontalUrls.add("http://rfp.laudert.de/previews/assets/prev_l/5/6/9/7/15055697.jpg");
		horizontalUrls.add("http://rfp.laudert.de/previews/assets/prev_l/6/9/3/5/15056935.jpg");
		horizontalUrls.add("http://rfp.laudert.de/previews/assets/prev_l/5/4/9/5/15055495.jpg");
		horizontalUrls.add("http://rfp.laudert.de/previews/assets/prev_l/6/6/2/5/15036625.jpg");
		horizontalUrls.add("http://rfp.laudert.de/previews/assets/prev_l/8/4/7/4/15038474.jpg");
		horizontalUrls.add("http://rfp.laudert.de/previews/assets/prev_l/6/6/4/1/15036641.jpg");
		horizontalUrls.add("http://rfp.laudert.de/previews/assets/prev_l/8/8/3/2/15078832.jpg");
		horizontalUrls.add("http://rfp.laudert.de/previews/assets/prev_l/8/2/5/5/15078255.jpg");
		horizontalUrls.add("http://rfp.laudert.de/previews/assets/prev_l/8/8/9/6/15078896.jpg");
		horizontalUrls.add("http://rfp.laudert.de/previews/assets/prev_l/6/7/8/9/15046789.jpg");
		horizontalUrls.add("http://rfp.laudert.de/previews/assets/prev_l/1/0/6/7/15051067.jpg");

		final List<String> mixedUrls = new ArrayList<String>();
		mixedUrls.addAll(verticalUrls);
		mixedUrls.addAll(horizontalUrls);
		Collections.shuffle(mixedUrls);

		// Show it in the middle of the screen
		final GridLayout layout = new GridLayout(2, 1);
		layout.setStyleName("demoContentLayout");
		layout.setSizeFull();
		createAndAddCoverflow(layout, horizontalUrls);
		createAndAddCoverflow(layout, verticalUrls);
		createAndAddCoverflow(layout, mixedUrls);

		setContent(layout);
	}

	private static CoverFlow createAndAddCoverflow(final GridLayout layout, final List<String> imgUrls) {
		// Initialize our new UI component
		final CoverFlow coverFlow = new CoverFlow(imgUrls);
		coverFlow.setWidth(400, Unit.PIXELS);
		coverFlow.setHeight(400, Unit.PIXELS);
		coverFlow.setCoverflowStyle(CoverflowStyle.CAROUSEL);
		coverFlow.setMaxImageSize(300);

		layout.addComponent(coverFlow);
		layout.setComponentAlignment(coverFlow, Alignment.MIDDLE_CENTER);

		return coverFlow;
	}
}
