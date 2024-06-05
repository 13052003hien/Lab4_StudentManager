package tranthanhien.com.buoi4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tranthanhien.com.buoi4.entity.SinhVien;

import java.util.List;

@Repository
public interface ISinhVienRepository extends JpaRepository<SinhVien, Long> {
    List<SinhVien> findByHoTenContainingIgnoreCase(String hoTen);
}
