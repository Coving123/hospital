package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import dao.MedicineDao;
import entity.Doctor;
import entity.Medicine;

@Repository
public class MedicineDaoImpl extends GenericDaoImpl<Medicine, Integer> implements MedicineDao{

	@Override
	public List<Medicine> selectByPage(Integer pageNo, Integer pageSize) {
		String hql = new StringBuffer()
					.append(" from Medicine ")
					.toString();
		Query query = createQueryByHql(hql);
		query.setFirstResult((pageNo-1)*pageSize);
		query.setMaxResults(pageNo*pageSize);
		return query.list();
	}

	@Override
	public Integer countMedicine() {
		String hql = new StringBuffer()
				.append(" select count(mid) from Medicine ")
				.toString();
		Object count = createQueryByHql(hql).list().get(0);
		if(count == null){
			return 0;
		}
		return Integer.parseInt(count.toString());
	}

	@Override
	public Medicine selectByName(String name) {
		String hql = new StringBuffer()
				.append(" from Medicine where name = ? ")
				.toString();
		List<Medicine> list = createQueryByHql(hql).setParameter(0, name).list();
		if(list!=null&&list.size()!=0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Medicine> selectByCategory(String category) {
		// TODO Auto-generated method stub
		String hql = new StringBuffer()
				.append(" from Medicine where category = ? ")
				.toString();
			List<Medicine> list = createQueryByHql(hql).setParameter(0, category).list();
			return list;
	}

	@Override
	public List<Medicine> findPriceByName(String mname) {
		// TODO Auto-generated method stub
		String hql = new StringBuffer()
				.append(" select price from Medicine where name = ? ")
				.toString();
		List<Medicine> price = createQueryByHql(hql).setParameter(0, mname).list();
		return price;
	}
	
}
