package com.ptjob.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ptjob.entity.Relation;
import com.ptjob.service.RelationService;

@Controller
public class RelationController {
	@Resource
	private RelationService rs;

	@RequestMapping(value = "sendResumes", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String sendResumes(Relation relation) {
		if (rs.selectByRecord(relation) == null) {
			relation.setBusinessflag("��");
			if (rs.sendResumes(relation))
				return "Ͷ�ݳɹ�";
			else
				return "ϵͳ����";

		}
		return "�������ְλͶ�ݼ����������ظ�Ͷ��";
	}
}
