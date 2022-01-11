package com.puppies.user.infrastructure.entity;

import com.puppies.security.auth.entity.UserSecurityEntity;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Entity(name = "CustomerEntity")
@Table(name = "customer")
public class CustomerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, updatable = false)
  @Type(type = "uuid-char")
  private UUID id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "dob", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
  private LocalDate dob;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(
      name = "user_security_id",
      referencedColumnName = "id",
      foreignKey = @ForeignKey(
          name = "customer_user_security_user_security_id_fk"
      )
  )
  private UserSecurityEntity userSecurity;

}
