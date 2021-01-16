package com.example.ldapconsole;

public abstract class LdapDemo {
	public static String LDAP_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
	public static String LDAP_HOST = "localhost";
	public static int LDAP_PORT = 10389;
	public static String LDAP_AUTH_TYPE = "simple";
	public static String SYS_BASE_DN = "ou=system";
	public static String SYS_ADMIN_PRINCIPAL = "admin";
	public static String SYS_ADMIN_PASSWORD = "secret";
	public static String SYS_DOMAIN_DN = "";
	public static String USERS_DOMAIN_DN = "dc=test,dc=com";
	public static String USERS_BASE_DN = "ou=people";
	
	public LdapDemo() {
		
	}
	
	protected String formatDN(String domainDN, String baseDN, String username) {
    	String dn = String.format("uid=%s%s%s", 
				  				  username,
				  				  baseDN.isEmpty() ? "" : "," + baseDN,
				  				  domainDN.isEmpty() ? "" : "," + domainDN);				  
    	return dn;
    }
}
