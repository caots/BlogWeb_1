$(document).ready(function () {
    findAllPageNewsNumber();
    searchNewsByName();
});

function pageNews(size) {
    let contentRow = '';
    for (let i = 1; i <= size; i++) {
        contentRow += `<li><a href="#" class="page" id="_${i}" name="${i}" >${i}</a></li>`;
    }

    $(".pagination").html(
        `<li><a href="#" class="prev" id="prev">&laquo</a></li>`
        + contentRow +
        `<li><a href="#" class="next" id="next">&raquo;</a></li>`
    );
    $("#_1").addClass("active");
}

function findAllPageNewsNumber() {
    $.ajax({
        type: "GET",
        headers: {
            'adminbksoftwarevn': value_token_public,
        },
        url: "api/v1/public/news/size",
        success: function (size) {

            pageNews(size);

            $('.page').click(function () {
                const page = $(this).attr("name");
                for (let i = 1; i <= size; i++) {
                    var id = "#_" + i;
                    $(id).removeClass("active");
                }
                $(this).addClass("active");
                findAllNews(page);
            });
            findAllNews(1);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function findAllNews(page) {
    $.ajax({
        type: "GET",
        headers: {
            'adminbksoftwarevn': value_token_public,
        },
        url: "api/v1/public/news/page?page=" + page,
        success: function (news) {

            displayAllNews(news);
            //============ delete =============
            deleteNews();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}

function displayAllNews(news) {
    $("#column-news").html(
        "<td style='text-align: center'>STT</td>" +
        "<td> Title</td>" +
        "<td> Content</td>" +
        "<td> Post time</td>" +
        "<td> Action</td>"
    );
    const listSize = Object.keys(news).length;
    if (listSize > 0) {
        let contentRow = '';
        var index = 1;

        $('#total-record-news').text(listSize);
        news.map(function (newss) {
            var timePost = newss.timePost + '';
            var list = timePost.split(',');
            timePost = list[2] + '-' + list[1] + '-' + list[0];
            contentRow += `
                        <tr>  
                        <td> ${index} </td>
                        <td style="text-align: left"> ${newss.title} </td>
                        <td>  <a href="update-news?id=${newss.id}"  style="cursor: pointer;color: green">Nội dung </a>&nbsp;<br> </td> </td>
                        <td> ${timePost} </td>
                        <td> 
                              <div class="btn-group">
                                   <a class="btn btn-primary" href="create-news"><i class="fa fa-lg fa-plus"></i></a>
                                   <a class="btn btn-primary" href="update-news?id=${newss.id}" name="${newss.id}"><i class="fa fa-lg fa-edit"></i></a>
                                   <a class="btn btn-primary delete-news" name="${newss.id}" ><i class="fa fa-lg fa-trash" style="color: white"></i></a>
                              </div>
                        </td>
                        </tr>
                    `;
            index++;
        });
        $("#row-news").html(contentRow);
    }
}

//============ Delete News ========================
function deleteNews() {

    $('.delete-news').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/news/delete?id=" + id,
            timeout: 30000,

            success: function () {
                alert('Xóa thành công');
                location.href = "news";
            },

            error: function (jqXHR, textStatus, errorThrown) {
                alert("Xóa thất bại");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });
}

function searchNewsByName() {

    $("#title-news").keypress(function (event) {
        const keycode = event.keycode ? event.keycode : event.which;
        if (keycode == '13') {
            const title = $('#title-news').val();
            $.ajax({
                type: "GET",
                headers: {
                    "adminbksoftwarevn": value_token_public,
                },
                url: "api/v1/public/news/find-by-title/all?title=" + title,
                success: function (news) {
                    displayAllNews(news);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    errMess(jqXHR, textStatus, errorThrown);
                }
            })
        }
    });
}
