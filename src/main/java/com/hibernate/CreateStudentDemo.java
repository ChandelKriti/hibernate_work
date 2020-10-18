package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		
		// create session factory
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// use the session object to save JAVA object :
			
			// create a student object
			System.out.println("Creating student object");
			Student tempStudent = new Student("Paul","Wall","paulw@gmail.com");
			
			//begin the transaction
			session.beginTransaction();
			
			//save the student object
			session.save(tempStudent);
			System.out.println("Saving student object");
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done");
			
		}
		
		finally {
			factory.close();
		}
	}

}
