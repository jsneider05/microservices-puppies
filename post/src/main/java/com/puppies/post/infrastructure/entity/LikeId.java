package com.puppies.post.infrastructure.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LikeId implements Serializable {

    @Column(name = "customer_id")
    @Type(type = "uuid-char")
    private UUID customerId;

    @Column(name = "post_id")
    @Type(type = "uuid-char")
    private UUID postId;

}
