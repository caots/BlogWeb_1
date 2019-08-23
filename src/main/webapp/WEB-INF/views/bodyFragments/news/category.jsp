<%@ page contentType="text/html;charset=UTF-8" %>
<script src="resources/js/ajax/news/category/ajax_category.js"></script>

<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Bảng danh mục chủ đề</h1>
        </div>
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item active"><a href="#">Category</a></li>
        </ul>
    </div>
    <!-- TABLE -->
    <div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
        <ul class="app-nav">
            <li style="margin-top: 10px;">
                <button class="btn btn-primary" type="button">
                    <a href="create-category" style="color: white">
                        <i class="fa fa-fw fa-lg fa-check-circle"></i>
                        Thêm
                    </a>
                </button>
            </li>
            <li class="app-search" style="margin: auto"></li>
        </ul>

        <table class="table text-center">
            <thead>
            <tr id="column-category" style="font-weight: 600"></tr>
            </thead>
            <tbody id="row-category"></tbody>
        </table>

    </div>
</main>