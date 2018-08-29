function FxFor(objFxa,_href,_title,_pic) {
		var url = "";
		if(objFxa == "fx_xl"){
			url = "http://v.t.sina.com.cn/share/share.php?url="  + encodeURIComponent(_href) + "&title=" + encodeURIComponent(_title)+"&appkey=3693970772&pic="+encodeURIComponent(_pic);
		}
		if(objFxa == "fx_qq"){
			url =  "http://connect.qq.com/widget/shareqq/index.html?url="+ encodeURIComponent(_href) + "&title=" + encodeURIComponent(_title)+"&pics="+_pic;
		}

    	if(objFxa == "fx_qq_space") {
			url ="http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url="+ encodeURIComponent(_href) + "&desc=&summary=&title=" + encodeURIComponent(_title)+"&site=&pics="+_pic;
		}
		window.open(url,'', 'width=600,height=450,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
};