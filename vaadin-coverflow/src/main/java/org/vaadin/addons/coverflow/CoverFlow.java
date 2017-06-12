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
import org.vaadin.addons.coverflow.client.NavigationType;

/**
 * The Coverflow widget uses the jQuery Addon flipster <a
 * href="https://github.com/drien/jquery-flipster">Flipster</a> to enable a
 * coverflow style image gallery.<br>
 * <br>
 * Every call to one of the CoverFlow-Setter methods will cause a complete
 * reinitialization of the coverflow on the client.
 *
 * @author Christian Thiel
 *
 * jquery version: 3.1.1 (slim version wouldn't work, migration package not required)
 * flipster version: 1.1.2
 */
@StyleSheet({ "vaadin://coverflow/jquery.flipster.css" })
@JavaScript({ "vaadin://coverflow/jquery.min.js", "vaadin://coverflow/jquery.flipster.min.js", "CoverFlow.js" })
public class CoverFlow extends AbstractJavaScriptComponent {

	private static final long serialVersionUID = 571299685547099480L;

	/**
	 * To avoid multiple reinitialisations at start-up-time because of multiple settings being used we allow
	 * constructing a Coverflow widget with this pre-set Configuration object.
	 * Using this still allows changing settings using the explicit setters, and using those will still force
	 * reinitialisation.
	 */
	public static class Configuration {

		private final int id;
		private final List<String> uris;
		private List<String> titles = null;
		private List<String> categories = null;
		private List<Integer> references = null;
		private CoverflowStyle coverflowStyle = CoverflowStyle.COVERFLOW;
		private int maximumImageSize = -1;
		private boolean isKeyboardEnabled = true;
		private boolean isMousewheelEnabled = true;
		private boolean isLoopEnabled = true;
		private boolean isNavigationButtonsEnabled = true;
		private int autoplayMilliseconds = -1;
		private int startElementIndex = -1; // default : 'center'
		private NavigationType navigationType = NavigationType.OFF;

		public Configuration(List<String> uris, int id) {
			if (uris == null)
				throw new IllegalArgumentException("List of URIs is mandatory!");
			this.uris = uris;
			this.id = id;
		}

		public void setTitles(List<String> titles) {
			if (titles != null && titles.size() != uris.size())
				throw new AssertionError("List of titles must be same size as List of URIs " +
						"and indexes of titles must correspond with index positions of according URIs!");
			this.titles = titles;
		}

		public void setCategories(List<String> categories) {
			if (categories != null && categories.size() != uris.size())
				throw new AssertionError("List of categories must be same size as List of URIs " +
						"and indexes of categories must correspond with index positions of according URIs!");
			this.categories = categories;
		}

		public void setReferences(List<Integer> references) {
			if (references != null && references.size() != uris.size())
				throw new AssertionError("List of categories must be same size as List of URIs " +
						"and indexes of categories must correspond with index positions of according URIs!");
			this.references = references;
		}

		public void setCoverflowStyle(CoverflowStyle coverflowStyle) {
			this.coverflowStyle = coverflowStyle;
		}

		public void setMaximumImageSize(int maximumImageSize) {
			this.maximumImageSize = maximumImageSize;
		}

		public void setKeyboardEnabled(boolean keyboardEnabled) {
			isKeyboardEnabled = keyboardEnabled;
		}

		public void setMousewheelEnabled(boolean mousewheelEnabled) {
			isMousewheelEnabled = mousewheelEnabled;
		}

		public void setLoopEnabled(boolean loopEnabled) {
			isLoopEnabled = loopEnabled;
		}

		public void setNavigationButtonsEnabled(boolean navigationButtonsEnabled) {
			isNavigationButtonsEnabled = navigationButtonsEnabled;
		}

		public void setAutoplayMilliseconds(int autoplayMilliseconds) {
			this.autoplayMilliseconds = autoplayMilliseconds;
		}

		public void setStartElementIndex(int startElementIndex) {
			this.startElementIndex = startElementIndex;
		}

		public void setNavigationType(NavigationType navigationType) {
			this.navigationType = navigationType;
		}
	}

	@SuppressWarnings("serial")
	private final CoverFlowServerRpc rpc = new CoverFlowServerRpc() {

		@Override
		public void click(int reference) {
			fireEvent(new ImageSelectionEvent(CoverFlow.this, reference));
		}
	};

	public CoverFlow(final Configuration config) {
		registerRpc(this.rpc);
		CoverFlowState state = getState();
		state.isInit = true;
		state.flipsterId = config.id;
		setUrlList(config.uris);
		if (config.titles != null)
			state.titleList = config.titles;
		if (config.categories != null)
			state.categoryList = config.categories;
		if (config.references != null)
			state.referenceList = config.references;
		setAutoplay(config.autoplayMilliseconds);
		state.maxSize = config.maximumImageSize;
		state.enableKeyboard = config.isKeyboardEnabled;
		state.enableMousewheel = config.isMousewheelEnabled;
		state.enableLoop = config.isLoopEnabled;
		state.enableNavigationButtons = config.isNavigationButtonsEnabled;
		state.start = config.startElementIndex;
		state.isInit = false;
		setCoverflowStyle(config.coverflowStyle);
	}

	/**
	 * Creates a new coverflow widget with the given image URLs.
	 * This field is mandatory for the widget to work.
	 *
	 * @param urls
	 *            image URL list
	 */
	public CoverFlow(final List<String> urls) {
		registerRpc(this.rpc);

		setUrlList(urls);
	}

	/**
	 * Sets the image URLs. Causes a reinitialization.
	 *
	 * @param urls
	 *            the URLs
	 */
	public void setUrlList(final List<String> urls) {
		getState().urlList = urls;
	}

	/**
	 * Sets the max size of the displayed images in pixels (both dimensions).
	 * Causes a reinitialization.
	 *
	 * @param maxSize
	 *            the max image size in pixels
	 */
	public void setMaxImageSize(final int maxSize) {
		getState().maxSize = maxSize;
	}

	public int getMaxImageSize() {
		return getState(false).maxSize;
	}

	/**
	 * Set true to enable global keyboard navigation with the arrow keys. Causes
	 * a reinitialization.
	 *
	 * @param keyboardEnable
	 *            if keyboard navigation should be enabled
	 */
	public void setKeyboardEnable(final boolean keyboardEnable) {
		getState().enableKeyboard = keyboardEnable;
	}

	public boolean isKeyboardEnabled() {
		return getState(false).enableKeyboard;
	}

	/**
	 * Set true to enable mousewheel navigation. Causes a reinitialization.
	 *
	 * @param mousewheelEnable
	 *            if mousewheel navigation should be enabled
	 */
	public void setMousewheelEnable(final boolean mousewheelEnable) {
		getState().enableMousewheel = mousewheelEnable;
	}

	public boolean isMousewheelEnabled() {
		return getState(false).enableMousewheel;
	}

	/**
	 * Set true to enable loop functionality, i.e. moving forward from the last element jumps to the first and vice versa.
	 * Causes a reinitialization.
	 *
	 * @param loopEnable
	 *            if loop functionality should be enabled
	 */
	public void setLoopEnable(final boolean loopEnable) {
		getState().enableLoop = loopEnable;
	}

	public boolean isLoopEnabled() {
		return getState(false).enableLoop;
	}

	/**
	 * Set true to enable navigation buttons. Causes a reinitialization.
	 *
	 * @param navigationButtonsEnable
	 *            if navigationButtonsEnable should be enabled
	 */
	public void setNavigationButtonsEnable(final boolean navigationButtonsEnable) {
		getState().enableNavigationButtons = navigationButtonsEnable;
	}

	public boolean isNavigationButtonsEnabled() {
		return getState(false).enableNavigationButtons;
	}

	/**
	 * Set milliseconds greater than 0 to activate autoplay feature.
	 *
	 * @param milliseconds null or lesser than 1 to switch autoplay off, otherwise millisecons greater than 1000 to define speed
	 */
	public void setAutoplay(final Integer milliseconds) {
		if (milliseconds == null || milliseconds <= 0)
			getState().autoplay_milliseconds = -1; // autoplay = off
		else if (milliseconds < 1000)
			getState().autoplay_milliseconds = 1000;
		else
			getState().autoplay_milliseconds = milliseconds;

		if (isAutoplay())
			setLoopEnable(true);
	}

	public boolean isAutoplay() {
		return getState().autoplay_milliseconds > 0;
	}

	/**
	 * Sets the navigation type of the component. Causes a reinitialization.
	 *
	 * @param type Value BEFORE will insert the navigation before the items, AFTER will append the navigation after the items
	 */
	public void setNavigationType(final NavigationType type) {
		getState().navigation = type.name();
	}

	public NavigationType getNavigationType() {
		String currentNavigation = getState(false).navigation;
		for (NavigationType nt : NavigationType.values())
			if (nt.name().equals(currentNavigation))
				return nt;
		assert false: "There should always be a navigation type set!";
		return NavigationType.OFF;
	}

	/**
	 * Sets the style of the coverflow. Causes a reinitialization.
	 *
	 * @param style
	 *            the style
	 */
	public void setCoverflowStyle(final CoverflowStyle style) {
		getState(true).style = style.name();
	}

	public CoverflowStyle getCoverflowStyle() {
		String currentStyle = getState(false).style;
		for (CoverflowStyle cfs : CoverflowStyle.values())
			if (cfs.name().equals(currentStyle))
				return cfs;
		assert false: "There should always be a style set!";
		return CoverflowStyle.COVERFLOW;
	}

	/**
	 * Sets the start position of the coverflow. Every value lower than 0 sets
	 * the start position to the center value.
	 *
	 * @param startIdx
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
		private final int reference;

		public ImageSelectionEvent(final Component source, final int reference) {
			super(source);
			this.reference = reference;
		}

		/**
		 * Returns the reference key of the selected image.
		 *
		 * @return the reference key of the selected image
		 */
		public int getReference() {
			return reference;
		}

	};

	public interface ImageSelectionListener extends Serializable {
		public static final Method IMAGE_SELECTION_METHOD = ReflectTools
				.findMethod(ImageSelectionListener.class, "onImageSelection", ImageSelectionEvent.class);

		public void onImageSelection(final ImageSelectionEvent event);
	}

	/**
	 * Adds an image selected listener. This listener will be called when an
	 * imageSelectionEvent occurs.
	 *
	 * @param listener
	 *            the listener to add
	 */
	public void addImageSelectionListener(final ImageSelectionListener listener) {
		addListener(ImageSelectionEvent.class, listener, ImageSelectionListener.IMAGE_SELECTION_METHOD);
	}

	/**
	 * Removes an image selectedlistener
	 *
	 * @param listener
	 *            the listener to remove
	 */
	public void removeImageSelectionListener(final ImageSelectionListener listener) {
		removeListener(ImageSelectionEvent.class, listener, ImageSelectionListener.IMAGE_SELECTION_METHOD);
	}

}
