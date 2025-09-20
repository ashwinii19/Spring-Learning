package com.aurionpro.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aurionpro.Entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class StudentRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(Student student) {
		entityManager.persist(student);
	}
	
	public Student findById(Long id) {
		return entityManager.find(Student.class,id);
	}
	
	public List<Student>findAll(){
		return entityManager.createQuery("from Student",Student.class).getResultList();
	}
	
	public void update(Student student) {
		entityManager.merge(student);
	}
	
	public void delete(Long id) {
		Student student= entityManager.find(Student.class,id);
		
		if(student!=null) {
			entityManager.remove(student);
		}
	}
	
	public List<Student>findByName(String name){
		TypedQuery<Student>query=entityManager.createQuery("from Student where name=?1",Student.class);
		query.setParameter(1, name);
		List<Student>list=query.getResultList();
		
		return list;
		
	}
	
	public void deleteByName(String name) {
		List<Student>list=findByName(name);
		
		if(list!=null) {
	
		for(Student student:list) {
			entityManager.remove(student);
		}
		
		}
	}
	
	
	public Student updateById(Long id ,String email) {
		Student student=findById(id);
		
		if(student!=null) {
			student.setEmail(email);
			entityManager.merge(student);
		}
		return student;
	}
	
}
