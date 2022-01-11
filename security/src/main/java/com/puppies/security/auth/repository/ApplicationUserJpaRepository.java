package com.puppies.security.auth.repository;

import com.puppies.security.auth.entity.UserSecurityEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationUserJpaRepository extends JpaRepository<UserSecurityEntity, UUID> {

    @Query("SELECT i FROM UserSecurityEntity i WHERE i.userName = :userName")
    UserSecurityEntity selectApplicationUserByUsername(@Param("userName") String userName);

    @Query("" +
        "SELECT CASE WHEN COUNT(u) > 0 THEN " +
        "TRUE ELSE FALSE END " +
        "FROM UserSecurityEntity u " +
        "WHERE u.email = :email"
    )
    Boolean selectExistsByEmail(@Param(value = "email") String email);
}
