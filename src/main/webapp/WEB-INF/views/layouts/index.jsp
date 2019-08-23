<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../library/taglib.jsp" %>
<html lang="en">
<head>
    <title><tiles:getAsString name="title"/></title>
    <%@include file="../../library/library_css.jsp" %>


    <meta name="description"
          content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
    <!-- Twitter meta-->
    <meta property="twitter:card" content="summary_large_image">
    <meta property="twitter:site" content="@pratikborsadiya">
    <meta property="twitter:creator" content="@pratikborsadiya">
    <!-- Open Graph Meta-->
    <meta property="og:type" content="website">
    <meta property="og:site_name" content="Vali Admin">
    <meta property="og:title" content="Vali - Free Bootstrap 4 admin theme">
    <meta property="og:url" content="http://pratikborsadiya.in/blog/vali-admin">
    <meta property="og:image" content="http://pratikborsadiya.in/blog/vali-admin/hero-social.png">
    <meta property="og:description"
          content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

    <%--=================================== CSS =======================================--%>
    <script src="resources/js/ajax/common.js"></script>
    <script src="resources/js/ajax/ajax_home.js"></script>


</head>
<body class="app sidebar-mini rtl">

<tiles:insertAttribute name="menu"/>

<tiles:insertAttribute name="header"/>

<tiles:insertAttribute name="body"/>
<!----------------------Script------------------------------------ -->
<%@include file="../../library/library_js.jsp" %>
</body>
</html>