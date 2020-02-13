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
        url : 'findRecordByPageByNurse.do',
        page : true,
        cellMinWidth : 95,
        height : "full-104",
        limit : 5,
        limits : [5,10,15,20],
        id : "linkListTab",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'dname', title: '医生', minWidth:150,templet:"#dname"},//这个先写的医生的id，到时候你查数据的时候关联查一下姓名就行
            {field: 'pname', title: '患者', minWidth:150,templet:"#pname"},//这个先写的患者的id，到时候你查数据的时候关联查一下姓名就行
            {field: 'symptom', title: '症状', minWidth:150},
            {field: 'diagnosis', title: '诊断', minWidth:150},
            {field: 'prescription', title: '处方', minWidth:150},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:110},
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
                    key: $(".searchVal").val(),  //搜索的关键字
                    patient_namet: $(".search-patient").val()   //查询的条件
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });

    //添加友链
    function addLink(edit){
        var index = layer.open({
            title : "查看病历信息",
            type : 2,
            area : ["50%","93%"],
            offset: ['25px', '400px'],
            maxmin: true,
            content : "showRecordView.do?rid="+edit.rid,
            success : function(layero, index){
                var body = $($(".layui-layer-iframe",parent.document).find("iframe")[0].contentWindow.document.body);
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
                },500)
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

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('linkListTab'),
            data = checkStatus.data,
            linkId = [];
        if(data.length > 0) {
            for (var i in data) {
                linkId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中的病历信息？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除病历接口",{
                //     did : did  //将需要删除的did作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        }else{
            layer.msg("请选择需要删除的病历信息");
        }
    })

    //列表操作
    table.on('tool(linkListTab)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'see'){ //查看
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