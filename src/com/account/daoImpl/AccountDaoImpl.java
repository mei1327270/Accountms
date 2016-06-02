package com.account.daoImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.account.dao.AccountDao;
import com.account.demo.Account;

public class AccountDaoImpl extends BaseDaoImpl<Account> implements AccountDao{
	public AccountDaoImpl() {
		super(Account.class);
		// TODO Auto-generated constructor stub
	}
	@Transactional
	public void createPo(Account po){
		create(po);
	}
	@Transactional
	public void updatePo(Account po) {
		// TODO Auto-generated method stub
		update(po);
	}

	@Transactional
	public void deletePo(Account po) {
		// TODO Auto-generated method stub
		delete(po);
	}
	@Override
	public Account getAccountInfoByAccNum(Integer accNum){
		String hql = " from Account where accountNum="+accNum;
		List list =  this.queryForList(hql, null);
		if(list != null && list.size() > 0){
			return (Account) list.get(0);
		}else{
			return null;
		}
	}
	@Override
	public List finAllAccountList(){
		String hql = " from Account where 1=1";
		List list =  this.queryForList(hql, null);
		return list;
	}
}
