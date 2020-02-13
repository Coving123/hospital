package dao;

import java.util.List;

import entity.AppointMent;

public interface AppointMentDao extends GenericDao<AppointMent, Integer>{
	public List<AppointMent> getAppointMentByPatientAndPage(Integer pid, Integer pageNo,
			Integer pageSize) throws Exception;
	
	public List<AppointMent> getAppointMentByDoctorAndPage(Integer did, Integer pageNo,
			Integer pageSize) throws Exception;

	public Integer getCountOfAppointMentByPatient(Integer pid) throws Exception;

	public Integer getCountOfAppointMentByDoctor(Integer did) throws Exception;
	
	public List<AppointMent> getAllAppointMent();
	
	public List<AppointMent> getAppointMentByDid(Integer did);
}
