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
                return '<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a><a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>';
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

    //添加医生信息
    function addLink(edit){
    	var url = "showMedicineAdd.do";
    	if(edit!=null){
    		url+="?mid="+edit.mid;
    	}
        var index = layer.open({
            title : "药品信息",
            type : 2,
            area : ["50%","93%"],
            offset: ['25px', '400px'],
            maxmin: true,
            content : url,
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
    });

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('linkListTab'),
            data = checkStatus.data,
            linkId = [];
        if(data.length > 0) {
            for (var i in data) {
                linkId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中的药品信息？', {icon: 3, title: '提示信息'}, function (index) {
                 $.get("removeMedicineById.do",{
                     mid : data.mid  //将需要删除的mid作为参数传入
                 },function(data){
                tableIns.reload();
                layer.close(index);
                 })
            })
        }else{
            layer.msg("请选择需要删除的药品信息");
        }
    })

    //列表操作
    table.on('tool(linkListTab)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addLink(data);
        } else if(layEvent === 'del'){ //删除
        	layer.confirm("确定删除选中的药品信息？？", {
				icon: 7,
				title: "提示"
			},function(index){
             $.get("removeMedicineById.do",{
                 mid : data.mid  //将需要删除的mid作为参数传入
             },function(data){
                tableIns.reload();
             });
             layer.close(index);
			});
             setTimeout(function(){
                 top.layer.close(index);
                 top.layer.msg("药品信息删除成功！");
                 layer.closeAll("iframe");
                 //刷新父页面
                 $(".layui-tab-item.layui-show",parent.document).find("iframe")[0].contentWindow.location.reload();
             },1000);
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

    form.on("submit(addMedicine)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        //这里写ajax保存方法
        $.ajax({
        	type : "POST",
        	async: false,
        	url : "addOrModifyMedicine.do",
        	data : {
        		mid:$("#mid").val(),
        		name:$("#name").val(),
        		category:$("#category").val(),
        		description:$("#description").val(),
        		price:$("#price").val(),
        		count:$("#count").val()
        	},
        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        	dataType : "json",
        	success : function(data) {
        		if(data.msg == 1){
					layer.alert("药品信息添加修改成功！", {
						icon: 1,
						title: "提示"
					});
					setTimeout(function(){
						layer.closeAll("iframe");
						 $(".layui-tab-item.layui-show",parent.document).find("iframe")[0].contentWindow.location.reload();
					},1000);
				}else if(data.msg == 3){
					layer.alert("药品已存在，请转至编辑修改！", {
						icon: 5,
						title: "提示"
					});
				}else if(data.msg == 2){
					layer.alert("药品添加修改失败！", {
						icon: 5,
						title: "提示"
					});
				}
        	}
        });
		
//	setTimeout(function(){
//            top.layer.close(index);
//            top.layer.msg("药品信息添加成功！");
//            layer.closeAll("iframe");
//            //刷新父页面
//            $(".layui-tab-item.layui-show",parent.document).find("iframe")[0].contentWindow.location.reload();
//        },1000);
        return false;
    })
})
