package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import service.DoctorService;
import tools.Pager;
import dao.AppointMentDao;
import dao.DoctorDao;
import dao.RecordDao;
import dao.UserDao;
import entity.AppointMent;
import entity.Doctor;
import entity.Record;
import exception.ServiceException;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorDao doctorDao;
	@Autowired
	private AppointMentDao appointMentDao;
	@Autowired
	private RecordDao recordDao;
	@Autowired
	private UserDao userDao; 
	
	public void registDoctor(Doctor doctor) throws ServiceException {
		try {
			userDao.insert(doctor.getUser());
			doctorDao.insert(doctor);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void modifyDoctor(Doctor doctor) throws ServiceException {
		userDao.update(doctor.getUser());
		doctorDao.update(doctor);
	}

	public DoctorDao getDoctorDao() {
		return doctorDao;
	}

	public void setDoctorDao(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public Doctor findDoctorById(Integer did) throws ServiceException {
		Doctor doctor = null;
		doctor = doctorDao.selectById(did);
		return doctor;
	}

	@Override
	public Doctor findDoctorByUid(Integer uid) throws ServiceException {
		return doctorDao.selectByUid(uid);
	}

	@Override
	public Pager<Doctor> findDoctorByPage(Integer pageNo, Integer pageSize) throws ServiceException {
		Pager<Doctor> page = new Pager<Doctor>();
		page.setContent(doctorDao.selectByPage(pageNo, pageSize));
		page.setPageCountByTotalCountAndPageSize(doctorDao.countDoctor(),pageSize);
		page.setPageNo(pageNo);
		return page;
	}

	@Override
	public void removeDoctor(Integer did) throws ServiceException {
		Doctor doctor = findDoctorById(did);
		List<AppointMent> appointMents = appointMentDao.getAppointMentByDid(did);
		if(appointMents!=null&&appointMents.size()!=0){
			for (AppointMent appointMent : appointMents) {
				appointMentDao.remove(appointMent);
			}
		}
		List<Record> records = recordDao.getRecordByDid(did);
		if(records!=null&&records.size()!=0){
			for (Record record : records) {
				recordDao.remove(record);
			}
		}
		if(doctor==null){
			throw new ServiceException("该id无效");
		}
		doctorDao.remove(doctor);
	}

	@Override
	public Doctor findDoctorByDname(String dname) throws ServiceException {
		Doctor doctor = doctorDao.selectByName(dname);
		return doctor;
	}

	@Override
	public List<Doctor> findDoctorBySubject(String subject)
			throws ServiceException {
		List<Doctor> doctors = doctorDao.selectBySubject(subject);
		return doctors;
	}

	public AppointMentDao getAppointMentDao() {
		return appointMentDao;
	}

	public void setAppointMentDao(AppointMentDao appointMentDao) {
		this.appointMentDao = appointMentDao;
	}

	public RecordDao getRecordDao() {
		return recordDao;
	}

	public void setRecordDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}

}
