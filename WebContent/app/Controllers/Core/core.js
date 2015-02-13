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