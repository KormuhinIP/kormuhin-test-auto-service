package org.vaadin.kormuhin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vaadin.kormuhin.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
