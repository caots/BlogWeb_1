$(document).ready(function () {
    findAllUser(1);
    findAllPageUserNumber();
    searchUserByName();

});

//==================================page=============================.unbind('click')
function pageUser(size) {
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

function findAllPageUserNumber() {
    $.ajax({
        type: "GET",
        headers: {
            "Authorization": tokenHeader_value,
        },
        url: "api/v1/admin/user/size",
        success: function (size) {

            pageUser(size);

            $('.page').click(function () {
                const page = $(this).attr("name");
                for (let i = 1; i <= size; i++) {
                    var id = "#_" + i;
                    $(id).removeClass("active");
                }
                $(this).addClass("active");
                findAllUser(page);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    });
}

function findAllUser(page) {
    //============ Get All User ========================
    $.ajax({
        type: "GET",
        headers: {
            "Authorization": tokenHeader_value,
        },
        url: "api/v1/admin/user/page?page=" + page,
        success: function (users) {
            displayOnTable(users);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}

function displayOnTable(users) {
    $("#column-user").html(
        "<td style='text-align: center'> STT</td>" +
        "<td> Name </td>" +
        "<td> Email</td>" +
        "<td> Init Date</td>" +
        "<td> Chức năng</td>"
    );
    const listSize = Object.keys(users).length;
    if (listSize > 0) {
        $('#total-record-user').text(listSize);
        let contentRow = '';
        var index = 1;
        users.map(function (user) {

            contentRow += `
                        <tr>
                        <td> ${index} </td>
                        <td> ${user.email} </td>
                        <td> ${user.initDate} </td>
                        <td> 
                              <div class="btn-group">
                                   <a class="btn btn-primary delete-user" name="${user.id}" ><i class="fa fa-lg fa-trash" style="color: white"></i></a>
                              </div>
                        </td>
                        </tr>
                    `;
            index++;
        });
        $("#row-user").html(contentRow);
        //============ delete =============
        deleteUser();
    }
}

//============ Delete User ========================
function deleteUser() {

    $('.delete-user').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/user/delete?id=" + id,
            timeout: 30000,
            success: function () {
                alert('Xóa thành công');
                location.href = "user";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Xóa thất bại");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });

}

function searchUserByName() {


    $("#name-user").keypress(function (event) {
        const keycode = event.keycode ? event.keycode : event.which;
        if (keycode == '13') {
            const nameUser = $('#name-user').val();
            console.log(nameUser);
            $.ajax({
                type: "GET",
                headers: {
                    "Authorization": tokenHeader_value,
                },
                url: "api/v1/admin/user/name/all?name=" + nameUser,
                success: function (users) {
                    displayOnTable(users)
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    errMess(jqXHR, textStatus, errorThrown);
                }
            })
        }
    });

}