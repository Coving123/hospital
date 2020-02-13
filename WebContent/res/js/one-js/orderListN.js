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
    function renderForm(){
    	  layui.use('form', function(){
    	   form.render();
    	  });
    }
    
    //友链列表
    var tableIns = table.render({
        elem: '#linkList',
        url : 'findEngageByPageByPatient.do',
        page : true,
        cellMinWidth : 95,
        height : "full-104",
        limit : 5,
        limits : [5,10,15,20],
        id : "linkListTab",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'nuname', title: '护士', minWidth:50,templet:"#nuname"},//这个先写的医生的id，到时候你查数据的时候关联查一下姓名就行
            {field: 'pname', title: '患者', minWidth:50,templet:"#pname"},//这个先写的患者的id，到时候你查数据的时候关联查一下姓名就行
            {field: 'description', title: '情况简述', minWidth:150},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:110},
            {field: 'effectDate', title: '生效时间', align:'center',minWidth:110},
            {field: 'expireDate', title: '失效时间', align:'center',minWidth:110},
            {field: 'state', title: '是否执行', align:'center',minWidth:50},
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

    //添加预约
    function addLink(edit){
    	var url = "toEngageMentAdd.do";
    	if(edit!=null){
    		url+="?eid="+edit.eid;
    	}
        var index = layer.open({
            title : "预约信息",
            type : 2,
            area : ["50%","93%"],
            offset: ['25px', '400px'],
            content : url,
            maxmin: true,
            success : function(layero, index){
               /* var body = $($(".layui-layer-iframe",parent.document).find("iframe")[0].contentWindow.document.body);
                if(edit){
                    body.find(".linkLogo").css("background","#fff");
                    body.find(".linkLogoImg").attr("src",edit.logo);
                    body.find(".linkName").val(edit.websiteName);
                    body.find(".linkUrl").val(edit.websiteUrl);
                    body.find(".masterEmail").val(edit.masterEmail);
                    body.find(".showAddress").prop("checked",edit.showAddress);
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回友链列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)*/
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
                //     nid : nid  //将需要删除的nid作为参数传入
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
             $.get("removeEngageMentById.do",{
                 eid : data.eid  //将需要删除的eid作为参数传入
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

    form.on("submit(addNurse)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        
        // 实际使用时的提交信息
        //这里写ajax保存方法
        $.ajax({
        	type : "POST",
        	async: false,
        	url : "addOrModifyEngageMent.do",
        	data : {
        		pname:$("#pname").val(),
        		description:$("#description").val(),
        		effectDate:$("#effectdate").val(),
        		nuname:$("#nuname").val(),
        		eid:$("#eid").val(),
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
            top.layer.msg("预约信息添加成功！");
            layer.closeAll("iframe");
            //刷新父页面
            $(".layui-tab-item.layui-show",parent.document).find("iframe")[0].contentWindow.location.reload();
        },1000);
        return false;
    })

})
