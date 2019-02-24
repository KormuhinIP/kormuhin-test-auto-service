package org.vaadin.kormuhin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vaadin.kormuhin.model.Mechanic;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {

}
