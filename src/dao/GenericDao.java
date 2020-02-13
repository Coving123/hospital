package dao;

import java.io.Serializable;

import org.hibernate.Query;

public interface GenericDao<E extends Serializable,PK extends Serializable>  {
	public void insert(E e);
	
	public E selectById(PK id);
	
	public void update(E e);
	
	public void remove(E e);
	
	public Query createQueryByHql(String hql);
}
