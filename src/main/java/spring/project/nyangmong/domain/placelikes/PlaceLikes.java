package spring.project.nyangmong.domain.placelikes;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.project.nyangmong.domain.boards.Boards;
import spring.project.nyangmong.domain.places.Places;
import spring.project.nyangmong.domain.user.User;

//  좋아요
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
// @Table(uniqueConstraints = {
// @UniqueConstraint(name = "fav_uk", columnNames = { "userId", "placesId" })
// })
public class PlaceLikes {
    // - 이 때 Boards와 User하고 연관관계가 생성된다. -PlaceLikes:Boards=1:N,PlaceLikes:User=1:
    // N의 관계이므로 다음과 같이 설정해 주었다.
    // 이 때 User를 보면, User 엔티티에서는 이미 boardsList로 관련 Boards를 참조하고 있다.
    // 그런데 PlaceLikes 엔티티에서 또 Boards를 참조한다.
    // 이러한 현상으로 발생할 수 있는 오류를 없애기 위해
    // JsonIgnoreProperties로 무시해 주었다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "boardsId")
    @ManyToOne
    private Boards boards;

    @JoinColumn(name = "userId")
    @JsonIgnoreProperties({ "boardsList" })
    @ManyToOne
    private User user;

    @JoinColumn(name = "placesId")
    @ManyToOne
    private Places places;

    @Builder
    public PlaceLikes(Boards boards, User user) {
        this.boards = boards;
        this.user = user;
    }
}