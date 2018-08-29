package com.ranba.adm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdmIndexController {

    @GetMapping("/adm/index")
    public String indexPage(ModelMap modelMap) {
        return "/adm/index";
    }
}
