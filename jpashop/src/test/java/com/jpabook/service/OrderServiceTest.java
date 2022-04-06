package com.jpabook.service;



import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jpabook.domain.Address;
import com.jpabook.domain.Member;
import com.jpabook.domain.Order;
import com.jpabook.domain.OrderStatus;
import com.jpabook.domain.item.Book;
import com.jpabook.domain.item.Item;
import com.jpabook.repository.OrderRepository;
@SpringBootTest
@Transactional
class OrderServiceTest {
@Autowired
EntityManager em;
@Autowired
OrderService orderService;
@Autowired
OrderRepository orderRepository;
@Test 
public void 상품주문()throws Exception{
	Member member=new Member();
	member.setName("회원1");
	member.setAddress(new Address("서울","강가","123-123"));
	em.persist(member);
	
	Book book=new Book();
	book.setName("시골 JPA");
	book.setPrice(10000);
	book.setStockQuantity(10);
	em.persist(book);
	
	int orderCount=2;
	Long orderId=orderService.order(member.getId(),book.getId(), orderCount);
	
	Order getOrder=orderRepository.findOne(orderId);
	assertEquals(OrderStatus.ORDER,getOrder.getStatus());
	assertEquals(1, getOrder.getOrderItems().size());
	assertEquals(10000*orderCount,getOrder.getTotalPrice());
	assertEquals(8 ,book.getStockQuantity());
}
  

}
