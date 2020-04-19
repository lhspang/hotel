layui.use(['jquery', 'form'], function() {
	var $ = layui.$;
	var form = layui.form;

	var id = localStorage.getItem("id");
	var token = localStorage.getItem("token");

	if (token == null) {
		return;
		window.location.href = "../passport/login.html"
	}

	var url = "http://localhost:8080/api/user/user";
	$.ajax({
		// headers: {
		// 	"Authorization": token
		// },
		url: url,
		type: "get",
		data: {
			"id": id
		},
		async: false,
		success: function(result) {
			form.val("user-form", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
			  "email": result.data.email // "name": "value"
			  ,"nickName": result.data.nickName
			  ,"sex": result.data.sex
			  ,"qq": result.data.qq
			  ,"address": result.data.address
			});
			
			$('#phone').html(result.data.phone);
			$('#uname').html(result.data.nickName);
			
			form.render();
		}
	});

	form.on('submit(update)', function(data) {
		var action = data.form.action; //表单提交URL地址
		var allData = {
			"id": id,
			"email": data.field.email,
			"nickName": data.field.nickName,
			"sex": data.field.sex,
			"qq": data.field.qq,
			"address": data.field.address
		};

		$.ajax({
			// headers: {
			// 	"Authorization": token
			// },
			url: action,
			type: "POST",
			data: JSON.stringify(allData),
			contentType: 'application/json; charset=UTF-8',
			async: false,
			dataType: 'json',
			success: function(result) {
				//根据返回结果作出相应处理
				if (result.meta.success) {
					layer.msg("保存成功", {
						icon: 1,
						time: 1000,
						end: function() {
							window.location.reload();
						}
					});

				} else {
					layer.msg(result.meta.message, {
						icon: 5
					});
				}
			}

		});
		return false;
	});

	form.verify({
		pass: [
			/^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格'
		],
		repass: function(value, item) {
			if (value != $('#password').val()) {
				return '两次密码输入不同';
			}
		}
	});

	form.on('submit(change)', function(data) {
		var action = data.form.action; //表单提交URL地址

		$.ajax({
			// headers: {
			// 	"Authorization": token
			// },
			url: action,
			type: "POST",
			data: {
				"id":id,
				"oldPass":data.field.oldPass,
				"newPass":data.field.newPass
			},
			success: function(result) {
				if (result.meta.success) {
					$('#changePass')[0].reset();
					layer.msg("修改成功", {
						icon: 1
					});

				} else {
					layer.msg(result.meta.message, {
						icon: 5
					});
				}
			}

		});
		return false;
	});

});
