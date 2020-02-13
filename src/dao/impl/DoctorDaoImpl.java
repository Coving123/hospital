package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import dao.DoctorDao;
import entity.Doctor;
@Repository
public class DoctorDaoImpl extends GenericDaoImpl<Doctor, Integer> implements DoctorDao{

	@Override
	public Doctor selectByUid(Integer uid) {
		String hql = new StringBuffer()
					.append(" from Doctor where user.id = ? ")
					.toString();
		List<Doctor> list = createQueryByHql(hql).setParameter(0, uid).list();
		if(list!=null&&list.size()!=0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Doctor> selectByPage(Integer pageNo, Integer pageSize) {
		String hql = new StringBuffer()
					.append(" from Doctor ")
					.toString();
		Query query = createQueryByHql(hql);
		query.setFirstResult((pageNo-1)*pageSize);
		query.setMaxResults(pageNo*pageSize);
		return query.list();
	}

	@Override
	public Integer countDoctor() {
		String hql = new StringBuffer()
				.append(" select count(did) from Doctor ")
				.toString();
		Object count = createQueryByHql(hql).list().get(0);
		if(count == null){
			return 0;
		}
		return Integer.parseInt(count.toString());
	}

	@Override
	public Doctor selectByName(String name) {
		String hql = new StringBuffer()
				.append(" from Doctor where name = ? ")
				.toString();
		List<Doctor> list = createQueryByHql(hql).setParameter(0, name).list();
		if(list!=null&&list.size()!=0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Doctor> selectBySubject(String subject) {
		String hql = new StringBuffer()
			.append(" from Doctor where subject = ? ")
			.toString();
		List<Doctor> list = createQueryByHql(hql).setParameter(0, subject).list();
		return list;
	}
}
