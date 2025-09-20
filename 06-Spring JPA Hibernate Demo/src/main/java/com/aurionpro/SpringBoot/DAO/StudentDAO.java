package com.aurionpro.SpringBoot.DAO;

import java.util.List;

import com.aurionpro.SpringBoot.Entity.Student;

public interface StudentDAO {

	public void save(Student student);
	
	public Student findStudent(Integer id);
	
	public void saveMultiple(List<Student>students);
	
	public List<Student> getAllStudents();
	
	public void deleteStudent(Integer id);
	
	public List<Student> searchByFirstName();
	
	public List<Student> searchByLastName(String lastname);
	
	public Student updateFirstName(String name,int id);
	
	public void deleteByLastName(String name);
}
