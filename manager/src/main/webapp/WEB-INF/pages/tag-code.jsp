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
            <%--侧边栏版--%>
            <%--<div class="col-md-1">
                <c:if test="${sessionScope.get('username')!=null}">
                    <%@include file="menu.jsp"%>
                </c:if>
            </div>--%>
            <div class="col-md-12" id="content">

                <img src="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=${ticket.encodeTicket}"/><span>${ticket.url}</span>

            </div>
        </div>

    </body>


</html>
