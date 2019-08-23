
$(document).ready(function () {
    clickBtnCategoryChangeSubmit();
});

//============ Create Category ========================
function createCategory() {

    let idMenu = '';
    $('#menu-value').click(function () {
        idMenu = $(this).val();
    });
    $('#btn-ok-category').click(function () {
        const nameCategory = $("#name-category").val();
        const category = {
            "name": nameCategory,
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/category?menu-id=" + idMenu,
            data: JSON.stringify(category),
            cache: false,
            timeout: 300000,
            success: function () {
                alert("Thêm thành công");
                $("#btn-ok-category").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Thêm thất bại ");
            }
        })
    });
}

//============ Find Big Category By Id ===================

function findCategoryById(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/category/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateCategory(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

// ============ UPDATE  Category ========================
function updateCategory(data) {

    $('#name-category').val(data.name);
    $("#menu-value").prop("disabled", true);
    $('#btn-ok-category').click(function () {
        data.name = $('#name-category').val();
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/category",
            data: JSON.stringify(data),
            timeout: 30000,
            success: function () {
                alert('Chỉnh sửa thành công');
                $("#btn-ok-category").prop("disabled", true);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                errMess(jqXHR, textStatus, errorThrown);
                alert("Chỉnh sửa thất bại ");
            }
        });
    });
}

function clickBtnCategoryChangeSubmit() {
    const urlCreateCategory = window.location.href;
    var str = urlCreateCategory.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findCategoryById(id)
    } else {
        createCategory();
    }

}