package com.example.finalEclips.eclips.store.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDto {
	private String userId;
	private String password;
	private String name;
	private LocalDate birth;
	private String phone;
	private String email;
	private String role;
	private String type;
}
