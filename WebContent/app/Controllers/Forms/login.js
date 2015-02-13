/**
 * This is controller for login form.
 */
var login = {}

/**
 * CSS selector to get the representation of login page.
 */
login.loginForm = "link[href='Forms/login.html']";

/**
 * This function builds login.
 */
login.build = function() {
	
	// get views
	var views = core.getViews();
	
	// get login content
	var content = views.querySelector(login.loginForm).import;
	
	// get element
	var element = content.querySelector(".login");
	
	// append login to header
	jq('#header').append(element);
	
	return true;
}

/**
 * This function hides login form.
 */
login.hide = function() {
	
	return true;
}

/**
 * This function shows login form
 */
login.show = function() {

	// get login content
	var login = VIEWS.querySelector(login.css.loginForm).import;
	
	jq('#header').append('');
	
	return true;
}