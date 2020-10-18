package com.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		
		// create session factory
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//begin the transaction
			session.beginTransaction();
			
			//query the students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			//display the students
			displayStudents(theStudents);
			
			//query students: lastname="doe"
			theStudents = session.createQuery("from Student s where s.lastName ='Doe' ").getResultList();
			
			//display the students
			displayStudents(theStudents);
			
			//query students: lastname="doe" Or firstName="Paul"
			theStudents = 
					session.createQuery("from Student s where " +""
							+ " s.lastName ='Doe' OR s.firstName ='Paul'").getResultList();
			
			//display the students
			displayStudents(theStudents);
			
			//query students where email LIKE "gmail.com"
			theStudents = 
					session.createQuery("from Student s where " +""
							+ " s.email LIKE '%gmail.com'").getResultList();
			
			//display the students
			displayStudents(theStudents);
			
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done");
			
		}
		
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
