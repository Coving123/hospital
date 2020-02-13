package service;

import tools.Pager;

import java.util.List;

import entity.RequireMent;
import exception.ServiceException;

public interface RequireMentService {

	public Pager<RequireMent> findRequireMentByNurseAndPage(Integer nid, Integer page, Integer limit) throws ServiceException;
	
	public Pager<RequireMent> findRequireMentByPatientAndPage(Integer pid, Integer page, Integer limit) throws ServiceException;

	public RequireMent findRequireMentById(Integer oid) throws ServiceException;

	public void modifyRequireMent(RequireMent requireMent) throws ServiceException;

	public void addRequireMent(RequireMent requireMent) throws ServiceException;

	public void removeRequireMent(Integer oid) throws ServiceException;
	
}

