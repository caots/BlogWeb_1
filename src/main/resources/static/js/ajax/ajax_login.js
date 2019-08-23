$(document).ready(function () {
    $(".btn-login ").click(function (event) {
        console.log("hihi");
        onSubmit(event);
    })
});

//action when click or enter
function onSubmit(event) {

    var username = $("#username").val();
    var password = $("#password").val();


    if (username.length === 0 || password.length === 0) {
        alert("username or password is blank!");
        event.preventDefault(event);
        return;
    }

    const loginForm = {
        "email": username,
        "password": password
    };

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "api/v1/public/user/login",
        data: JSON.stringify(loginForm),
        cache: false,
        timeout: 300000,
        success: function (data) {
            sessionStorage.setItem("token", data);
            document.cookie = "token=" + data;
            location.href = "home";
            passwordAdmin = password;

        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
            alert("error");
        }
    });
}
