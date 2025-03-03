package com.github.payment_manager.repository;

import com.github.payment_manager.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    public Bill findById(String id);
}
