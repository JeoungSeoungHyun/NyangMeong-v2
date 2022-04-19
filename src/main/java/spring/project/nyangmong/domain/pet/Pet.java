package spring.project.nyangmong.domain.pet;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 반려동물 이름
    @Column(length = 20, nullable = false)
    private String petName;

    // 반려동물 성별
    @Column(length = 10)
    private String petGender;

    // 반려동물 종류
    @Column(length = 60)
    private String petSpices;

    // 반려동물 나이
    @Column(length = 300)
    private String petAge;

    // 프로필 사진 경로 저장
    @Column(length = 300)
    private String petImgurl;

    // 입력한때
    @CreatedDate // insert 할때만 동작
    private LocalDateTime createDate;

    // 외래키 userID
    // n:1 - 사람은 많은 반려동물과 함께할 수 있다.
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}