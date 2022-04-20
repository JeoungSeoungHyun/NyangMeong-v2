package spring.project.nyangmong.domain.boards;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.comment.Comment;
import spring.project.nyangmong.domain.user.User;

/**
 * GET /post/1 상세보기
 * User, Post, List<Comment>
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Boards { // N (드라이빙 테이블, FK의 주인)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tag;
    private String text;

    @Column(length = 300, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer pageCount; // 조회수

    // @JsonIgnoreProperties({ "boards" })
    // @OneToMany(mappedBy = "boards")
    // private List<PlaceLikes> placelikesList;

    private long PlaceLikesCount; // 좋아요 수

    @Builder
    public Boards(String tag, String text, User user, long PlaceLikesCount) {
        this.tag = tag;
        this.text = text;
        this.user = user;
        this.PlaceLikesCount = PlaceLikesCount;
    }

    public void setPlaceLikesCount(long PlaceLikesCount) {
        this.PlaceLikesCount = PlaceLikesCount;
    }

    @JsonIgnoreProperties({ "password" })
    // @JsonIgnoreProperties({"boardsList"})
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JsonIgnoreProperties({ "boards" }) // messageConverter에게 알려주는 어노테이션
    @OneToMany(mappedBy = "boards", cascade = CascadeType.REMOVE) // 연관관계의 주인의 변수명
    private List<Comment> comments;

    @CreatedDate // insert
    private LocalDateTime createDate;
    @LastModifiedDate // insert, update
    private LocalDateTime updateDate;
}