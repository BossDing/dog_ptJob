package com.ptjob.service;

import com.ptjob.entity.Relation;

public interface RelationService {
	
	/***
	 * Ͷ�ݼ���
	 * 
	 * @param relation
	 */
	public boolean sendResumes(Relation relation);
	
	/***
	 * ȡ��Ͷ��
	 * 
	 */
	public boolean cancelResumes(Relation relation);
	
	/***
	 * ¼��
	 */
	public boolean employStudent(Relation relation);
	
	
	public Relation selectByRecord(Relation relation);
	
}
 