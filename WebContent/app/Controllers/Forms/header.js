/**
 * This is controller for header element.
 */
var header = {};

/**
 * CSS selector to get the representation of login page.
 */
header.loginButton = "link[href='Components/button-login.html']";
header.loginLink = "link[href='Components/link-login.html']";

/**
 * This function builds login button into header.
 */
header.build = function() {
	
	// get views
	var views = core.getViews();
	
	// query element
	var loginButton = core.imp(views, header.loginButton).querySelector(".button-login");
	
	// build login
	jq("#header").append(jq("#header").append(loginButton));
}

/**
 * This function builds login button into header.
 */
header.buildLoginLink = function() {
	
	// get views
	var views = core.getViews();
	
	// query element
	var loginLink = core.imp(views, header.loginLink).querySelector(".link-login");
	
	// build login
	jq("#header").append(jq("#header").append(loginLink));
}

