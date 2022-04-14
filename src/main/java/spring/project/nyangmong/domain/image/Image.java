package spring.project.nyangmong.domain.image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import spring.project.nyangmong.domain.places.Places;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "image_UK", columnNames = { "placeId" })
})
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // 이미지 링크
    @Column(length = 300)
    private String image;

    @JoinColumn(name = "placeId")
    @ManyToOne
    private Places places;

}
