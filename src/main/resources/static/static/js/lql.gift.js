

function initGift(id,maxCount,addCallback,placeholder){
	
	var gift_add_html = '<!-- gift-add-item -->' +
	'<div class="gift-item add-item" data-idn="0" id="gift_add_item">' +
    	'<div class="htp"></div><div class="gift-panel gift-a gift-on">' +
        	'<img class="gift-img" src="/static/img/gift_box_a.png" />' +
			'<div class="htp-m"></div>'+
			'<div class="gift-add-count">(剩余 <span id="gift-add-count">0</span> 份)</div>' +
         '</div>' +
         '<div class="gift-panel gift-b ">' +
         	'<img class="gift-img" src="/static/img/gift_box_b.png" />' +
         	'<div class="gift-from">' +
				//寄语
            	'<div class="gift-from-line">' +
               		'<span class="gift-from-tilte" style="margin-top:1em;"><span class="fz-14">&nbsp寄语&nbsp&nbsp&nbsp</span></span>' +
			        '<input type="text" name="gnee_name" class="gnee_name_add" id="gnee_name_add" placeholder="'+placeholder+'" maxlength="32">' +
                '</div>' +
				//份数
                '<div class="gift-from-line">' +
        			'<span class="gift-from-tilte" style="line-height: 2.5em;"><span class="fz-14" style="float: left">&nbsp份数&nbsp&nbsp&nbsp</span></span>' +
                	'<div class="num-btn" id="lqlNum_add" data-vlu="0" data-min="0" data-max="0" style="float: inherit;display: inline-block;">' +
                    	'<span class="num-sub">-</span>' +
                    	'<label class="num-txt">1</label>' +
                        '<span class="num-add">+</span>' +
                	'</div>' +
                    '<a class="btn" id="gift_add_btn" style="float: right;display: inline-block;width: 3.2em;margin-right: 0.6em;margin-left: 0;"><span class="fz-14">包装</span></a>' +
                '</div>' +
				//备注
        		'<div class="gift-from-line" style="margin-top:1.1em;">' +
					'<span class="gift-from-tilte"><span class="fz-14">&nbsp备注&nbsp&nbsp&nbsp</span></span>' +
					'<input type="text" name="gnee_remind" id="gnee_remind_add" placeholder="例如：送给小乐（仅自己可见）" maxlength="28">' +
        		'</div>' +

				'<div class="gift-from-delete"><span class="fz-14" id="gift_canel_btn" style="line-height: 1.2em;display: block">取消</span></div>' +
            '</div>' +
        '</div>' +    
      '</div>';
	
	$("#"+id).addClass("gift-list");
	$("#"+id).append(gift_add_html);
	
	$("#gift_add_item").children(".gift-a").click(function(){
		$(this).removeClass("gift-on");
		$(this).next(".gift-b").addClass("gift-on");
	});
	
	$("#gift_canel_btn").click(function(){
		$("#gift_add_item").children(".gift-b").removeClass("gift-on");
		$("#gift_add_item").children(".gift-a").addClass("gift-on");
	}).hover(
  	function () {
    	$(this).addClass("hover");
  	},
  	function () {
    	$(this).removeClass("hover");
  	});

	createNumBtnAtrr('lqlNum_add',1,1,maxCount,null);
	
	$("#gift_add_btn").click(function(){
		
		$("#gift_add_item").children(".gift-b").removeClass("gift-on");
		$("#gift_add_item").children(".gift-a").addClass("gift-on");
		if(typeof(addCallback)!='undefined' && addCallback!=""){
			setTimeout(addCallback,1);
		}
	});
}


function addGift(id,defCount,maxCount,url,editCallback,delCallback,placeholder,textVlu,gift_remind,share_status){
	var share = "";
	if (share_status == 0) {
        share = "（未分享）";
	} else {
        share = "（已分享）";
	}
    var gift_remind_add = ""
	if ( gift_remind == null || gift_remind == "") {
        gift_remind_add = "";
    } else {
        gift_remind_add = gift_remind ;
	}

	var gift_html = '<!-- gift-item -->' +
	'<div class="gift-item" data-idn="'+id+'" id="gift_item_'+id+'">' +
		'<div class="htp"></div>' +
		'<div class="gift-panel gift-c gift-on">' +
			'<img class="gift-img-c " src="/static/img/gift_box_c.png?v=02" />' +
			'<div class="gift-qr-con" >' +
				'<div class="gift-qr-inner" >' +
					'<div class="gift-qr" id="qr_'+id+'"></div>' +
				'</div>' +
			'<br><div style="color: white;margin-top: -4px">使用微信扫一扫，发送给亲友</div>' +
			'</div>' +
			'<div class="gift-qr-area" id="gift_qrare_'+id+'" style="width:20em;height:16em;left:3.8em; top:0.5em;"></div>' +
			'<div class="gift-search-area"  style=" width:20em; height:3em; position:absolute; left:3.8em; top:17.5em;"></div>' +
		'</div>' +
		'<div class="gift-panel gift-b ">' +
		'<img class="gift-img" src="/static/img/gift_box_b.png" />' +
		'<div class="gift-from">' +
			//寄语
			'<div class="gift-from-line">' +
				'<span class="gift-from-tilte" style="margin-top:1em;"><span class="fz-14">&nbsp寄语&nbsp&nbsp&nbsp</span></span>' +
				'<input type="text" name="gnee_name" maxlength="32" id="gnee_name_'+id+'" placeholder="'+placeholder+'" value="'+textVlu+'">' +
			'</div>' +
			//份数
			'<div class="gift-from-line">' +
				'<span class="gift-from-tilte" style="line-height: 2.5em;"><span class="fz-14" style="float: left">&nbsp份数&nbsp&nbsp&nbsp</span></span>' +
				'<div class="num-btn" id="lqlNum_'+id+'" data-vlu="1" data-min="1" data-max="1" style="float: inherit;display: inline-block;">' +
					'<span class="num-sub">-</span>' +
					'<label class="num-txt">1</label>' +
					'<span class="num-add">+</span>' +
				'</div>' +
				'<a class="btn" id="gift_edit_'+id+'" style="float: right;display: inline-block;width: 3.2em;margin-right: 0.6em;margin-left: 0;"><span class="fz-14" >保存</span></a>' +
			'</div>' +
			//备注
			'<div class="gift-from-line" style="margin-top:1.1em;">' +
				'<span class="gift-from-tilte"><span class="fz-14">&nbsp备注&nbsp&nbsp&nbsp</span></span>' +
				'<input type="text" name="gnee_remind" id="gnee_remind_'+id+'" placeholder="例如：送给小乐（仅自己可见）" maxlength="28" value="'+gift_remind+'">' +
			'</div>' +
		  '<div class="gift-from-delete"><span class="fz-14" id="gift_del_'+id+'" style="line-height: 1.2em;display: block">删除礼品盒</span></div>' +
		'</div>' +
	  '</div>' +
	  '<div  style="text-align: center;color: #5A5877;font-size: 0.95em;"><span class="gift-remind-'+id+'">'+gift_remind_add+'</span><span class="gift-share-'+id+'">'+share+'</span></div>'+
	'</div>';

	$(".add-item").after(gift_html);

    $("#gift_item_"+id).find(".gift-search-area")
		.css( 'cursor','pointer')
		.click(function(){
			$(this).closest('.gift-c').removeClass("gift-on");
			$(this).closest('.gift-c').next(".gift-b").addClass("gift-on");
    	});
	$("#gift_qrare_"+id).prev(".gift-qr-con").hide();
	//检查是否已分享
	function is_shared() {

		$.get("/my/gifts/isSharedAjax",{id:id},
			function (response) {
			if ( response == 1 ) {
				$( ".gift-share-"+id ).text("（已分享）");
			} else if ( response == 0 ) {
                $( ".gift-share-"+id ).text("（未分享）");
			} else {
				alert ("礼盒参数错误") ;
			}
        },"text")
    }
	$("#gift_qrare_"+id).hover(
  	function () {
        //检查是否已分享
        is_shared();
    	$(this).prev(".gift-qr-con").show();
  	},
  	function () {
    	$(this).prev(".gift-qr-con").hide();
  	}).click(
	function(){
		$(this).prev(".gift-qr-con").show();
		event.stopPropagation();    //  阻止事件冒泡
	}
	);
	$("#qr_"+id).empty();
 	var gl_qrcode = new QRCode(document.getElementById("qr_"+id), {width : 230,height : 230});
  	gl_qrcode.makeCode(url);

	createNumBtnAtrr('lqlNum_'+id,defCount,1,maxCount,null);
	
	$("#gift_edit_"+id).click(function(){
		var itemg = $("#gift_item_"+id);
		itemg.children(".gift-b").removeClass("gift-on");
		itemg.children(".gift-c").addClass("gift-on");
		if(typeof(editCallback)!='undefined' && editCallback!=""){
			setTimeout(editCallback,1);
		}
	});
	
	$("#gift_del_"+id).click(function(){
		if(typeof(delCallback)!='undefined' && delCallback!=""){
			setTimeout(delCallback,1);
		}
	}).hover(
  	function () {
    	$(this).addClass("hover");
  	},
  	function () {
    	$(this).removeClass("hover");
  	});

};

function addCompleteGift(id,countVlu,textVlu,userName){

	var gift_html = '<!-- gift-item -->' +
		'<div class="gift-item" data-idn="'+id+'" id="gift_item_'+id+'">' +
			'<div class="htp"></div><div class="gift-panel gift-c gift-on">' +
				'<img class="gift-img-c " src="/static/img/gift_box_d.png" />' +
                '<span class="gift-text">礼盒被'+userName+'接收</span>' +
			'</div>' +
			'<div class="gift-panel gift-b ">' +
				'<img class="gift-img" src="/static/img/gift_box_b.png" />' +
				'<div class="gift-from-over">' +
					'<div class="gift-from-line-auto">' +
                        textVlu +
						'<br><br>'+
						countVlu +
					'份</div>' +
					'<div class="gift-from-delete" ><span class="fz-14" id="gift_del_'+id+'">关闭</span></div>' +
				'</div>' +
			'</div>' +
		'</div>';

	$(".add-item").after(gift_html);

	$("#gift_item_"+id).children(".gift-c").click(function(){
		$(this).removeClass("gift-on");
		$(this).next(".gift-b").addClass("gift-on");
	});


	$("#gift_del_"+id).click(function(){
        $("#gift_item_"+id).children(".gift-b").removeClass("gift-on");
        $("#gift_item_"+id).children(".gift-c").addClass("gift-on");
	}).hover(
		function () {
			$(this).addClass("hover");
		},
		function () {
			$(this).removeClass("hover");
		});

};

function addOutTimeGift(id,countVlu,textVlu){

	var gift_html = '<!-- gift-item -->' +
		'<div class="gift-item" data-idn="'+id+'" id="gift_item_'+id+'">' +
		'<div class="htp"></div><div class="gift-panel gift-c gift-on">' +
		'<img class="gift-img-c " src="/static/img/gift_box_e.png" />' +
		'<span class="gift-text">已过期失效，客服已为您处理完毕</span>' +
		'</div>' +
		'<div class="gift-panel gift-b ">' +
		'<img class="gift-img" src="/static/img/gift_box_b.png" />' +
		'<div class="gift-from-over">' +
		'<div class="gift-from-line-auto">' +
		textVlu +
		'<br><br>'+
		countVlu +
		'份</div>' +
		'<div class="gift-from-delete" ><span class="fz-14" id="gift_del_'+id+'">关闭</span></div>' +
		'</div>' +
		'</div>' +
		'</div>';

	$(".add-item").before(gift_html);

	$("#gift_item_"+id).children(".gift-c").click(function(){
		$(this).removeClass("gift-on");
		$(this).next(".gift-b").addClass("gift-on");
	});


	$("#gift_del_"+id).click(function(){
		$("#gift_item_"+id).children(".gift-b").removeClass("gift-on");
		$("#gift_item_"+id).children(".gift-c").addClass("gift-on");
	}).hover(
		function () {
			$(this).addClass("hover");
		},
		function () {
			$(this).removeClass("hover");
		});

};

function convertCompleteGift(id,countVlu,textVlu,userName){

    var item = $("#gift_item_"+id);
    var item_c = item.children(".gift-c");
    item_c.children(".gift-img-c").attr("src","/static/img/gift_box_d.png");
    item_c.children(".gift-qr-con").remove();
    item_c.children(".gift-qr-area").remove();
    item_c.children(".gift-img-c").after('<span class="gift-text">礼盒被'+userName+'接收</span>');
    var item_from_line = item.children(".gift-b").children(".gift-from").children(".gift-from-line");
    item_from_line.children("#gnee_name_"+id).replaceWith(textVlu);
    item_from_line.children("#lqlNum_"+id).replaceWith(countVlu);
    item_from_line.children("#gift_edit_"+id).remove();

    var del_btn = item.children(".gift-b").children(".gift-from").children(".gift-from-delete").children("#gift_del_"+id);
    del_btn.off();
    del_btn.html("关闭");
    del_btn.click(function(){
        $("#gift_item_"+id).children(".gift-b").removeClass("gift-on");
        $("#gift_item_"+id).children(".gift-c").addClass("gift-on");
    }).hover(
        function () {
            $(this).addClass("hover");
        },
        function () {
            $(this).removeClass("hover");
        });
}