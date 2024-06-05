package tranthanhien.com.buoi4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import tranthanhien.com.buoi4.entity.Lop;
import tranthanhien.com.buoi4.entity.MonHoc;
import tranthanhien.com.buoi4.entity.SinhVien;
import tranthanhien.com.buoi4.repository.IMonHocRepository;
import tranthanhien.com.buoi4.repository.ISinhVienRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonHocService {
    @Autowired
    private IMonHocRepository monHocRepository;

    public List<MonHoc> getAllMonHoc(){
        return monHocRepository.findAll();
    }
    public MonHoc getMonHocById(Long id){
        return monHocRepository.findById(id).orElse(null);
    }

    public void addMonHoc(MonHoc monHoc){
        monHocRepository.save(monHoc);
    }
    public void deleteMonHoc(Long id){
        monHocRepository.deleteById(id)    ;
    }
    public void updateMonHoc(MonHoc monHoc){
        monHocRepository.save(monHoc);
    }

    //thêm hàm tìm kiếm mon hoc theo ten
    public List<MonHoc> searchMonHoc(String tenMonHoc){
        if(StringUtils.isEmpty(tenMonHoc))
            return monHocRepository.findAll();
        return monHocRepository.findAll().stream()
                .filter(monHoc -> monHoc.getTenMonHoc().toLowerCase().contains(tenMonHoc.toLowerCase()))
                .collect(Collectors.toList());
    }
}
