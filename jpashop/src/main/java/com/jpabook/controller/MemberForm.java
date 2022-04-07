package com.jpabook.controller;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {
	@NotEmpty(message = "이름이 비어있습니다.")
	private String name;
	
	private String city;
	
	private String street;
	
	private String zipcode;
}
