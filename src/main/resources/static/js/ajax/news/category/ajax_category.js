$(document).ready(function () {
    findAllCategory();

});

function findAllCategory() {
    $.ajax({
        type: "GET",
        headers: {
            "adminbksoftwarevn": value_token_public,
        },
        url: "api/v1/public/category/all",
        success: function (categories) {
            $("#column-category").html(
                "<td style='text-align: center'> STT</td>" +
                "<td> Category </td>" +
                "<td> Menu</td>" +
                "<td> Action</td>"
            );
            const listSize = Object.keys(categories).length;
            if (listSize > 0) {
                $('#total-record').text(listSize);
                let contentRow = '';
                var index = 1;
                categories.map(function (category) {
                    contentRow += `
                        <tr>
                        <td style='text-align: center' width="15%"> ${index} </td>
                        <td  width="30%"> ${category.name} </td>
                        <td  width="30%" > ${category.menu.name} </td>
                        <td  width="25%"> 
                              <div class="btn-group">
                                   <a class="btn btn-primary" href="create-category"><i class="fa fa-lg fa-plus"></i></a>
                                   <a class="btn btn-primary" href="update-category?id=${category.id}"><i class="fa fa-lg fa-edit"></i></a>
                                   <a class="btn btn-primary delete-category" name="${category.id}" ><i class="fa fa-lg fa-trash" style="color: white"></i></a>
                              </div>
                        </td>
                        </tr>
                    `;
                    index++;
                });
                $("#row-category").html(contentRow);
                //============ delete =============
                deleteCategory();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}

//============ Delete Category ========================
function deleteCategory() {

    $('.delete-category').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/category/delete?id=" + id,
            timeout: 30000,
            success: function () {
                alert('Xóa thành công');
                location.href = "category";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Xóa thất bại");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });

}