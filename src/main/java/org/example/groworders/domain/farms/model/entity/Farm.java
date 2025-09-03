package org.example.groworders.domain.farms.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.groworders.domain.crops.model.entity.Crop;
import org.example.groworders.domain.users.model.entity.User;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Farm {
    @Id // nullable = false 포함
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(nullable = false) // 허용 범위는 validation에서 처리
    private Integer size;

    @Column(length = 100) // 농장 소개글 생략 가능
    private String contents;

    // 농장 사진 생략 가능
    private String farmImage;

    // 다대일 (farm:user)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 일대다 (farm:crop)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "farm")
    private List<Crop> cropList;
}
