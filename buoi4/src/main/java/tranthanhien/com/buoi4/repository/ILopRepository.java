package tranthanhien.com.buoi4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tranthanhien.com.buoi4.entity.Lop;

import java.util.List;

@Repository
public interface ILopRepository extends JpaRepository<Lop, Long> {
    List<Lop> findByTenLop(String tenLop);
}

