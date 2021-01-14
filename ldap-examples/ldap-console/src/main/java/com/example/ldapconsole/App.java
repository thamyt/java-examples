package com.example.ldapconsole;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
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

import org.apache.commons.codec.binary.Hex;
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
	public static String SYS_DOMAIN_DN = "";
	public static String USERS_DOMAIN_DN = "dc=test,dc=com";
	public static String USERS_BASE_DN = "ou=people";
	
	public static enum PASSWORD_HASH {
		  SHA,
		  SHA_256,
		  SHA_384,
		  SHA_512,
		  SSHA,
		  SSHA_256,
		  SSHA_384,
		  SSHA_512,
		  MD5,
		  SMD5,
		  PKCS5C2,
		  CRYPT,
		  CRYPT_MD5,
		  CRYPT_SHA_256,
		  CRYPT_SHA_512,
		  PLAINTEXT
		};
	
    public static void main( String[] args )
    {
        System.out.println("LDAP Console Demo");
        System.out.println("=================");
        
        App app = new App();
        
        //app.test();
        
        // Test Browser Ldap users
        app.browseLdapUsers();
        
        // Test authenticate ldap users
        app.authenticateLdapUser(USERS_DOMAIN_DN, "user04", "hello@World");
        
        // Test authenticate ldap users
        //app.changeLdapUserPasswordBySysAdmin(USERS_DOMAIN_DN, "user04", "P@ssw0rd123", "hello@World");
        
        // Test change ldap users password
        //app.changeLdapUserPassword(USERS_DOMAIN_DN, "user01", "P@ssw0rd123", "hello@World");
        
        // Test authenticate ldap users
        //app.authenticateLdapUser("user02", "hello@World");
        
        System.out.println("exit");
    }
    
    public String hashWith256(String textToHash) {
        MessageDigest digest;
        String encoded = "";
		try {
			digest = MessageDigest.getInstance("SHA-256");
			
			byte[] byteOfTextToHash = textToHash.getBytes(StandardCharsets.UTF_8);
	        byte[] hashedByetArray = digest.digest(byteOfTextToHash);
	        encoded = Base64.getEncoder().encodeToString(hashedByetArray);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return encoded;
    }
    
    
    public void test() {
    	String password = "P@ssw0rd123";
    	
    	byte[] digest = DigestUtils.sha256(password);
    	
    	// method 1
    	System.out.println("length = %d" + digest.length);
    	System.out.println("sha256 = " + digest.toString());
    	// method 2
    	StringBuilder sb = new StringBuilder(digest.length * 2);
    	for(byte b : digest) {
    		sb.append(String.format("%02x", b & 0xff));
    	}
    	System.out.println("StringBuilder method = " + sb.toString());
    	// method 3
    	System.out.println("sha256Hex = " + DigestUtils.sha256Hex(password));
    	// method 4
    	System.out.println("hashWith256 = " + hashWith256(password));
    	// method 5
    	System.out.println("encoded sha256 = " + Base64.getEncoder().encodeToString(DigestUtils.sha256(password)));
    }
    
    private static byte[] generatePasswordSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        return salt;
    }
       
    private String formatEncodedHashPassword(PASSWORD_HASH eumPwdHash, String password) {
    	byte[] hashPassword = null;
    	String prefix = "";
    	
    	switch(eumPwdHash) {
	    	case SHA: {
	    		prefix = "{SHA}";
	    		hashPassword = DigestUtils.sha1(password);
	    		break;
	    	}
	    	case SHA_256: {
	    		prefix = "{SHA256}";
	    		hashPassword = DigestUtils.sha256(password);
	    		break;
	    	}
	    	case SHA_384: {
	    		prefix = "{SHA384}";
	    		hashPassword = DigestUtils.sha384(password);
	    		break;
	    	}
	    	case SHA_512: {
	    		prefix = "{SHA512}";
	    		hashPassword = DigestUtils.sha512(password);
	    		break;
	    	}
	    	case SSHA: {
	    		prefix = "{SSHA}";
	    		byte[] salt = generatePasswordSalt(); 
	    		byte[] pwd = password.getBytes();
	    		
	    		// combine password and salt bytes
	    		byte[] pwdsalt = new byte[pwd.length + salt.length];
	    		System.arraycopy(pwd, 0, pwdsalt, 0, pwd.length);
	            System.arraycopy(salt, 0, pwdsalt, pwd.length, salt.length);
	    		
	            // generate the password hash using (password + salt)
	            byte[] hashTemp = DigestUtils.sha1(pwdsalt);
	            
	            // combine password hash and salt bytes
	            hashPassword = new byte[hashTemp.length + salt.length];
	            System.arraycopy(hashTemp, 0, hashPassword, 0, hashTemp.length);
	            System.arraycopy(salt, 0, hashPassword, hashTemp.length, salt.length);
	    		break;
	    	}
	    	case SSHA_256: {
	    		prefix = "{SSHA256}";
	    		byte[] salt = generatePasswordSalt(); 
	    		byte[] pwd = password.getBytes();
	    		
	    		// combine password and salt bytes
	    		byte[] pwdsalt = new byte[pwd.length + salt.length];
	    		System.arraycopy(pwd, 0, pwdsalt, 0, pwd.length);
	            System.arraycopy(salt, 0, pwdsalt, pwd.length, salt.length);
	    		
	            // generate the password hash using (password + salt)
	            byte[] hashTemp = DigestUtils.sha256(pwdsalt);
	            
	            // combine password hash and salt bytes
	            hashPassword = new byte[hashTemp.length + salt.length];
	            System.arraycopy(hashTemp, 0, hashPassword, 0, hashTemp.length);
	            System.arraycopy(salt, 0, hashPassword, hashTemp.length, salt.length);
	    		break;
	    	}
	    	case SSHA_384: {
	    		prefix = "{SSHA384}";
	    		byte[] salt = generatePasswordSalt(); 
	    		byte[] pwd = password.getBytes();
	    		
	    		// combine password and salt bytes
	    		byte[] pwdsalt = new byte[pwd.length + salt.length];
	    		System.arraycopy(pwd, 0, pwdsalt, 0, pwd.length);
	            System.arraycopy(salt, 0, pwdsalt, pwd.length, salt.length);
	    		
	            // generate the password hash using (password + salt)
	            byte[] hashTemp = DigestUtils.sha384(pwdsalt);
	            
	            // combine password hash and salt bytes
	            hashPassword = new byte[hashTemp.length + salt.length];
	            System.arraycopy(hashTemp, 0, hashPassword, 0, hashTemp.length);
	            System.arraycopy(salt, 0, hashPassword, hashTemp.length, salt.length);
	    		break;
	    	}
	    	case SSHA_512: {
	    		prefix = "{SSHA512}";
	    		byte[] salt = generatePasswordSalt(); 
	    		byte[] pwd = password.getBytes();
	    		
	    		// combine password and salt bytes
	    		byte[] pwdsalt = new byte[pwd.length + salt.length];
	    		System.arraycopy(pwd, 0, pwdsalt, 0, pwd.length);
	            System.arraycopy(salt, 0, pwdsalt, pwd.length, salt.length);
	    		
	            // generate the password hash using (password + salt)
	            byte[] hashTemp = DigestUtils.sha512(pwdsalt);
	            
	            // combine password hash and salt bytes
	            hashPassword = new byte[hashTemp.length + salt.length];
	            System.arraycopy(hashTemp, 0, hashPassword, 0, hashTemp.length);
	            System.arraycopy(salt, 0, hashPassword, hashTemp.length, salt.length);
	    		break;
	    	}
	    	case MD5: {
	    		break;
	    	}
	    	case SMD5: {
	    		break;
	    	}
	    	case PKCS5C2: {
	    		break;
	    	}
	    	case CRYPT: {
	    		break;
	    	}
	    	case CRYPT_MD5: {
	    		break;
	    	}
	    	case CRYPT_SHA_256: {
	    		break;
	    	}
	    	case CRYPT_SHA_512: {
	    		break;
	    	}
	    	case PLAINTEXT: {
	    		break;
	    	}
	    	default:
	    		break;
    	}
    	
    	return prefix + Base64.getEncoder().encodeToString(hashPassword);	
   }
    
    
    private String formatDN(String domainDN, String baseDN, String username) {
    	String dn = String.format("uid=%s%s%s", 
				  				  username,
				  				  baseDN.isEmpty() ? "" : "," + baseDN,
				  				  domainDN.isEmpty() ? "" : "," + domainDN);				  
    	return dn;
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
		env.put(Context.PROVIDER_URL, LDAP_URL + "/" + domainDN);
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
    
    public Boolean changeLdapUserPasswordBySysAdmin(String domainDN, String username, String oldPassword, String newPassword) {
    	Boolean result = false;
    	
    	System.out.println(String.format("Change Password by SysAdmin for User (%s) ...", username));
    	
    	// Prepare the special user with special privilege
    	String sysDN = formatDN(SYS_DOMAIN_DN, SYS_BASE_DN, SYS_ADMIN_PRINCIPAL);
    	System.out.println("sysDN = " + sysDN);
    	// Connect to the ldap server
    	LdapContext ctx = ldapConnect(SYS_DOMAIN_DN, sysDN, SYS_ADMIN_PASSWORD);    	
    	if( ctx != null ) {
    		try {
    			String newEncodedHashPwd = formatEncodedHashPassword(App.PASSWORD_HASH.SSHA_256, newPassword);
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
    	
    	System.out.println(String.format("Change Password for User (%s) ...", username));
    	
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
    		
    			String newEncodedHashPwd = formatEncodedHashPassword(App.PASSWORD_HASH.SHA_256, newPassword);
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
    			e.printStackTrace();
    		}    		
    	}
    	else {
    		System.out.println("Change Password : User authenticated failed!");
    	}
    	
    	return result;
    }
}
