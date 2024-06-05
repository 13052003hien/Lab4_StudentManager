package tranthanhien.com.buoi4.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tranthanhien.com.buoi4.entity.Lop;
import tranthanhien.com.buoi4.entity.MonHoc;
import tranthanhien.com.buoi4.services.MonHocService;

import java.util.List;
import java.util.stream.Stream;

@Controller
@RequestMapping("/monhoc")
public class MonHocController {
    @Autowired
    private MonHocService monHocService;
    @GetMapping
    public String showAllMonHoc(Model model, @RequestParam(name = "tenMonHoc", required = false) String tenMonHoc){
        List<MonHoc> dsMonHoc;
        if(tenMonHoc != null && !tenMonHoc.isEmpty()){
            dsMonHoc = monHocService.searchMonHoc(tenMonHoc);
        }else {
            dsMonHoc = monHocService.getAllMonHoc();
        }
        model.addAttribute("dsMonHoc", dsMonHoc);
        return "monhoc/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("monhoc", new MonHoc());
        return "monhoc/add";
    }
    @PostMapping("/add")
    public String addMonHoc (@ModelAttribute("monhoc") MonHoc monHoc) {
        monHocService.addMonHoc(monHoc);
        return "redirect:/monhoc";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        MonHoc monHoc = monHocService.getMonHocById(id);
        return Stream.ofNullable(monHoc)
                .peek(foundMonHoc -> model.addAttribute("monhoc", foundMonHoc))
                .map(foundMonHoc -> "monhoc/edit")
                .findFirst()
                .orElse("redirect:/monhoc");
    }

    @PostMapping("/edit")
    public String updateMonHoc(@ModelAttribute("monhoc") MonHoc monHoc) {
        monHocService.updateMonHoc(monHoc);
        return "redirect:/monhoc";
    }
    @GetMapping("/delete/{id}")
    public String deleteMonHoc(@PathVariable("id") Long id) {
        monHocService.deleteMonHoc(id);
        return "redirect:/monhoc";
    }
}
