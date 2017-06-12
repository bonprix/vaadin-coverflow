package org.vaadin.addons.coverflow.client;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.shared.ui.JavaScriptComponentState;

@SuppressWarnings("serial")
public class CoverFlowState extends JavaScriptComponentState {

	public int flipsterId = 1;

	public List<String> urlList = new ArrayList<String>();
	public List<String> titleList = new ArrayList<String>();
	public List<String> categoryList = new ArrayList<String>();
	public List<Integer> referenceList = new ArrayList<Integer>();
	public boolean isInit = false;

	public int maxSize = -1;

	public boolean enableKeyboard = true;
	public boolean enableMousewheel = true;
	public boolean enableNavigationButtons = false;
	public boolean enableLoop = true;
	public int autoplay_milliseconds = -1; // -1 = autoplay off
	public int start = -1; // -1 = center

	public String style = CoverflowStyle.CAROUSEL.name();
	public String navigation = NavigationType.OFF.name();

}
