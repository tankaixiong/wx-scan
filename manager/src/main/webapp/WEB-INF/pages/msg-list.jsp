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
                <form class="form-horizontal" action="${pageContext.request.contextPath}/mg/msg-sub" method="post">
                    <div class="form-group">
                        <label for="textarea1" class="col-sm-2 control-label">关注自动回复</label>
                        <div class="col-sm-10">
                            <textarea style="height: 200px;" id="textarea1" name="subtext" class="form-control" rows="3">${sbuText}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">保存</button>
                        </div>
                    </div>
                </form>

                <form class="form-horizontal" action="${pageContext.request.contextPath}/mg/msg-resp" method="post">
                    <div class="form-group">
                        <label for="textarea2" class="col-sm-2 control-label">收到消息回复</label>
                        <div class="col-sm-10">
                            <textarea id="textarea2" style="height: 200px;" name="resptext" class="form-control" rows="3">${respText}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">保存</button>
                        </div>
                    </div>
                </form>


            </div>
        </div>



    </body>


</html>
