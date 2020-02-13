package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import service.PatientService;
import dao.PatientDao;
import dao.UserDao;
import entity.Patient;
import exception.ServiceException;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientDao patientDao;
	@Autowired
	private UserDao userDao;
	
	public void addPatient(Patient patient) throws ServiceException {
		try {
			userDao.insert(patient.getUser());
			patientDao.insert(patient);
		} catch (Exception e) {
			throw new ServiceException("添加病人信息出错", e);
		}
	}

	public void modifyPatient(Patient patient) throws ServiceException {
		try {
			patientDao.update(patient);
		} catch (Exception e) {
			throw new ServiceException("修改病人信息出错", e);
		}
	}

	public Patient findPatientById(Integer pid) throws ServiceException {
		Patient patient = null;
		try {
			patient = patientDao.selectById(pid);
		} catch (Exception e) {
			throw new ServiceException("查询病人信息出错", e);
		}
		return patient;
	}

	public PatientDao getPatientDao() {
		return patientDao;
	}

	public void setPatientDao(PatientDao patientDao) {
		this.patientDao = patientDao;
	}

	@Override
	public Patient findPatientByUid(Integer uid) throws ServiceException {
		return patientDao.selectByUid(uid);
	}

	@Override
	public Patient findPatientByPname(String pname) throws ServiceException {
		Patient patient = patientDao.selectByName(pname);
		return patient;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
