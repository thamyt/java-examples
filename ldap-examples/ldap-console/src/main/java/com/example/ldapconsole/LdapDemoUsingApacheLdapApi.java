package com.example.ldapconsole;

import java.util.Collection;

import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.LdapContext;

import org.apache.directory.api.ldap.model.constants.LdapSecurityConstants;
import org.apache.directory.api.ldap.model.cursor.CursorException;
import org.apache.directory.api.ldap.model.cursor.EntryCursor;
import org.apache.directory.api.ldap.model.entry.Attribute;
import org.apache.directory.api.ldap.model.entry.DefaultModification;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.entry.Modification;
import org.apache.directory.api.ldap.model.entry.ModificationOperation;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.api.ldap.model.password.PasswordUtil;
import org.apache.directory.api.util.Strings;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;

public class LdapDemoUsingApacheLdapApi extends LdapDemo {

	private LdapConnection connection;
	
	public LdapDemoUsingApacheLdapApi() {
		connection = new LdapNetworkConnection(LDAP_HOST, LDAP_PORT);
	}
	
	public void browseLdapUsers() {
    	String baseDN = "OU=people" + "," + USERS_DOMAIN_DN;
    	String filter = "(objectClass=organizationalPerson)";
    	
    	System.out.println("Browse LDAP users Demo");
    	
    	try {
	    	// Prepare the special user with special privilege
	    	String sysDN = formatDN(SYS_DOMAIN_DN, SYS_BASE_DN, SYS_ADMIN_PRINCIPAL);
	    	System.out.println("sysDN = " + sysDN);
	    	// Connect to the ldap server
	    	connection.bind(sysDN, SYS_ADMIN_PASSWORD );
	    	
	    	EntryCursor cursor = connection.search(baseDN, 
				       filter, 
				       SearchScope.ONELEVEL, 
				       "*" );
			while ( cursor.next() )
			{
				Entry entry = cursor.get();
				Collection<Attribute> attrs = entry.getAttributes();
				for(Attribute a : attrs) {
					System.out.println(String.format("%s = %s", a.getId(), a.getString()));
				}
				System.out.println("--------------------------------------------------");
			}
			cursor.close();
			
			connection.unBind();
	    	
    	}
	    catch(LdapException e) {
	    	System.out.println("Failed to connect to ldap server!");
	    }
    	catch(CursorException e) {
    		System.out.println(e.getMessage());
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
	}
	
	public Boolean authenticateLdapUser(String domainDN, String username, String password) {
    	Boolean result = false;
    	
    	System.out.println(String.format("Authenticated user (%s) ...", username));
    	
    	try {
    		// Prepare the user DN
        	String userDN = formatDN(domainDN, 
        							 domainDN.compareToIgnoreCase(SYS_DOMAIN_DN)==0 ? SYS_BASE_DN : USERS_BASE_DN, 
        						     username);
        	System.out.println("userDN = " + userDN);
	    	// Connect to the ldap server
	    	connection.bind(userDN, password );
	    	
	    	System.out.println("User authenticated successfully.");
    		result = true;
    	}
	    catch(LdapException e) {
	    	System.out.println(e.getMessage());
	    	System.out.println("User authenticated failed!");
	    }
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	finally {
    		// Disconnect to the ldap server
    		try {
				connection.unBind();
			} catch (LdapException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	return result;
    }
	
    public Boolean changeLdapUserPasswordBySysAdmin(String domainDN, String username, String newPassword) {
    	Boolean result = false;
    	
    	System.out.println(String.format("Change Password by SysAdmin for User (%s) ...", username));
    	
    	try {
	    	// Prepare the special user with special privilege
	    	String sysDN = formatDN(SYS_DOMAIN_DN, SYS_BASE_DN, SYS_ADMIN_PRINCIPAL);
	    	System.out.println("sysDN = " + sysDN);
	    	
	    	// Connect to the ldap server
	    	connection.bind(sysDN, SYS_ADMIN_PASSWORD);
    	
	    	// hash the new Password
	    	byte[] pwd = PasswordUtil.createStoragePassword(newPassword, 
						LdapSecurityConstants.HASH_METHOD_SHA256);
	    	String newEncodedHashPwd = Strings.utf8ToString(pwd);
	    	System.out.println("New Encoded Hashed Password = " + newEncodedHashPwd);
    	
	    	Modification modifyUserPassword = new DefaultModification(ModificationOperation.REPLACE_ATTRIBUTE,
	    															  "userPassword", newEncodedHashPwd);
	    	
	    	// Prepare the user DN
			String userDN = formatDN(domainDN, 
							  		 domainDN.compareToIgnoreCase(SYS_DOMAIN_DN)==0 ? SYS_BASE_DN : USERS_BASE_DN, 
							  		 username);
			System.out.println("UserDN = " + userDN);
	    	
			connection.modify(userDN, modifyUserPassword);
			result = true;
	    	
			System.out.println(String.format("Change Password by SysAdmin for User (%s) success", username));
    	}
		catch(LdapException e) {
	    	System.out.println(e.getMessage());
	    	System.out.println("Change Password failed!");
	    }
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	finally {
    		// Disconnect to the ldap server
    		try {
				connection.unBind();
			} catch (LdapException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	return result;
    }
    
    public Boolean changeLdapUserPassword(String domainDN, String username, String oldPassword, String newPassword) {
    	Boolean result = false;
    	
    	System.out.println(String.format("Change Password by User Itself (%s) ...", username));
    	
    	try {
	    	// Prepare the user DN
	    	String userDN = formatDN(domainDN, 
					 				 domainDN.compareToIgnoreCase(SYS_DOMAIN_DN)==0 ? SYS_BASE_DN : USERS_BASE_DN, 
					 				 username);
	    	System.out.println("UserDN = " + userDN);
	    	
	    	// Connect to the ldap server
	    	connection.bind(userDN, oldPassword);
	    	
	    	// hash the new Password
	    	byte[] pwd = PasswordUtil.createStoragePassword(newPassword, 
					 										LdapSecurityConstants.HASH_METHOD_SHA256);
			String newEncodedHashPwd = Strings.utf8ToString(pwd);
			System.out.println("New Encoded Hashed Password = " + newEncodedHashPwd);
	    	
			Modification modifyUserPassword = new DefaultModification(ModificationOperation.REPLACE_ATTRIBUTE,
					  												  "userPassword", newEncodedHashPwd);
			
			connection.modify(userDN, modifyUserPassword);
			result = true;
			
			System.out.println(String.format("Change Password for User (%s) success", username));
    	}
		catch(LdapException e) {
	    	System.out.println(e.getMessage());
	    	System.out.println("Change Password failed!");
	    }
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	finally {
    		// Disconnect to the ldap server
    		try {
				connection.unBind();
			} catch (LdapException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}			
			
    	return result;
    } 
}
