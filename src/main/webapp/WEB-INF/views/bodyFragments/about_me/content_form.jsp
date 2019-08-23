<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/user/ajax_content_form.js"></script>
<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-edit"></i> Thông tin Form khách hàng</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item"><a href="#">content</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-md-6">
            <div class="tile">
                <h3 class="tile-title">Nội dung Form khách hàng</h3>
                <div class="tile-body">
                    <form>
                        <div class="form-group">
                            <label class="control-label">Nội dung</label>
                            <textarea id="content-form" class="form-control" rows="4"></textarea>
                        </div>
                    </form>
                </div>
                <div class="tile-footer">
                    </button>&nbsp;&nbsp;&nbsp;<a class="btn btn-secondary" href="form-contact"><i
                        class="fa fa-fw fa-lg fa-times-circle"></i>Quay lại</a>
                </div>
            </div>
        </div>
        <div class="col-sm-3"></div>
    </div>
</main>