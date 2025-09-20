package com.aurionpro.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.Entity.Student;
import com.aurionpro.Repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;
	
	public void addStudent(Student student) {
		repository.save(student);
	}
	
	public Student getStudent(Long id) {
		return repository.findById(id);
	}
	
	public List<Student>getAllStudents(){
		return repository.findAll();
	}
	
	public void update(Student student) {
		repository.update(student);
	}
	
	public void deleteStudent(Long id) {
		repository.delete(id);
	}
	
	public void addStudents(List<Student>studentList) {
		for(Student student:studentList) {
			repository.save(student);
		}
	}
	
	public List<Student>findStudentByName(String name){
		List<Student>list=repository.findByName(name);
		return list;
	}
	
	public void deleteStudentByName(String name) {
		repository.deleteByName(name);
	}
	
	public Student updateById(Long id,String email) {
		Student student=repository.updateById(id, email);
		return student;
	}
}
