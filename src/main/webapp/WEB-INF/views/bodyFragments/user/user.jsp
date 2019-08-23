<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/user/ajax_user.js"></script>
<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Bảng người dùng</h1>
        </div>
        <h3 class="title-body">
            Tổng người dùng : <span id="total-record-user"></span>
        </h3>
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
            <li class="breadcrumb-item active"><a href="#">user</a></li>
        </ul>
    </div>
    <!-- TABLE -->
    <div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
        <div class="title-table">
            <ul class="app-nav">
                <li class="app-search">
                    <input class="app-search__input" id="name-user" type="search" placeholder="Tìm kiếm">
                    <button class="app-search__button" id="btn-search-user"><i class="fa fa-search"></i></button>
                </li>
            </ul>
        </div>

        <table class="table text-center">
            <thead>
            <tr id="column-user" style="font-weight: 600"></tr>
            </thead>
            <tbody id="row-user"></tbody>
        </table>
        <div class="pageable">
            <ul class="pagination"></ul>
        </div>
    </div>
</main>