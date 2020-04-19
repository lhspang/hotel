layui.use(['jquery', 'form'], function() {
	var $ = layui.$;
	var form = layui.form;

	var codeBtn = $('#codebtn');
	var countdown = 30; //初始值
	var regex = /^((\(\d{3}\))|(\d{3}\-))?13\d{9}|15[89]\d{8}/;
	var realCode = "";

	codeBtn.click(function settime() {
		var phone = $('#phone').val();
		if (codeBtn.val() === "1") {
			if (!(regex.test(phone))) {
				layer.msg("请输入正确的手机号", {
					icon: 2
				});
				return false;
			}
			$.ajax({
				url: "http://localhost:8080/api/code/phoneCode",
				type: "get",
				data: {
					"phone": phone
				},
				contentType: 'application/json; charset=UTF-8',
				async: false,
				dataType: "json",
				success: function(result) {
					if (result.meta.success) {
						realCode = result.data;
						$('#hidd').val(realCode);
					} else {
						layer.msg(result.meta.message, {
							icon: 5
						});
					}

				}
			});
			codeBtn.val("0");
		} else {
			if (countdown === 0) {
				codeBtn.removeAttr("disabled");
				codeBtn.attr("class", "layui-btn layui-btn-primary");
				codeBtn.html("获取验证码");
				countdown = 30;
				codeBtn.val("1");
				return false;
			} else {
				codeBtn.attr("disabled", true);
				codeBtn.addClass("layui-btn layui-btn-disabled");
				codeBtn.html("重新发送(" + countdown + ")");
				codeBtn.val("0");
				countdown--;
			}
		}
		setTimeout(function() { //设置一个定时器，每秒刷新一次
			settime();
		}, 1000);
	});
	
	$('#phoneCode').blur(function(){
		var code = $('#phoneCode').val();
		if (code.trim() === "") {
			return;
		}
		if(code != $('#hidd').val()){
			layer.msg("验证码输入有误",{icon:5})
		}
	})
});
