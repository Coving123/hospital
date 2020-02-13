package dao;

import java.util.List;

import entity.Doctor;

public interface DoctorDao extends GenericDao<Doctor, Integer> {

	public Doctor selectByUid(Integer uid);
	
	public List<Doctor> selectByPage(Integer pageNo,Integer pageSize);
	
	public Integer countDoctor();

	public Doctor selectByName(String name);

	public List<Doctor> selectBySubject(String subject);
	
}
