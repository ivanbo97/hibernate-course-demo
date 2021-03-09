package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		try {
			
			int studentId  = 1;
			
			
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on id: primary key
			System.out.println("\nGetting student with the id: " + studentId);
			
			Student retrievedStudent = session.get(Student.class, studentId);
			
			// delete student
			
			System.out.println("Deleting student: " + retrievedStudent);
			session.delete(retrievedStudent);
			
			// delete student alternative approach
			int delId=2;
			System.out.println("Deleteing student with id=" + delId);
			session
			.createQuery("delete from Student where id='" + delId +"'")
			.executeUpdate();
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		
			
			
		} finally {
			factory.close();
		}

	}

}

