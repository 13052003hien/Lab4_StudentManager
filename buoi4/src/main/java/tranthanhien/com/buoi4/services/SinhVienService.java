package tranthanhien.com.buoi4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import tranthanhien.com.buoi4.entity.SinhVien;
import tranthanhien.com.buoi4.repository.ISinhVienRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SinhVienService {
    @Autowired
    private ISinhVienRepository sinhVienRepositor;

    public List<SinhVien> getAllSinhVien(){
        return sinhVienRepositor.findAll();
    }
    public SinhVien getSinhVienById(Long id){
        return sinhVienRepositor.findById(id).orElse(null);
    }

    public void addSinhVien(SinhVien sinhVien){
        sinhVienRepositor.save(sinhVien);
    }
    public void deleteSinhVien(Long id){
        sinhVienRepositor.deleteById(id)    ;
    }
    public void updateLop(SinhVien sinhVien){
        sinhVienRepositor.save(sinhVien);
    }

    //thêm hàm tìm kiếm sinh vien theo ten
    public List<SinhVien> searchSinhVien(String hoTen){
        if(StringUtils.isEmpty(hoTen))
            return sinhVienRepositor.findAll();
        return sinhVienRepositor.findAll().stream()
                .filter(sinhVien -> sinhVien.getHoTen().toLowerCase().contains(hoTen.toLowerCase()))
                .collect(Collectors.toList());
    }

}
