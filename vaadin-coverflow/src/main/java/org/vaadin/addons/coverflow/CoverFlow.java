package org.vaadin.addons.coverflow;

import java.util.List;

import org.vaadin.addons.coverflow.client.CoverFlowServerRpc;
import org.vaadin.addons.coverflow.client.CoverFlowState;
import org.vaadin.addons.coverflow.client.CoverflowStyle;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;

/**
 * Work in Progress
 *
 * @author cthiel
 *
 */
@StyleSheet({ "vaadin://coverflow/flipster.min.css" })
@JavaScript({ "vaadin://coverflow/jquery-1.11.2.min.js", "vaadin://coverflow/flipster.min.js", "CoverFlow.js" })
public class CoverFlow extends AbstractJavaScriptComponent {

	private static final long serialVersionUID = 571299685547099480L;

	@SuppressWarnings("serial")
	private final CoverFlowServerRpc rpc = new CoverFlowServerRpc() {

		@Override
		public void click(final int idx) {
		}

	};

	public CoverFlow(final List<String> urls) {
		registerRpc(this.rpc);

		setUrlList(urls);
	}

	public void setUrlList(final List<String> urls) {
		getState().urlList = urls;
	}

	public void setMaxImageSize(final int maxSize) {
		getState().maxSize = maxSize;
	}

	public int getMaxImageSize() {
		return getState(false).maxSize;
	}

	public void setKeyboardEnable(final boolean keyboardEnable) {
		getState().enableKeyboard = keyboardEnable;
	}

	public boolean isKeyboardEnabled() {
		return getState(false).enableKeyboard;
	}

	public void setMousewheelEnable(final boolean mousewheelEnable) {
		getState().enableMousewheel = mousewheelEnable;
	}

	public boolean isMousewheelEnabled() {
		return getState(false).enableMousewheel;
	}

	public void setCoverflowStyle(final CoverflowStyle style) {
		getState().style = style;
	}

	public CoverflowStyle getCoverflowStyle() {
		return getState(false).style;
	}

	/**
	 * Sets the start position of the coverflow. Every value lower than 0 sets
	 * the start position to the center value.
	 *
	 * @param start
	 *            start value
	 */
	public void setStartElement(final int startIdx) {
		getState().start = startIdx;
	}

	public int getStartElement() {
		return getState(false).start;
	}

	@Override
	protected CoverFlowState getState() {
		return (CoverFlowState) super.getState();
	}

	@Override
	protected CoverFlowState getState(final boolean markAsDirty) {
		return (CoverFlowState) super.getState(markAsDirty);
	}
}
