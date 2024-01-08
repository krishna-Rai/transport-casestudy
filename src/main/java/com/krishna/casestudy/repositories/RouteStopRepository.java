package com.krishna.casestudy.repositories;

import com.krishna.casestudy.models.RouteStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteStopRepository extends JpaRepository<RouteStop,Long> {
}
