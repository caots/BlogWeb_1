<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main class="app-content">
    <div class="row user">
        <div class="col-md-12">
            <div class="profile">
                <div class="info">
                    <img class="user-img" src="https://avatars3.githubusercontent.com/u/44569407?s=460&v=4"
                         alt="User Image">
                    <h4>Cao Trần</h4>
                    <p>Developer</p>
                </div>
                <div class="cover-image"></div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="tile p-0">
                <ul class="nav flex-column nav-tabs user-tabs">
                    <li class="nav-item"><a class="nav-link" href="#user-settings" data-toggle="tab">Settings</a></li>
                </ul>
            </div>
        </div>
        <div class="col-md-9">
            <div class="tab-content">
                <div class="tab-pane active" id="user-timeline">
                    <div class="timeline-post">
                        <div class="post-media">
                            <a href="#">
                                <img width="10%"
                                     src="">
                            </a>
                            <div class="content">
                                <h5><a href="#">Cao Trần</a></h5>
                                <p class="text-muted">
                                    <small>2 January at 9:30</small>
                                </p>
                            </div>
                        </div>
                        <div class="post-content">
                            <p>
                                Cần một quả gì đó siêu to khổng lồ
                            </p>
                        </div>
                        <ul class="post-utility">
                            <li class="likes"><a href="#"><i class="fa fa-fw fa-lg fa-thumbs-o-up"></i>Like</a></li>
                            <li class="shares"><a href="#"><i class="fa fa-fw fa-lg fa-share"></i>Share</a></li>
                            <li class="comments"><i class="fa fa-fw fa-lg fa-comment-o"></i> 5 Comments</li>
                        </ul>
                    </div>
                    <div class="tab-pane fade" id="user-settings">
                        <div class="tile user-settings">
                            <h4 class="line-head">Settings</h4>
                            <form>
                                <div class="row mb-4">
                                    <div class="col-md-4">
                                        <label>First Name</label>
                                        <input class="form-control" type="text">
                                    </div>
                                    <div class="col-md-4">
                                        <label>Last Name</label>
                                        <input class="form-control" type="text">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-8 mb-4">
                                        <label>Email</label>
                                        <input class="form-control" type="text">
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="col-md-8 mb-4">
                                        <label>Mobile No</label>
                                        <input class="form-control" type="text">
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="col-md-8 mb-4">
                                        <label>Office Phone</label>
                                        <input class="form-control" type="text">
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="col-md-8 mb-4">
                                        <label>Home Phone</label>
                                        <input class="form-control" type="text">
                                    </div>
                                </div>
                                <div class="row mb-10">
                                    <div class="col-md-12">
                                        <button class="btn btn-primary" type="button"><i
                                                class="fa fa-fw fa-lg fa-check-circle"></i> Save
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>