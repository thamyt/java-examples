package com.example.ldapconsole;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Hello world!
 *
 */
public class App 
{
	public static String LDAP_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
	public static String LDAP_URL = "ldap://localhost:10389";
	public static String LDAP_AUTH_TYPE = "simple";
	public static String SYS_BASE_DN = "ou=system";
	public static String SYS_ADMIN_PRINCIPAL = "admin";
	public static String SYS_ADMIN_PASSWORD = "secret";
	public static String USERS_BASE_DN = "ou=users,ou=system";
	
    public static void main( String[] args )
    {
        System.out.println("LDAP Console Demo");
        System.out.println("=================");
        
        App app = new App();
        
        // Test Browser Ldap users
        app.browseLdapUsers();
        
        // Test authenticate ldap users
        app.authenticateLdapUser("user02", "hello@World");
        
        // Test change ldap users password
        //app.changeLdapUserPassword("user02", "qwerty", "hello@World");
        
        // Test authenticate ldap users
        //app.authenticateLdapUser("user02", "hello@World");
        
        System.out.println("exit");
    }
    
    private SearchControls getSimpleSearchControls() {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchControls.setTimeLimit(30000);
        //String[] attrIDs = {"objectGUID"};
        //searchControls.setReturningAttributes(attrIDs);
        return searchControls;
    }
    
    private LdapContext ldapConnect(String userDN, String password) {
    	LdapContext ctx = null;
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, LDAP_FACTORY);
		env.put(Context.PROVIDER_URL, LDAP_URL);
		env.put(Context.SECURITY_AUTHENTICATION, LDAP_AUTH_TYPE);
		env.put(Context.SECURITY_PRINCIPAL, userDN);
	    env.put(Context.SECURITY_CREDENTIALS, password);
	    
		try {
			System.out.println("Connect to ldap ...");
			ctx = new InitialLdapContext(env, null);
			System.out.println("Connected");
		} 
		catch (Exception e) {
		    e.printStackTrace();
		}
	
		return ctx;
	}
    
    public void browseLdapUsers() {
    	String baseDN = "OU=users,OU=system";
    	String filter = "(objectClass=organizationalPerson)";
    	
    	System.out.println("Browse LDAP users Demo");
    	
    	// Prepare the special user with special privilege
    	String userDN = String.format("uid=%s,%s", SYS_ADMIN_PRINCIPAL, SYS_BASE_DN);
    	// Connect to the ldap server
    	LdapContext ctx = ldapConnect(userDN, SYS_ADMIN_PASSWORD);
    	if( ctx == null ) {
    		System.out.println("Failed to connect to ldap server!");
    		return;
    	}
    	
    	try {
    		System.out.println("Browse ldap users ...");
		    ctx.setRequestControls(null);
		    NamingEnumeration<?> namingEnum = ctx.search(baseDN, filter, getSimpleSearchControls());
		    while (namingEnum.hasMore ()) {
		        SearchResult result = (SearchResult) namingEnum.next ();    
		        Attributes attrs = result.getAttributes ();
		        System.out.println(attrs.get("cn"));
		        System.out.println(attrs.get("mail"));
		        System.out.println(attrs.get("uid"));
		        System.out.println(attrs.get("userPassword"));
		    } 
		    namingEnum.close();
		    System.out.println("Done");
		} 
    	catch (Exception e) {
		    e.printStackTrace();
		}
    	
    	try {
			ctx.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public Boolean authenticateLdapUser(String username, String password) {
    	Boolean result = false;
    	
    	// Prepare the special user with special privilege
    	String userDN = String.format("cn=%s,%s", username, USERS_BASE_DN);
    	
    	// Connect to the ldap server
    	LdapContext ctx = ldapConnect(userDN, password);
    	if( ctx != null ) {
    		System.out.println("User authenticated successfully.");
    		result = true;
    		
    		try {
    			ctx.close();
    		} catch (NamingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else {
    		System.out.println("User authenticated failed!");
    	}
    	
    	return result;
    }
    
    public Boolean changeLdapUserPassword(String username, String oldPassword, String newPassword) {
    	Boolean result = false;
    	
    	// Prepare the special user with special privilege
    	String userDN = String.format("cn=%s,%s", username, USERS_BASE_DN);
    	
    	// Connect to the ldap server
    	LdapContext ctx = ldapConnect(userDN, oldPassword);
    	if( ctx != null ) {
    		try {
    			System.out.println("User authenticated successfully.");
    		
    			String newEncryptPwd = DigestUtils.sha256(newPassword).toString();
    			System.out.println("New Encrpyted Password = " + newEncryptPwd);
    		
    			ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, 
    												     new BasicAttribute("userPassword", "{SHA256}" + newEncryptPwd));
    			ctx.modifyAttributes(userDN, new ModificationItem[]{item});   		
    			result = true;
    			ctx.close();
    		} catch (NamingException e) {
    			System.out.println("Change Password failed!");
    			e.printStackTrace();
    		}    		
    	}
    	else {
    		System.out.println("User authenticated failed!");
    	}
    	
    	return result;
    }
}
