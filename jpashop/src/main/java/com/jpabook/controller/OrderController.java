package com.jpabook.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jpabook.domain.Member;
import com.jpabook.domain.item.Item;
import com.jpabook.service.ItemService;
import com.jpabook.service.MemberService;
import com.jpabook.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {
private final  OrderService orderService;
private final MemberService memberService;
private final ItemService itemService;

@GetMapping("/order")
public String createForm(Model model) {
	List<Member> members=memberService.findMembers();
	List<Item> items=itemService.findItems();
	model.addAttribute("members",members);
	model.addAttribute("items",items);
	return "order/orderForm";
}
}
