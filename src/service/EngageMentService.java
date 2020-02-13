package service;

import tools.Pager;
import entity.EngageMent;
import exception.ServiceException;

public interface EngageMentService {
	public void addEngageMent(EngageMent engageMent) throws ServiceException;
	
	public void modifyEngageMent(EngageMent engageMent)throws ServiceException;
	
	public EngageMent findEngageMentById(Integer eid)throws ServiceException;
	
	public Pager<EngageMent> findEngageMentByPatientAndPage(Integer pid, Integer pageNo,Integer pageSize)throws ServiceException;

	public Pager<EngageMent> findEngageMentByNurseAndPage(Integer nid, Integer pageNo,Integer pageSize)throws ServiceException;
	
	public void removeEngageMent(Integer eid) throws ServiceException;
}

