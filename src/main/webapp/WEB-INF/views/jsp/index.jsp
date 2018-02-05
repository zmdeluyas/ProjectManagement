<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Project Management</title>
<!-- CSS -->
<spring:url value="/resources/core/css/thirdparty/bootstrap-theme.min.css" var="bootstrapThemeCss" />
<spring:url value="/resources/core/css/thirdparty/bootstrap.min.css" var="bootstrapCss" />
<spring:url value="/resources/core/css/thirdparty/dataTables.bootstrap.min.css" var="dataTablesbootstrapCss" />
<spring:url value="/resources/core/css/thirdparty/responsive.bootstrap.min.css" var="responsivebootstrapCss" />
<spring:url value="/resources/core/css/thirdparty/buttons.dataTables.min.css" var="buttonsdataTablesCss" />
<spring:url value="/resources/core/css/thirdparty/buttons.bootstrap.min.css" var="buttonsbootstrapCss" />
<spring:url value="/resources/core/css/thirdparty/bootstrap-datetimepicker.min.css" var="bootstrapdatetimepickerCss" />
<spring:url value="/resources/core/css/common.css" var="commonCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${bootstrapThemeCss}" rel="stylesheet" />
<link href="${dataTablesbootstrapCss}" rel="stylesheet" />
<link href="${responsivebootstrapCss}" rel="stylesheet" />
<link href="${buttonsdataTablesCss}" rel="stylesheet" />
<link href="${buttonsbootstrapCss}" rel="stylesheet" />
<link href="${bootstrapdatetimepickerCss}" rel="stylesheet" />
<link href="${commonCss}" rel="stylesheet" />

<!-- JavaScripts -->
<spring:url value="/resources/core/js/thirdparty/jquery-3.1.1.min.js" var="jqueryjs" />
<spring:url value="/resources/core/js/thirdparty/bootstrap.min.js" var="bootstrapjs" />
<spring:url value="/resources/core/js/thirdparty/jquery.dataTables.min.js" var="dataTablesjqueryjs" />
<spring:url value="/resources/core/js/thirdparty/jquery.number.min.js" var="jquerynumberj" />
<spring:url value="/resources/core/js/thirdparty/dataTables.bootstrap.min.js" var="dataTablesbootstrapjs" />
<spring:url value="/resources/core/js/thirdparty/dataTables.responsive.min.js" var="dataTablesresponsivejs" />
<spring:url value="/resources/core/js/thirdparty/responsive.bootstrap.min.js" var="responsivebootstrapjs" />
<spring:url value="/resources/core/js/thirdparty/dataTables.buttons.min.js" var="dataTablesbuttonsjs" />
<spring:url value="/resources/core/js/thirdparty/buttons.bootstrap.min.js" var="buttonsbootstrapjs" />
<spring:url value="/resources/core/js/thirdparty/moment.js" var="momentjs" />
<spring:url value="/resources/core/js/thirdparty/bootstrap-datetimepicker.min.js" var="bootstrapdatetimepickerjs" />
<spring:url value="/resources/core/js/thirdparty/timer.jquery.min.js" var="timerjqueryjs" />
<spring:url value="/resources/core/js/thirdparty/date-eu.js" var="dateeujs" />
<spring:url value="/resources/core/js/common.js" var="commonjs" />
<spring:url value="/resources/core/js/project/projectlist.js" var="projectlistjs" />
<spring:url value="/resources/core/js/project/project.js" var="projectjs" />
<spring:url value="/resources/core/js/request/requestlist.js" var="requestlistjs" />
<spring:url value="/resources/core/js/request/request.js" var="requestjs" />
<spring:url value="/resources/core/js/attachment/attachment.js" var="attachmentjs" />
<script language="Javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${jqueryjs}"></script>
<script src="${bootstrapjs}"></script>
<script src="${dataTablesjqueryjs}"></script>
<script src="${jquerynumberj}"></script>
<script src="${dataTablesbootstrapjs}"></script>
<script src="${dataTablesresponsivejs}"></script>
<script src="${responsivebootstrapjs}"></script>
<script src="${dataTablesbuttonsjs}"></script>
<script src="${buttonsbootstrapjs}"></script>
<script src="${momentjs}"></script>
<script src="${bootstrapdatetimepickerjs}"></script>
<script src="${timerjqueryjs}"></script>
<script src="${dateeujs}"></script>
<script src="${commonjs}"></script>
<script src="${projectlistjs}"></script>
<script src="${projectjs}"></script>
<script src="${requestlistjs}"></script>
<script src="${requestjs}"></script>
<script src="${attachmentjs}"></script>
</head>
<body>
<div id="main-header">
	<jsp:include page="common/header.jsp"></jsp:include>
</div>
<div class="container-fluid" id="main-body">
	<c:choose>
	    <c:when test="${appUser.username == null}">
	       <jsp:include page="common/login.jsp"></jsp:include>
	    </c:when>  
	    <c:when test="${appUser.username != null}">
	       <jsp:include page="common/main.jsp"></jsp:include>
	    </c:when>  
	</c:choose>
</div>
<div id="main-footer">
	<%-- <jsp:include page="common/footer.jsp"></jsp:include> --%>
</div>
<script>
	var $main_body = $('#main-body');
	var $logo = $('#logo');
	var $home = $('#home');
	var $back = $('#back');
	var $logout = $('#logout');
	initCommons();
</script>
</body>
</html>