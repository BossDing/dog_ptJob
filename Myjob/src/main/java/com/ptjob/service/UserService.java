package com.ptjob.service;

import java.util.List;

import com.ptjob.entity.Users;

public interface UserService {
	/**
	 * ����ѧ�����û�����login���в����û�����Ϊѧ�����û�
	 * @param userName �û���
	 * @return
	 */
	public Users getUsersByNum (String userName);
	/**
	 * �û�ע��
	 * @param u
	 * @return
	 */
	public boolean insertUsers(Users u);
	
	
	public List<Users> getAllUsers();
	
	
	public boolean updatePsw(Users u);
}
