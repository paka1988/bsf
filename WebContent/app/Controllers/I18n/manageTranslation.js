/**
 * This is controller for languages.
 */
var i18n = {};

i18n.language = "en_GB";

/**
 * @param source - path to translation file.
 * @param translation - elements which should be replaced with translation
 * @return JSON object or false if error occurred.
 */
i18n.setTranslation = function(source, translation) {
	
	console.log(source);
	
	// send ajax request and set translation on done event.
	var response = jq.getJSON(source).done(function(data) {
		
		for (var key in translation) {
			
			jq(key).text(data[translation[key]]["en_GB"]);
		}
	}).error(function() {
		
		console.log("error by getting of JSON object." + source);
	});
	
	return true;
}

i18n.setLanguage = function(languageCode) {
	
	i18n.language = languageCode;
	
	return true;
}