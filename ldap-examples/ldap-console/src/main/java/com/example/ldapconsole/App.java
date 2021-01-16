package com.example.ldapconsole;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("LDAP Console Demo");
        System.out.println("=================");
        
        //runLdapDemoUsingJndi();
        
        runLdapDemoUsingApacheLdapApi();
        
        System.out.println("exit");
    }
    
    public static void runLdapDemoUsingJndi() {
    	
    	System.out.println("Test using JNDI method");
        LdapDemoUsingJndi demo = new LdapDemoUsingJndi();
        
        // Test Browser Ldap users
        demo.browseLdapUsers();
        
        // Test authenticate ldap users
        demo.authenticateLdapUser(LdapDemo.USERS_DOMAIN_DN, "user01", "dsfsdfP@ssw0rd123");
        
        // Test change ldap users password by SysAdmin
        demo.changeLdapUserPasswordBySysAdmin(LdapDemo.USERS_DOMAIN_DN, "user01", "P@ssw0rd123");
        
        // Test change ldap users password by itself
        demo.changeLdapUserPassword(LdapDemo.USERS_DOMAIN_DN, "user01", "dsfsdfP@ssw0rd123", "hello@World");
    }
    
    public static void runLdapDemoUsingApacheLdapApi() {
    	
    	System.out.println("Test using Apache LDAP API method");
    	LdapDemoUsingApacheLdapApi demo = new LdapDemoUsingApacheLdapApi();
    	
    	// Test Browser Ldap users
        demo.browseLdapUsers();
        
        // Test authenticate ldap users
        demo.authenticateLdapUser(LdapDemo.USERS_DOMAIN_DN, "user01", "abc@world");
        
        // Test change ldap users password by SysAdmin
        demo.changeLdapUserPasswordBySysAdmin(LdapDemo.USERS_DOMAIN_DN, "user01", "testtest");
        
        // Test change ldap users password by itself
        demo.changeLdapUserPassword(LdapDemo.USERS_DOMAIN_DN, "user01", "testtest", "qwerty@123");
    }
}
