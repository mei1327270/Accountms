package com.account.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.account.dao.AccountDao;
import com.account.demo.Account;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AccountAction {
	private AccountDao accountDao;
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	public void addAccountInfo(){
		HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
		String accName = httpServletRequest.getParameter("accName");
		String accNumStr = httpServletRequest.getParameter("accNum");
		String accState = httpServletRequest.getParameter("accState");
		String accType = httpServletRequest.getParameter("accType");
		String remark = httpServletRequest.getParameter("remark");
		Integer accNum = Integer.valueOf(accNumStr);
		Account account = accountDao.getAccountInfoByAccNum(accNum);
		if(account == null){
			Account po = new Account();
			po.setAccName(accName);
			po.setAccNum(accNum);
			po.setAccState(accState);
			po.setAccType(accType);
			Date date = new Date();
			po.setLastLoginTime(date);
			po.setRegisterTime(date);
			po.setRemark(remark);
			accountDao.createPo(po);
			JSONObject o = new JSONObject();
			o.put("message", "添加成功");
			reactRequestProcess(o);
		}else{
			JSONObject o = new JSONObject();
			o.put("message", "用户已经存在！");
			reactRequestProcess(o);
		}
	}
	public void updateAccountInfo(){
		HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
		String accNumStr = httpServletRequest.getParameter("accNum");
		Integer accNum = Integer.valueOf(accNumStr);
		Account account = accountDao.getAccountInfoByAccNum(accNum);
		String accName = httpServletRequest.getParameter("accName");
		String accState = httpServletRequest.getParameter("accState");
		String accType = httpServletRequest.getParameter("accType");
		String remark = httpServletRequest.getParameter("remark");
		account.setAccName(accName);
		account.setAccState(accState);
		account.setAccType(accType);
		account.setRemark(remark);
		accountDao.updatePo(account);
	}
	public void deleteAccountInfo(){
		HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
		String accNumStr = httpServletRequest.getParameter("accNum");
		Integer accNum = Integer.valueOf(accNumStr);
		Account account = accountDao.getAccountInfoByAccNum(accNum);
		accountDao.deletePo(account);
		JSONObject o = new JSONObject();
		o.put("message", "删除成功");
		reactRequestProcess(o);		
	}
	public void showAccountInfo(){
		HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
		String accNumStr = httpServletRequest.getParameter("accNum");
		Integer accNum = Integer.valueOf(accNumStr);
		Account acc = accountDao.getAccountInfoByAccNum(accNum);
		JSONObject o = new JSONObject();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				JSONObject object = new JSONObject();
				object.put("accNum",acc.getAccNum());
				object.put("accName", acc.getAccName());
				object.put("accState", acc.getAccState());
				object.put("accType", acc.getAccType());
				if(acc.getRegisterTime() != null)
					object.put("registerTime", df.format(acc.getRegisterTime()));
				else
					object.put("registerTime","");
				if(acc.getLastLoginTime() != null)
				object.put("lastLoginTime", df.format(acc.getLastLoginTime()));
				else
					object.put("lastLoginTime", "");
				object.put("remark", acc.getRemark());		
			o.put("object", object);
			reactRequestProcess(o);
		}	
	public void showAllAccountInfo(){
		List list = accountDao.finAllAccountList();
		JSONObject o = new JSONObject();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(list != null && list.size() > 0){
			JSONArray array = new JSONArray();
			for(Account acc : (List<Account>) list){
				JSONObject object = new JSONObject();
				object.put("accNum",acc.getAccNum());
				object.put("accName", acc.getAccName());
				object.put("accState", acc.getAccState());
				object.put("accType", acc.getAccType());
				if(acc.getRegisterTime() != null)
					object.put("registerTime", df.format(acc.getRegisterTime()));
				else
					object.put("registerTime","");
				if(acc.getLastLoginTime() != null)
				object.put("lastLoginTime", df.format(acc.getLastLoginTime()));
				else
					object.put("lastLoginTime", "");
				object.put("remark", acc.getRemark());
				array.add(object);
			}
			o.put("array", array);
			reactRequestProcess(o);
		}else{
			o.put("message", "not find!");
			reactRequestProcess(o);
		}
	}
	/**
	 * 前端用react写的
	 * @param o
	 */
	public void reactRequestProcess(JSONObject o){
		try {
			HttpServletResponse httpServletResponse = ServletActionContext.getResponse();
			httpServletResponse.setContentType("application/json; charset=UTF-8");		
			PrintWriter out = httpServletResponse.getWriter();
			out.print(o.toString());
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
}
