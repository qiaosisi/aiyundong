//倒计时
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

//倒计时
function fresh_day(leftsecond,a_id,i){
    var tmp = leftsecond;
    var __d=parseInt(tmp/3600/24);
    if (__d ==0){
        // 只剩一天
        __d = parseInt(tmp/3600)
        if (__d < 1){
            // 小于一小时
            __d = parseInt(tmp/60)
            if(__d <1){
                // 小于一分钟
                if (tmp < 0){
                    $('.'+a_id).eq(i).html('0天');
                }else{
                    $('.'+a_id).eq(i).html(tmp+'秒');
                }
            }else{
                // 大于等于1分钟
                $('.'+a_id).eq(i).html(__d+'分');
            }
        }else{
            // 大于一小时
            $('.'+a_id).eq(i).html(__d+'小时');
        }
    }else if(__d < 0){
        // 已经到期
        $('.'+a_id).eq(i).html('0天');
    }else{
        // 还没有到期
        $('.'+a_id).eq(i).html(__d+'天');
    }
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
		var vieW = $(window).outerWidth(true) + "px";
		var vieH;
		if($(document.body).outerHeight(true) > $(window).outerHeight(true)){
			vieH = $(document.body).outerHeight(true)  + $(window).scrollTop()+ "px";
		}else {
			vieH = $(window).outerHeight(true) + "px";
		}
		$(".dialog-mask").css({"height": vieH , "width": vieW});
		$(".dialog-mask").show();
	};
	function hidMask(){
		$("body").css({"overflow-x":"visible","overflow-y":"visible"});
		$(".dialog-mask").hide();
	};

//选择类型弹出窗
function hidDialogTypeById(id){
	$("#"+id).hide();
	hidTypeMask();
};
function showDialogTypeById(id){
	showTypeMask();
	var dialog = $("#"+id);

	var o_top = $(window).height() - dialog.height() + $(window).scrollTop();
	var o_left = ($(window).width() - dialog.width())/2;
	dialog.show();
	dialog.offset({top:o_top , left:o_left});
};
function showTypeMask(){
	$("body").css({"position":"fixed","overflow-x":"hidden","overflow-y":"hidden"});
	var vieW = $(document.body).outerWidth(true) + "px";
	var vieH = $(document.body).outerHeight(true) + "px";
	$(".dialog-mask").css({"height": vieH , "width": vieW});
	$(".dialog-mask").show();
	$(".dashboard-btn").hide();
};
function hidTypeMask(){
	$("body").css({"position":"relative","overflow-x":"visible","overflow-y":"visible"});
	$(".dialog-mask").hide();
	$(".dashboard-btn").show();
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

//时间格式转换
function formatDate(date) {
	var year=date.getYear()+1900;
	var month=date.getMonth()+1;
	var date=date.getDate();
	return year+"."+month+"."+date;
}

// 订单24小时倒计时
function freshOrder(leftsecond,a_id,i){
    var tmp = leftsecond;
    if(tmp<0){tmp=0;}
    var __h=parseInt((tmp/3600)%24)>=10?parseInt((tmp/3600)%24):"0"+parseInt((tmp/3600)%24);
    var __m=parseInt((tmp/60)%60)>=10?parseInt((tmp/60)%60):"0"+parseInt((tmp/60)%60);
    var __s=parseInt(tmp%60)>=10?parseInt(tmp%60):"0"+parseInt(tmp%60);
	if (__h=="00") {
		if (__m=="00") {
			$('.'+a_id).eq(i).html( __s+'秒后自动取消订单');
		}else{
			$('.'+a_id).eq(i).html(__m+'分'+__s+'秒后自动取消订单');
		}
	}else{
		$('.'+a_id).eq(i).html(__h+'时'+__m+'分'+__s+'秒后自动取消订单');
	}
};

// H5支付阴影点击事件
function hidePayType() {
    $("body").css({"overflow-x":"visible","overflow-y":"visible"});
    $(".dialog-mask").hide();
    $(".pay-confirm").removeClass("dis-none")
    $(".select-payment-type").addClass("dis-none");
}