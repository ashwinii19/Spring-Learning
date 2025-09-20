package com.aurionpro.SpringBoot.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurionpro.SpringBoot.Entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDAOImple implements StudentDAO{

	private EntityManager entityManager;
	
	@Autowired
	public StudentDAOImple(EntityManager entityManager) {
		this.entityManager=entityManager;
	}

	@Override
	@Transactional
	public void save(Student student) {
		entityManager.persist(student);
		
	}

	@Override
	public Student findStudent(Integer id) {
		
		return entityManager.find(Student.class,id);
	}

	@Override
	@Transactional
	public void saveMultiple(List<Student> students) {
		for(Student stu:students) {
			entityManager.persist(stu);
		}
		
	}

	@Override
	
	public List<Student> getAllStudents() {
	TypedQuery<Student>theQuery= entityManager.createQuery("from Student",Student.class);
		List<Student>list=theQuery.getResultList();
		return list;
	}

	@Override
	@Transactional
	public void deleteStudent(Integer id) {
		Student stu=entityManager.find(Student.class,id);
		
		if(stu!=null) {
			entityManager.remove(stu);
		}
		
	}

	@Override
	public List<Student> searchByFirstName() {
		TypedQuery<Student>theQuery=entityManager.createQuery("from Student where firstName='sahil'",Student.class);
		List<Student>list=theQuery.getResultList();
		return list;
	}

	@Override
	public List<Student> searchByLastName(String lastname) {
		
		TypedQuery<Student>theQuery=entityManager.createQuery("from Student where lastName=:theData",Student.class);
		theQuery.setParameter("theData", lastname);
		List<Student>list=theQuery.getResultList();
		return list;
	}

	@Override
	@Transactional
	public Student updateFirstName(String name,int id) {
		Student stu=entityManager.find(Student.class,id);
		stu.setFirstName(name);
		
		entityManager.merge(stu);
		
		return stu;
	}

	@Override
	@Transactional
	public void deleteByLastName(String name) {
		List<Student>list=searchByLastName(name);
		
		Student stu=list.get(0);
		
		if(stu!=null) {
			entityManager.remove(stu);
		}
		
	}

	
	
}
