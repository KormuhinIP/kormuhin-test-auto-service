package org.vaadin.kormuhin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vaadin.kormuhin.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
   /* @Query("from Mechanic m where concat(m.firstName, ' ',m.lastName,' ',m.patronymic, '',m.hourlyPay) like concat('%', :name,'%') ")
    List<Mechanic>findByName(@Param("name")String name);*/
}
