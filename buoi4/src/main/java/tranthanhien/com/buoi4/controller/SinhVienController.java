package tranthanhien.com.buoi4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tranthanhien.com.buoi4.entity.Lop;
import tranthanhien.com.buoi4.entity.MonHoc;
import tranthanhien.com.buoi4.entity.SinhVien;
import tranthanhien.com.buoi4.services.LopService;
import tranthanhien.com.buoi4.services.MonHocService;
import tranthanhien.com.buoi4.services.SinhVienService;

import java.util.List;
import java.util.stream.Stream;

@Controller
//@RequestMapping("/sinhvien")
public class SinhVienController {
    @Autowired
    private SinhVienService sinhVienService;
    @Autowired
    private LopService lopService;
    @Autowired
    private MonHocService monHocService;
    @GetMapping("/")
    public String redirectToSinhvien() {
        return "redirect:/sinhvien";
    }
    @GetMapping("/sinhvien")
    public String showAllSinhVien (Model model, @RequestParam(name = "hoTen", required = false) String hoTen) {
        List<SinhVien> dsSinhVien;
        if(hoTen != null && !hoTen.isEmpty()){
            dsSinhVien = sinhVienService.searchSinhVien(hoTen);
        }else {
            dsSinhVien = sinhVienService.getAllSinhVien();
        }
        model.addAttribute(  "dsSinhVien", dsSinhVien);
        return "sinhvien/list";
    }
    @GetMapping("/add")
    public String showAddForm (Model model) {
        model.addAttribute( "sinhvien", new SinhVien());
        List<Lop> dsLop = lopService.getAllLop();
        model.addAttribute("dsLop", dsLop);
        List<MonHoc> dsMonHoc = monHocService.getAllMonHoc();
        model.addAttribute("dsMonHoc", dsMonHoc);
        return "sinhvien/add";
    }
    @PostMapping("/add")
    public String addSinhVien (@ModelAttribute("sinhvien") SinhVien sinhVien) {
        sinhVienService.addSinhVien(sinhVien);
        return "redirect:/sinhvien";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        SinhVien sinhVien = sinhVienService.getSinhVienById(id);
        List<Lop> dsLop = lopService.getAllLop();
        model.addAttribute("dsLop", dsLop);
        List<MonHoc> dsMonHoc = monHocService.getAllMonHoc();
        model.addAttribute("dsMonHoc", dsMonHoc);
        return Stream.ofNullable(sinhVien)
                .peek(foundSinhVien -> model.addAttribute("sinhvien", foundSinhVien))
                .map(foundSinhVien -> "sinhvien/edit")
                .findFirst()
                .orElse("redirect:/sinhvien");
    }

    @PostMapping("/edit")
    public String updateSinhVien(@ModelAttribute("sinhvien") SinhVien sinhVien) {
        sinhVienService.updateLop(sinhVien);
        return "redirect:/sinhvien";
    }
    @GetMapping("/delete/{id}")
    public String deleteSinhVien(@PathVariable("id") Long id) {
        sinhVienService.deleteSinhVien(id);
        return "redirect:/sinhvien";
    }
}
