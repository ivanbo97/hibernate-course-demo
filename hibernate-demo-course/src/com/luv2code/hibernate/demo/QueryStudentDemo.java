package com.luv2code.hibernate.demo;

import java.util.List;

import javax.persistence.criteria.From;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Student;



public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		try {
			
			
			
			// start a transaction
			session.beginTransaction();
			
			// query students
			// !!! Here we use getResultList() because list() is deprecated since Hibernate 5.2
			List<Student> theStudents = session
					.createQuery("from Student")
					.getResultList();
			
			// display students (using lambda expression)
			displayStudents(theStudents);
			
			// query the students whose last name is 'Doe'
			
			theStudents = session
					.createQuery("from Student s where s.lastName = 'Doe'")
					.getResultList();
			
			System.out.println("\nThe students who have last name of 'Doe'");
			displayStudents(theStudents);
			
			// query the students with lastName='Doe' OR firstName='Daffy'
			theStudents = session
					.createQuery("from Student s where s.firstName='Daffy' "
					+ "OR s.lastName='Doe'")
					.getResultList();
			System.out.println("\nThe students who have last name of 'Doe' or first name 'Daffy'");
			displayStudents(theStudents);
			
			// query students where email like '%luv2code.com'
			theStudents = session
					.createQuery("from Student s where s.email like '%luv2code.com'")
					.getResultList();
			System.out.println("\nThe students whose email ends with 'luv2code.com'");
			displayStudents(theStudents);
			
			// commit the transaction
			System.out.println("The transaction is done.");
			session.getTransaction().commit();		
			
		} finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		theStudents.forEach(System.out::println);
	}

}

