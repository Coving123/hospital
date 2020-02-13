package dao;

import java.util.List;

import entity.EngageMent;

public interface EngageMentDao extends GenericDao<EngageMent, Integer>{
	
	public List<EngageMent> getEngageMentByPatientAndPage(Integer pid, Integer pageNo,
			Integer pageSize) throws Exception;
	
	public List<EngageMent> getEngageMentByNurseAndPage(Integer nid, Integer pageNo,
			Integer pageSize) throws Exception;

	public Integer getCountOfEngageMentByPatient(Integer pid) throws Exception;

	public Integer getCountOfEngageMentByNurse(Integer nid) throws Exception;
	
	public List<EngageMent> getAllEngageMent();
	
	public List<EngageMent> getEngageMentByNid(Integer nid);
}

