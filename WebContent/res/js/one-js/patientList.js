layui.use(['form','layer','laydate','table','upload'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        upload = layui.upload,
        table = layui.table;
    
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

    form.on("submit(addPatient)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        
        if($("#oldPassword").val()!="${sessionScope.user.password}"){
			layer.alert("原密码输入错误", {
				icon: 5,
				title: "修改失败"
			});
			return false;
		}
		console.log($("#password").val());
		console.log($("#repassword").val());
		var password = $("#password").val();
		var repassword = $("#repassword").val();
		
		if(password!=repassword){
			layer.alert("两次输入密码不一致", {
				icon: 5,
				title: "修改失败"
			});
			return false;
		}
		
        // 实际使用时的提交信息
        //这里写ajax保存方法
        $.ajax({
        	type : "POST",
        	async: false,
        	url : "${pageContext.request.contextPath}/user/ModifyPatient.do",
        	data : {
        		pid:$("#pid").val(),
        		uid:$("#uid").val(),
        		username : $("#name").val(),
				password : $("#password").val(),
				birth : $("#birth").val(),
				address : $("#address").val(),
				phone : $("#phone").val(),
				sex :  $("input[type='radio']:checked").val()	
        	},
        	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        	dataType : "json",
        	success : function(data) {
        	}
        });
		
        setTimeout(function(){
            layer.closeAll("iframe");
            //刷新父页面
            window.parent.location.href="${pageContext.request.contextPath}/showLogin.do";
        },3000);
        return false;
    })


})