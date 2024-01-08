package com.krishna.casestudy.repositories;

import com.krishna.casestudy.models.Bus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus,Long> {
    Optional<Bus> findByBusNumber(String busNumber);

}
