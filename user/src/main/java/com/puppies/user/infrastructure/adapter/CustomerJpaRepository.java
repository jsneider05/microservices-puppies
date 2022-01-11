package com.puppies.user.infrastructure.adapter;

import com.puppies.user.infrastructure.entity.CustomerEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {

}
