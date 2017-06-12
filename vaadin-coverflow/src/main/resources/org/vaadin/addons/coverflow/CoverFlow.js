window.org_vaadin_addons_coverflow_CoverFlow = function() {

	var elem = this.getElement(), self = this;

	this.clearSelection = function() {
		jcrop_api.release();
	}

	this.onStateChange = function(e) {
		var state = this.getState();
		if (state.isInit)
			return;

		$(elem).empty();

		var ulElem = $("<ul>");
		$(elem).append(ulElem);

		for (i in state.urlList) {

			var liElem = $("<li>");
			$(ulElem).append(liElem);

			var src = state.urlList[i];
			var title = i;
			if (state.titleList.length != null && state.titleList.length == state.urlList.length)
				title = state.titleList[i];
			var category = "";
			if (state.categoryList.length != null && state.categoryList.length == state.urlList.length)
				category = state.categoryList[i];
			var referenceNo = -1;
			if (state.referenceList.length != null && state.referenceList.length == state.urlList.length)
				referenceNo = state.referenceList[i];

			var imageElem = $("<img>").attr({
				"src" : src,
				"data-flip-title" : title,
				"data-flip-category" : category,
				"data-flip-reference" : referenceNo,
				"flip-id" : state.flipsterId
			})

			$(liElem).append(imageElem);
		}

		var start = state.start < 0 ? 'center' : state.start;
		var autoplay = state.autoplay_milliseconds <= 0 ? false : state.autoplay_milliseconds;
		var nav = state.navigation.toLowerCase() == 'off' ? false : state.navigation.toLowerCase();

		$(elem).flipster({
			style : state.style.toLowerCase(),
			start : start,
			keyboard : state.enableKeyboard,
			scrollwheel : state.enableMousewheel,
			loop : state.enableLoop,
			autoplay : autoplay,

			buttons: state.enableNavigationButtons,
			nav : nav,

			// Callback function when items are switched
			onItemSwitch : function(currentItem, previousItem) {

				var currentImage = $(currentItem).find("img");
				var url = currentImage.attr("src");
				var title = currentImage.attr("data-flip-title");
				var category = currentImage.attr("data-flip-category");
				var reference = currentImage.attr("data-flip-reference");
				var flipsterId = currentImage.attr("flip-id");

				$("#carousel-caption-label"+flipsterId).text(title);
				$("#carousel-editing-bar-label"+flipsterId).text(category);

				$(previousItem).off('click.aisEscalatedClick');
				$(currentItem).on("click.aisEscalatedClick", { ref : reference }, function(e) {
					self.getRpcProxy().click(e.data.ref);
				});
			}

		}).flipster('next'); // this is to guarantee the clickListener on the fronting item is there from the outset

	}

}