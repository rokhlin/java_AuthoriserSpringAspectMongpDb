package tel_ran.mongo.dao;

import org.springframework.beans.factory.annotation.Autowired;

import tel_ran.mongo.entity.Account;
import tel_ran.mongo.repository.AccountsRepo;

public class AccauntsDao {
	@Autowired
	AccountsRepo accountsRepo;
	
	
	public boolean add(Account acc) {
		if(acc == null || accountsRepo.exists(acc.getId()))
			return false;
		
		if(accountsRepo.save(acc) != null)
			return true;
		return false;
	}
	
	public Iterable<Account> getAccounts() {
		return accountsRepo.findAll();
	}
	
	public String getRole(String id, String password) {
		accountsRepo.findOne(id);
		Account account = accountsRepo.findOne(id);
		if(account.getPassword().equals(password))
			return account.getRole();
		return null;
	}

	

}
