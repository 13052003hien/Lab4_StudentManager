package tranthanhien.com.buoi4.services;

import ch.qos.logback.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import tranthanhien.com.buoi4.entity.Lop;
import tranthanhien.com.buoi4.repository.ILopRepository;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class LopService {

    @Autowired
    private ILopRepository lopRepository;
    public List<Lop> getAllLop()
    {
        return lopRepository.findAll();
    }
    public Lop getLopById(Long id) {
        return lopRepository.findById(id). orElse( null);
    }
    public void addLop (Lop lop) {
        lopRepository.save(lop);
    }

    public void deleteLop (Long id) {
        lopRepository.deleteById(id);
    }
    public void updateLop (Lop lop)
    {
        lopRepository.save(lop);
    }

    //thêm hàm tìm kiếm lop theo ten
    public List<Lop> searchLop(String tenlop){
        if(StringUtils.isEmpty(tenlop))
            return lopRepository.findAll();
        return lopRepository.findAll().stream()
                .filter(lop -> lop.getTenLop().toLowerCase().contains(tenlop.toLowerCase()))
                .collect(Collectors.toList());
    }
}
