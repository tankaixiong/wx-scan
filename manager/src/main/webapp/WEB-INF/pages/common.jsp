<%@ page language="java" contentType="text/html;charset=UTF-8"  %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/bootstrap/plugins/bootstrapValidator.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/bootstrap/plugins/bootstrap-table.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/bootstrap/plugins/bootstrap-treeview.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/bootstrap/plugins/bootstrap-datetimepicker.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-3.0.0.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/plugins/bootstrapValidator.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/plugins/bootstrap-table.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/plugins/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/plugins/bootstrap-treeview.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/plugins/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/plugins/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/utils.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqueryExtends.js" ></script>


<script type="text/javascript">
    $(function(){
        $.ajaxSetup({
            beforeSend:function(xhr,setting){
                var lastTime = Number("<%=session.getLastAccessedTime()%>");
                var validTime = Number("<%=session.getMaxInactiveInterval()%>")*1000;
                var now = new Date().getTime();
                setting.url ='${pageContext.request.contextPath}'+setting.url;
                //判断session是否过期
                if(now>(lastTime+validTime)){
                    xhr.abort();
                    $.alert("登录信息已失效，请重新登录");
                    setTimeout(function(){
                        top.location="${pageContext.request.contextPath}/";
                    },2000);
                }
            }
        });

        $('#registerForm').bootstrapValidator({
            message: '无效值',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                username: {
                    message: '用户名无效',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: '用户名的长度必须在6-30个字符之间'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        different:{
                            field:'username',
                            message:'密码和用户名不能相同'
                        }
                    }
                },
                confirmPassword:{
                    validators:{
                        notEmpty: {
                            message: '确认密码不能为空'
                        },
                        identical:{
                            field:'password',
                            message:'两次输入的密码不一致'
                        },
                        different:{
                            field:'username',
                            message:'密码和用户名不能相同'
                        }
                    }
                }
            }
        }).on('success.form.bv', function(e) {
            // 拦截表单提交
            e.preventDefault();

            // 获得表单对象
            var $form = $(e.target);

            // 获得bootstrapValidator对象
            var bv = $form.data('bootstrapValidator');

            // 使用ajax提交表单数据
            $.post($form.attr('action'), $form.serialize(), function(result) {
                $("#regisModal").modal('hide');
                $.alert(result.info);
            }, 'json');

            $("#registerForm input").val('');

            $form.bootstrapValidator('disableSubmitButtons', false)     // 启用按钮
                    .bootstrapValidator('resetForm', true);             // 重置表单
        });

        //登录表单
        $('#loginForm').bootstrapValidator({
            message: '无效值',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                username: {
                    message: '用户名无效',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        stringLength: {
                            max: 30,
                            message: '用户名的长度必须在6-30个字符之间'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        different:{
                            field:'username',
                            message:'密码和用户名不能相同'
                        }
                    }
                }
            }
        }).on('success.form.bv', function(e) {
            // 拦截表单提交
            e.preventDefault();

            // 获得表单对象
            var $form = $(e.target);

            // 获得bootstrapValidator对象
            var bv = $form.data('bootstrapValidator');

            // 使用ajax提交表单数据
            $.post($form.attr('action'), $form.serialize(), function(result) {
                $("#loginModal").modal('hide');
                $.alert(result.info);
                if(result.info==''){
                    top.location="${pageContext.request.contextPath}/";
                }
            }, 'json');

            $("#loginForm input").val('');

            $form.bootstrapValidator('disableSubmitButtons', false)     // 启用按钮
                    .bootstrapValidator('resetForm', true);             // 重置表单


        });
    });
</script>