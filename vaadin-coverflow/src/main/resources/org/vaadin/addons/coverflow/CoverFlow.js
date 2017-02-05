window.org_vaadin_addons_coverflow_CoverFlow = function() {

	var elem = this.getElement(), self = this;
	
	var _current = null;

	this.clearSelection = function() {
		jcrop_api.release();
	}

	this.onStateChange = function(e) {
		var state = this.getState();

		$(elem).empty();

		var ulElem = $("<ul>");
		$(elem).append(ulElem);

		for (i in state.urlList) {

			var liElem = $("<li>");
			$(ulElem).append(liElem);

			var imageElem = $("<img>").attr({
				"src" : state.urlList[i]
			}).css({
				"max-height" : state.maxSize + "px",
				"max-width" : state.maxSize + "px"
			});
			$(liElem).append(imageElem);
		}

		var start = state.start < 0 ? 'center' : state.start;
		var autoplay = state.autoplay_milliseconds <= 0 ? false : state.autoplay_milliseconds;

		$(elem).flipster({
			style : state.style.toLowerCase(),
			start : start,
			keyboard : state.enableKeyboard,
			scrollwheel : state.enableMousewheel,
            loop : state.enableLoop,
            autoplay : autoplay,
            buttons: state.enableNavigationButtons,

			onItemSwitch : function() {
				var url = $(elem).find(".flip-current img").attr("src");

				self.getRpcProxy().click(url, _current == null);
				_current = url;
			} // Callback function when items are switches
		});
	}

}