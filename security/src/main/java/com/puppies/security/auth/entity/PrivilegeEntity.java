package com.puppies.security.auth.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PrivilegeEntity")
@Table(name = "privilege")
public class PrivilegeEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @Type(type="uuid-char")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

}