layui.use(['form','layer','laydate','table','upload'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        upload = layui.upload,
        table = layui.table;

    form.on('select(subject)', function(data){
    	$("#dname").empty();
    	renderForm();
    	$.ajax({
        	type : "POST",
        	async: false,
        	url : "getDoctorBySubject.do",
        	data : {
        		subject:data.value
        	},
        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        	dataType : "json",
        	success : function(data) {
        		for ( var i=0; i<data.length;i++) {
        			$("#dname").append("<option value='"+data[i].name+"'>"+data[i].name+"</option>");
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
        url : 'findAppiontByPageByPatient.do',
        page : true,
        cellMinWidth : 95,
        height : "full-104",
        limit : 5,
        limits : [5,10,15,20],
        id : "linkListTab",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'dname', title: '医生', minWidth:50,templet:"#dname"},//这个先写的医生的id，到时候你查数据的时候关联查一下姓名就行
            {field: 'pname', title: '患者', minWidth:50,templet:"#pname"},//这个先写的患者的id，到时候你查数据的时候关联查一下姓名就行
            {field: 'description', title: '情况简述', minWidth:150},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:110},
            {field: 'effectDate', title: '生效时间', align:'center',minWidth:110},
            {field: 'expireDate', title: '失效时间', align:'center',minWidth:110},
            {field: 'state', title: '是否接诊', align:'center',minWidth:50},
            {title: '操作', width:130,fixed:"right",align:"center", templet:function(){
                return '<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a><a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>';
            }}
        ]]
    });
    
    //添加预约
    function addLink(edit){
    	var url = "toAppointMentAdd.do";
    	if(edit!=null){
    		url+="?aid="+edit.aid;
    	}
        var index = layer.open({
            title : "预约信息",
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

    
    /* 批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('linkListTab'),
            data = checkStatus.data,
            linkId = [];
        if(data.length > 0) {
            for (var i in data) {
                linkId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中的预约信息？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除预约接口",{
                //     did : did  //将需要删除的did作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        }else{
            layer.msg("请选择需要删除的预约信息");
        }
    })
    */

    //列表操作
    table.on('tool(linkListTab)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addLink(data);
        } else if(layEvent === 'del'){ //删除
        	layer.confirm("确定删除选中的预约信息？？", {
				icon: 7,
				title: "提示"
			},function(index){
				$.get("removeAppointMentById.do",{
	                 aid : data.aid  //将需要删除的aid作为参数传入
	             },function(data){
	                tableIns.reload();
	             });
				layer.close(index);
			});
         	setTimeout(function(){
                top.layer.close(index);
                top.layer.msg("预约信息删除成功！");
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

    form.on("submit(addDoctor)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        
        // 实际使用时的提交信息
        //这里写ajax保存方法
        $.ajax({
        	type : "POST",
        	async: false,
        	url : "addOrModifyAppointMent.do",
        	data : {
        		pname:$("#pname").val(),
        		description:$("#description").val(),
        		effectDate:$("#effectdate").val(),
        		dname:$("#dname").val(),
        		aid:$("#aid").val(),
        		did:$("#did").val(),
        		pid:$("#pid").val()
        	},
        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        	dataType : "json",
        	success : function(data) {
        	}
        });
		
	setTimeout(function(){
            top.layer.close(index);
            top.layer.msg("预约信息添加成功！");
            layer.closeAll("iframe");
            //刷新父页面
            $(".layui-tab-item.layui-show",parent.document).find("iframe")[0].contentWindow.location.reload();
        },1000);
        return false;
    })

})