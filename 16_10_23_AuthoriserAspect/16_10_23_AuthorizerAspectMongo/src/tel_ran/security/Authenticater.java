package tel_ran.security;

import java.util.HashMap;
import java.util.Map;

import tel_ran.mongo.dao.AccauntsDao;


public class Authenticater {
//	private Map<String, String> rulesPasswords;  //key - role, value-password
	private Map<Object, String> authObjects = new HashMap<Object, String>();  //key - reference to object, value - role
	private AccauntsDao db;
	
	
	
	
	public Authenticater() {
		super();
	
	}
	public Authenticater(AccauntsDao db) {
		super();
		this.db = db;
	}

	

//
//	public Map<String, String> getRulesPasswords() {
//		return rulesPasswords;
//	}
//
//	public void setRulesPasswords(Map<String, String> rulesPasswords) {
//		this.rulesPasswords = rulesPasswords;
//	}

	public Map<Object, String> getAuthObjects() {
		return authObjects;
	}

	public void setAuthObjects(Map<Object, String> authObjects) {
		this.authObjects = authObjects;
	}

	/**
	 * 
	 * @param role
	 * @param password
	 * @param object
	 * @return true if password matches role, 
	 */
	public boolean authenticate(String userName, String password, Object object) {
		String role = db.getRole(userName, password);
		if(role == null) 
			return false;
		
		authObjects.put(object, role);
		return true;
	} 
	
	/**
	 * 
	 * @return returns role for a given object or null
	 */
	public String getRole(Object key ) {
		if(authObjects.containsKey(key))
			return authObjects.get(key);
		return null;
	}


}
