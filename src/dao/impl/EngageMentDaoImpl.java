package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import dao.EngageMentDao;
import entity.EngageMent;

@Repository
public class EngageMentDaoImpl extends GenericDaoImpl<EngageMent, Integer> implements EngageMentDao {

	public List<EngageMent> getEngageMentByPatientAndPage(Integer pid, Integer pageNo, Integer pageSize)
			throws Exception {
		String hql = new StringBuffer()
			.append(" from EngageMent where patient.pid = ? ")
			.toString();
		Query query = createQueryByHql(hql);
		query.setParameter(0, pid);
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageNo * pageSize);
		return query.list();
	}

	public List<EngageMent> getEngageMentByNurseAndPage(Integer nid, Integer pageNo, Integer pageSize)
			throws Exception {
		String hql = new StringBuffer()
			.append(" from EngageMent where nurse.nid = ? ")
			.toString();
		Query query = createQueryByHql(hql);
		query.setParameter(0, nid);
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageNo * pageSize);
		return query.list();
	}

	public Integer getCountOfEngageMentByPatient(Integer pid) throws Exception {
		String hql = new StringBuffer()
			.append(" select count(eid) from EngageMent where patient.pid = ? ")
			.toString();
		Object count = createQueryByHql(hql).setParameter(0, pid).list().get(0);
		if (count == null) {
			return 0;
		}
		return Integer.parseInt(count.toString());
	}

	public Integer getCountOfEngageMentByNurse(Integer nid) throws Exception {
		String hql = new StringBuffer()
			.append(" select count(eid) from EngageMent where nurse.nid = ? ")
			.toString();
		Object count = createQueryByHql(hql).setParameter(0, nid).list().get(0);
		if (count == null) {
			return 0;
		}
		return Integer.parseInt(count.toString());
	}

	@Override
	public List<EngageMent> getAllEngageMent() {
		String hql = new StringBuffer()
		.append(" from EngageMent ")
		.toString();
		return createQueryByHql(hql).list();
	}

	@Override
	public List<EngageMent> getEngageMentByNid(Integer nid) {
		String hql = new StringBuffer()
		.append(" from EngageMent where nurse.nid = ? ")
		.toString();
		return createQueryByHql(hql).setParameter(0, nid).list();
	}

}