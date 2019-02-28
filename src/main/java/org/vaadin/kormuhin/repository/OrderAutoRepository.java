package org.vaadin.kormuhin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.vaadin.kormuhin.domain.Mechanic;
import org.vaadin.kormuhin.domain.OrderAuto;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderAutoRepository extends JpaRepository<OrderAuto, Long> {


    @Query("SELECT u FROM OrderAuto u WHERE u.mechanic = :mechanic and u.dateCreate >= :dateBegin and u.dateCreate <= :dateEnd")
    List<OrderAuto> orderAuto(@Param("mechanic") Mechanic mechanic, @Param("dateBegin") Date dateBegin, @Param("dateEnd") Date dateEnd);


}
