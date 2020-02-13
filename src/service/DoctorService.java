package service;

import java.util.List;

import tools.Pager;
import entity.Doctor;
import exception.ServiceException;

public interface DoctorService {
	public void registDoctor(Doctor doctor) throws ServiceException;
	
	public void modifyDoctor(Doctor doctor) throws ServiceException;
	
	public Doctor findDoctorByDname(String dname)throws ServiceException;
	
	public Doctor findDoctorById(Integer did) throws ServiceException;
	
	public Doctor findDoctorByUid(Integer uid) throws ServiceException;
	
	public Pager<Doctor> findDoctorByPage(Integer pageNo,Integer pageSize) throws ServiceException;
	
	public void removeDoctor(Integer did) throws ServiceException;

	public List<Doctor> findDoctorBySubject(String subject) throws ServiceException;
}
