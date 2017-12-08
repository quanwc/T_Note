
//点击笔记本加载笔记本信息列表
function loadnotes(){
	
	//将点击的笔记本li：设置为选中状态.  this:代表每一个笔记本li
	$("#book_list li a").removeClass("checked");
	$(this).find("a").addClass("checked");
	
	//获取bookId: this:点前点击的li，需要将其变为jquery对象
	var bookId = $(this).data("bookId");
	//发送ajax请求
	$.ajax({
		url: "http://localhost:8081/T_Note/note/loadnotes.do",
		type: "post",
		data: {"bookId" : bookId}, //注：参数中间是":"，而不是","
		dataType: "json",
		success: function(result){  //注：result是服务器返回的数据，不能省略不写
			if(result.status==0){//加载笔记成功
				//取出笔记信息，循环生成笔记列表
				var notes = result.data;  //笔记数组对象
				
				//清除原有笔记
				$("#note_list").empty();
				
				//循环
				for(var i=0; i<notes.length; ++i){
					var noteId = notes[i].cn_note_id;  //获取笔记id，用于后面对笔记进行操作
					var noteTitle = notes[i].cn_note_title;  //获取笔记标题
					//拼成笔记li
					var s_li = '<li class="online">';
						s_li +=	'<a >';
						s_li +=	'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+ noteTitle +'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
						s_li +=	'</a>';
						s_li +=	'<div class="note_menu" tabindex="-1">';
						s_li +=	'	<dl>';
						s_li +=	'		<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
						s_li +=	'		<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
						s_li +=	'		<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
						s_li +=	'	</dl>';
						s_li +=	'</div>';
						s_li +=	'</li>';
					//将s_li字符串转成jquery对象
					var $li = $(s_li);
					$li.data("noteId", noteId);//将note_id隐藏在li这个元素上，
					//将li添加到笔记的ul区域
					$("#note_list").append($li);
					
				}
				
				//切换显示区
				$("#pc_part_6").hide();  //隐藏搜索区域
				$("#pa_part_2").show();  //显示笔记列表区域
			}
		},
		error: function(){
			alert("加载笔记失败");
		}
		
	});

};


//弹出添加比较对话框
function showAddNoteWindow(){

	//加载添加笔记对话框内容
	$("#can").load("./alert/alert_note.html");
	//显示背景色，将原页面遮住
	$(".opacity_bg").show();

}


//创建笔记按钮操作
function sureAddNote(){
	
	//获取笔记本id，笔记title，用户id
	var bookId = $("#book_list a.checked").parent().data("bookId");//获取选中的笔记本li的id
	var noteTitle = $("#input_note").val().trim();
	//TODO 检查笔记名是否为空
	//发送ajax请求
	$.ajax({
		url: "http://localhost:8081/T_Note/note/add.do",
		type: "post",
		data: {"bookId":bookId, "noteTitle":noteTitle, "userId":userId},
		dataType: "json",
		success: function(result){
			if(result.status==0){//创建笔记成功
				//关闭对话框
				closeAlertWindow();
				//生成笔记li，添加到笔记列表区域
				var noteId = result.data;  //获取笔记id
				//拼成笔记li
				var s_li = '<li class="online">';
					s_li +=	'<a >';
					s_li +=	'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+ noteTitle +'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
					s_li +=	'</a>';
					s_li +=	'<div class="note_menu" tabindex="-1">';
					s_li +=	'	<dl>';
					s_li +=	'		<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
					s_li +=	'		<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
					s_li +=	'		<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
					s_li +=	'	</dl>';
					s_li +=	'</div>';
					s_li +=	'</li>';
				
					//将s_li字符串转成jquery对象
					var $li = $(s_li);
					$li.data("noteId", noteId);//将note_id隐藏在li这个元素上，
					//将li添加到笔记的ul区域
					$("#note_list").prepend($li);
					//将当前追加的笔记设置为选中状态
					$("#note_list a").removeClass("checked");
					$("#note_list li a:first").addClass("checked");
					//将笔记标题添加到编辑区标题输入框中
					$("#input_note_title").val(noteTitle);
					//将笔记内容编辑区清空
					um.setContent("");
					
			}
		},
		error: function(){
			alert("创建笔记失败");
		}
	});

}



//根据笔记id加载笔记信息
function loadNote(){

	//将当前选中的笔记设置为选中状态
	$("#note_list a.checked").removeClass("checked");//找到note_list下面带有checked属性的a元素
	$(this).find("a").addClass("checked");//给当前点击的li中的a元素添加checked样式
				
	//获取笔记id
	var noteId = $(this).data("noteId");
	//发送ajax请求
	$.ajax({
		url: "http://localhost:8081/T_Note/note/load.do",
		type: "post",
		data: {"noteId" : noteId},
		dataType: "json",
		success: function(result){
			if(result.status==0){//成功
				var note = result.data;  //获取服务器的笔记对象
				//将返回的笔记信息填充到编辑区
				$("#input_note_title").val(note.cn_note_title);
				um.setContent(note.cn_note_body);
				//alert(note.cn_note_body);
			}
		},
		error: function(){
			alert("加载笔记信息失败");
		}
	});

}



//控制笔记列表的下拉菜单显示或隐藏
function showNoteMenu(){

	var $menu = $(this).parent().next();//$menu:就是div
	//is函数：判断元素是否满足选择器
	
	if($menu.is(":hidden")){
		$menu.slideDown(100);  //显示点中笔记的菜单
	}else{
		$menu.slideUp(100);  //隐藏点钟笔记的菜单
	}
	
	//将其他的li菜单隐藏
	$menu.parent().siblings().find(".note_menu").hide();
	
	//将当前li设置为选中状态
	$("#note_list a").removeClass("checked");
	$menu.prev().addClass("checked");
	//阻止事件冒泡
	return false;
	
	
	
}


//更新笔记信息
function updateNote(){

	//获取笔记id，笔记标题，笔记内容
//	var noteId = $("#note_list a.checked").parent().data("noteId");
//	var noteTitle = $("#input_note_title").val().trim();
//	var noteBody = um.getContent();//使用um编辑器对象

	 var noteId = $("#note_list a.checked").parent().data("noteId");
	 var noteTitle = $("#input_note_title").val().trim();
	 var noteBody = um.getContent();

	//格式检查
	if(noteId==undefined){
		alert("请选择要更新的笔记");
		return;
	}
	//发送ajax请求
	$.ajax({
		url: "http://localhost:8081/T_Note/note/update.do",
		type: "post",
		data: {"noteId":noteId, "noteTitle":noteTitle, "noteBody":noteBody},
		dataType: "json",
		success: function(result){
			if(result.status==0){ //成功
				//如果标题发送改变，修改笔记列表中li的标题
				var li_noteTitle = $("#note_list a.checked").text();
				if(li_noteTitle != noteTitle){
					//修改
					var str = '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+ noteTitle +'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
					$("#note_list a.checked").html(str);
				}
				
				alert("保存笔记成功");
			}
		},
		error: function(){
			alert("保存笔记失败");
		}
	});
	
}


//删除笔记操作
function sureDeleteNote(){
	
	var ok = confirm("确定将该笔记放入回收站?");
	if(!ok){
		return false;  //阻止事件冒泡
	}

	//获取笔记id
	var noteId = $(this).parents("li").data("noteId");
	//发送ajax请求
	$.ajax({
		url: "http://localhost:8081/T_Note/note/delete.do",
		type: "post",
		data: {"noteId":noteId},
		dataType: "json",
		success: function(result){
			if(result.status==0){
				$("#note_list a.checked").parent().remove();
				//alert(result.msg);
				return;
			}
		},
		error: function(){
			alert("删除笔记失败");
		}
	});
	return false;  //阻止事件冒泡

}



//分享笔记操作
function sureShareNote(){
	
	//获取笔记ID
	var noteId = $(this).parents("li").data("noteId");
	//发送Ajax请求
	$.ajax({
		url: "http://localhost:8081/T_Note/note/share.do",
		type: "post",
		data: {"noteId":noteId},
		dataType: "json",
		success: function(result){
			if(result.status==0 || result.status==1){
				//隐藏菜单(div)
				$("#note_list a.checked").next().hide();
				//弹出提示框
				alert(result.msg);
			}
		},
		error: function(){
			alert("分享笔记失败");
		}
	});

}



//分享笔记操作
function searchShareNotes(){
	
	//传入事件对象event，可以保证浏览器的兼容性
	var keyCode = event.keyCode;
	if(keyCode==13){  //回车的键值(ASCII码)：13
		//获取查询关键字
		var keyword = $("#search_note").val().trim();
		//发送ajax请求
		$.ajax({
			url: "http://localhost:8081/T_Note/note/search.do",
			type: "post",
			data: {"keyword":keyword},
			dataType: "json",
			success: function(result){
				if(result.status==0){
					var share_notes = result.data;  //查询的分享笔记结果
					//清空先前的查询结果
					$("#search_list").empty();
					//循环查询结果，生成li添加到搜索结果列表
					for(var i=0; i<share_notes.length; ++i){
						
						var shareTitle = share_notes[i].cn_share_title;
						//拼成笔记li
						var s_li = '<li class="online">';
							s_li +=	'<a >';
							s_li +=	'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+ shareTitle;
							s_li +=	'</a>';
							s_li +=	'</li>';
						$("#search_list").append(s_li);
					}
					//切换笔记显示区
					$("#pc_part_6").show();//搜索笔记笔记区显示
					$("#pc_part_2").hide();//全部笔记区隐藏
					
				}
			},
			error: function(){
				alert("搜索笔记失败");
			}
		});
	}

}