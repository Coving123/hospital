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
        url : 'findEngageByPageByNurse.do',
        page : true,
        cellMinWidth : 95,
        height : "full-104",
        limit : 5,
        limits : [5,10,15,20],
        id : "linkListTab",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'nuname', title: '护士', minWidth:50,templet:"#nuname"},//这个先写的护士的id，到时候你查数据的时候关联查一下姓名就行
            {field: 'pname', title: '患者', minWidth:50,templet:"#pname"},//这个先写的患者的id，到时候你查数据的时候关联查一下姓名就行
//            {field: 'sex', title: 'XXXXX', minWidth:50,templet:"#sex"},
            {field: 'description', title: '情况简述', minWidth:150},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:110},
            {field: 'effectDate', title: '生效时间', align:'center',minWidth:110},
            {field: 'expireDate', title: '失效时间', align:'center',minWidth:110},
            {field: 'state', title: '是否执行', align:'center',minWidth:50},
            {title: '操作', width:130,fixed:"right",align:"center", templet:function(){
                return '<a class="layui-btn layui-btn-xs" lay-event="see">查看</a>';
            }}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            table.reload("linkListTab",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val()  //搜索的关键字
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });

    //添加预约
    function addLink(edit){
        var index = layer.open({
            title : "查看预约信息",
            type : 2,
            area : ["50%","93%"],
            offset: ['25px', '400px'],
            maxmin: true,
            content : "showOrderViewN.do?eid="+edit.eid,
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