package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import dao.RecordDao;
import entity.Record;
@Repository
public class RecordDaoImpl extends GenericDaoImpl<Record, Integer> implements RecordDao {

	public Integer getCountOfRecordByPatient(Integer pid) throws Exception {
		String hql = new StringBuffer()
				.append(" select count(rid) from Record where patient.pid = ?")
				.toString();
		Object count = createQueryByHql(hql).setParameter(0, pid).list().get(0);
		if(count == null){
			return 0;
		}
		return Integer.parseInt(count.toString());
	}

	public Integer getCountOfRecordByDoctor(Integer did) throws Exception {
		String hql = new StringBuffer()
				.append(" select count(rid) from Record where doctor.did = ?")
				.toString();
		Object count = createQueryByHql(hql).setParameter(0, did).list().get(0);
		if(count == null){
			return 0;
		}
		return Integer.parseInt(count.toString());
	}
	
	public Integer getCountOfRecordByNurse() throws Exception {
		String hql = new StringBuffer()
				.append(" select count(rid) from Record ")
				.toString();
		Object count = createQueryByHql(hql).list().get(0);
		if(count == null){
			return 0;
		}
		return Integer.parseInt(count.toString());
	}
	
	public List<Record> getRecordByPatientAndPage(Integer pid,
			Integer pageNo, Integer pageSize) throws Exception {
		String hql = new StringBuffer()
				.append(" from Record where patient.pid = ? ")
				.toString();
		Query query = createQueryByHql(hql);
		query.setParameter(0, pid);
		query.setFirstResult((pageNo-1)*pageSize);
		query.setMaxResults(pageNo*pageSize);
		return query.list();
	}

	public List<Record> getRecordByDoctorAndPage(Integer did, Integer pageNo,
			Integer pageSize) throws Exception {
		String hql = new StringBuffer()
				.append(" from Record where doctor.did = ? ")
				.toString();
		Query query = createQueryByHql(hql);
		query.setParameter(0, did);
		query.setFirstResult((pageNo-1)*pageSize);
		query.setMaxResults(pageNo*pageSize);
		return query.list();
	}
	
	public List<Record> getRecordByNurseAndPage(Integer pageNo, Integer pageSize) throws Exception {
		String hql = new StringBuffer()
				.append(" from Record ")
				.toString();
		Query query = createQueryByHql(hql);
		query.setFirstResult((pageNo-1)*pageSize);
		query.setMaxResults(pageNo*pageSize);
		return query.list();
	}

	@Override
	public List<Record> getRecordByDid(Integer did) {
		String hql = new StringBuffer()
		.append(" from Record where doctor.did = ? ")
		.toString();
		Query query = createQueryByHql(hql);
		query.setParameter(0, did);	
		return query.list();
	}

}
