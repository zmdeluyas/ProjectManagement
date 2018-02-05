var $dashboard_body;
var $dashboard_projects;
var $dashboard_requests;
var $dashboard_history;
var current_dashboard;
var username;
var fullName;
var useraccess;


function initdashboard(){
	$dashboard_body = $('#dashboard-body');
	$dashboard_projects = $('#dashboard-projects');
	$dashboard_requests = $('#dashboard-requests');
	$dashboard_history = $('#dashboard-history');
	
	$dashboard_projects.click(function(){
		loadProjectList();
	});
	$dashboard_requests.click(function(){
		loadRequestList();
	});
}

function removeCurrDashboard(){
	$dashboard_projects.removeClass("current-dashboard");
	$dashboard_requests.removeClass("current-dashboard")
	$dashboard_history.removeClass("current-dashboard")
}

function loadProjectList(){
	try {
		$.ajax({
			url : contextPath + "/project/page",
			method : "GET",
			success : function(result) {
				//if (checkErrorOnResponse(result)) {
				$dashboard_body.html(result);
				removeCurrDashboard();
				$dashboard_projects.addClass("current-dashboard");
				current_dashboard = 'project';
				//}
			},
		});
	} catch (e) {
		alert( 'loadProjectList - ' + e);
	}
}

function loadRequestList(){
	try {
		$.ajax({
			url : contextPath + "/request/page",
			method : "GET",
			success : function(result) {
				//if (checkErrorOnResponse(result)) {
				$dashboard_body.html(result);
				removeCurrDashboard();
				$dashboard_requests.addClass("current-dashboard");
				current_dashboard = 'request';
				//}
			},
		});
	} catch (e) {
		alert( 'loadRequestList - ' + e);
	}
}

function nvl(val, dflt) {
	return ($.trim(val) == "" || val == undefined || val == null || val === "") ? dflt
			: val;
}

function loadMainPage(){
	try {
		$('#timer-div').timer('remove');
		$.ajax({
			url : contextPath + "/main",
			method : "GET",
	        async: false,
			success : function(result) {
				//if (checkErrorOnResponse(result)) {
				$main_body.html(result);
				$back.addClass('hide');
				//}
			},
		});
	} catch (e) {
		alert( 'loadMainPage - ' + e);
	}
}

function initCommons(){
	$logo.click(function(){
		if(useraccess != undefined){
			console.log(useraccess);
			loadMainPage();
			loadProjectList();
		} else {
			console.log("Inside else -- " +useraccess);
		}
	});
	$home.click(function(){
		loadMainPage();
		loadProjectList();
	});
	$back.click(function(){
		loadMainPage();
		if(current_dashboard == 'project'){
			loadProjectList();
		}else if(current_dashboard == 'request'){
			loadRequestList();
		}else if(current_dashboard == 'history'){
		}
		$back.addClass('hide');
	});
	$logout.click(function(){
		logout();
	});
}

//max of 12
function padLeft(str, len){
	var pad = "000000000000";
	return pad.substring(0, len - str.toString().length) + str.toString();
}

/**
 * @description escapes HTML tags and replaces single and double quotes with its
 *              corresponding html component
 * @param str -
 *            string to be escaped
 */
function escapeHTML(str) {
	if (nvl(str, null) != null) {
		return str.replace(/&/g, '&#38;').replace(/</g, '&#60;').replace(/>/g,
		'&#62;').replace(/\'/g, "&#039;").replace(/\"/g, "&#34;")
		.replace(/\u00f1/g, "&#241;").replace(/\u00D1/g, "&#209;");
	} else {
		return "";
	}
}

/**
 * @description unescapes HTML tags and replaces single and double quotes html
 *              components with single and double quotes literal
 * @param str -
 *            string to be unescaped
 */
function unescapeHTML(str) {
	if (nvl(str, null) != null) {
		return str.replace(/&#38;/g, '&').replace(/&#241;/g, "\u00f1").replace(
				/&#209;/g, "\u00D1").replace(/&#60;/g, '<').replace(/&#62;/g,
				'>').replace(/&#039;/g, "'").replace(/&#34;/g, "\"");
	} else {
		return "";
	}
}

function initDatePicker(){
	$('.date-picker').each(function(){
		$(this).datetimepicker({
			format: 'MMMM D, YYYY'
		});
	});
}

function initCurrency(){
	$('.currency').each(function(){
		$(this).number( true, 2 );
	});
}

function initLogin(){
	$('#loginBtn').click(function(){
		login();
	});
}

function login(){
	$.ajax({
		url : contextPath + "/login",
		method : "GET",
		data : {
			username: $('#username').val(),
			password: $('#password').val()
		},
		success : function(result) {
			//if (checkErrorOnResponse(result)) {
			if(result == 'success'){
				loadMainPage();
			}else if(result == 'failed'){
				$('#login-fail').removeClass('hide');
			}
			//}
		},
	});
}

function setHeader(){
	$('#username-header').html(fullName).removeClass('hide');
	$('#avatar').removeClass('hide');
	$('#header-menu').removeClass('hide');
}

function logout(){
	$.ajax({
		url : contextPath + "/logout",
		method : "POST",
		success : function(result) {
			//if (checkErrorOnResponse(result)) {
			$main_body.html(result);
			$('#username-header').html('').addClass('hide');
			$('#avatar').addClass('hide');
			$('#header-menu').addClass('hide');
			current_dashboard = '';
			useraccess = undefined;
			//}
		},
	});
}