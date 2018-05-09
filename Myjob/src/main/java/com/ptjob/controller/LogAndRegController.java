package com.ptjob.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.etc.exam.miaodiapi.IndustrySMS;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.ptjob.entity.Users;
import com.ptjob.service.UserService;
import com.ptjob.util.AppMD5Util;

@Controller
public class LogAndRegController {
	@Resource
	private UserService us;
	private int regcount;

	// ���ԣ���ȡ���е��û���Ϣ
	@RequestMapping("allusers")
	@ResponseBody
	public List<Users> getAllUsers() {
		return us.getAllUsers();
	}

	/**
	 * �ж��ֻ����Ƿ����
	 * 
	 * @param userName
	 *            �û�������ֻ��ţ��û�����
	 * @return
	 */
	@RequestMapping(value = "judgePhoneNum", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String judgeStuIsExis(String userName) {
		regcount = 0;
		String regex = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
		Boolean flag = Pattern.matches(regex, userName);
		Users u = us.getUsersByNum(userName);
		if (flag == true) {
			if (u == null) {
				regcount++;
				return "1���ֻ��ſ���ʹ��"; // ǰ�������Ϊ״̬��ʶ����1��ʾ�ֻ��ſ���ע��
			} else {
				return "2���ֻ����ѱ�ע�ᣬ��ֱ�ӵ�¼";// 2��ʾ���ֻ����Ѿ���ע��
			}
		} else {
			return "0��������ȷ���ֻ���";
		}
	}

	/**
	 * ������֤��
	 */
	@RequestMapping(value = "sendYZM", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String sendYZM(String phoneNum) {

		return IndustrySMS.execute(phoneNum);

	}

	/**
	 * �ж���֤���Ƿ���ȷ
	 * 
	 * @param yanzhengma
	 * @return
	 */
	@RequestMapping(value = "judgeYZM", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String judgeYZM(String yanzhengma) {
		int yznum;
		if (!yanzhengma.equals("")) {
			yznum = Integer.parseInt(yanzhengma);
			if (yznum == IndustrySMS.yanZhengNum) {
				return "1��֤����ȷ";
			} else {
				return "0��֤�����";
			}
		} else {
			return "0��֤�����";
		}

	}

	// ע�ᣬ���û���Ϣд�뵽login����
	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public boolean insertUsers(@RequestBody Users u,HttpSession session) {
		// Users u =new Users(null, userName, passWord, userType);
		// System.out.println(u);
		try {
			// ����MD5�����㷨�Ѽ��ܺ���û�����������ݿ���
			session.setAttribute("reguser", u.getUserName());
			String newpassword = AppMD5Util.EncoderByMd5(u.getPassWord());
			u.setPassWord(newpassword);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return us.insertUsers(u);
	}

	/***
	 * �����û�������û�����������е�¼���������û����ͣ���ת��ͬ����ҳ
	 * 
	 * @param userName
	 * @param passWord
	 * @return ����Ϊnull���ʾ�û��������벻ƥ��
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping(value = "login", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String judgeLogin(String userName, String passWord, HttpSession session, SessionStatus sta)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// System.out.println(userName + "," + passWord);
		Users user = us.getUsersByNum(userName);
		if (user != null) {
			if (AppMD5Util.checkpassword(passWord, user.getPassWord())) {
				if (!sta.isComplete()) // ���session����
					sta.setComplete(); // �þɵ�session����
				session.setAttribute("userName", userName);
				if (user.getUserType().equals("ѧ��")) {
					session.setAttribute("userType", "student");
					return "ѧ����¼";
				} else if(user.getUserType().equals("��ҵ")){
					session.setAttribute("userType", "company");
					return "��ҵ��¼";
				}else {
					session.setAttribute("userType", "admin");
					return "����Ա��¼";
				}

			} else {
				return null;
			}
		} else {
			// System.out.println("�û��������벻ƥ��");
			return null;
		}
	}

	/***
	 * ע����¼
	 * 
	 * @param session
	 * @param sta
	 * @return
	 */
	@RequestMapping(value = "logout")
	public String logout(HttpSession session, SessionStatus sta) {
		session.invalidate();
		return "redirect:/index.jsp";
	}

	
	/***
	 * �޸��û�����
	 * @param session
	 * @param oldpassword
	 * @param newpassword1
	 * @param newpassword2
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "updatepsw", method = RequestMethod.POST,produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String updatePsw(HttpSession session, String oldpassword, String newpassword1, String newpassword2)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String userName = session.getAttribute("userName") + "";
		Users user = us.getUsersByNum(userName);
		if (AppMD5Util.checkpassword(oldpassword, user.getPassWord())) {
			if (newpassword1.equals(newpassword2)) {
				user.setPassWord(AppMD5Util.EncoderByMd5(newpassword1));
				if(us.updatePsw(user)) {
					return "�޸ĳɹ�";
				}
				else {
					return "ϵͳ����";
				}
			} else {
				return "������������벻һ��";
			}
		} else {
			return "ԭ���벻��ȷ������������";
		}
	}

}
