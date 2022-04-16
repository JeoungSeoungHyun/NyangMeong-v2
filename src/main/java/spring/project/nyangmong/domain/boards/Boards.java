package spring.project.nyangmong.domain.boards;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.boardlikes.BoardLikes;
import spring.project.nyangmong.domain.comment.Comment;
import spring.project.nyangmong.domain.image.BoardsImage;
import spring.project.nyangmong.domain.user.User;

// 댕댕이 자랑 게시판, 공지사항 합친거
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Boards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 댕댕이 자랑 게시판은 friend, 공지사항은 notice로 구분할 것
    @Column(length = 20, nullable = false)
    private String category;

    @Column(length = 60, nullable = false)
    private String title;

    // 작성내용
    @Lob
    @Column
    private String content;

    // 사진 경로 저장
    @Column(length = 300)
    private String boardImgurl;

    @ColumnDefault("0")
    @Column
    private int visitCheck;

    // 작성시간
    @CreatedDate // insert 할때만 동작
    private LocalDateTime createDate;

    // 수정시간
    @LastModifiedDate // update 할때만 동작
    private LocalDateTime updateDate;

    // 외래키로 받을 userId
    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    @JsonIgnoreProperties({ "boards" }) // messageConverter에게 알려주는 어노테이션
    @OneToMany(mappedBy = "boards", cascade = CascadeType.REMOVE) // 연관관계의 주인의
    // 변수명
    private List<BoardLikes> boardLikesList;

    @JsonIgnoreProperties({ "boards" }) // messageConverter에게 알려주는 어노테이션
    @OneToMany(mappedBy = "boards", cascade = CascadeType.REMOVE) // 연관관계의 주인의
    // 변수명
    private List<Comment> commentList;
    @JsonIgnoreProperties({ "boards" }) // messageConverter에게 알려주는 어노테이션
    @OneToMany(mappedBy = "boards", cascade = CascadeType.REMOVE) // 연관관계의 주인의
    // 변수명
    private List<BoardsImage> boardsImageList;
}
