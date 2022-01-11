package com.puppies.security.auth.entity;

import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@Entity(name = "UserSecurityEntity")
@Table(
    name = "user_security",
    uniqueConstraints = {
        @UniqueConstraint(name = "user_security_user_name_unique", columnNames = "user_name"),
        @UniqueConstraint(name = "user_security_email_unique", columnNames = "email"),
        @UniqueConstraint(name = "user_security_user_id_unique", columnNames = "user_id")
    }
)
public class UserSecurityEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, updatable = false)
  @Type(type = "uuid-char")
  private UUID id;

  @Column(name = "user_id", nullable = false, updatable = false)
  @Type(type = "uuid-char")
  private UUID userId;

  @Column(name = "user_name", nullable = false)
  private String userName;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "enabled", nullable = false)
  private boolean enabled;

  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @JoinTable(
      name = "user_security_role",
      joinColumns = @JoinColumn(
          name = "user_security_id", referencedColumnName = "id",
          foreignKey = @ForeignKey(name = "user_security_role_user_security_id_fk")
      ),
      inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id",
          foreignKey = @ForeignKey(name = "user_security_role_role_id_fk")
      )
  )
  private Set<RoleEntity> roles;

}
