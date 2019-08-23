$(document).ready(function () {
    findAllAboutMe();

});

function findAllAboutMe() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/about-me/all",
        success: function (aboutMes) {
            $("#column-about-me").html(
                "<td style='text-align: center'> STT</td>" +
                "<td> Name </td>" +
                "<td> Value</td>" +
                "<td> Action</td>"
            );
            const listSize = Object.keys(aboutMes).length;
            if (listSize > 0) {
                let contentRow = '';
                var index = 1;
                aboutMes.map(function (aboutMe) {
                    contentRow += `
                        <tr>
                        <td style='text-align: center' width="15%"> ${index} </td>
                        <td  width="30%"> ${aboutMe.name} </td>
                        <td  width="30%" > ${aboutMe.value} </td>
                        <td  width="25%"> 
                              <div class="btn-group">
                                   <a class="btn btn-primary" href="create-about-me"><i class="fa fa-lg fa-plus"></i></a>
                                   <a class="btn btn-primary" href="update-about-me?id=${aboutMe.id}"><i class="fa fa-lg fa-edit"></i></a>
                                   <a class="btn btn-primary delete-about-me" name="${aboutMe.id}" ><i class="fa fa-lg fa-trash" style="color: white"></i></a>
                              </div>
                        </td>
                        </tr>
                    `;
                    index++;
                });
                $("#row-about-me").html(contentRow);
                //============ delete =============
                deleteAboutMe();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}

function deleteAboutMe() {

    $('.delete-about-me').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/about-me/delete?id=" + id,
            timeout: 30000,
            success: function () {
                alert('Xóa thành công');
                location.href = "about-me";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Xóa thất bại");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });

}