<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../library/library_css.jsp" %>
    <!-- Font-icon css-->
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>login</title>
    <!-- ajax login -->
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js">
    </script>
    <script src="resources/js/ajax/common.js"></script>
    <script src="resources/js/ajax/ajax_login.js"></script>

</head>
<body>

<section class="material-half-bg">
    <div class="cover"></div>
</section>
<section class="login-content">
    <%--<div class="logo">--%>
        <%--<img src="resources/img/bksoftwareLogo.png"--%>
             <%--alt="" class="img-fluid">--%>
    <%--</div>--%>
    <div class="login-box">
        <div class="login-form">
            <div class="app-sidebar__user" style="justify-content: center;margin: 0;padding: 0;">
                <img class="app-sidebar__user-avatar"
                     src="resources/img/abc.jpg"
                     alt="User Image" width="30%" style="background: #eff7ff">
            </div>
            <div class="form-group">
                <label class="control-label">USERNAME</label>
                <input class="form-control" type="text" placeholder="Email" autofocus id="username">
            </div>
            <div class="form-group">
                <label class="control-label">PASSWORD</label>
                <input class="form-control" type="password" placeholder="Password" id="password">
            </div>
            <div class="form-group">
                <div class="utility">
                    <div class="animated-checkbox">
                        <label>
                            <input type="checkbox"><span class="label-text">Stay Signed in</span>
                        </label>
                    </div>
                    <p class="semibold-text mb-2"><a href="#" data-toggle="flip">Forgot Password ?</a></p>
                </div>
            </div>
            <div class="form-group btn-container">
                <button class="btn btn-primary btn-block btn-login"><i class="fa fa-sign-in fa-lg fa-fw"></i>SIGN IN
                </button>
            </div>
        </div>
        <div class="forget-form">
            <h3 class="login-head"><i class="fa fa-lg fa-fw fa-lock"></i>Forgot Password ?</h3>
            <div class="form-group">
                <label class="control-label">EMAIL</label>
                <input class="form-control" type="text" placeholder="Email">
            </div>
            <div class="form-group btn-container">
                <button class="btn btn-primary btn-block btn-login"><i class="fa fa-unlock fa-lg fa-fw"></i>RESET
                </button>
            </div>
            <div class="form-group mt-3">
                <p class="semibold-text mb-0"><a href="#" data-toggle="flip"><i class="fa fa-angle-left fa-fw"></i> Back
                    to Login</a></p>
            </div>
        </div>
    </div>
</section>
<!-- Essential javascripts for application to work-->
<%@include file="../../library/library_js.jsp" %>
<script type="text/javascript">
    // Login Page Flipbox control
    $('.login-content [data-toggle="flip"]').click(function () {
        $('.login-box').toggleClass('flipped');
        return false;
    });
</script>

</body>
</html>