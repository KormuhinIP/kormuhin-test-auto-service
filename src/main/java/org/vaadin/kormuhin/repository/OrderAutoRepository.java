package org.vaadin.kormuhin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vaadin.kormuhin.model.OrderAuto;

@Repository
public interface OrderAutoRepository extends JpaRepository<OrderAuto, Long> {


    /*@Query("from OrderAuto m where m.description=:description" )
            List<String> description();*/
 /*   @Query("from OrderAuto m where m.dateCreate" )
    Date dateCreate();
    @Query("from OrderAuto m where m.dateCompletion" )
    Date dateCompletion();


    @Query("from OrderAuto m where m.cost" )
   Double cost();


    @Query("from OrderAuto m where m.client" )
    String client();

    @Query("from OrderAuto m where m.statusOrder" )
    String statusOrder();

    @Query("from OrderAuto m where m.mechanic" )
    Mechanic mechanic();*/


}
