package com.puppies.post.infrastructure.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Entity(name = "TemporalPostImageStorageEntity")
@Table(name = "temporal_post_image_storage")
public class TemporalPostImageStorageEntity {

    @Id
    @Column(name = "filename", nullable = false, updatable = false)
    private String filename;

    @Column(name = "path", nullable = false, updatable = false)
    private String path;

    @Column(name = "image", nullable = false, updatable = false)
    private byte[] image;

}
