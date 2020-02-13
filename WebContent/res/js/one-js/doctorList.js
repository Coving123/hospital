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
        url : 'findDoctorByPage.do',
        page : true,
        cellMinWidth : 95,
        height : "full-104",
        limit : 5,
        limits : [5,10,15,20],
        id : "linkListTab",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'did', title: '编号', minWidth:125},
            {field: 'name', title: '姓名', minWidth:125},
            {field: 'username', title: '登录名', minWidth:125,templet:"#username"},
            {field: 'password', title: '密码', minWidth:125,templet:"#password"},
            {field: 'titel', title: '职称', minWidth:125},
            {field: 'sex', title: '性别', minWidth:125},
            {field: 'subject', title: '科室', minWidth:125},
            {field: 'education', title: '学历', minWidth:125},
            {title: '操作', width:130,fixed:"right",align:"center", templet:function(){
                return '<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a><a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>';
            }}
        ]]
    });

    //添加医生信息
    function addLink(edit){
    	var url = "showDoctorAdd.do";
    	if(edit!=null){
    		url+="?did="+edit.did;
    	}
        var index = layer.open({
            title : "医生信息",
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
            layer.confirm('确定删除选中的医生信息？', {icon: 3, title: '提示信息'}, function (index) {
                 $.get("removeDoctorById.do",{
                     did : data.did  //将需要删除的did作为参数传入
                 },function(data){
                tableIns.reload();
                layer.close(index);
                 })
            })
        }else{
            layer.msg("请选择需要删除的医生信息");
        }
    })

    //列表操作
    table.on('tool(linkListTab)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addLink(data);
        } else if(layEvent === 'del'){//删除
        	layer.confirm("确定删除选中的医生信息？？", {
				icon: 7,
				title: "提示"
			},function(index){
        	$.get("removeDoctorById.do",{
                 did : data.did  //将需要删除的did作为参数传入
             },function(data){
                tableIns.reload();              
             });
        	 layer.close(index);
			});
             setTimeout(function(){
                 top.layer.close(index);
                 top.layer.msg("医生信息删除成功！");
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
        	url : "addOrModifyDoctor.do",
        	data : {
        		did:$("#did").val(),
        		uid:$("#uid").val(),
        		name : $("#name").val(),
        		username : $("#username").val(),
        		password : $("#password").val(),
        		titel : $("#titel").val(),
        		subject : $("#subject").val(),
        		sex : $("input[name='sex']:checked").val(),
        		education : $("#education").val()
        	},
        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        	dataType : "json",
        	success : function(data) {
        		if(data.msg == 1){
					layer.alert("医生信息添加修改成功！", {
						icon: 1,
						title: "提示"
					});
					setTimeout(function(){
						layer.closeAll("iframe");
						 $(".layui-tab-item.layui-show",parent.document).find("iframe")[0].contentWindow.location.reload();
					},1000);
				}else if(data.msg == 3){
					layer.alert("用户名已存在！", {
						icon: 5,
						title: "提示"
					});
				}else if(data.msg == 2){
					layer.alert("医生信息添加修改失败！", {
						icon: 5,
						title: "提示"
					});
				}
        	}
        });
		
//	setTimeout(function(){
//            top.layer.close(index);
//            top.layer.msg("医生信息添加成功！");
//            layer.closeAll("iframe");
//            //刷新父页面
//            $(".layui-tab-item.layui-show",parent.document).find("iframe")[0].contentWindow.location.reload();
//        },1000);
        
        return false;
    })


})