$(document).ready(function () {
    clickBtnSendMailChangeSubmit();
});

function findFormById(id) {

    $.ajax({
        type: "GET",
        dataType: "json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/contact-form/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            $('#name-email').val(result.email);
            sendEmailContactForm();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}


function sendEmailContactForm() {

    $("#btn-ok-email").click(function () {

        var email = $("#name-email").val();
        var title = $("#title-email").val();
        var content = $("#content-email").val();

        $.ajax({
            type: "GET",
            dataType: "json",
            headers: {
                "adminbksoftwarevn": value_token_public,
            },
            url: "api/v1/public/send-email?email=" + email + "&title=" + title + "&content=" + content,
            timeout: 30000,
            success: function () {
                alert("Gửi email thành công");
                $('#btn-ok-email').prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Gửi email không thành công");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });

    })
}

function clickBtnSendMailChangeSubmit() {
    const urlForm = window.location.href;
    var str = urlForm.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findFormById(id)
    }
}
