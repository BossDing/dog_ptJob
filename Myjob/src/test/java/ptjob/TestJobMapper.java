package ptjob;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ptjob.dao.JobMapper;
import com.ptjob.entity.Job;
import com.ptjob.entity.JobPage;

public class TestJobMapper {

	JobMapper jm = null;

	@Before
	public void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml", "mybatis-config.xml");

		jm = context.getBean(JobMapper.class);
	}

	@Test
	public void insert() {
		Job job = new Job(null, "ʵ����������������", "15900000000", "������", "ˢ��", "������˼����", "������˼�������԰����", "����",
				"15900000000", "20", "��Ů����", "15Ԫ/��", "����", "", "2018��3��9��10:59:27","","");
		jm.insert(job);
	}
	
	@Test
	public void selectByPrimary() {
		System.out.println(jm.selectByPrimaryKey(1));
	}
	
	@Test
	public void getAllJobs() {
		List<Job> list = jm.getAllJobs();
		for (Job job : list) {
			System.out.println(job);
		}
	}
	
	@Test
	public void getJobByUserName() {
		List<Job> list = jm.getJobByUserName("15900000000");
		for (Job job : list) {
			System.out.println(job);
		}
	}
	@Test
	public void getJobByNoChecked() {
		List<Job> list = jm.getJobByNoChecked("��");
		for (Job job : list) {
			System.out.println(job);
		}
	}
	
	@Test
	public void getJobsByPages() {
		JobPage jp = new JobPage();
		
		
		jp.setJobTitle("����");
		jp.setStart(0);
		jp.setPagesize(3);
		List<Job> list = jm.getJobsByPages(jp);
		for (Job job2 : list) {
			System.out.println(job2);
		}
		
//		System.out.println("��"+total+"������");
	
	}
	
/*	@Test
	public void getTotal() {
		Job job = new Job();
		job.setJobTitle("�������");
		int total = jm.getJobsTotal(job);
		System.out.println("��"+total+"������");
		
				
	}*/

	
	
}
