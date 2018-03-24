<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"  %>

<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid"></div>
	<div class="navbar-header">
		<a class="navbar-brand">后台管理系统</a>
	</div>
	<div class="pull-left" style="padding-top: 10px;">
		<c:if test="${sessionScope.get('username')!=null}">
		<ul class="nav nav-pills" id="menus">
		<li ><a href="${pageContext.request.contextPath}/mg/tag-list"  >标签扫码管理</a></li>
		<li ><a href="${pageContext.request.contextPath}/mg/msg-list" >消息回复</a></li>
		<li ><a href="#"  role="button" data-toggle="modal" data-target="#regisModal">账号维护</a></li>
		</ul>
		</c:if>
	</div>

	<div class="pull-right" style="padding-top: 10px;padding-right: 10px;">
		<c:if test="${sessionScope.get('username')!=null}">
			欢迎登录: <a href="#" >${sessionScope.get("username")}</a>
			<a href="${pageContext.request.contextPath}/user/logout" class="btn btn-success navbar-btn" role="button" >退出</a>
		</c:if>
		<c:if test="${sessionScope.get('username')==null}">
			<a href="#" class="btn btn-default" role="button" data-toggle="modal" data-target="#loginModal">登录</a>
		</c:if>
	</div>
</nav>

<%--注册弹窗--%>
<div class="modal fade" id="regisModal" tabindex="-1" role="dialog" aria-labelledby="ffff" aria-hidden="true">
	<div class="modal-dialog" style="width: 400px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">注册</h4>
			</div>
			<div class="modal-body">
				<form id="registerForm" class="form-horizontal" action="/user/register" role="form" method="post">
					<div class="form-group">
						<div class="col-md-4 control-label">
							<label>用户名</label>
						</div>
						<div class="col-md-7">
							<input type="text"  class="form-control" name="username" placeholder="请输入用户名">
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-4 control-label">
							<label>密码</label>
						</div>
						<div class="col-md-7">
							<input type="password" class="form-control" name="password" placeholder="请输入密码">
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-4 control-label">
							<label>确认密码</label>
						</div>
						<div class="col-md-7">
							<input type="password"  class="form-control" name="confirmPassword" placeholder="再次输入密码">
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-10 col-md-offset-1">
							<button type="submit" class="btn btn-primary btn-block ">保存</button>
						</div>

					</div>

				</form>
			</div>
		</div>
	</div>
</div>