package service;

import entity.User;
import exception.DuplicateUsernameException;
import exception.ServiceException;

public interface UserService {
	public void regist(User user) throws ServiceException;
	
	public User findById(Integer id) throws ServiceException;
	
	public User login(User user) throws ServiceException;
	
	public void modifyUser(User user)throws ServiceException;
	
	public void removeUser(Integer uid);
}
