package org.vaadin.addons.coverflow.client;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.shared.ui.JavaScriptComponentState;

@SuppressWarnings("serial")
public class CoverFlowState extends JavaScriptComponentState {

	public List<String> urlList = new ArrayList<String>();
	public int maxSize = 200;

	public boolean enableKeyboard = true;
	public boolean enableMousewheel = true;
	public int start = -1; // -1 = center

	public CoverflowStyle style = CoverflowStyle.CAROUSEL;

}
