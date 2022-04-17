package spring.project.nyangmong.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.boardlikes.BoardLikes;
import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.domain.comment.Comment;
import spring.project.nyangmong.domain.fav.Fav;
import spring.project.nyangmong.domain.pet.Pet;

@AllArgsConstructor
@NoArgsConstructor
@Data
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

    // 회원가입 시 받을 이메일
    @Column(length = 60, nullable = false)
    private String email;

    // 프로필 사진 경로 저장
    @Column(length = 300)
    private String userImgurl;

    // 아이디 생성 날짜
    @CreatedDate // insert 할때만 동작
    private LocalDateTime createDate;

    // 유저 권한
    @Column(length = 20)
    // @ColumnDefault("일반")
    private String userAuth;

    // // 장소 좋아요 한 목록 -연결
    @JsonIgnoreProperties({ "user" }) // messageConverter에게 알려주는 어노테이션
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE) // 연관관계의 주인의 변수명
    private List<Fav> FavList;

    // // 쓴 댓글 목록 -연결
    @JsonIgnoreProperties({ "user" }) // messageConverter에게 알려주는 어노테이션
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE) // 연관관계의 주인의 변수명
    private List<Comment> CommentList;
    // // 쓴 게시글 목록 -연결
    @JsonIgnoreProperties({ "user" }) // messageConverter에게 알려주는 어노테이션
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE) // 연관관계의 주인의 변수명
    private List<Boards> BoardList;
    // // 반려동물 목록 - 연결
    @JsonIgnoreProperties({ "user" }) // messageConverter에게 알려주는 어노테이션
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE) // 연관관계의 주인의 변수명
    private List<Pet> petList;
    // // 게시글 좋아요 -연결
    @JsonIgnoreProperties({ "user" }) // messageConverter에게 알려주는 어노테이션
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE) // 연관관계의 주인의 변수명
    private List<BoardLikes> BoardLikesList;

    // // @LastModifiedDate // update 할때만 동작
    // // private LocalDateTime updateDate;

}
