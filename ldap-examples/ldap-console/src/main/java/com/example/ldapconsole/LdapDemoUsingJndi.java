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

import org.apache.directory.api.ldap.model.constants.LdapSecurityConstants;
import org.apache.directory.api.ldap.model.password.PasswordUtil;
import org.apache.directory.api.util.Strings;

public class LdapDemoUsingJndi extends LdapDemo {

	
	public LdapDemoUsingJndi() {
		
	}
    
    private SearchControls getSimpleSearchControls() {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchControls.setTimeLimit(30000);
        //String[] attrIDs = {"objectGUID"};
        //searchControls.setReturningAttributes(attrIDs);
        return searchControls;
    } 
    
    private LdapContext ldapConnect(String domainDN, String userDN, String password) {
    	LdapContext ctx = null;
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, LDAP_FACTORY);
		env.put(Context.PROVIDER_URL, 
				String.format("ldap://%s:%d/%s", LDAP_HOST, LDAP_PORT, domainDN));
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
    	String baseDN = "OU=people" + "," + USERS_DOMAIN_DN;
    	String filter = "(objectClass=organizationalPerson)";
    	
    	System.out.println("Browse LDAP users Demo");
    	
    	// Prepare the special user with special privilege
    	String sysDN = formatDN(SYS_DOMAIN_DN, SYS_BASE_DN, SYS_ADMIN_PRINCIPAL);
    	System.out.println("sysDN = " + sysDN);
    	// Connect to the ldap server
    	LdapContext ctx = ldapConnect(SYS_DOMAIN_DN, sysDN, SYS_ADMIN_PASSWORD);
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
		        System.out.println("uid  = " + attrs.get("uid"));
		        System.out.println("sn   = " + attrs.get("sn"));
		        System.out.println("cn   = " + attrs.get("cn"));
		        System.out.println("mail = " + attrs.get("mail"));
		        System.out.println("--------------------------------------------------");
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
    
    public Boolean authenticateLdapUser(String domainDN, String username, String password) {
    	Boolean result = false;
    	
    	System.out.println(String.format("Authenticated user (%s) ...", username));
    	
    	// Prepare the user DN
    	String userDN = formatDN(domainDN, 
    							 domainDN.compareToIgnoreCase(SYS_DOMAIN_DN)==0 ? SYS_BASE_DN : USERS_BASE_DN, 
    						     username);
    	System.out.println("userDN = " + userDN);
    	// Connect to the ldap server
    	LdapContext ctx = ldapConnect(domainDN, userDN, password);
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
    
    public Boolean changeLdapUserPasswordBySysAdmin(String domainDN, String username, String newPassword) {
    	Boolean result = false;
    	
    	System.out.println(String.format("Change Password by SysAdmin for User (%s) ...", username));
    	
    	// Prepare the special user with special privilege
    	String sysDN = formatDN(SYS_DOMAIN_DN, SYS_BASE_DN, SYS_ADMIN_PRINCIPAL);
    	System.out.println("sysDN = " + sysDN);
    	// Connect to the ldap server
    	LdapContext ctx = ldapConnect(SYS_DOMAIN_DN, sysDN, SYS_ADMIN_PASSWORD);    	
    	if( ctx != null ) {
    		try {
    			byte[] pwd = PasswordUtil.createStoragePassword(newPassword, 
					     										LdapSecurityConstants.HASH_METHOD_SHA256);
    			String newEncodedHashPwd = Strings.utf8ToString(pwd);
    			System.out.println("New Encoded Hashed Password = " + newEncodedHashPwd);
    		
    			ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, 
    												     	 new BasicAttribute("userPassword", newEncodedHashPwd));
    			
    			// Prepare the user DN
    			String userDN = formatDN(domainDN, 
    							  		 domainDN.compareToIgnoreCase(SYS_DOMAIN_DN)==0 ? SYS_BASE_DN : USERS_BASE_DN, 
    							  		 username);
    			System.out.println("UserDN = " + userDN);
    			ctx.modifyAttributes(userDN, new ModificationItem[]{item});   		
    			result = true;
    			ctx.close();
    			System.out.println(String.format("Change Password by SysAdmin for User (%s) success", username));
    		} catch (NamingException e) {
    			System.out.println("Change Password failed!");
    			e.printStackTrace();
    		}    		
    	}
    	else {
    		System.out.println("Change Password : User authenticated failed!");
    	}
    	
    	return result;
    }
    
    public Boolean changeLdapUserPassword(String domainDN, String username, String oldPassword, String newPassword) {
    	Boolean result = false;
    	
    	System.out.println(String.format("Change Password by User Itself (%s) ...", username));
    	
    	// Prepare the user DN
    	String userDN = formatDN(domainDN, 
				 				 domainDN.compareToIgnoreCase(SYS_DOMAIN_DN)==0 ? SYS_BASE_DN : USERS_BASE_DN, 
				 				 username);
    	System.out.println("UserDN = " + userDN);
    	
    	// Connect to the ldap server
    	LdapContext ctx = ldapConnect(domainDN, userDN, oldPassword);
    	if( ctx != null ) {
    		try {
    			System.out.println("Change Password : User authenticated successfully.");
    			
    			byte[] pwd = PasswordUtil.createStoragePassword(newPassword, 
		     													 LdapSecurityConstants.HASH_METHOD_SHA256);
    			String newEncodedHashPwd = Strings.utf8ToString(pwd);
    			System.out.println("New Encoded Hashed Password = " + newEncodedHashPwd);

    			ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, 
    												     	 new BasicAttribute("userPassword", newEncodedHashPwd));
    			
    			// Prepare the user DN without domain
    			userDN = formatDN("", 
    							  domainDN.compareToIgnoreCase(SYS_DOMAIN_DN)==0 ? SYS_BASE_DN : USERS_BASE_DN, 
    							  username);
    			System.out.println("UserDN (without domain) = " + userDN);
    			ctx.modifyAttributes(userDN, new ModificationItem[]{item});   		
    			result = true;
    			ctx.close();
    			System.out.println(String.format("Change Password for User (%s) success", username));
    		} catch (NamingException e) {
    			System.out.println("Change Password failed!");
    			System.out.println("--" + e.getExplanation());
    			System.out.println("--" + e.getMessage());
    			
    			e.printStackTrace();
    		}    		
    	}
    	else {
    		System.out.println("Change Password : User authenticated failed!");
    	}
    	
    	return result;
    } 
}
