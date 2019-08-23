<%@ page contentType="text/html;charset=UTF-8" %>
<script src="resources/js/ajax/news/news/ajax_news.js"></script>
<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Bảng danh mục bài viết</h1>
        </div>
        <h3 class="title-body">
            Tổng số lượng : <span id="total-record-news"></span>
        </h3>
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item active"><a href="#">News</a></li>
        </ul>

    </div>

    <!-- TABLE -->
    <div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
        <ul class="app-nav">

            <li style="margin-top: 10px;">
                <button class="btn btn-primary" type="button">
                    <a href="create-news" style="color: white">
                        <i class="fa fa-fw fa-lg fa-check-circle"></i>
                        Thêm
                    </a>
                </button>
            </li>
            <li class="app-search" style="margin: auto;padding-right: 40px;">
                <input class="app-search__input" id="title-news" type="search" placeholder="Tìm kiếm">
                <button class="app-search__button" id="btn-search-news"><i class="fa fa-search"></i></button>
            </li>
        </ul>

        <table class="table text-center">
            <thead>
            <tr id="column-news" style="font-weight: 600"></tr>
            </thead>
            <tbody id="row-news"></tbody>
        </table>
        <div class="pageable">
            <ul class="pagination"></ul>
        </div>
    </div>
</main>