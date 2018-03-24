function showAlert(message){
    $.alert(message);
};

var defaultOptions={
    method: 'post',                      //请求方式（*）
    striped: true,                      //是否显示行间隔色
    cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true,                   //是否显示分页（*）
    sortable: false,                     //是否启用排序
    sortOrder: "asc",                   //排序方式
    sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
    pageNumber:1,                       //初始化加载第一页，默认第一页
    pageSize: 25,                       //每页的记录行数（*）
    pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
    search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
    strictSearch: false,                //完全匹配搜索
    showColumns: false,                  //是否显示所有的列
    showRefresh: true,                  //是否显示刷新按钮
    minimumCountColumns: 2,             //最少允许的列数
    clickToSelect: true,                //是否启用点击选中行
    height: 600,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
    uniqueId: "id",                     //每一行的唯一标识，一般为主键列
    showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
    cardView: false,                    //是否显示详细视图
    detailView: false,                   //是否显示父子表
    singleSelect:false,                  //是否单选
};

var datetimepickOptions={
    language:  'zh-CN',   //语言包
    weekStart: 1,
    todayBtn:  1,         //今天按钮
    autoclose: 1,         //自动关闭
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,        //是否强制解析
    showMeridian: 1
};

Date.prototype.format = function(fmt){
    var o = {
        "M+":this.getMonth()+1,
        "d+":this.getDate(),
        "h+":this.getHours(),
        "m+":this.getMinutes(),
        "s+":this.getSeconds(),
        "q+":Math.floor((this.getMonth()+3)/3),
        "S":this.getMilliseconds()
    };
    if(/(y+)/.test(fmt)){
        fmt = fmt.replace(RegExp.$1,(this.getFullYear()+"").substr(4-RegExp.$1.length));
    }
    for(var k in o){
        if(new RegExp("("+k+")").test(fmt)){
            fmt =fmt.replace(RegExp.$1,(RegExp.$1.length==1)?(o[k]):(("00"+o[k]).substr((""+o[k]).length)));
        }
    }
    return fmt;
}