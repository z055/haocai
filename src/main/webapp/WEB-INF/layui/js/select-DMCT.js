layui.use(['form'],function (){
    var form = layui.form,
        $ = layui.jquery
    // 获取所有二级学院
    $.post('/dept/findDeptToSelect', function (res) {
        var str = ''
        for (let i = 0; i < res.length; i++) {
            str += '<option value="' + res[i]['full_name'] + '">' + res[i]['full_name'] + '</option>'
        }
        $('#department option:not(:first)').remove()
        $('#department').append(str)
        form.render('select')
    }, 'json')

// 根据学院查专业
    form.on('select(departmentSelect)', function (data) {
        let department = data.value
        $.post('/chuku/getMajorByDept', {department: department}, function (res) {
            let str = ''
            for (let i = 0; i < res.length; i++) {
                str += '<option value="' + res[i]["name"] + '">' + res[i]["name"] + '</option>'
            }
            $('#major option:not(:first)').remove()
            $('#major').append(str)
            form.render('select')
        }, 'json')
    })

// 根据专业查找课程与实训项目
    form.on('select(majorSelect)', function (data) {
        let major = data.value
        $.post('/chuku/getCourse', {major: major}, function (res) {
            let courseStr = ''
            // let trainingStr = ''
            for (let i = 0; i < res.length; i++) {
                courseStr += '<option value="' + res[i]["kechengName"] + '">' + res[i]["kechengName"] + '</option>'
                // trainingStr += '<option value="' + res[i]["shixunName"] + '">' + res[i]["shixunName"] + '</option>'
            }
            $('#course option:not(:first),#training option:not(:first)').remove()
            $('#course').append(courseStr)
            // $('#training').append(trainingStr)
            form.render('select')
        }, 'json')
    })
    // 根据课程查询实训项目
    form.on('select(courseSelect)', function (data) {
        let course = data.value
        $.post('/chuku/getTraining', {course: course}, function (res) {
            let str = ''
            for (let i = 0; i < res.length; i++) {
                str += '<option value="' + res[i] + '">' + res[i] + '</option>'
            }
            $('#training option:not(:first)').remove()
            $('#training').append(str)
            form.render('select')
        }, 'json')
    })



})
