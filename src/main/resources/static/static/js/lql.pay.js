 	var LQL={};
    LQL.currCouponId = -1;
	
	
	function pay_1_dialog_init(){
		
		$("#addDialog").click(function(){
            $("#gnee_tag").val("");
            $("#gnee_name").val("");
            $("#gnee_phone").val("");
            $("#gnee_address").val("");
			$("#submitDialog").attr("href","javascript:addAddress();");
            province();
            showDialogById("gneeAdd");
		});
		
		$(".dialog-close").click(function(){
            $(this).parent().find(".dialog-field").find(".dialog-info").empty();
			hidDialogById("gneeAdd");
		});
		
		$(".gnee-tag-con").click(function(){
			$(".gnee-tag-txt").val($(this).html());
		});
		
	}
	
	function createGoodsSelect(callback){
				
		$(".option-type").click(function(){
			if($(this).attr("data-sale")==1){
				var currhl = $(this).attr("data-idn");
				$("#goodsId").val(currhl);

				$(".option-type").each(function(i){
					$(this).removeClass("option-select");
					$(this).removeClass("option-hover");
				});

				$(this).addClass("option-select");
				if(typeof(callback)!='undefined' && callback!=""){
					setTimeout(callback,1);
				}
			}
			
		}).hover(
  			function () {
				var perhl = $("#goodsId").val();
				var currhl = $(this).attr("data-idn");
				
			    if(perhl != currhl){
    			   $(this).addClass("option-hover");
				}
  			},
  			function () {
				
				var perhl = $("#goodsId").val();
				var currhl = $(this).attr("data-idn");
				
				if(perhl != currhl){
					$(this).removeClass("option-hover");
				}	
  		});
		
	};
	
	function createCouponSelect(callback){
		
		$(".coupon").click(function(event){
            var limitCardAmountFlag = $("#limitCardAmountFlag").val();
            if(limitCardAmountFlag > 0 && $('#chargeCardVal').is(':checked')){
                $('#chargeCardVal').prop("checked", false);
            }

            if(!$(this).hasClass("coupon-no")){

                var currIdn = Number($(this).attr("data-idn"));

                $(".coupon").removeClass("coupon-select");
                $(".coupon").children(".inner").children(".cp-das").children(".cp-desc").html("");
                $(".coupon").children(".inner").children(".cp-das").children(".cp-action").html("点击使用");

                if(currIdn != LQL.currCouponId){
                    LQL.currCouponId = currIdn;
                    $("#couponId").val(currIdn);
                    $(this).addClass("coupon-select");
                    $(this).children(".inner").children(".cp-das").children(".cp-desc").html("已选择，");
                    $(this).children(".inner").children(".cp-das").children(".cp-action").html("点击取消");
                }else if(currIdn == LQL.currCouponId){
                    LQL.currCouponId = -1;
                }

				if(typeof(callback)!='undefined' && callback!=""){
					setTimeout(callback,1);
				}
			}

			return false;
		});

	};

	function createOneCouponSelect(callback){

		$(".one-coupon").click(function(event){
            var limitCardAmountFlag = $("#limitCardAmountFlag").val();
            if(limitCardAmountFlag > 0 && $('#chargeCardVal').is(':checked')){
                $('#chargeCardVal').prop("checked", false);
            }

			if(!$(this).hasClass("coupon-no")){

				var currIdn = Number($(this).attr("data-idn"));

				$(".one-coupon").removeClass("coupon-select");
				$(".one-coupon").children(".inner").children(".cp-das").children(".cp-desc").html("");
				$(".one-coupon").children(".inner").children(".cp-das").children(".cp-action").html("点击使用");
				$("#com_coupons").css("display","block");

				if(currIdn != LQL.currCouponId){
					LQL.currCouponId = currIdn;
					$("#couponId").val(currIdn);
					$(this).addClass("coupon-select");
					$(this).children(".inner").children(".cp-das").children(".cp-desc").html("已选择，");
					$(this).children(".inner").children(".cp-das").children(".cp-action").html("点击取消");
					$("#com_coupons").css("display","none");
				}else if(currIdn == LQL.currCouponId){
					LQL.currCouponId = -1;
				}

				if(typeof(callback)!='undefined' && callback!=""){
					setTimeout(callback,1);
				}
			}

			return false;
		});

	};
	
	function initBankSelect(){

		$(".bank").click(function(eve){

			$(".bank").each(function(i){
				$(this).removeClass("bank-selet");
			});

			$(this).addClass("bank-selet");
		}).hover(
  			function () {
    			$(this).addClass("bank-hover");
  			},
  			function () {
				$(this).removeClass("bank-hover");
  		});

		$(".bank").eq(0).trigger("click");
	};

	//时间格式转换
	function formatDate(date) {
		var year=date.getYear()+1900;
		var month=date.getMonth()+1;
		var date=date.getDate();
		return year+"."+month+"."+date;
	}