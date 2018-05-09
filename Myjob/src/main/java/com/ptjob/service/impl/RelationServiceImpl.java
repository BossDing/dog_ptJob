package com.ptjob.service.impl;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ptjob.dao.RelationMapper;
import com.ptjob.entity.Relation;
import com.ptjob.service.RelationService;

@Service
public class RelationServiceImpl implements RelationService {
	
	@Resource
	private RelationMapper rm;
	
	public boolean sendResumes(Relation relation) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
		String time = sdf.format(System.currentTimeMillis());
		relation.setStudentflag("��");
		relation.setStutime(time);
		return rm.insert(relation)>0;

	}

	public boolean cancelResumes(Relation relation) {
		// TODO Auto-generated method stub
		relation.setStudentflag("��");
		return rm.updateByJobIdAndUserName(relation)>0;
	}

	public boolean employStudent(Relation relation) {
		// TODO Auto-generated method stub
		relation.setBusinessflag("��");
		return rm.updateByJobIdAndUserName(relation)>0;
	}

	public Relation selectByRecord(Relation relation) {
		// TODO Auto-generated method stub
		return rm.selectByRecord(relation);
	}

}
