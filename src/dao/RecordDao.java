package dao;

import java.util.List;

import entity.Record;

public interface RecordDao extends GenericDao<Record, Integer>{
	
	public List<Record> getRecordByPatientAndPage(Integer pid, Integer pageNo,
			Integer pageSize) throws Exception;

	public List<Record> getRecordByDoctorAndPage(Integer did, Integer pageNo,
			Integer pageSize) throws Exception;
	
	public List<Record> getRecordByNurseAndPage(Integer pageNo, 
			Integer pageSize) throws Exception;
	
	public Integer getCountOfRecordByPatient(Integer pid) throws Exception;
	
	public Integer getCountOfRecordByDoctor(Integer did) throws Exception;
	
	public Integer getCountOfRecordByNurse() throws Exception;

	public List<Record> getRecordByDid(Integer did);
}
