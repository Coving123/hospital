<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8">
		<title>社区医疗服务平台</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">

 		<link rel="stylesheet" href="${pageContext.request.contextPath}/res/layui/css/layui.css" media="all" >
	  	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/font-awesome-4.7.0/css/font-awesome.css" />
	  	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/one-css/layout.css" media="all" />
  	
	</head>

	<body>
		<div class="layui-layout layui-layout-admin beg-layout-container">
			<div class="layui-header beg-layout-header">
				<div class="beg-layout-main beg-layout-logo">
					<a href="" target="_blank" class="one-logo">社区医疗服务系统</a>
				</div>
				<div class="beg-layout-main beg-layout-side-toggle" style="border-radius: 10%;">
					<i class="fa fa-bars" aria-hidden="true"></i>
				</div>
				<div class="beg-layout-main beg-layout-panel">
					<ul class="layui-nav beg-layout-nav" lay-filter="user">
						<li class="layui-nav-item">
							<a href="javascript:;" data-tab="true" class="admin-side-full">
								<i class="fa fa-arrows-alt" aria-hidden="true"></i>
								全屏
							</a>
						</li>
						
						<li class="layui-nav-item">
							<a href="javascript:;" class="beg-layou-head">
								<img src="${pageContext.request.contextPath}/res/images/avatar/admin.jpg" class="layui-nav-img">
								<span>${sessionScope.user.username }</span>
							</a>
							<dl class="layui-nav-child">
							<c:if test="${sessionScope.doctor!=null }">
								<dd>
									<a href="javascript:;"  id="info-doctor" style="cursor:hand">
										<i class="fa fa-user-circle" aria-hidden="true"></i>
										<cite>密码修改</cite>
									</a>
								</dd>
							</c:if>
							<c:if test="${sessionScope.nurse!=null }">
								<dd>
									<a href="javascript:;" id="info-nurse" style="cursor:hand">
										<i class="fa fa-user-circle" aria-hidden="true"></i>
										<cite>密码修改</cite>
									</a>
								</dd>
							</c:if>
							<c:if test="${sessionScope.patient!=null }">
								<dd>
									<a class="javascript:;" id="pmodify" style="cursor:hand">
										<i class="fa fa-user-circle" aria-hidden="true"></i>
										<cite>密码修改</cite>
									</a>
								</dd>
							</c:if>
								<dd>
									<a href="javascript:;" onclick="turnToLogin()">
										<i class="fa fa-sign-out" aria-hidden="true"></i>
										<cite>注销</cite>
									</a>
								</dd>
							</dl>
						</li>
					</ul>
				</div>
			</div>
			
			<!--侧边导航栏-->
			<div class="layui-side beg-layout-side" >
				 <div class="layui-side-scroll">
				 		<!-- 管理员信息      -->
				    	<div class="user-info">            
				            <c:if test="${sessionScope.user.role == 1}">
				            <div class="photo">
				                <img src="${pageContext.request.contextPath}/res/images/avatar/doc.jpg" alt="">
				              </div>
					            <p>${sessionScope.doctor.name}医生您好！欢迎登录</p>
				            </c:if>
				            <c:if test="${sessionScope.user.role == 2}">
					        <div class="photo">
				                <img src="${pageContext.request.contextPath}/res/images/avatar/admin.jpg" alt="">
				              </div>
					            <p>管理员您好！欢迎登录</p>
				            </c:if>
				            <c:if test="${sessionScope.user.role == 3}">
					        <div class="photo">
				                <img src="${pageContext.request.contextPath}/res/images/avatar/admin.jpg" alt="">
				              </div>    
					            <p>${sessionScope.patient.name}您好！欢迎登录</p>
				            </c:if>
				            <c:if test="${sessionScope.user.role == 4}">
					        <div class="photo">
				                <img src="${pageContext.request.contextPath}/res/images/avatar/nur.jpg" alt="">
				              </div>    
					            <p>${sessionScope.nurse.name}护士您好！欢迎登录</p>
				            </c:if>
				        </div>
				        <ul class="layui-nav layui-nav-tree beg-navbar layui-inline"  lay-filter="side" id="side">
							<c:if test="${sessionScope.user.role == 2}">
								<li class="layui-nav-item">
									<a href="javascript:;" data-url="${pageContext.request.contextPath}/showDoctorList.do">
										<i class="fa fa-address-book-o" aria-hidden="true" data-icon="fa-address-book-o"></i>&nbsp;
										<cite>医生管理</cite>
									</a>
								</li>
								<li class="layui-nav-item">
									<a href="javascript:;" data-url="${pageContext.request.contextPath}/showNurseList.do">
										<i class="fa fa-address-book-o" aria-hidden="true" data-icon="fa-address-book-o"></i>&nbsp;
										<cite>护士管理</cite>
									</a>
								</li>
							</c:if>
							
				 		 	<c:if test="${sessionScope.user.role == 1}">
								<li class="layui-nav-item">
									<a href="javascript:;" data-url="${pageContext.request.contextPath}/showRecordManage.do">
										<i class="fa fa-file-zip-o" aria-hidden="true" data-icon="fa-file-zip-o"></i>&nbsp;
										<cite>病历管理</cite>
									</a>
								</li>
								<li class="layui-nav-item">
									<a href="javascript:;" data-url="${pageContext.request.contextPath}/showAppointMentList.do">
										<i class="fa fa-at" aria-hidden="true" data-icon="fa-at"></i>&nbsp;
										<cite>查看预约</cite>
									</a>
								</li>
							</c:if>
							
				 		 	<c:if test="${sessionScope.user.role == 3}">
								<li class="layui-nav-item"> 
									<a><i class="fa fa-caret-square-o-right" aria-hidden="true" data-icon="fa-caret-square-o-right"></i>&nbsp;
									&nbsp;预约管理</a>
									<dl class="layui-nav-child">
									   <dd>
									      <li class="layui-nav-item">
									      <a href="javascript:;" data-url="${pageContext.request.contextPath}/showAppointMentManage.do">
									           <i class="fa fa-comments" aria-hidden="true" data-icon="fa-comments"></i>&nbsp;
									          <cite>医生预约</cite>
									      </a>
									      </li>   
									   </dd>
									   <dd>
									    <li class="layui-nav-item">
									      <a href="javascript:;" data-url="${pageContext.request.contextPath}/showEngageMentManage.do">
									           <i class="fa fa-comments-o" aria-hidden="true" data-icon="fa-comments-o"></i>&nbsp;
									          <cite>护士预约</cite>
									      </a>
									     </li>
									   </dd>
								     </dl>
								</li>
								<li class="layui-nav-item">
									<a href="javascript:;" data-url="${pageContext.request.contextPath}/showRecordList.do">
										<i class="fa fa-cogs" aria-hidden="true" data-icon="fa-cogs"></i>&nbsp;
										<cite>查看病例</cite>
									</a>
								</li>
								
								<li class="layui-nav-item"> 
									<a><i class="fa fa-caret-square-o-right" aria-hidden="true" data-icon="fa-caret-square-o-right"></i>&nbsp;
									&nbsp;医药房</a>
									<dl class="layui-nav-child">
									   <dd>
									      <li class="layui-nav-item">
									      <a href="javascript:;" data-url="${pageContext.request.contextPath}/showMedicineList.do">
									           <i class="fa fa-comments" aria-hidden="true" data-icon="fa-comments"></i>&nbsp;
									          <cite>查看药品</cite>
									      </a>
									      </li>   
									   </dd>
									   <dd>
									    <li class="layui-nav-item">
									      <a href="javascript:;" data-url="${pageContext.request.contextPath}/showBookManage.do">
									           <i class="fa fa-comments-o" aria-hidden="true" data-icon="fa-comments-o"></i>&nbsp;
									          <cite>药品预订</cite>
									      </a>
									     </li>
									   </dd>
								     </dl>
								</li>	
							</c:if>
							
							<c:if test="${sessionScope.user.role == 4}">
								<li class="layui-nav-item">
									<a href="javascript:;" data-url="${pageContext.request.contextPath}/showEngageMentList.do">
										<i class="fa fa-at" aria-hidden="true" data-icon="fa-at"></i>&nbsp;
										<cite>查看预约</cite>
									</a>
								</li>
								<li class="layui-nav-item">
									<a href="javascript:;" data-url="${pageContext.request.contextPath}/showRecordListN.do">
										<i class="fa fa-cogs" aria-hidden="true" data-icon="fa-cogs"></i>&nbsp;
										<cite>查看病例</cite>
									</a>
								</li>
								
								
							   <li class="layui-nav-item"> 
									<a><i class="fa fa-caret-square-o-right" aria-hidden="true" data-icon="fa-caret-square-o-right"></i>&nbsp;
									&nbsp;医药房</a>
									<dl class="layui-nav-child">
									   <dd>
									      <li class="layui-nav-item">
									      <a href="javascript:;" data-url="${pageContext.request.contextPath}/showMedicineManage.do">
									           <i class="fa fa-comments" aria-hidden="true" data-icon="fa-comments"></i>&nbsp;
									          <cite>药品管理</cite>
									      </a>
									      </li>   
									   </dd>
									   <dd>
									    <li class="layui-nav-item">
									      <a href="javascript:;" data-url="${pageContext.request.contextPath}/showBookList.do">
									           <i class="fa fa-comments-o" aria-hidden="true" data-icon="fa-comments-o"></i>&nbsp;
									          <cite>查看预订</cite>
									      </a>
									     </li>
									   </dd>
								     </dl>
								</li>	
							</c:if>
						</ul>
				 </div>
			
			</div>
			<div class="layui-body beg-layout-body">
				<div class="layui-tab layui-tab-brief layout-nav-card" lay-filter="layout-tab" style="border: 0; margin: 0;box-shadow: none; height: 100%;">
					<ul class="layui-tab-title">
						<li class="layui-this">
							<a href="javascript:;">
								<i class="fa fa-home" aria-hidden="true"></i>
								<cite><c:if test ="${sessionScope.user.role == 2}">后</c:if><c:if test="${sessionScope.user.role != 2}">前</c:if>台首页</cite>
							</a>
						</li>
					</ul>
					<div class="layui-tab-content">
						<div class="layui-tab-item layui-show">
							<iframe src="${pageContext.request.contextPath}/showMain.do"></iframe>
						</div>
					</div>
				</div>
			</div>
			<!--页脚-->
			<div class="layui-footer beg-layout-footer">
				<p>2019 &copy; 欢迎访问社区医疗服务系统!
				</p>
			</div>
		</div>		
		<script type="text/javascript">
			var path = '${pageContext.request.contextPath}';
		</script>
		
		<script src="${pageContext.request.contextPath}/res/layui_menu/layui.js"></script>
		<script src="${pageContext.request.contextPath}/res/js/layout.js "></script>
		<script src="${pageContext.request.contextPath}/res/js/jquery.js "></script>
		<script type="text/javascript">
		
		layui.use('element', function(){
			var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
			//监听导航点击
			element.on('nav(side)', function(elem){
				//console.log(elem)
				layer.msg(elem.text());
				});
			});
		
		//信息修改-患者
		 $("#pmodify").click(function(){
			 var index = layer.open({
				 title : "修改个人信息",
				 type : 2,
                 area : ["50%","93%"],
                 offset: ['25px', '400px'],
                 content : "showChangePassword.do",
                 success : function(layero, index){
                    }
                 });
			 }) 
			  
		 //密码修改-医生
		 $("#info-doctor").click(function(){
			 var index = layer.open({
				 title : "修改医生密码",
				 type : 2,
                 area : ["50%","93%"],
                 offset: ['25px', '400px'],
                 content : "showChangePassword.do",
                 success : function(layero, index){
              }
                 });
			 })
		 
		 //密码修改-护士
         $("#info-nurse").click(function(){
	        var index = layer.open({
	            title : "修改护士密码",
	            type : 2,
	            area : ["50%","93%"],
	            offset: ['25px', '400px'],
	            content : "showChangePassword.do",
	            success : function(layero, index){
	            }
	        });
	    })
		
		function turnToLogin(){
				window.location.href="${pageContext.request.contextPath}/showLogin.do";
			}
		</script>
		
	</body>

</html>