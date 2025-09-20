package com.aurionpro.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int roleId;
	
	@Column
	private String rolename;
	
	@OneToMany(mappedBy = "role")
	private List<User>users;
}


//		user --->Role 
//		1----->1 Role
//		N----->1 Role
//		
//		student -->course 
//		1----> N
//		M<---1
//		
//		M<->N