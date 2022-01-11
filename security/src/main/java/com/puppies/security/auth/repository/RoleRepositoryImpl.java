package com.puppies.security.auth.repository;

import com.puppies.security.auth.mapper.RoleMapper;
import com.puppies.security.auth.model.Role;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RoleRepositoryImpl implements RoleRepository {

  private final RoleJpaRepository jpaRepository;
  private final RoleMapper mapper;

  @Override
  public Set<Role> getAll() {
    return mapper.toRoles(jpaRepository.findAll());
  }
}
