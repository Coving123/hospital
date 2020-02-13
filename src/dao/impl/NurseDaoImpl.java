package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import dao.NurseDao;
import entity.Nurse;

@Repository
public class NurseDaoImpl extends GenericDaoImpl<Nurse, Integer> implements NurseDao{

	@Override
	public Nurse selectByUid(Integer uid) {
		String hql = new StringBuffer()
					.append(" from Nurse where user.id = ? ")
					.toString();
		List<Nurse> list = createQueryByHql(hql).setParameter(0, uid).list();
		if(list!=null&&list.size()!=0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Nurse> selectByPage(Integer pageNo, Integer pageSize) {
		String hql = new StringBuffer()
					.append(" from Nurse ")
					.toString();
		Query query = createQueryByHql(hql);
		query.setFirstResult((pageNo-1)*pageSize);
		query.setMaxResults(pageNo*pageSize);
		return query.list();
	}

	@Override
	public Integer countNurse() {
		String hql = new StringBuffer()
				.append(" select count(nid) from Nurse ")
				.toString();
		Object count = createQueryByHql(hql).list().get(0);
		if(count == null){
			return 0;
		}
		return Integer.parseInt(count.toString());
	}

	@Override
	public Nurse selectByName(String name) {
		String hql = new StringBuffer()
				.append(" from Nurse where name = ? ")
				.toString();
		List<Nurse> list = createQueryByHql(hql).setParameter(0, name).list();
		if(list!=null&&list.size()!=0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Nurse> selectBySubject(String subject) {
		String hql = new StringBuffer()
			.append(" from Nurse where subject = ? ")
			.toString();
		List<Nurse> list = createQueryByHql(hql).setParameter(0, subject).list();
		return list;
	}
}
