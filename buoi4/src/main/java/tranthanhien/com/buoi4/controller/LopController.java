package tranthanhien.com.buoi4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tranthanhien.com.buoi4.entity.Lop;
import tranthanhien.com.buoi4.services.LopService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Controller
@RequestMapping("/lop")
public class LopController {
    @Autowired
    private LopService lopService;
    @GetMapping
    public String showAllLop (Model model, @RequestParam(name = "tenLop", required = false) String tenLop) {
        List<Lop> dsLop;
        if(tenLop != null && !tenLop.isEmpty()){
            dsLop = lopService.searchLop(tenLop);
        }else {
            dsLop = lopService.getAllLop();
        }
        model.addAttribute(  "dsLop", dsLop);
        return "lop/list";
    }
    @GetMapping("/add")
    public String showAddForm (Model model) {
        model.addAttribute( "lop", new Lop());
        return "lop/add";
    }
    @PostMapping("/add")
    public String addLop (@ModelAttribute("lop") Lop lop) {
        lopService.addLop(lop);
        return "redirect:/lop";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Lop lop = lopService.getLopById(id);
        return Stream.ofNullable(lop)
                .peek(foundLop -> model.addAttribute("lop", foundLop))
                .map(foundLop -> "lop/edit")
                .findFirst()
                .orElse("redirect:/lop");
    }

    @PostMapping("/edit")
    public String updateLop(@ModelAttribute("lop") Lop lop) {
        lopService.updateLop(lop);
        return "redirect:/lop";
    }
    @GetMapping("/delete/{id}")
    public String deleteLop(@PathVariable("id") Long id) {
        lopService.deleteLop(id);
        return "redirect:/lop";
    }
}
