package dao;

import entity.Patient;

public interface PatientDao extends GenericDao<Patient, Integer>{

	public Patient selectByUid(Integer uid);

	public Patient selectByName(String name);
	
}
