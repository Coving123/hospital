package dao;

import java.util.List;

import entity.RequireMent;
import exception.ServiceException;

public interface RequireMentDao extends GenericDao<RequireMent, Integer>{
	
	public List<RequireMent> getRequireMentByNurseAndPage(Integer nid, Integer pageNo, Integer pageSize) throws Exception;
	
	public List<RequireMent> getRequireMentByPatientAndPage(Integer pid, Integer pageNo, Integer pageSize) throws Exception;
	
	public int getCountOfRequireMentByNurse(Integer nid) throws Exception;
	
	public int getCountOfRequireMentByPatient(Integer pid) throws Exception;
	
	public List<RequireMent> getAllRequireMent();

}
