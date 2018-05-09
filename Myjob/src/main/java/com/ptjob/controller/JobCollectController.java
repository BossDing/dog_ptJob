package com.ptjob.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ptjob.entity.Job;
import com.ptjob.entity.JobCollect;
import com.ptjob.entity.JobRelation;
import com.ptjob.entity.JobRelationPage;
import com.ptjob.service.JobCollectService;
import com.ptjob.service.JobRelationService;
import com.ptjob.util.PageData;

@Controller
public class JobCollectController {
	@Resource
	private JobCollectService jrs;
	
	//���ո����������з�ҳ��ѯ
		@RequestMapping(value="getJobCollectByPage/page/{page}")
		@ResponseBody
		public PageData<JobCollect> getJobRelationByPage(@PathVariable(value = "page") int page,
				@RequestParam(value = "pageSize", defaultValue = "3") int pageSize,
				JobCollect jobcollect){
			pageSize = 5;
		//System.out.println(jp);
			//System.out.println("ѧ������"+jobcollect.getStudentName());
			PageData<JobCollect> pd = jrs.getJobCollectionByPages(page, pageSize, jobcollect);
			//System.out.println(pd.getData().get(0).getJobTitle());
//			List<JobCollect> list = pd.getData();
//			for (JobCollect jr : list) {
//				System.out.println("����000"+jr);
//			}
			return pd;
		}
		
		
		@RequestMapping(value="deleteCollection",method=RequestMethod.POST,produces="text/html; charset=UTF-8")
		@ResponseBody
		public String deleteCollection(JobCollect jobcollect) {
			if(jrs.deleteCollection(jobcollect))
				return "ȡ���ɹ�";
			return "ϵͳ����";
		}
		
		
}
