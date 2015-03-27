/**
 * This file contains ready function which is responsible 
 * for initialization of main global variables and 
 * objects after document is fully loaded.
 */
var core = {}

/**
 * returns links to views. Each view can be accessed via css
 * selector. e.g. 'link[href="your_view"]'.
 */
core.getViews = function() {
	
	return document.querySelector("link[href='Views/views.html']").import;
}

/**
 * wrapper for import property.
 *   
 * @param object where the resources to be looked for. (optional)
 * @param importLink path to resources. e.g.: "link[href='Views/views.html']".
 * @return content of html resources.
 * 
 * @see http://www.html5rocks.com/en/tutorials/webcomponents/imports/
 */
core.imp = function(object, importLink) {
	
	if (object == undefined) {
		
		return document.querySelector(importLink).import;
	} else {
		
		return object.querySelector(importLink).import;
	}
}