layui.config({
	base:path+'/res/js/'
}).use(['element', 'layer', 'navbar', 'tab'], function() {
	var element = layui.element()
	$ = layui.jquery,
		layer = layui.layer,
		navbar = layui.navbar(),
		tab = layui.tab({
			elem: '.layout-nav-card', //设置选项卡容器
			contextMenu:true
		});

	//iframe自适应
	$(window).on('resize', function() {
		var $content = $('.layout-nav-card .layui-tab-content');
		$content.height(400);
		$content.find('iframe').each(function() {
			$(this).height($content.height());
		});
	}).resize();

	//可以通过ajax获取，根据不同角色获取不同的json菜单
	//初始化菜单
	/*	var url = '../json/menu.json';
	//设置navbar
	navbar.set({
		elem: '#side', //存在navbar数据的容器ID
		url: url
	});
	
	
	//渲染navbar
	navbar.render();
	//监听点击事件
	navbar.on('click(side)', function(data) {
		//layer.msg(data.field.href);
		tab.tabAdd(data.field);
	});*/
	//监听点击事件
	$(document).on("click",".layui-nav-item",function(){  
		var $a = $(this).children('a');
            var href = $a.data('url');
            var icon = $a.children('i:first').data('icon');
            var title = $a.children('cite').text();
            var data = {
                elem: $a,
                field: {
                    href: href,
                    icon: icon,
                    title: title
                }
            };
     tab.tabAdd(data.field);       
    }); 
	
				
	element.on('nav(user)', function(data) {
		var $a = data.children('a');
		if($a.data('tab') !== undefined && $a.data('tab')) {
			tab.tabAdd({
				title: $a.children('cite').text(),
				//icon: 'fa-user',
				href: $a.data('url')
			});
		}
	});

	$('.beg-layout-side-toggle').on('click', function() {
		var sideWidth = $('.beg-layout-side').width();
		if(sideWidth === 200) {
			$('.beg-layout-body').animate({
				left: '0'
			});
			$('.beg-layout-footer').animate({
				left: '0'
			});
			$('.beg-layout-side').animate({
				width: '0'
			});
		} else {
			$('.beg-layout-body').animate({
				left: '200px'
			});
			$('.beg-layout-footer').animate({
				left: '200px'
			});
			$('.beg-layout-side').animate({
				width: '200px'
			});
		}
	});
	
	/*<li class="layui-nav-item layui-this">
		<a href="javascript:;" data-module-id="1">
			<i class="fa fa-desktop" aria-hidden="true"></i>
			<cite>内容管理</cite>
		</a>
	</li>*/
						
						
	//顶级菜单获取html字符串
	function  getTopHtml(data) {
		var ulHtml = '';
			ulHtml += '<ul class="layui-nav beg-layout-nav" lay-filter="">';
		for (var i = 0; i < data.length; i++) {
		 	if (i == 0) {
	          ulHtml += '<li class="layui-nav-item layui-this">';
	        } else {
	          ulHtml += '<li class="layui-nav-item">';
	        }
          ulHtml += '<a href="javascript:;" data-module-id="'+data[i].id+'">';
          ulHtml += '<i class="one-top-icon fa '+ data[i].icon+'" aria-hidden="true"></i>&nbsp;';
          ulHtml += '<cite>' + data[i].title + '</cite>';
          ulHtml += '</a>';
          ulHtml += '</i>';
		}
		ulHtml += '</ul>';
		return ulHtml;
	}
	
	//全屏
	$('.admin-side-full').on('click', function () {
        var docElm = document.documentElement;
        //W3C  
        if (docElm.requestFullscreen) {
            docElm.requestFullscreen();
        }
        //FireFox  
        else if (docElm.mozRequestFullScreen) {
            docElm.mozRequestFullScreen();
        }
        //Chrome等  
        else if (docElm.webkitRequestFullScreen) {
            docElm.webkitRequestFullScreen();
        }
        //IE11
        else if (elem.msRequestFullscreen) {
            elem.msRequestFullscreen();
        }
        layer.msg('按Esc即可退出全屏');
     });
	
	/* 
	信息修改-患者
	$("#info-patient").click(function(){
         var index = layer.open({
            title : "修改患者信息",
            type : 2,
            area : ["50%","93%"],
            offset: ['25px', '400px'],
            content : "showChangePassword.do",
            success : function(layero, index){
               
            }
        });
    }) 
	*/
});