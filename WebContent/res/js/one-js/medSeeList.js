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
        url : 'findMedicineByPage.do',
        page : true,
        cellMinWidth : 95,
        height : "full-104",
        limit : 5,
        limits : [5,10,15,20],
        id : "linkListTab",
        cols : [[
        	{type: "checkbox", fixed:"left", width:50},
            {field: 'mid', title: '编号', width:75},
            {field: 'name', title: '药品名称', minWidth:125},
            {field: 'category', title: '药品类别', minWidth:125},
            {field: 'description', title: '功效描述', minWidth:150},
            {field: 'price', title: '单价', minWidth:125,templet:function(d){return d.price.toFixed(2)}},
            {field: 'count', title: '库存数量', minWidth:125},
            {title: '操作', width:130,fixed:"right",align:"center", templet:function(){
                return '<a class="layui-btn layui-btn-xs" lay-event="see">查看</a>';
            }}
        ]]
    });
    
    
    function addLink(edit){
        var index = layer.open({
            title : "查看药品信息",
            type : 2,
            area : ["50%","93%"],
            offset: ['25px', '400px'],
            maxmin: true,
            content : "showMedicineView.do?mid="+edit.mid,
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

//    form.on("submit(addBook)",function(data){
//        //弹出loading
//        var index = top.layer.msg('正在前往订购页面',{icon: 16,time:false,shade:0.8});
//        
//        // 实际使用时的提交信息
//        //这里写ajax保存方法
//        $.ajax({
//        	type : "POST",
//        	async: false,
//        	url : "addOrModifyRequireMent.do",
//        	data : {
//		    },
//        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
//        	dataType : "json",
//        	success : function(data) {
//        		window.location.href="";
//        	}
//        });
//		
//	setTimeout(function(){
//            top.layer.close(index);
//            top.layer.msg("预订信息添加成功！");
//            layer.closeAll("iframe");
//            //刷新父页面
//            $(".layui-tab-item.layui-show",parent.document).find("iframe")[0].contentWindow.location.reload();
//        },1000);
//        return false;
//    })
    
    
    
    
})