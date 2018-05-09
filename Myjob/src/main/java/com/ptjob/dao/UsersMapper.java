package com.ptjob.dao;

import java.util.List;

import com.ptjob.entity.Users;

public interface UsersMapper {
	//��ѯ���е��û�
	List<Users> getAllUsers();
	//�����û�����ѯ�û�
	Users selectStuByName(String userName);
	
    int deleteByPrimaryKey(Integer logId);

    int insertUsers(Users record);

    int insertSelective(Users record);
    //�����ֻ��Ų�ѯ�û��Ƿ�
    Users getUsersByNum(String userName);
    
    Users selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
    //�޸��û�����
    int updatePsw(Users user);
}