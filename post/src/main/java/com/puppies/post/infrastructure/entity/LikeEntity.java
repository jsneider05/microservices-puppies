package com.puppies.post.infrastructure.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "LikeEntity")
@Table(name = "like")
public class LikeEntity {

    @EmbeddedId
    private LikeId id;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(
            name = "post_id",
            foreignKey = @ForeignKey(
                    name = "like_post_post_id_fk"
            )
    )
    private PostEntity post;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;

}
