<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <div class="app-sidebar__user" style="padding-left: 20px;">
        <img class="app-sidebar__user-avatar"
             src="resources/img/abc.jpg"
             alt="User Image" width="30%" style="background: #eff7ff">
        <div>
            <div class="app-sidebar__user-name">Cao tran</div>
            <div class="app-sidebar__user-name">Trùm cuối ND</div>
        </div>
    </div>
    <ul class="app-menu">
        <li><a class="app-menu__item" href="home"><i class="app-menu__icon fas fa-home"></i><span
                class="app-menu__label"> Home</span></a></li>

        <li><a class="app-menu__item" href="user"><i class="fas fa-users"></i></i><span
                class="app-menu__label">&nbsp; User</span></a></li>

        <li><a class="app-menu__item" href="form-contact"><i class="fab fa-wpforms"></i><span
                class="app-menu__label">&nbsp; Contact Form </span></a></li>

        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview">
            <i class="far fa-newspaper"></i><span class="app-menu__label">&nbsp;News</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li>
                    <a class="treeview-item" href="category">Category</a>
                </li>
                <li>
                    <a class="treeview-item" href="news" rel="noopener"></i> News</a>
                </li>
            </ul>
        </li>

        <li><a class="app-menu__item" href="about-me"><i class="fas fa-meh"></i><span
                class="app-menu__label">&nbsp; Info User</span></a></li>
    </ul>
</aside>
