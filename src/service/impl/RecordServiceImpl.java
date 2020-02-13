package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import service.RecordService;
import tools.Constants;
import tools.Pager;
import dao.RecordDao;
import entity.Record;
import exception.ServiceException;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RecordServiceImpl implements RecordService {

	@Autowired
	private RecordDao recordDao;

	public Pager<Record> findRecordByDoctorAndPage(Integer did, Integer pageNo, Integer pageSize) throws Exception {
		Pager<Record> pager = new Pager<Record>();
		pager.setPageNo(pageNo);
		try {
			pager.setPageNo(pageNo);
			int totalCount = recordDao.getCountOfRecordByDoctor(did);
			pager.setPageCountByTotalCountAndPageSize(totalCount, pageSize);
			pager.setContent(recordDao.getRecordByDoctorAndPage(did, pageNo, Constants.PAGE_SIZE_10));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(this.getClass().getSimpleName() + "查询错误", e);
		}
		return pager;
	}

	public void addRecord(Record record) throws Exception {
		try {
			recordDao.insert(record);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(this.getClass().getSimpleName() + "添加错误", e);
		}
	}

	public void modifyRecord(Record record) throws Exception {
		try {
			recordDao.update(record);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(this.getClass().getSimpleName() + "修改错误", e);
		}
	}

	public Pager<Record> findRecordByPatientAndPage(Integer pid, Integer pageNo, Integer pageSize) throws Exception {
		Pager<Record> pager = new Pager<Record>();
		pager.setPageNo(pageNo);
		try {
			pager.setPageNo(pageNo);
			int totalCount = recordDao.getCountOfRecordByPatient(pid);
			pager.setPageCountByTotalCountAndPageSize(totalCount, pageSize);
			pager.setContent(recordDao.getRecordByPatientAndPage(pid, pageNo, Constants.PAGE_SIZE_10));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(this.getClass().getSimpleName() + "查询错误", e);
		}
		return pager;
	}
	
	
	
	public Pager<Record> findRecordByNurseAndPage(Integer pageNo, Integer pageSize) throws Exception {
		Pager<Record> pager = new Pager<Record>();
		pager.setPageNo(pageNo);
		try {
			pager.setPageNo(pageNo);
			int totalCount = recordDao.getCountOfRecordByNurse();
			pager.setPageCountByTotalCountAndPageSize(totalCount, pageSize);
			pager.setContent(recordDao.getRecordByNurseAndPage(pageNo, Constants.PAGE_SIZE_10));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(this.getClass().getSimpleName() + "查询错误", e);
		}
		return pager;
	}

	
	
	
	
	public RecordDao getRecordDao() {
		return recordDao;
	}

	public void setRecordDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}

	@Override
	public Record findRecordByRid(Integer rid) throws Exception {
		Record record = recordDao.selectById(rid);
		return record;
	}

	@Override
	public void removceRecord(Integer rid) throws Exception {
		Record record = findRecordByRid(rid);
		if(record!=null){
			recordDao.remove(record);
		}
	}

}
