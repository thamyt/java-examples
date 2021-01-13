package com.example.strutsmvc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.example.strutsmvc.dto.Person;
import com.opensymphony.xwork2.ActionSupport;

public class FormSubmitAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private Person person;
	private List<String> occupationList;
	private File myFile;
	private String myFileContentType;
	private String myFileFileName;
	private String destPath;

	public FormSubmitAction() {
		// populate occupations list
		occupationList = new ArrayList<String>();
		occupationList.add("Salesman");
		occupationList.add("Engineer");
		occupationList.add("Manager");
		occupationList.add("Doctor");
		occupationList.add("Hawker");
	}
	
	public String display() throws Exception {
		return NONE;
	}

    public String submit() throws Exception {
    	System.out.println("Inside FormSubmitAction...");
    	
    	// check if there is any error
    	for(String s : this.getErrorMessages()) {
    		System.out.println("Error Message = " + s);
    	}
    	
    	for(String s : this.getActionErrors()) {
    		System.out.println("Action Error = " + s);
    	}
    	
    	for(Map.Entry<String,List<String>> entry : getFieldErrors().entrySet()) {
    		System.out.println("Field name = " + entry.getKey());
    		for(String s : entry.getValue()) {
    			System.out.println("---> Error = " + entry);	
    		}
    	}
    	
    	if( person != null ) {
    		System.out.println("firstname : " + person.getFirstName());
        	System.out.println("lastname : " + person.getLastName());
        	System.out.println("DOB : " + person.getDob());
        	
        	destPath = "D:/";
        	
    		try {
    			System.out.println("Src File name: " + myFile);
    			System.out.println("Dst File name: " + myFileFileName);
    			System.out.println("File Content Type: " + myFileContentType);
    			
    			if( myFile != null ) {	    	 
    				File destFile  = new File(destPath, myFileFileName);
    				FileUtils.copyFile(myFile, destFile); 
    				person.setPhoto(destFile.getName());
    			}
    		} 
    		catch(IOException e) {
    			e.printStackTrace();
    		    return ERROR;
    		}
    	}
		
		return SUCCESS;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<String> getOccupationList() {
		return occupationList;
	}
	
	public void setOccupationList(List<String> occupationList) {
		this.occupationList = occupationList;
	}
}
