package tranthanhien.com.buoi4.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Entity(name = "Lop")
@Data
@Table(name = "Lop")
public class Lop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLop")
    private Integer maLop;

    @Size(min = 1, max = 7, message = "Tên lớp phải từ 1 đến 7 ký tự")
    @NotNull(message = "Tên lớp không được để trống")
    @Column(name = "TenLop",length = 7)
    private String tenLop;

    @OneToMany(mappedBy = "lop",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SinhVien> sinhViens;

}
