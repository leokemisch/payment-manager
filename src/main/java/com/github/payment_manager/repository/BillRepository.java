package com.github.payment_manager.repository;

import com.github.payment_manager.domain.Bill;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    public Bill findById(String id);

    @Query("SELECT b FROM Bill b WHERE b.due BETWEEN :initialPeriod AND :finalPeriod AND (COALESCE(:description, '') = '' OR b.description = :description)")
    public List<Bill> list(@Param("initialPeriod") Date initialPeriod,
                           @Param("finalPeriod") Date finalPeriod,
                           @Param("description") String description,
                           Pageable pageRequest);


    @Query("SELECT COALESCE(SUM(b.value), 0) FROM Bill b WHERE b.payment BETWEEN :initialPeriod AND :finalPeriod")
    Double sumValuesBetweenPeriods(@Param("initialPeriod") Date initialPeriod,
                                   @Param("finalPeriod") Date finalPeriod);
}
