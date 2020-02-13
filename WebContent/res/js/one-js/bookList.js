layui.use(['form','layer','laydate','table','upload'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        upload = layui.upload,
        table = layui.table;
    
    
    form.on('select(subject)', function(data){
    	$("#nuname").empty();
    	renderForm();
    	$.ajax({
        	type : "POST",
        	async: false,
        	url : "getNurseBySubject.do",
        	data : {
        		subject:data.value
        	},
        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        	dataType : "json",
        	success : function(data) {
        		for ( var i=0; i<data.length;i++) {
        			$("#nuname").append("<option value='"+data[i].name+"'>"+data[i].name+"</option>");
        			renderForm();
				}
        	}
        });
    });
    
    form.on('select(category)', function(data){
    	$("#mname").empty();
    	renderForm();
    	$.ajax({
        	type : "POST",
        	async: false,
        	url : "getMedicineByCategory.do",
        	data : {
        		category:data.value
        	},
        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        	dataType : "json",
        	success : function(data) {
        		for ( var i=0; i<data.length;i++) {
        			$("#mname").append("<option value='"+data[i].name+"'>"+data[i].name+"</option>");
        			$("#count").val(data[i].count);
        			renderForm();
				}
        	}
        });
    });
    
    function renderForm(){
    	  layui.use('form', function(){
    	   form.render();
    	  });
    }
    
    //友链列表
    var tableIns = table.render({
        elem: '#linkList',
        url : 'findRequireByPageByPatient.do',
        page : true,
        cellMinWidth : 95,
        height : "full-104",
        limit : 5,
        limits : [5,10,15,20],
        id : "linkListTab",
        cols : [[
        	{type: "checkbox", fixed:"left", width:50},
        	{field: 'nuname', title: '负责人', width:90,templet:"#nuname"},
            {field: 'mname', title: '药品名称', minWidth:50,templet:"#mname"},
            {field: 'category', title: '药品类别', minWidth:50,templet:"#cate"},
            {field: 'description', title: '功效描述', minWidth:150,templet:"#desc"},
            {field: 'price', title: '单价', width:80,templet:"#price"},
            {field: 'total', title: '数量', width:80},
            {field: 'money', title: '总价', width:80,templet:function(d){return d.money.toFixed(2)}},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:110},
            {field: 'state', title: '受理状态', align:'center',minWidth:50},
            {title: '操作', width:130,fixed:"right",align:"center", templet:function(){
            	return '<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a><a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>'
            }}
        ]]
    });
    
    //添加
    function addLink(edit){
    	var url = "toRequireMentAdd.do";
    	if(edit!=null){
    		url+="?oid="+edit.oid;
    	}
        var index = layer.open({
            title : "药品预订信息",
            type : 2,
            area : ["50%","93%"],
            offset: ['25px', '400px'],
            content : url,
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

        if(layEvent === 'edit'){ //编辑
            addLink(data);
        } else if(layEvent === 'del'){ //删除
        	layer.confirm("确定删除选中的预订信息？？", {
				icon: 7,
				title: "提示"
			},function(index){
             $.get("removeRequireMentById.do",{
                 oid : data.oid  //将需要删除的did作为参数传入
             },function(data){
                tableIns.reload();
             });
             layer.close(index);
			});
         	setTimeout(function(){
                top.layer.close(index);
                top.layer.msg("订购信息添加成功！");
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

    form.on("submit(addBook)",function(data){
    	var a = $("#total").val();
    	var b = $("#count").val();
    	if(parseInt(a)>parseInt(b)){
    		layer.alert("药品库存不足，剩余"+ b +"件", {
					icon: 2,
					title: "注意"
				});
    	}else if(parseInt(a)<0 || parseInt(a) == 0){
    		layer.alert("药品数量不能为0或负数", {
				icon: 2,
				title: "注意"
			});
    	}else{
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        //这里写ajax保存方法
        $.ajax({
        	type : "POST",
        	async: false,
        	url : "addOrModifyRequireMent.do",
        	data : {
        		pname:$("#pname").val(),
        		mname:$("#mname").val(),
        		nuname:$("#nuname").val(),
        		total:$("#total").val(),
        		oid:$("#oid").val(),
        		mid:$("#mid").val(),
        		nid:$("#nid").val(),
        		pid:$("#pid").val()
		    },
        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        	dataType : "json",
        	success : function(data) {
        	}
        });
		
	setTimeout(function(){
            top.layer.close(index);
            top.layer.msg("预订信息添加成功！");
            layer.closeAll("iframe");
            //刷新父页面
            $(".layui-tab-item.layui-show",parent.document).find("iframe")[0].contentWindow.location.reload();
        },1000);   
    }
	return false;
    })

})