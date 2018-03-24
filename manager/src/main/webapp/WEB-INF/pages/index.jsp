<%@ page language="java" contentType="text/html;charset=UTF-8"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>公众号后台管理系统</title>
        <%@include file="common.jsp"%>
        <style type="text/css">
            .help-block{
                margin-left: 38px;
            }
            .form-horizontal .has-feedback .form-control-feedback {
                right: -19px;
            }
            .row {
                margin-right: 0px;
                margin-left: 0px;
            }
            .btn-default.active,.btn-default.active:focus, .btn-default.active:hover{
                color:#fff;
                background-color: #6fb16f;
            }

        </style>

    </head>
    <body>
        <div class="row">
            <div class="col-md-12">
                <jsp:include page="menu.jsp"></jsp:include>
            </div>
        </div>
        <div class="row">

            <div class="col-md-12" id="content">

            </div>
        </div>



        <%--登录弹窗--%>
        <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="ffff" aria-hidden="true">
            <div class="modal-dialog" style="width: 400px;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">登录</h4>
                    </div>
                    <div class="modal-body">
                        <form id="loginForm" class="form-horizontal" role="form" action="/user/login" method="post">
                            <div class="form-group">
                                <div class="col-md-10 col-md-offset-1">
                                    <input type="text"  class="form-control" name="username" placeholder="请输入用户名">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-10 col-md-offset-1">
                                    <input type="password" class="form-control" name="password" placeholder="请输入密码">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-10 col-md-offset-1">
                                    <button id="loginBtn" class="btn btn-primary btn-block ">登录</button>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>

    </body>


</html>
