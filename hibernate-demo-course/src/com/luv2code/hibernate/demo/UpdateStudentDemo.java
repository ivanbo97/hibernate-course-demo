package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Student;

public class UpdateStudentDemo {

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
			
			// normally we check if retrievedStudent is null
			// update the retrieved student name
			System.out.println("Updating student:...");
			retrievedStudent.setFirstName("Tim");
			
			// with committing the transaction, student's name get automatically updated
			session.getTransaction().commit();
			
			// Bulk update all student's e-mails
			
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Updating the e-mails...");
			session.
				createQuery("update Student set email='foo.gmail.com'")
				.executeUpdate();
			
			// committing the transaction
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}

	}

}

