$(document).ready(function () {
    clickBtnNewsChangeSubmit();

});

//============ Create News ========================
function createNews() {
    let idCategory = '';
    $('#category-value').click(function () {
        idCategory = $(this).val();
    });
    var formData;
    $("#change-news").change(function () {
        formData = new FormData($('form')[0]);
    });

    $('#btn-ok-news').click(function () {
        const contentNews = $('.nicEdit-main').text();
        const titleNews = $('#name-title').val();
        const tags = $('#name-tag').val();
        uploadFile(formData).then(data => {
            const news = {
                "content": contentNews,
                'title': titleNews,
                'image': data.data.display_url,
            };
            $.ajax({
                type: "POST",
                contentType: "application/json",
                headers: {
                    "Authorization": tokenHeader_value,
                },
                url: "api/v1/admin/news?category-id=" + idCategory + "&tag=" + tags,
                data: JSON.stringify(news),
                cache: false,
                timeout: 300000,
                success: function (data) {
                    alert("Thêm thành công");
                    $('#url-image-news').attr('src', data.data.display_url);
                    $("#btn-ok-news").prop("disabled", true);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    errMess(jqXHR, textStatus, errorThrown);
                    alert("Thêm thất bại ");
                }
            })
        });

    });

}

//============ Find News By Id ===================

function findNewsById(id) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/news/find-by-id?id=" + id,
        timeout: 30000,
        success: function (result) {
            updateNews(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

// ============ update News ========================
function updateNews(data) {
    var listTag = '';
    console.log(data);
    data.tags.map(function (tag) {
        listTag += '@' + tag.name+' ';
    });
    $('#list-tag').text(listTag);
    $('#name-title').val(data.title);
    $('.nicEdit-main').text(data.content);
    $("#category-value").prop("disabled", true);
    $('#url-image-news').attr('src', data.image);
    var formData;
    $("#change-news").change(function () {
        formData = new FormData($('form')[0]);
    });
    $('#btn-ok-news').click(function () {

        data.content = $('.nicEdit-main').text();
        data.title = $('#name-title').val();
        uploadFile(formData).then(dataImage => {
            data.image = dataImage.data.display_url;
            $.ajax({
                type: "PUT",
                contentType: "application/json",
                headers: {
                    "Authorization": tokenHeader_value,
                },
                url: "api/v1/admin/news?list-tag="+ listTag,
                data: JSON.stringify(data),
                timeout: 30000,
                success: function () {
                    alert('Chỉnh sửa thành công');
                    $("#btn-ok-news").prop("disabled", true);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    errMess(jqXHR, textStatus, errorThrown);
                    alert("Chỉnh sửa thất bại ");
                }
            });
        });
    });
}

function clickBtnNewsChangeSubmit() {
    const urlCreateNews = window.location.href;
    console.log(urlCreateNews);
    var str = urlCreateNews.split("=");
    const id = str[str.length - 1];
    if ((id - 1) >= 0) {
        findNewsById(id)
    } else {
        createNews();
    }

}