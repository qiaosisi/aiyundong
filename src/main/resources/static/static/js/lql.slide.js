function setSlideImgP(totalW){
	var subw = (1920 - totalW)/2;
	if(subw>0){
		$(".slide-content").css("overflow","auto");
		$(".slide-content").scrollLeft(subw);
		$(".slide-content").css("overflow","hidden");
	}
};

function clearSlideImgFocus(idn){
	$(".slide-content").children("a").eq(idn).blur().css("outline","none");
	$(".slide-control").children(".slide-ctrl-item").eq(idn).focus().css("outline","none");
};
function lazySlide(idn){
	var img = $(".slide-content").children("a").eq(idn).children("img");
	if(typeof(img.attr("lazy-src"))!="undefined"){
		if(img.attr("lazy-src") != img.attr("src")){
			img.attr("src",img.attr("lazy-src")).removeAttr("lazy-src");
		}
	}
};
function openSlideByIdn(idn){
	lazySlide(idn);
	$(".slide-content").children("a").hide().eq(idn).show();
	$(".slide-control").children(".slide-ctrl-item").removeClass("slide-ctrl-hl").eq(idn).addClass("slide-ctrl-hl");

	setSlideImgP(Number($(window).width()));

	var idnext = idn + 1;
	setTimeout("lazySlide("+idnext+")",800);
};
function setSlideW(){
	var totalW = Number($(window).width());
	if(totalW<=1000){
		$("body").css("width","1000px");
		$(".index-slide-login").css("width","1000px");
		$(".slide-content").css("width","1000px");

		var ctrleft = (totalW - $(".slide-control").width())/2;
		$(".slide-control").css("left",ctrleft+"px");

		$(".slide-prev").css("left","0px");

		var nextW = Number($(".slide-next").width());
		var nextleft = totalW - nextW;
		$(".slide-next").css("left",nextleft+"px");

		setSlideImgP(1000);
	}else if(totalW<=1920){
		$("body").css("width",totalW+"px");
		$(".index-slide-login").css("width",totalW+"px");
		$(".slide-content").css("width",totalW+"px");

		var ctrleft = (totalW - $(".slide-control").width())/2;
		$(".slide-control").css("left",ctrleft+"px");

		var prevW = Number($(".slide-prev").width());
		var prevleft = (totalW - 1000)/2 - prevW;
		$(".slide-prev").css("left",prevleft+"px");

		var nextW = Number($(".slide-next").width());
		var nextleft = (totalW + 1000)/2;
		$(".slide-next").css("left",nextleft+"px");

		setSlideImgP(totalW);
	}else{
		$("body").css("width",totalW+"px");
		$(".index-slide-login").css("width",totalW+"px");
		$(".slide").css("width","1920px");
		$(".slide-content").css("width","1920px");

		var subs = (totalW - 1920)/2;

		var ctrleft = (totalW - $(".slide-control").width())/2 - subs;
		$(".slide-control").css("left",ctrleft+"px");

		var prevW = Number($(".slide-prev").width());
		var prevleft = (totalW - 1000)/2 - prevW - subs;
		$(".slide-prev").css("left",prevleft+"px");

		var nextW = Number($(".slide-next").width());
		var nextleft = (totalW + 1000)/2 - subs;
		$(".slide-next").css("left",nextleft+"px");

		setSlideImgP(1920);
	}

};
function slideLoop(){
	var idn = Number($(".slide-content").children("a:visible").attr("idn"));
	var count = Number($(".slide-content").children("a").length - 1);
	if(idn < count){
		openSlideByIdn(Number(idn+1));
	}else{
		openSlideByIdn(0);
	}

};
function initSlide(){
	$(".slide-content").before("<img src='/static/img/silder_btn_left.png' class='slide-prev'/>").after("<img src='/static/img/silder_btn_right.png' class='slide-next'/>").after("<div class='slide-control'></div>");
	$(".slide-content").children("a").each(function(){
		$(".slide-control").append("<a href='javascript:void(0);' class='slide-ctrl-item' idn='"+ $(this).attr("idn") +"'></a>");
	});

	setSlideW();
	openSlideByIdn(0);

	$(".slide").hover(function(){
			$(".slide-prev").show();
			$(".slide-next").show();
		},
		function(){
			$(".slide-prev").hide();
			$(".slide-next").hide();
		});

	var slop = setInterval(slideLoop,5000);

	$(".slide-ctrl-item").click(function(){
		openSlideByIdn(Number($(this).attr("idn")));
	}).hover(function(){
			clearInterval(slop);
		},
		function(){
			slop = setInterval(slideLoop,5000);
		});

	$(".slide-prev").mouseover(function(){
		$(this).attr("src","/static/img/silder_btn_left_on.png");
	}).mouseout(function () {
		$(this).attr("src","/static/img/silder_btn_left.png");
	}).click(function(){
		var idn = Number($(".slide-content").children("a:visible").attr("idn"));
		var count = Number($(".slide-content").children("a").length - 1);

		if(idn > 0){
			var clIdn = Number(idn-1);
			openSlideByIdn(clIdn);
			clearSlideImgFocus(clIdn);
		}else if(idn == 0){
			openSlideByIdn(count);
			clearSlideImgFocus(count);
		}
	}).hover(function(){
			clearInterval(slop);
		},
		function(){
			slop = setInterval(slideLoop,5000);
		});

	$(".slide-next").mouseover(function(){
		$(this).attr("src","/static/img/silder_btn_right_on.png");
	}).mouseout(function () {
		$(this).attr("src","/static/img/silder_btn_right.png");
	}).click(function(){
		var idn = Number($(".slide-content").children("a:visible").attr("idn"));
		var count = Number($(".slide-content").children("a").length - 1);

		if(idn < count){
			var clIdn = Number(idn+1);
			openSlideByIdn(clIdn);
			clearSlideImgFocus(clIdn);
		}else if(idn == count){
			openSlideByIdn(0);
			clearSlideImgFocus(0);
		}
	}).hover(function(){
			clearInterval(slop);
		},
		function(){
			slop = setInterval(slideLoop,5000);
		});
};