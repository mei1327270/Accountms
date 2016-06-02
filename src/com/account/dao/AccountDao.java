package com.account.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.account.demo.Account;


public interface AccountDao extends BaseDao<Account>{

	void createPo(Account po);
	void updatePo(Account po);
	void deletePo(Account po);
	public Account getAccountInfoByAccNum(Integer accNum);
	public List finAllAccountList();
}
