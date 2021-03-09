package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		try {
			
			// create a student object
			System.out.println("Creating a new student object...");
			Student student = new Student("Daffy","Duck","daffy@luv2code.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the student...");
			System.out.println(student);
			session.save(student);
			
			// commit the transaction
			System.out.println("The transaction is done.");
			session.getTransaction().commit();	
			
			System.out.println("Saved student. Generated id: " + student.getId());
			
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on id: primary key
			System.out.println("\nGetting student with the id: " + student.getId());
			
			Student retrievedStudent = session.get(Student.class, student.getId());
			
			// normally we check if retrievedStudent is null
			System.out.println("Get complete: " + retrievedStudent);
			
			// commit the transaction
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}

	}

}

