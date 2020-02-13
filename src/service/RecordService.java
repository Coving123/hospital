package service;

import entity.Record;
import tools.Pager;

public interface RecordService {
	
	public void addRecord(Record record) throws Exception;
	
	public void removceRecord(Integer rid) throws Exception;
	
	public void modifyRecord(Record record) throws Exception;

	public Record findRecordByRid(Integer rid) throws Exception;
	
	public Pager<Record> findRecordByDoctorAndPage(Integer did,Integer pageNo,Integer pageSize) throws Exception;
	
	public Pager<Record> findRecordByPatientAndPage(Integer pid,Integer pageNo,Integer pageSize) throws Exception;
	
	public Pager<Record> findRecordByNurseAndPage(Integer pageNo,Integer pageSize) throws Exception;
	
}
