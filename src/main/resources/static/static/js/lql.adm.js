function goback(){
    window.location.href = history.back();
}

function refreshRedis(key) {
    $.get({
        url: "/adm/refresh",type:"GET",data:{key:key},
        success: function (response) {
            switch (response) {
                case 0 : alert("操作成功");break;
                case 1 : alert("操作失败");break;
                default: alert("操作失败");break;
            }
        }
    })
}
/*适用于所有产品datalist搜索框*/
function getProductsByNameOrId(thisDom) {
    var thisJq=$(thisDom);
    var product_name = thisJq.val();
    $.ajax({
        url: "/adm/products/getProductsByNameOrId",
        data: {product_name: product_name},
        type: "POST",
        success: function (products) {
            var productJq = thisJq.next();
            productJq.empty();
            var optionHtml = "";
            $.each(products, function (index, product) {
                optionHtml += "<option  value='" + product.id + "' >" + product.id + "    " + product.product_name + "</option>";
            });
            productJq.append(optionHtml);
        }
    })
}

/*适用于所有产品选项datalist搜索框*/
function getProductOptionsByNameOrId(thisDom) {
    var thisJq=$(thisDom);
    var option_title = thisJq.val();
    $.ajax({
        url: "/adm/productOptions/getProductOptionsByNameOrId",
        data: {option_title: option_title},
        type: "POST",
        success: function (options) {
            var optionJq = thisJq.next();
            optionJq.empty();
            var optionHtml = "";
            $.each(options, function (index, option) {
                optionHtml += "<option  value='" + option.id + "' >" + option.id + "    " + option.option_title + "</option>";
            });
            optionJq.append(optionHtml);
        }
    })
}