/**
 * Created by ZKP on 2017/6/23.
 */
/*直接购买订单页添加或修改地址*/
var address_html=
    "<div class='dialog-mask'></div>" +
    "<div id='add_address_main' style='width: 100%;background-color: #fff;'>" +
    "<div class='rl-input-msg dis-none'></div>" +
    "<input type='hidden' name='addressId' id='addressId'>" +
    "<input type='hidden' name='is_default' id='is_default' value='0'>" +
    "<div class='h5-add-address-line'>" +
    "<label for='gnee_name'>收货人：</label>" +
    "<input type='text' name='gnee_name' id='gnee_name'/>" +
    "</div>" +
    "<div class='h5-add-address-line'>" +
    "<label for='gnee_phone'>手机号码：</label>" +
    "<input type='number' name='gnee_phone' id='gnee_phone' maxlength='11'  onkeyup='phone_input()'/>" +
    "</div>" +
    "<div class='h5-add-address-line'>" +
    "<label for='gnee_address'>所在地区：</label>" +
    "<input onfocus='gnee_address.blur()' readonly='readonly' type='text'  name='gnee_address' id='gnee_address' />" +
    "<input type='hidden' name='district_code_province' id='address_code_province'>" +
    "<input type='hidden' name='district_code_city' id='address_code_city' >" +
    "<input type='hidden' name='district_code_county' id='address_code_county'>" +
    "<img class='arrow' src='/static/img/wx/assets/arrow.png'/>" +
    "</div>" +
    "<div class='h5-add-address-line'>" +
    "<label for='gnee_address_detail'>详细地址：</label>" +
    "<input type='text' name='gnee_address_detail' id='gnee_address_detail' />" +
    "</div>" +
    "<div class='h5-add-address-line' >" +
    "<label for='gnee_tag'>地址别名：</label>" +
    "<input type='text' name='gnee_tag' id='gnee_tag' />" +
    "<div class='h5-add-address-note'><span class='fz-12'>家里</span></div>" +
    "<div class='h5-add-address-note'><span class='fz-12'>公司</span></div>" +
    "<div class='h5-add-address-note'><span class='fz-12'>父母家</span></div>" +
    "<div class='h5-add-address-note'><span class='fz-12'>朋友</span></div>" +
    "<div class='h5-add-address-note'><span class='fz-12'>同事</span></div>" +
    "</div>" +
    "<div class='button-float'>" +
    "<div class='left return'>返回</div>" +
    "<div class='right save'>保存</div>" +
    "</div>" +
    "</div>" +
    "<div id='add_address_minor'>" +
    "<div style='background-color: white;width: 100%;height: 5em'>" +
    "<div class='fz-16' style='color:#C4CCE2;margin-top: 5px;'><span>所在地区</span></div>" +
    "<div style='height: 13px;float: right;margin-right: 20px;padding-top: 2px'>" +
    "<button type='button' id='add_address_minor_button' style='width:40px;border: 1px solid #6A16A4;line-height:19px;background-color: white;color: #5A5877;border-radius: 2px'>确定</button>" +
    "</div><br/>" +
    "<div class='fz-14' style='float: left;margin-left: 20px;margin-top:5px;width: 100%;'>" +
    "<div id='add_address_minor_span1' style='display: none;float: left;color: #5A5877;border-bottom: 2px solid #6A16A4;height: 27.5px;'>请选择</div>" +
    "<div id='add_address_minor_span2' style='display: none;float: left;margin-left: 30px;color: #5A5877;border-bottom: 2px solid #6A16A4;height: 27.5px;'>请选择</div>" +
    "<div id='add_address_minor_span3' style='display: none;float: left;margin-left: 30px;color: #5A5877;border-bottom: 2px solid #6A16A4;height: 27.5px;'>请选择</div>" +
    "<input type='hidden' id='district_code_province_minor'>" +
    "<input type='hidden' id='district_code_city_minor'>" +
    "<input type='hidden' id='district_code_county_minor'>" +
    "</div><br>" +
    "<div style='width: 100%;border-top: 1px solid #C4CCE2;margin-top: 14px;margin-bottom: 0px'></div>" +
    "</div>" +
    "<div style='width:100%;height:25em;overflow: scroll;overflow-x: hidden' class='add_address_minor_selects'>" +
    "<div id='add_address_minor_support0'style=' width:100%;text-align:left;padding-left:20px;color: #5A5877;font-size: 14px;' >" +
    "<ul id='add_address_minor_select1' ></ul>" +
    "</div>" +
    "<div id='add_address_minor_support1'style=' width:100%;text-align:left;float: left;padding-left:20px;color: #5A5877;font-size: 14px;'>" +
    "<ul id='add_address_minor_select2' ></ul>" +
    "</div>" +
    "<div id='add_address_minor_support2' style=' width:100%;text-align:left;float: left;padding-left:20px;color: #5A5877;font-size: 14px;'>" +
    "<ul id='add_address_minor_select3' ></ul>" +
    "</div>" +
    "</div>" +
    "<br>" +
    "</div>" ;
