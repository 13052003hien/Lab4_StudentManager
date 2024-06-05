package tranthanhien.com.buoi4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tranthanhien.com.buoi4.entity.MonHoc;

import java.util.List;

@Repository
public interface IMonHocRepository extends JpaRepository<MonHoc, Long> {
    List<MonHoc> findByTenMonHoc(String tenMonHoc);
}
