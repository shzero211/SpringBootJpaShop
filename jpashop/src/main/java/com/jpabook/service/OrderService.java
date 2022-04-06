package com.jpabook.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpabook.domain.Delivery;
import com.jpabook.domain.Member;
import com.jpabook.domain.Order;
import com.jpabook.domain.OrderItem;
import com.jpabook.domain.OrderStatus;
import com.jpabook.domain.item.Item;
import com.jpabook.repository.ItemRepository;
import com.jpabook.repository.MemberRepository;
import com.jpabook.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
private final OrderRepository orderRepository;
private final MemberRepository memberRepository;
private final ItemRepository itemRepository;

//주문
@Transactional
public Long order(Long memberId,Long itemId,int count) {
	Member member=memberRepository.findOne(memberId);
	Item item=itemRepository.findOne(itemId);
	
	Delivery delivery=new Delivery();
	delivery.setAddress(member.getAddress());
	
	OrderItem orderItem=OrderItem.createOrderItem(item,item.getPrice(),count);
	Order order=Order.createOrder(member, delivery,orderItem);
	orderRepository.save(order);
	return order.getId();
}
//주문취소
public void cancelOrder(Long orderId) {
	Order order=orderRepository.findOne(orderId);
	order.cancel();
}

//public List<Order> findOrders(OrderSearch orderSearch){
//	return orderRepository.findAll(orderSearch);
//} 

}
