layui.use(['form','layer','laydate','table','upload'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        upload = layui.upload,
        table = layui.table;

    //友链列表
    var tableIns = table.render({
        elem: '#linkList',
        url : 'findRequireByPageByNurse.do',
        page : true,
        cellMinWidth : 95,
        height : "full-104",
        limit : 5,
        limits : [5,10,15,20],
        id : "linkListTab",
        cols : [[
        	{type: "checkbox", fixed:"left", width:50},
        	{field: 'pname', title: '预订人', width:80,templet:"#pname"},
            {field: 'mname', title: '药品名称', minWidth:50,templet:"#mname"},
            {field: 'category', title: '药品类别', minWidth:50,templet:"#cate"},
            {field: 'description', title: '功效描述', minWidth:150,templet:"#desc"},
            {field: 'price', title: '单价', width:80,templet:"#price"},
            {field: 'total', title: '数量', width:80},
            {field: 'money', title: '总价', width:80,templet:function(d){return d.money.toFixed(2)}},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:110},
            {field: 'state', title: '受理状态', align:'center',minWidth:50},
            {title: '操作', width:130,fixed:"right",align:"center", templet:function(){
                return '<a class="layui-btn layui-btn-xs" lay-event="see">查看</a>';
            }}
        ]]
    });

//    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
//    $(".search_btn").on("click",function(){
//        if($(".searchVal").val() != ''){
//            table.reload("linkListTab",{
//                page: {
//                    curr: 1 //重新从第 1 页开始
//                },
//                where: {
//                    key: $(".searchVal").val()  //搜索的关键字
//                }
//            })
//        }else{
//            layer.msg("请输入搜索的内容");
//        }
//    });

    //添加
    function addLink(edit){
        var index = layer.open({
            title : "查看预订信息",
            type : 2,
            area : ["50%","93%"],
            offset: ['25px', '400px'],
            maxmin: true,
            content : "showBookView.do?oid="+edit.oid,
            maxmin: true,
            success : function(layero, index){

            }
        });
        layui.layer.full(index);
         //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        });
    }
    
    $(".addLink_btn").click(function(){
        addLink();
    })

    //列表操作
    table.on('tool(linkListTab)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'see'){ //编辑
            addLink(data);
        } 
    });


    //格式化时间
    function filterTime(val){
        if(val < 10){
            return "0" + val;
        }else{
            return val;
        }
    }
    //添加时间
    var time = new Date();
    var submitTime = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());

})