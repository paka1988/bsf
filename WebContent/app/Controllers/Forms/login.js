/**
 * This is controller for login form.
 */
var login = {}

/**
 * CSS selector to get the representation of login page.
 */
login.loginForm = "link[href='Forms/login.html']";
login.userField = "link[href='Components/field-text.html']";
login.userPass  = "link[href='Components/field-pass.html']";

/**
 * This function builds login.
 */
login.build = function() {
	
	// get views
	var views = core.getViews();
	
	// get elements
	var elementLogin = core.imp(views, login.loginForm).querySelector(".login");
	var elementFieldUser = core.imp(views, login.userField).querySelector(".field-text");
	var elementFieldPass = core.imp(views, login.userPass).querySelector(".field-pass");
	
	// build login
	jq("#header").append(jq(elementLogin).append(elementFieldUser, elementFieldPass));
	
	return true;
}

/**
 * This function hides login form.
 */
login.hide = function() {
	
	// change login style to visible.
	jq(".login").css("visibility", "hidden");
	
	return true;
}

/**
 * This function shows login form
 */
login.show = function() {

	// change login style to visible.
	jq(".login").css("visibility", "visible");
	
	return true;
}