var serverSelectorTmpl = [
    '<div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">',
        '<div class="modal-dialog" style="width: 400px;">',
            '<div class="modal-content">',
                '<div class="modal-header">',
                    '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>',
                    '<h4 class="modal-title">选择服务器</h4>',
                '</div>',
                '<div class="modal-body" id="serverList">',

                '</div>',
                '<div class="modal-footer">',
                    '<button type="button" class="btn btn-default" id="checkAll">全选</button>',
                    '<button type="button" class="btn btn-default" id="notCheckAll">全不选</button>',
                    '<button type="button" class="btn btn-primary" id="confirm">确定</button>',
                '</div>',
            '</div>',
        '</div>',
    '</div>'
];

var confirmTmpl = [
    '<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog"  aria-hidden="true">',
        '<div class="modal-dialog" style="width: 300px;">',
            '<div class="modal-content">',
                '<div class="modal-header">',
                    '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>',
                    '<h4 class="modal-title">重要提醒</h4>',
                '</div>',
                '<div class="modal-body" id="confirmText">',

                '</div>',
                '<div class="modal-footer">',
                    '<button class="btn btn-default" data-dismiss="modal" id="cancel">关闭</button>',
                    '<button class="btn btn-primary" data-dismiss="modal" id="confirm">确定</button>',
                '</div>',
            '</div>',
        '</div>',
    '</div>'
];

var alertTmpl = [
    '<div class="modal fade" id="tipModal" tabindex="-1" role="dialog" aria-labelledby="ffff" aria-hidden="true">',
        '<div class="modal-dialog" style="width: 400px;">',
            '<div class="modal-content">',
                '<div class="modal-header">',
                    '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>',
                    '<h4 class="modal-title">提示</h4>',
                '</div>',
                '<div class="modal-body" id="tipText">',

                '</div>',
                '<div class="modal-footer">',
                    '<button class="btn btn-default" data-dismiss="modal">关闭</button>',
                '</div>',
            '</div>',
        '</div>',
    '</div>'
];
jQuery.serverSelector = function(options,callBack){
    var defaultOption = {
        singleMode:false
    };
    if(typeof(options)=='function'){
        callBack = options;
    }

    var $serverBox = $(serverSelectorTmpl.join(''));
    if(typeof(options)=='object'){
        $.extend(defaultOption,options);
        if(defaultOption.singleMode){
            $serverBox.find("#checkAll").remove();
            $serverBox.find("#notCheckAll").remove();
        }
    }else{
        $serverBox.find("#checkAll").click(function(){
            $serverBox.find("#serverList button").addClass("active");
        });
        $serverBox.find("#notCheckAll").click(function(){
            $serverBox.find("#serverList button").removeClass("active");
        });
    }
    $serverBox.find(".close").click(function(){
        $serverBox.prev().remove();
        $serverBox.remove();
    });

    $serverBox.find("#confirm").click(function(){
        $($serverBox.find("#serverList button")).each(function(){
            if($(this).hasClass("active")){
                callBack.call(this,$(this).data("name"),$(this).data("ip"),$(this).data("tcpPort"),$(this).data("httpPort"));
            }
        });
        $serverBox.find(".close").click();
    });
    $.post("/server/list",function(result){
        $(result).each(function(index,obj){
            var $btnObj = $('<button type="button" class="btn btn-default" style="margin: 2px;">'+obj.name+'</button>');
            $btnObj.click(function(){
                if($btnObj.hasClass("active")){
                    $btnObj.removeClass("active");
                }else{
                    if(defaultOption.singleMode&& $serverBox.find("#serverList button.active").length>=1){
                        $.alert("不能选择多个");
                        return;
                    }
                    $btnObj.addClass("active");
                }
            });
            $btnObj.data('name',obj.name);
            $btnObj.data('ip',obj.ip);
            $btnObj.data('tcpPort',obj.port);
            $btnObj.data('httpPort',obj.httpPort);
            $serverBox.find("#serverList").append($btnObj);
        });
    });

    $serverBox.modal('show');
};

jQuery.confirm = function(options){
    var $confirmBox = $(confirmTmpl.join(''));
    $confirmBox.find(".close").click(function(){
        $confirmBox.prev().remove();
        $confirmBox.remove();
    });
    if(options.content!=null){
        $confirmBox.find("#confirmText").html(options.content);
    }

    $confirmBox.find("#confirm").click(function(){
        if(options.confirm!=null){
            options.confirm.call(this);
        }
        $confirmBox.find(".close").click();
    });
    $confirmBox.find("#cancel").click(function(){
        if(options.cancel!=null){
            options.cancel.call(this);
        }
        $confirmBox.find(".close").click();
    });
    $confirmBox.modal('show');
};
jQuery.alert = function(content){
    var $alertBox = $(alertTmpl.join(''));

    $alertBox.find(".close,.modal-footer .btn").click(function(){
        $alertBox.modal('hide');
        setTimeout(function(){
            $alertBox.remove();
        },1000);
    });
    if(content!=null){
        $alertBox.find("#tipText").html(content);
    }
    $(".modal-backdrop").remove();
    $alertBox.modal('show');
    setTimeout(function(){
        $alertBox.find(".close").click();
    },1000);
};

jQuery.fn.extend({
    'timePicker':function(options){
        $(this).datetimepicker($.extend({},datetimepickOptions,options));
    }
})