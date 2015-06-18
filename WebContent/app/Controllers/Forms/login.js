/**
 * This is controller for login form.
 */
var login = {}

/**
 * CSS selector to get the representation of login page.
 */
login.loginForm     = "link[href='Forms/login.html']";
login.loginFormPure = "link[href='Forms/login-pure.html']";
login.userField   	= "link[href='Components/field-text.html']";
login.userPass    	= "link[href='Components/field-pass.html']";
login.submitLogin 	= "link[href='Components/simple-button.html']";

/**
 * This function builds login.
 */
login.build = function() {
	
	// get views
	var views = core.getViews();
	
	// get elements
	var elementLogin        = core.imp(views, login.loginForm).querySelector(".login > fieldset");
	var elementFieldUser    = core.imp(views, login.userField).querySelector(".field-text");
	var elementFieldPass    = core.imp(views, login.userPass).querySelector(".field-pass");
	var elementButtonSubmit = core.imp(views, login.submitLogin).querySelector(".simple-button");
	
	return jq(elementLogin).append(elementFieldUser, elementFieldPass, elementButtonSubmit);
}

login.build_pure = function() {
	
	// get views
	var views = core.getViews();
	
	return core.imp(views, login.loginFormPure).querySelector(".login-pure");
}

login.getLoginGorm = function() {
	
	jq("#header").append(login.build());
	
	return true;
};

login.getLoginFormPure = function(parentElement) {
	
	var form = login.build_pure();
	var script = form.querySelector(".login-pure > #login-pure-script");
	eval(script.innerHTML);
	
	jq(parentElement).append(form);
	
	return true;
};

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