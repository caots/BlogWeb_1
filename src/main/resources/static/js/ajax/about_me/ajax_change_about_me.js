$(document).ready(function () {
    clickBtnAboutMeChangeSubmit();
});

function createAboutMe() {

    $('#btn-ok-about-me').click(function () {
        var nameAboutMe = $("#name-about-me").val();
        var valueAboutMe = $('#value-about-me').val();
        var category = {
            "name": nameAboutMe,
            'value': valueAboutMe
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/about-me",
            data: JSON.stringify(category),
            cache: false,
            timeout: 300000,
            success: function () {
                alert("Thêm thành công");
                $("#btn-ok-about-me").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Thêm thất bại ");
            }
        })
    });
}


function findAboutMeById(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/about-me/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            console.log(result);
            updateAboutMe(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function updateAboutMe(data) {

    $('#name-about-me').val(data.name);
    $('#value-about-me').val(data.value);
    $('#btn-ok-about-me').click(function () {
        data.name = $('#name-about-me').val();
        data.value = $('#value-about-me').val();
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/about-me",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('Chỉnh sửa thành công');
                $("#btn-ok-about-me").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Chỉnh sửa thất bại ");
            }
        });
    });
}

function clickBtnAboutMeChangeSubmit() {
    const urlCreateCategory = window.location.href;
    var str = urlCreateCategory.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findAboutMeById(id)
    } else {
        createAboutMe();
    }

}