package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SharedSessionContract;
import org.springframework.stereotype.Repository;

import dao.RequireMentDao;
import entity.RequireMent;
import entity.Nurse;
import entity.Medicine;
import entity.Patient;

@Repository
public class RequireMentDaoImpl extends GenericDaoImpl<RequireMent, Integer> implements RequireMentDao{

	@Override
	public List<RequireMent> getRequireMentByNurseAndPage(Integer nid, Integer pageNo, Integer pageSize)
			throws Exception {
		String hql = new StringBuffer()
       		 .append(" from RequireMent where nurse.nid = ? ")
       		 .toString(); 
			Query query = createQueryByHql(hql);
			query.setParameter(0, nid);
			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageNo * pageSize);
			return query.list();
	}

	@Override
	 public List<RequireMent> getRequireMentByPatientAndPage(Integer pid, Integer pageNo, Integer pageSize)
			throws Exception {
		    
		
		         String hql = new StringBuffer()
		        		 .append(" from RequireMent where patient.pid = ? ")
		        		 .toString(); 
//		       from RequireMent a, Medicine b where a.patient.pid = ? 
//        StringBuffer hql = new StringBuffer();
//
//        hql.append(" from RequireMent a, Medicine b where 1=1 ");
//        if(a.pid!=null){
//            hql.append(" and patient.pid = ?");
//        }

//		    String hql ="from Medicine a, RequireMent b where b.patient.pid = ?  from Medicine a join a.RequireMent b where 1=1""; 
			Query query = createQueryByHql(hql);
			query.setParameter(0, pid);
			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageNo * pageSize);
			List<RequireMent> list = query.list();
			return list;
	}

	@Override
	public int getCountOfRequireMentByNurse(Integer nid) throws Exception {
		String hql = new StringBuffer()
				.append(" select count(oid) from RequireMent where nurse.nid = ? ")
				.toString();
			Object count = createQueryByHql(hql).setParameter(0, nid).list().get(0);
			if (count == null) {
				return 0;
			}
			return Integer.parseInt(count.toString());
	}

	@Override
	public int getCountOfRequireMentByPatient(Integer pid) throws Exception {
		String hql = new StringBuffer()
				.append(" select count(oid) from RequireMent where patient.pid = ? ")
				.toString();
			Object count = createQueryByHql(hql).setParameter(0, pid).list().get(0);
			if (count == null) {
				return 0;
			}
			return Integer.parseInt(count.toString());
	}

	@Override
	public List<RequireMent> getAllRequireMent() {
		String hql = new StringBuffer()
				.append(" from RequireMent ")
				.toString();
				return createQueryByHql(hql).list();
	}

}
