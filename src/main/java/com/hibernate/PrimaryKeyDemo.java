package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Student;

public class PrimaryKeyDemo {

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
					
					// create 3 student objects
					System.out.println("Creating 3 student objects..");
					Student tempStudent1 = new Student("Paul","Wall","paulw@gmail.com");
					Student tempStudent2 = new Student("Mary","Public","mary@gmail.com");
					Student tempStudent3 = new Student("Bonita","Doe","bonita@gmail.com");
					
					//begin the transaction
					session.beginTransaction();
					
					//save the student object
					session.save(tempStudent1);
					session.save(tempStudent2);
					session.save(tempStudent3);
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
