package tel_ran.mongo.repository;

import tel_ran.mongo.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountsRepo extends CrudRepository<Account, String> {
//	Iterable<Person> findByName(String name);
//	Iterable<Person> findByAddressCity(String city);
//	Iterable<Person> findByBirthYearBetweenOrderByBirthYear(int yearFrom, int yearTo);
	

}
