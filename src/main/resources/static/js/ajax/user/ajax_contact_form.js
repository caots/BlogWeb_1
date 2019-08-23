$(document).ready(function () {
    findAllContactForm();
});

function findAllContactForm() {
    $.ajax({
        type: "GET",
        headers: {
            "Authorization": tokenHeader_value,
        },
        url: "api/v1/admin/contact-form/all" ,
        success: function (contactForms) {
            $("#column-form-contact").html(
                "<td style='text-align: center'> STT</td>" +
                "<td> Name</td>" +
                "<td> Email </td>" +
                "<td> Nội dung</td>" +
                "<td> Check</td>" +
                "<td> Chức năng</td>"
            );
            const listSize = Object.keys(contactForms).length;
            if (listSize > 0) {
                $('#total-record').text(listSize);
                let contentRow = '';
                var index = 1;


                contactForms.map(function (contactForm) {
                    var checked = contactForm.checked;
                    if (checked === true) {
                        checked = 'Đã Check';
                    } else {
                        checked = 'Chưa check';
                    }
                    contentRow += `
                        <tr>
                        <td> ${index} </td>
                        <td> ${contactForm.name} </td>
                        <td> ${contactForm.email} </td>
                        <td> 
                             <a href="content-form-contact?id=${contactForm.id}" style="cursor: pointer;color: green">Nội dung </a>&nbsp;<br> </td>
                        </td>
                        <td> ${checked} </td>
                        <td> 
                              <div class="btn-group">
                                   <a class="btn btn-primary" href="form-send-email?id=${contactForm.id}" name="${contactForm.id}"><i class="fa fa-lg fa-edit"></i></a>
                                   <a class="btn btn-primary delete-form" name="${contactForm.id}" ><i class="fa fa-lg fa-trash" style="color: white"></i></a>
                              </div>
                        </td>
                        </tr>
                    `;
                    index++;
                });
                $("#row-form-contact").html(contentRow);
                //============ delete =============
                deleteContactForm();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            errMess(jqXHR, textStatus, errorThrown);
        }
    })
}

function deleteContactForm() {

    $('.delete-form').click(function () {
        const id = $(this).attr("name");
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": tokenHeader_value,
            },
            url: "api/v1/admin/contact-form/delete?id=" + id,
            timeout: 30000,
            success: function () {
                alert('Xóa thành công');
                location.href = "form-contact";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Xóa thất bại");
                errMess(jqXHR, textStatus, errorThrown);
            }
        });
    });

}