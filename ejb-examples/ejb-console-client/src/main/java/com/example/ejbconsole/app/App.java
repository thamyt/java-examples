package com.example.ejbconsole.app;

import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.example.ejb.sessionbean.CurrencyRemote;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final String HTTP = "http";
	
    public static void main( String[] args )
    {
        System.out.println( "Start..." );
        
        try {
        	CurrencyRemote currEx = lookupEJBCurrencyExchange();
			
			System.out.println("Getting supported currency using list...");
			List<String> currencies = currEx.getCurrencyList();
			for(String curr : currencies) {
				System.out.format("- %s\n", curr);
			}
			
			System.out.println("\n\n");
			
			System.out.format("SGD currency rate : %f\n", currEx.getCurrencyRate("SGD"));
			System.out.format("MYR currency rate : %f\n", currEx.getCurrencyRate("MYR"));
			System.out.format("USD currency rate : %f\n", currEx.getCurrencyRate("USD"));
			System.out.format("RMB currency rate : %f\n", currEx.getCurrencyRate("RMB"));
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println( "End" );
    }
    
    
    private static CurrencyRemote lookupEJBCurrencyExchange() throws NamingException {
    	System.out.println("Inside lookupEJBCurrencyExchange...");
    	
    	int option = 4;
    	
    	// Prepare the context properties 
    	Properties prop = new Properties();
    	
    	switch(option) {
	    	case 1: {	// use the connection setting specify in the default jboss-ejb-client.properties
	    				prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
	    				break;
	    	}
	    	case 2: {	// use the connection setting specify in the default jboss-ejb-client.properties
	    				prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
	    				break;
	    	}
	    	case 3: {	// use the custom connection setting specify in code
	    				prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
	    				prop.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
	    		        prop.put(Context.SECURITY_PRINCIPAL, "admin");
	    		        prop.put(Context.SECURITY_CREDENTIALS, "Passw0rd123");
	    				break;
	    	}
	    	case 4:	{	// use the custom connection setting specify in code
	    				prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
						prop.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
				        prop.put(Context.SECURITY_PRINCIPAL, "admin");
				        prop.put(Context.SECURITY_CREDENTIALS, "Passw0rd123");
						break;
	    	}
    	}
        
        //prop.put("jboss.naming.client.ejb.context", false);
        //Context  context = new InitialContext(prop);
    	Context  context = new InitialContext();
    	
        
        return (CurrencyRemote) context.lookup("ejb:/ejb-sessionbean-0.0.1-SNAPSHOT/CurrencyExchange!"
				+ CurrencyRemote.class.getName());
        /*
        return (CurrencyExchangeRemote) context.lookup("ejb-sessionbean-0.0.1-SNAPSHOT/CurrencyExchange!"
				+ CurrencyExchangeRemote.class.getName());
		*/
    }
    
    
    
    private static CurrencyRemote lookupRemoteStatelessCurrencyExchange() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        if(Boolean.getBoolean(HTTP)) {
            //use HTTP based invocation. Each invocation will be a HTTP request
            jndiProperties.put(Context.PROVIDER_URL,"http://localhost:8080/wildfly-services");
        } else {
            //use HTTP upgrade, an initial upgrade requests is sent to upgrade to the remoting protocol
            jndiProperties.put(Context.PROVIDER_URL,"remote+http://localhost:8080");
        }
        final Context context = new InitialContext(jndiProperties);

        // The JNDI lookup name for a stateless session bean has the syntax of:
        // ejb:<appName>/<moduleName>/<distinctName>/<beanName>!<viewClassName>
        //
        // <appName> The application name is the name of the EAR that the EJB is deployed in
        // (without the .ear). If the EJB JAR is not deployed in an EAR then this is
        // blank. The app name can also be specified in the EAR's application.xml
        //
        // <moduleName> By the default the module name is the name of the EJB JAR file (without the
        // .jar suffix). The module name might be overridden in the ejb-jar.xml
        //
        // <distinctName> : EAP allows each deployment to have an (optional) distinct name.
        // This example does not use this so leave it blank.
        //
        // <beanName> : The name of the session been to be invoked.
        //
        // <viewClassName>: The fully qualified classname of the remote interface. Must include
        // the whole package name.

        // let's do the lookup
        // ejb:/ejb-sessionbean-0.0.1-SNAPSHOT/CurrencyExchange!com.example.ejb.sessionbean.CurrencyExchangeRemote
        return (CurrencyRemote) context.lookup("ejb:/ejb-sessionbean-0.0.1-SNAPSHOT/CurrencyExchange!"
        										+ CurrencyRemote.class.getName());
    }
}
