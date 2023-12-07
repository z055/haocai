layui.use(['laytpl','form'],function (){
    var laytpl = layui.laytpl,
        $ = layui.jquery,
        form = layui.form

    // 加载模板引擎
    var currentUser = layui.sessionData('userInfo')['user'],
        roleName = currentUser['roleName'],
        deptName = currentUser['deptName'],
        isSch = roleName.indexOf('教务处') !== -1 || roleName.indexOf('院长') !== -1
    var data = { //数据
        "selectId":"department",
        "deptName":deptName,
        "roleName":roleName,
        "isSch":isSch,
    }
    var getTpl = $('#departmentModel').html()
        ,view = $('#departmentView');
    laytpl(getTpl).render(data, function(html){
        view.html(html);
    });

    // 校级人员查询所有院系
    function isSchLevel (roleName){
        if(roleName.indexOf('教务处') !== -1 || roleName.indexOf('院长') !== -1){
            $.ajax({
                url:'/dept/findDeptToSelect',
                dataType:'json',
                type:'post',
                async:false,
                success:function (res){
                    var str = '<option value="">所有二级学院</option>'
                    for (let i = 0; i < res.length; i++) {
                        str += '<option value="' + res[i]['full_name'] + '">' + res[i]['full_name'] + '</option>'
                    }
                    $('#department option').remove()
                    $('#department').append(str)
                    form.render('select')
                }
            })
        }
    }
    isSchLevel(roleName);
})