package com.jpabook.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.jpabook.domain.Order;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
private final EntityManager em;
 public void save(Order order) {
	 em.persist(order);
 }
 
 public Order findOne(Long id) {
	 return em.find(Order.class, id);
 }
 

}
