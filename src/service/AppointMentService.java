package service;

import tools.Pager;

import java.util.List;

import entity.AppointMent;
import exception.ServiceException;

public interface AppointMentService {
	public void addAppointMent(AppointMent appointMent) throws ServiceException;
	
	public void modifyAppointMent(AppointMent appointMent)throws ServiceException;
	
	public AppointMent findAppointMentById(Integer aid)throws ServiceException;
	
	public Pager<AppointMent> findAppointMentByPatientAndPage(Integer pid, Integer page,Integer limit)throws ServiceException;

	public Pager<AppointMent> findAppointMentByDoctorAndPage(Integer did, Integer pageNo,Integer pageSize)throws ServiceException;
	
	public List<AppointMent> findAppointMentByPatient(Integer pid, Integer pageNo, Integer pageSize) throws Exception;
	
	public void removeAppointMent(Integer aid) throws ServiceException;
}
