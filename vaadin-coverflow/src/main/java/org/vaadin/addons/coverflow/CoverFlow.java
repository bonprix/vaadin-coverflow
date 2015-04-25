package org.vaadin.addons.coverflow;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import org.vaadin.addons.coverflow.client.CoverFlowServerRpc;
import org.vaadin.addons.coverflow.client.CoverFlowState;
import org.vaadin.addons.coverflow.client.CoverflowStyle;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.Component;
import com.vaadin.util.ReflectTools;

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

	private int selectedIndex = -1;

	@SuppressWarnings("serial")
	private final CoverFlowServerRpc rpc = new CoverFlowServerRpc() {

		@Override
		public void click(final String url, final boolean initialSelection) {
			final int selection = getState(false).urlList.indexOf(url);

			selectedIndex = selection;
			if (!initialSelection) {
				fireEvent(new ImageSelectionEvent(CoverFlow.this, url, selection));
			}
		}
	};

	public CoverFlow(final List<String> urls) {
		registerRpc(this.rpc);

		setUrlList(urls);
	}

	public void setUrlList(final List<String> urls) {
		getState().urlList = urls;
	}

	public int getSelectedIndex() {
		return selectedIndex;
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

	public static class ImageSelectionEvent extends Component.Event {

		private static final long serialVersionUID = -4717161002326588670L;
		private final String url;
		private final int idx;

		public ImageSelectionEvent(final Component source, final String url, final int idx) {
			super(source);
			this.url = url;
			this.idx = idx;
		}

		public String getUrl() {
			return url;
		}

		public int getSelectedIndex() {
			return idx;
		}

	};

	public interface ImageSelectionListener extends Serializable {
		public static final Method IMAGE_SELECTION_METHOD = ReflectTools
		        .findMethod(ImageSelectionListener.class, "onImageSelection", ImageSelectionEvent.class);

		public void onImageSelection(final ImageSelectionEvent event);
	}

	/**
	 * Adds a scroll listener. This listener will be called when a scrollEvent
	 * occurs.
	 *
	 * @param listener
	 *            the listener to add
	 */
	public void addImageSelectionListener(final ImageSelectionListener listener) {
		addListener(ImageSelectionEvent.class, listener, ImageSelectionListener.IMAGE_SELECTION_METHOD);
	}

	/**
	 * Removes a scroll listener
	 *
	 * @param listener
	 *            the listener to remove
	 */
	public void removeImageSelectionListener(final ImageSelectionListener listener) {
		removeListener(ImageSelectionEvent.class, listener, ImageSelectionListener.IMAGE_SELECTION_METHOD);
	}

}
