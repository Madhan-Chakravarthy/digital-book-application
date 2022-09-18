package com.digitalbooks.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalbooks.entity.Book;
import com.digitalbooks.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	List<Payment> findByUserId(Long userId);
}
