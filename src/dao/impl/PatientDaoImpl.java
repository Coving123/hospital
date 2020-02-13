package dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import dao.PatientDao;
import entity.Patient;

@Repository
public class PatientDaoImpl extends GenericDaoImpl<Patient, Integer> implements PatientDao {

	@Override
	public Patient selectByUid(Integer uid) {
		String hql = new StringBuffer()
					.append(" from Patient where user.id = ? ")
					.toString();
		List<Patient> list = createQueryByHql(hql).setParameter(0, uid).list();
		if(list!=null&&list.size()!=0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Patient selectByName(String name) {
		String hql = new StringBuffer()
				.append(" from Patient where name = ? ")
				.toString();
		List<Patient> list = createQueryByHql(hql).setParameter(0, name).list();
		if(list!=null&&list.size()!=0){
			return list.get(0);
		}
		return null;
	}

}
