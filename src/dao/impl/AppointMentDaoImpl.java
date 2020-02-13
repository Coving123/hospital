package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import dao.AppointMentDao;
import entity.AppointMent;

@Repository
public class AppointMentDaoImpl extends GenericDaoImpl<AppointMent, Integer> implements AppointMentDao {

	public List<AppointMent> getAppointMentByPatientAndPage(Integer pid, Integer pageNo, Integer pageSize)
			throws Exception {
		String hql = new StringBuffer()
			.append(" from AppointMent where patient.pid = ? ")
			.toString();
		Query query = createQueryByHql(hql);
		query.setParameter(0, pid);
		query.setFirstResult(pageSize * (pageNo - 1));
		query.setMaxResults(pageNo * pageSize);
		return query.list();
	}

	public List<AppointMent> getAppointMentByDoctorAndPage(Integer did, Integer pageNo, Integer pageSize)
			throws Exception {
		String hql = new StringBuffer()
			.append(" from AppointMent where doctor.did = ? ")
			.toString();
		Query query = createQueryByHql(hql);
		query.setParameter(0, did);
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageNo * pageSize);
		return query.list();
	}

	public Integer getCountOfAppointMentByPatient(Integer pid) throws Exception {
		String hql = new StringBuffer()
			.append(" select count(aid) from AppointMent where patient.pid = ? ")
			.toString();
		Object count = createQueryByHql(hql).setParameter(0, pid).list().get(0);
		if (count == null) {
			return 0;
		}
		return Integer.parseInt(count.toString());
	}

	public Integer getCountOfAppointMentByDoctor(Integer did) throws Exception {
		String hql = new StringBuffer()
			.append(" select count(aid) from AppointMent where doctor.did = ? ")
			.toString();
		Object count = createQueryByHql(hql).setParameter(0, did).list().get(0);
		if (count == null) {
			return 0;
		}
		return Integer.parseInt(count.toString());
	}

	@Override
	public List<AppointMent> getAllAppointMent() {
		String hql = new StringBuffer()
		.append(" from AppointMent ")
		.toString();
		return createQueryByHql(hql).list();
	}

	@Override
	public List<AppointMent> getAppointMentByDid(Integer did) {
		String hql = new StringBuffer()
		.append(" from AppointMent where doctor.did = ? ")
		.toString();
		return createQueryByHql(hql).setParameter(0, did).list();
	}

}
