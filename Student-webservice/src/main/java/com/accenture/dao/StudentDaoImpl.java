package com.accenture.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.model.Student;




@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private SessionFactory SessionFactory;

	private Session getSession() {
		Session session = null;
		try {
			session = SessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
			session = SessionFactory.openSession();
		}
		return session;

	}

	@Override
	public String SaveStudent(Student student) {
		getSession().save(student);
		return "Record Submitted Successfully with :" + student.getName();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> GetAllStudent() {
		//List<Student> liststudent=new ArrayList<>();
		//Criteria criteria=getSession().createCriteria(Student.class);
		//liststudent=criteria.list();
		return getSession().createCriteria(Student.class).list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> GetStudentByName(String name) {
		//List<Student> liStudents=new ArrayList<>();
		//Criteria criteria=getSession().createCriteria(Student.class).add(Restrictions.eq("name", name));
		//liStudents=criteria.list();
		return getSession().createCriteria(Student.class).add(Restrictions.eq("name", name)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> GetStudentById(int id) {
		return getSession().createCriteria(Student.class).add(Restrictions.eq("id", id)).list();
	}

	@Override
	public String deleteStudentById(int id) {
		Query query= getSession().createQuery("delete from Student as s where s.id=:stdId");
		query.setParameter("stdId", id);
		int i=query.executeUpdate();
		String msg=null;
		if(i>0) {
			msg="Record deleted successfully..";
		}else {
			msg="Having Some trouble while deleting records";
		}
		return msg;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> updateStudentById(Student student) {
		Query query=getSession().createQuery("update Student as s set s.hobbies=:hobbies, s.city=:city,s.state=:state where s.id=:id");
		query.setParameter("hobbies", student.getHobbies());
		query.setParameter("city", student.getCity());
		query.setParameter("state", student.getState());
		query.setParameter("id", student.getId());
		int i=query.executeUpdate();
		List<Student> listStudent=null;
		if(i>0) {
			listStudent=(List<Student>) getSession().createCriteria(Student.class).add(Restrictions.eq("id", student.getId())).list();
		}
		return  listStudent;
	}

}