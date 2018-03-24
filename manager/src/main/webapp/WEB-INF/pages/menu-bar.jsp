<%@ page language="java" contentType="text/html;charset=UTF-8"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>后台管理系统</title>
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
        <script>
            $(function(){

                $('body').on('click','button[sub-menu-del]',function(){
                    $(this).parent().parent().remove();
                });

                $('body').on('click','button[sub-menu-add]',function(){

                    var submenu=$(this).parent().parent().parent().children('.list-group');
                    if(submenu.children('li').length<5){
                        submenu.prepend('<li class="list-group-item">Porta ac consectetur ac <span class="pull-right"> <button type="button" class="btn " style="padding: 0px;"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> edit</button> <button type="button" class="btn " sub-menu-del style="padding: 0px;"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span> delete</button> </span> </li>');
                    }else{
                        alert('最多创建5个');
                    }
                });

                $('body').on('click','button[menu-del]',function(){
                    $(this).parent().parent().parent().parent().remove();
                });

                $('button[menu-add]').click(function () {
                    if($('#menu-contain .col-md-4').length<3){
                        $('#menu-contain').append('<div class="col-md-4"> <div class="panel panel-default"> <ul class="list-group"> </ul> <div class="panel-footer">Panel footer <span class="pull-right"> <button type="button" class="btn " sub-menu-add style="padding: 0px;"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> add</button> <button type="button" class="btn " menu-del style="padding: 0px;"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span> delete</button> </span> </div> </div> </div>');
                    }else{
                        alert("最多只能创建三个");
                    }
                });
            });
        </script>

    </head>
    <body>
        <div class="row">
            <div class="col-md-12">
                <jsp:include page="menu.jsp"></jsp:include>
            </div>
        </div>

        <button type="button" class="btn " menu-add ><span class="glyphicon glyphicon-minus" aria-hidden="true"></span> delete</button>
        <div class="row" id="menu-contain">
            <div class="col-md-4">
                <div class="panel panel-default">

                    <!-- List group -->
                    <ul class="list-group">
                        <li class="list-group-item">Porta ac consectetur ac
                            <span class="pull-right">
                                <button type="button" class="btn " style="padding: 0px;"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> edit</button>
                                <button type="button" class="btn " sub-menu-del style="padding: 0px;"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span> delete</button>
                            </span>
                        </li>
                        <li class="list-group-item">Vestibulum at eros
                           <span class="pull-right">
                                <button type="button" class="btn " style="padding: 0px;"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> edit</button>
                                <button type="button" class="btn " sub-menu-del style="padding: 0px;"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span> delete</button>
                            </span>
                        </li>
                    </ul>
                    <div class="panel-footer">Panel footer
                        <span class="pull-right">
                            <button type="button" class="btn " sub-menu-add style="padding: 0px;"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> add</button>
                            <button type="button" class="btn " menu-del style="padding: 0px;"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span> delete</button>
                        </span>
                    </div>
                </div>
            </div>

        </div>

        <div class="row ">

        </div>

    </body>


</html>
