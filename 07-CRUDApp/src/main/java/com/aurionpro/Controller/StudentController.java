package com.aurionpro.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.aurionpro.Entity.Student;
import com.aurionpro.Service.StudentService;

@Component
public class StudentController implements CommandLineRunner{
	
	@Autowired
	private StudentService service;

	@Override
	public void run(String... args) throws Exception {
		
//		//1.Insert
//		Student s1=new Student("ashwini","ash@gmail.com");
//		service.addStudent(s1);
//		
//		System.out.println("Student 1 added: "+s1);
//		
//		Student s2=new Student("pradip","pradip@gmail.com");
//		service.addStudent(s2);
//		
//		System.out.println("Student 2 added: "+s2);
//		
//		//2.Read
//		Student found1=service.getStudent(1L);
//		System.out.println("Found Student 1:"+found1);
//		
//		Student found2=service.getStudent(2L);
//		System.out.println("Found Student 2:"+found2);
//		
//		
//		//3.Update
//		found1.setName("sahil");
//		service.update(found1);
//		
//		//4.List All
//		System.out.println("All students:"+service.getAllStudents());
//		
//		
//		//5.Delete
//		service.deleteStudent(1L);
//		System.out.println("After Deletion:"+service.getAllStudents());
//		
//		
//		//6.Add Many Students
//		List<Student>studentList=new ArrayList<>();
//		studentList.add(new Student("ashwini","ash@gmail.com"));
//		studentList.add(new Student("sahil","sahil@gmail.com"));
//		studentList.add(new Student("nihal","nihal@gmail.com"));
//		studentList.add(new Student("anurag","anurag@gmail.com"));
//		studentList.add(new Student("shubham","shubham@gmail.com"));
//		studentList.add(new Student("durgesh","durgesh@gmail.com"));
//		studentList.add(new Student("sudershan","shudershan@gmail.com"));
//		service.addStudents(studentList);
		
		
//		//7.Find students by name
//		String name="ashwini";
//		List<Student>list=service.findStudentByName(name);
//		System.out.println(list);
//		
		//8.Delete By Name
//		
//		System.out.println("All students before Delete:"+service.getAllStudents());
//		String studentName="anurag";
//		service.deleteStudentByName(studentName);
//		System.out.println("All students After Delete:"+service.getAllStudents());
		
		//9. Update email 
		String email="xyz@gmail.com";
		long id=4;
		Student student=service.updateById(id, email);
		System.out.println(student);
		
		
	}

	
}
