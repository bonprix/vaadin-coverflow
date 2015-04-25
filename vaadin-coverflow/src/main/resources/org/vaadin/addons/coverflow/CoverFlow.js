window.org_vaadin_addons_coverflow_CoverFlow = function() {

	var elem = this.getElement(), self = this;

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

		$(elem).flipster({
	        style:              state.style.toLowerCase(), // Switch between 'coverflow' or 'carousel' display styles
	        start:              start, // Starting item. Set to 0 to start at the first, 'center' to start in the middle or the index of the item you want to start with.

	        enableKeyboard:     state.enableKeyboard, // Enable left/right arrow navigation
	        enableMousewheel:   state.enableMousewheel, // Enable scrollwheel navigation (up = left, down = right)

	        onItemSwitch:       function(){} // Callback function when items are switches
		});
	}

}