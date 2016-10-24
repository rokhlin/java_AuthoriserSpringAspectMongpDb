package tel_ran.mongo.dao;

import org.springframework.beans.factory.annotation.Autowired;

import tel_ran.mongo.entity.Account;
import tel_ran.mongo.repository.AccountsRepo;

public class AccauntsDao {
	
	@Autowired
	AccountsRepo accountsRepo;
	
	/**
	 * Adding the new user account in MongoDb
	 * @param acc new account that contain UserName, Password and permission role
	 * @return false if the UserName already contains in database
	 */
	public boolean add(Account acc) {
		if(acc == null || accountsRepo.exists(acc.getId()))
			return false;
		
		if(accountsRepo.save(acc) != null)
			return true;
		return false;
	}
	
	/**
	 * 
	 * @return all user accounts from MongoDb
	 */
	public Iterable<Account> getAccounts() {
		return accountsRepo.findAll();
	}
	
	/**
	 * 
	 * @param id unique userName in database  
	 * @param password
	 * @return The user role from userName-password pair if userName or password doesn't exist return null
	 */
	public String getRole(String id, String password) {
		accountsRepo.findOne(id);
		Account account = accountsRepo.findOne(id);
		if(account.getPassword().equals(password))
			return account.getRole();
		return null;
	}

	

}
