$(document).ready(function () {
    findAllNameMenuCategory();
    findAllNameCategory();
});


function findAllNameMenuCategory() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/menu/all",
        success: function (menus) {
            const listSize = Object.keys(menus).length;
            if (listSize > 0) {
                let contentRow = '';
                menus.map(function (menu) {
                    contentRow += `
                       <option value="${menu.id}">${menu.name}</option>
                    `;
                });
                $("#menu-value").html(contentRow);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function findAllNameCategory() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/category/all",
        success: function (categories) {
            const listSize = Object.keys(categories).length;
            if (listSize > 0) {
                let contentRow = '';
                categories.map(function (category) {
                    contentRow += `
                       <option value="${category.id}">${category.name}</option>
                    `;
                });
                $("#category-value").html(contentRow);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

