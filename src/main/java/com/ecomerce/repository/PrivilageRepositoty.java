package com.ecomerce.repository;


import com.ecomerce.model.Privilege;
import com.ecomerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilageRepositoty extends JpaRepository<Privilege, Long> {

    Optional<Privilege> findByName(String name);
}
