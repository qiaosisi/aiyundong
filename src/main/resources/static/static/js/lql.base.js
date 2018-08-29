 
 function fresh(leftsecond,a_id,i){
	  var tmp = leftsecond;
	  if(tmp<0){tmp=0;}
	  var __d=parseInt(tmp/3600/24);
	  var __h=parseInt((tmp/3600)%24)>=10?parseInt((tmp/3600)%24):"0"+parseInt((tmp/3600)%24);
	  var __m=parseInt((tmp/60)%60)>=10?parseInt((tmp/60)%60):"0"+parseInt((tmp/60)%60);
	  var __s=parseInt(tmp%60)>=10?parseInt(tmp%60):"0"+parseInt(tmp%60);
	  if (parseInt(__d)==0) {
		  if (__h=="00") {
			  if (__m=="00") {
				  $('.'+a_id).eq(i).html( __s+'秒');
			  }else{
				  $('.'+a_id).eq(i).html(__m+'分'+__s+'秒');
			  }
		  }else{
			  $('.'+a_id).eq(i).html(__h+'时'+__m+'分'+__s+'秒');
		  }
	  }else{
		  $('.'+a_id).eq(i).html(__d+'天'+__h+'时'+__m+'分'+__s+'秒');
	  }

  };
  
  function openTabByIdx(idx){
	$(".tab").children(".tab-items").children().removeClass("tab-open").eq(idx).addClass("tab-open");
	$(".tab").children(".tab-content").removeClass("dis-block").addClass("dis-none").eq(idx).removeClass("dis-none").addClass("dis-block");
  };
  
  //弹出窗
  	function hidDialogById(id){
		$("#"+id).hide();
		hidMask();
	};   
   	function showDialogById(id){
		showMask();
		var dialog = $("#"+id);
		var o_top = ($(window).height() - dialog.height())/2 + $(window).scrollTop();
		var o_left = ($(window).width() - dialog.width())/2;
		dialog.show();
		dialog.offset({top:o_top , left:o_left});
	};
   	function showMask(){
		$("body").css({"overflow-x":"hidden","overflow-y":"hidden"});
		var vieW = $(document.body).outerWidth(true) + "px";
		var vieH = $(document.body).outerHeight(true) + "px";
		$(".dialog-mask").css({"height": vieH , "width": vieW});
		$(".dialog-mask").show();
	};
	function hidMask(){
		$("body").css({"overflow-x":"visible","overflow-y":"visible"});
		$(".dialog-mask").hide();
	};
	//弹窗
	var init_shadow =function (selecter) {
	 var height = document.body.clientHeight;
	 var width =  document.body.clientWidth;
	 $(selecter).css({width:width,height:height})
	}
	var init_popping = function (selecter) {
	 var h1 = window.screen.availHeight;
	 var w1 = window.screen.availWidth;
	 var h2 =parseInt($(selecter).css("height"));
	 var w2 =parseInt( $(selecter).css("width"));
	 var top = (h1-h2)/2; var left = (w1-w2)/2;
	 $(selecter).css({top:top-100,left:left})
	}
	var dialog_init = function (selecter_shadow,selecter_pop) {
	 init_shadow(selecter_shadow);
	 init_popping(selecter_pop);
	}
	var dialog_show = function (selecter_shadow,selecter_pop) {
	 $("body").css({"overflow-x":"hidden","overflow-y":"hidden"});
	 $(selecter_shadow).css({display:"block"})
	 $(selecter_pop).css({display:"block"})
	}
	var dialog_close = function (selecter_shadow,selecter_pop) {
	 $("body").css({"overflow-x":"visible","overflow-y":"visible"});
	 $(selecter_shadow).css({display:"none"})
	 $(selecter_pop).css({display:"none"})
	}
	//单选钮
	function createRadioBtn(group,radioVluId,index,callback){

		var groupRadio = $(".radio-btn").filter("[data-group="+group+"]");
		groupRadio.each(function(){		
			$(this).unbind("click");
			$(this).children(".ico-radio-btn").removeClass("ico-radio-btn-on");
		});
		var defRadio = groupRadio.eq(index);
		$("#"+radioVluId).val(defRadio.attr("data-vlu"));
		defRadio.children(".ico-radio-btn").addClass("ico-radio-btn-on");
		
		groupRadio.each(function(){
			$(this).click(function(){
				if(!$(this).children(".ico-radio-btn").hasClass("ico-radio-btn-on")){
					$(".radio-btn").filter("[data-group="+$(this).attr("data-group")+"]").each(function(){
						$(this).children(".ico-radio-btn").removeClass("ico-radio-btn-on");						
					});
					$(this).children(".ico-radio-btn").addClass("ico-radio-btn-on");
					$("#"+radioVluId).val($(this).attr("data-vlu"));
					if(typeof(callback)!='undefined' && callback!=""){
						setTimeout(callback,1);
					}
				}
			});	
		});	
	};
  
    //数字加减组件
	   function setNumBtnVlu(cmp,vlu,minVlu,maxVlu){

            var cmpText = cmp.children(".num-txt");
            var cmpSub = cmp.children(".num-sub");
            var cmpAdd = cmp.children(".num-add");

            var setVlu = vlu;

            if(vlu<=minVlu){
                setVlu = minVlu;
								
				cmpAdd.removeClass("num-add-dis");
                cmpSub.addClass("num-sub-dis");
            }

            if(vlu>=maxVlu){
                setVlu = maxVlu;
								
				cmpAdd.addClass("num-add-dis");
                cmpSub.removeClass("num-sub-dis");
            }

            if(vlu>minVlu && vlu<maxVlu){				
				cmpAdd.removeClass("num-add-dis");
                cmpSub.removeClass("num-sub-dis");
            }

            cmp.attr("data-vlu", setVlu);
            cmpText.text(setVlu);
        };

        function createNumBtn(cmpId,defVlu,minVlu,maxVlu,callback){

            var cmp = $("#"+cmpId);
            var cmpSub = cmp.children(".num-sub");
            var cmpAdd = cmp.children(".num-add");

            setNumBtnVlu(cmp,defVlu,minVlu,maxVlu);

            cmpSub.click(function(){
                var subVlu = Number(cmp.attr("data-vlu")) - 1;
                setNumBtnVlu(cmp,subVlu,minVlu,maxVlu);
				if($(".time-limit").length > 1){
					$(".time-limit:last").remove();
				}
				if(typeof(callback)!='undefined' && callback!=""){
				   setTimeout(callback,1);
			    }
            });

            cmpAdd.click(function(){
                var addVlu = Number(cmp.attr("data-vlu")) + 1;
                setNumBtnVlu(cmp,addVlu,minVlu,maxVlu);
				$(".time-limit:last").after("<div class='time-limit'><img src='/static/img/send_time_img.png'><input class='datetimepicker' type='text' value='选择配送时间'></div>");
				if(typeof(callback)!='undefined' && callback!=""){
				   setTimeout(callback,1);
			    }
            });

        };

 	function createNumBtnAtrr(cmpId,defVlu,minVlu,maxVlu,callback){

	 	var cmp = $("#"+cmpId);
	 	var cmpSub = cmp.children(".num-sub");
	 	var cmpAdd = cmp.children(".num-add");

	 	setNumBtnVlu(cmp,defVlu,minVlu,maxVlu);

		cmp.attr("data-min", minVlu);
		cmp.attr("data-max", maxVlu);

	 	cmpSub.click(function(){
			var minV = cmp.attr("data-min");
			var maxV = cmp.attr("data-max");
		 	var subVlu = Number(cmp.attr("data-vlu")) - 1;

		 	setNumBtnVlu(cmp,subVlu,minV,maxV);

		 	if(typeof(callback)!='undefined' && callback!=""){
			 	setTimeout(callback,1);
		 	}
	 	});

	 	cmpAdd.click(function(){
			var minV = cmp.attr("data-min");
			var maxV = cmp.attr("data-max");
		 	var addVlu = Number(cmp.attr("data-vlu")) + 1;

		 	setNumBtnVlu(cmp,addVlu,minV,maxV);

		 	if(typeof(callback)!='undefined' && callback!=""){
			 	setTimeout(callback,1);
		 	}
	 	});

 	};

 
 	//浮点数运算
	function flpAdd(a, b) {
		var c, d, e;
		try {
			c = a.toString().split(".")[1].length;
		} catch (f) {
			c = 0;
		}
		try {
			d = b.toString().split(".")[1].length;
		} catch (f) {
			d = 0;
		}
		return e = Math.pow(10, Math.max(c, d)), (flpMul(a, e) + flpMul(b, e)) / e;
	};

	function flpSub(a, b) {
		var c, d, e;
		try {
			c = a.toString().split(".")[1].length;
		} catch (f) {
			c = 0;
		}
		try {
			d = b.toString().split(".")[1].length;
		} catch (f) {
			d = 0;
		}
		return e = Math.pow(10, Math.max(c, d)), (flpMul(a, e) - flpMul(b, e)) / e;
	};

	function flpMul(a, b) {
		var c = 0,
			d = a.toString(),
			e = b.toString();
		try {
			c += d.split(".")[1].length;
		} catch (f) {}
		try {
			c += e.split(".")[1].length;
		} catch (f) {}
		return Number(d.replace(".", "")) * Number(e.replace(".", "")) / Math.pow(10, c);
	};

	function flpDiv(a, b) {
		var c, d, e = 0,
			f = 0;
		try {
			e = a.toString().split(".")[1].length;
		} catch (g) {}
		try {
			f = b.toString().split(".")[1].length;
		} catch (g) {}
		return c = Number(a.toString().replace(".", "")), d = Number(b.toString().replace(".", "")), flpMul(c / d, Math.pow(10, f - e));
	};
	
	function fmoney(s, n){ 
		n = n > 0 && n <= 20 ? n : 2;  
		s = parseFloat(s + "").toFixed(n) + ""; 
		var l = s.split(".")[0].split("").reverse(); 
		var e = s.split(".")[1];
		var t = "";
		for (var i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : ""); 
		} 
		return t.split("").reverse().join("")+"."+e ; 
	};

    // 手机浏览器访问网站跳转H5 wx==微信内跳转路径，other == 除微信外其他浏览器跳转路径
	function webToMobile(wx,other) {
        if(navigator.userAgent.match(/(iPhone|iPod|iPad|Android|MQQBrowser)/i)){
            // 如果是微信内部访问加默认授权，其他手机浏览器直接跳转H5首页
            if (navigator.userAgent.match(/(MicroMessenger)/i)) {
                window.location.href = 'https://www.lequlai.com'+wx;
            }else{
                window.location.href = 'https://www.lequlai.com'+other;
            }
        }
    }

  $(document).ready(function(){
	  
  	  //选项卡组件
	  $(".tab-item").click(function(){
		  var per_idx = $(this).parent().children().index($(this).siblings(".tab-open"));
		  var idx = $(this).parent().children().index($(this));
		  
		  $(this).siblings(".tab-open").removeClass("tab-open");
		  $(this).addClass("tab-open");
		  
		  $(this).parent().parent().children(".tab-content").eq(per_idx).removeClass("dis-block").addClass("dis-none");
		  $(this).parent().parent().children(".tab-content").eq(idx).removeClass("dis-none").addClass("dis-block");
		  
		  $(this).nextAll(".tab-more").children("a").eq(per_idx).removeClass("dis-block").addClass("dis-none");
		  $(this).nextAll(".tab-more").children("a").eq(idx).removeClass("dis-none").addClass("dis-block");
	  });
	  
	  $("input").focusin(function(){
   			$(this).addClass("input-hl");
		}).focusout(function(){
			$(this).removeClass("input-hl");
		});
	  
	});