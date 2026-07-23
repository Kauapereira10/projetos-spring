package com.kaua.booking_api.repository;

import com.kaua.booking_api.entity.EntityService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<EntityService, Long> {

    boolean existsServiceById(Long  id);
}
