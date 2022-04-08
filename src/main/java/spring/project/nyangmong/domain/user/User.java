package spring.project.nyangmong.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class) // 이 부분 추가
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 회원가입 시 ID
    @Column(length = 20, nullable = false, unique = true)
    private String userId;

    // 유저닉네임
    @Column(length = 40, nullable = false)
    private String userName;

    // 회원가입 시 입력할 비밀번호
    // 1234 -> SHA256(해시 알고리즘) -> AB4539GDUF3AE -> 이렇게 안하면 시큐리티 거부
    @Column(length = 20, nullable = false)
    private String password;

    // 회원가입 시 받을 전화번호
    @Column(length = 60, nullable = false)
    private String phoneNum;

    // 프로필 사진 경로 저장
    @Column(length = 300)
    private String userImgurl;

    // 아이디 생성 날짜
    @CreatedDate // insert 할때만 동작
    private LocalDateTime createDate;

    // 유저 권한
    @Column(length = 20)
    @ColumnDefault("일반")
    private String userAuth;

    // @LastModifiedDate // update 할때만 동작
    // private LocalDateTime updateDate;

}
