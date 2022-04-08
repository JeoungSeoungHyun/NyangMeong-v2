package spring.project.nyangmong.domain.fav;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.catalina.User;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import spring.project.nyangmong.domain.places.Places;

// 즐겨찾기
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "fav_uk", columnNames = { "userId", "placesId" })
})
public class fav {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    @JoinColumn(name = "placesId")
    @ManyToOne
    private Places places;

}
