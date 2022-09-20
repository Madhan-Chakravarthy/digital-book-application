package com.digitalbooks.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class LoginResponse {
	private Long id;
	private List<String> roles = new ArrayList<String>();
	private String token;
}
