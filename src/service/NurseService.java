package service;

import java.util.List;

import tools.Pager;
import entity.Nurse;
import exception.ServiceException;

public interface NurseService {
	public void registNurse(Nurse nurse) throws ServiceException;
	
	public void modifyNurse(Nurse nurse) throws ServiceException;
	
	public Nurse findNurseByNuname(String nuname)throws ServiceException;
	
	public Nurse findNurseById(Integer nid) throws ServiceException;
	
	public Nurse findNurseByUid(Integer uid) throws ServiceException;
	
	public Pager<Nurse> findNurseByPage(Integer pageNo,Integer pageSize) throws ServiceException;
	
	public void removeNurse(Integer nid) throws ServiceException;

	public List<Nurse> findNurseBySubject(String subject) throws ServiceException;
}
