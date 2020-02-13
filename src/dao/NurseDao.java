package dao;

import java.util.List;

import entity.Nurse;

public interface NurseDao extends GenericDao<Nurse,Integer>{
	
    public Nurse selectByUid(Integer uid);
	
	public List<Nurse> selectByPage(Integer pageNo,Integer pageSize);
	
	public Integer countNurse();

	public Nurse selectByName(String name);

	public List<Nurse> selectBySubject(String subject);

}
