package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Student;

public class ReadStudentDemo {

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
			Student tempStudent = new Student("Daffy","Duck","daffy@gmail.com");
			
			//begin the transaction
			session.beginTransaction();
			
			//save the student object
			session.save(tempStudent);
			System.out.println("Saving student object");
			System.out.println(tempStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			//find out the student's id : primary key
			System.out.println("Saved student " + tempStudent.getId());
			
			//get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("Getting student with id "+  tempStudent.getId());
			
			Student myStudent = session.get(Student.class ,tempStudent.getId());
			
			System.out.println("Get complete" + myStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done..");
		}
		
		finally {
			factory.close();
		}
	}

}
