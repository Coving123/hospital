package service;

import entity.Patient;
import exception.ServiceException;

public interface PatientService {
	public void addPatient(Patient patient) throws ServiceException;
	
	public void modifyPatient(Patient patient) throws ServiceException;

	public Patient findPatientById(Integer pid) throws ServiceException;
	
	public Patient findPatientByUid(Integer uid) throws ServiceException;
	
	public Patient findPatientByPname(String pname) throws ServiceException;
}
