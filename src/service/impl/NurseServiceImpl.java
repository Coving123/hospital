package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import service.NurseService;
import tools.Pager;
import dao.EngageMentDao;
import dao.NurseDao;
import dao.RecordDao;
import dao.UserDao;
import entity.EngageMent;
import entity.Nurse;
import entity.Record;
import exception.ServiceException;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class NurseServiceImpl implements NurseService {

	@Autowired
	private NurseDao nurseDao;
	@Autowired
	private EngageMentDao EngageMentDao;
	@Autowired
	private RecordDao recordDao;
	@Autowired
	private UserDao userDao; 
	
	public void registNurse(Nurse nurse) throws ServiceException {
		try {
			userDao.insert(nurse.getUser());
			nurseDao.insert(nurse);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void modifyNurse(Nurse nurse) throws ServiceException {
		userDao.update(nurse.getUser());
		nurseDao.update(nurse);
	}

	public NurseDao getNurseDao() {
		return nurseDao;
	}

	public void setNurseDao(NurseDao nurseDao) {
		this.nurseDao = nurseDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public Nurse findNurseById(Integer nid) throws ServiceException {
		Nurse nurse = null;
		nurse = nurseDao.selectById(nid);
		return nurse;
	}

	@Override
	public Nurse findNurseByUid(Integer uid) throws ServiceException {
		return nurseDao.selectByUid(uid);
	}

	@Override
	public Pager<Nurse> findNurseByPage(Integer pageNo, Integer pageSize) throws ServiceException {
		Pager<Nurse> page = new Pager<Nurse>();
		page.setContent(nurseDao.selectByPage(pageNo, pageSize));
		page.setPageCountByTotalCountAndPageSize(nurseDao.countNurse(),pageSize);
		page.setPageNo(pageNo);
		return page;
	}

	@Override
	public void removeNurse(Integer nid) throws ServiceException {
		Nurse nurse = findNurseById(nid);
		List<EngageMent> engageMents = EngageMentDao.getEngageMentByNid(nid);
		if(engageMents!=null&&engageMents.size()!=0){
			for (EngageMent engageMent : engageMents) {
				EngageMentDao.remove(engageMent);
			}
		}
		List<Record> records = recordDao.getRecordByDid(nid);
		if(records!=null&&records.size()!=0){
			for (Record record : records) {
				recordDao.remove(record);
			}
		}
		if(nurse==null){
			throw new ServiceException("该id无效");
		}
		nurseDao.remove(nurse);
	}

	@Override
	public Nurse findNurseByNuname(String nuname) throws ServiceException {
		Nurse nurse = nurseDao.selectByName(nuname);
		return nurse;
	}

	@Override
	public List<Nurse> findNurseBySubject(String subject)
			throws ServiceException {
		List<Nurse> nurses = nurseDao.selectBySubject(subject);
		return nurses;
	}

	public EngageMentDao getEngageMentDao() {
		return EngageMentDao;
	}

	public void setEngageMentDao(EngageMentDao EngageMentDao) {
		this.EngageMentDao = EngageMentDao;
	}

	public RecordDao getRecordDao() {
		return recordDao;
	}

	public void setRecordDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}

}