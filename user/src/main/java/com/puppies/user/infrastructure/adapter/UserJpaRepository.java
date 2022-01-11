package com.puppies.user.infrastructure.adapter;

import com.puppies.user.infrastructure.adapter.mapper.UserDetailDto;
import com.puppies.user.infrastructure.entity.UserEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

  @Query(value = "SELECT "
      + "u.id as id, "
      + "u.first_name as firstName, "
      + "u.last_name as lastName, "
      + "u.dob as dob, "
      + "us.email as email, "
      + "us.user_name as userName "
      + "FROM public.user u "
      + "LEFT JOIN public.user_security us on u.id = us.user_id "
      + "WHERE u.id = :id",
      nativeQuery = true
  )
  UserDetailDto selectUserDataById(@Param(value = "id") String id);

}
