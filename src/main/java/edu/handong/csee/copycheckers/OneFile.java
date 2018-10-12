package edu.handong.csee.copycheckers;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

public class OneFile {
	private String code;
	private Set<String> localVariable;
	private HashMap<String, String> fieldVariable = new HashMap(); //variable, value
	private File file;
	
	public String getCode() {
		return code;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public Set<String> getLocalVariable() {
		return localVariable;
	}
	public void setLocalVariable(Set<String> localVariable) {
		this.localVariable = localVariable;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public HashMap<String, String> getFieldVariable() {
		return fieldVariable;
	}
	public void setFieldVariable(HashMap<String, String> fieldVariable) {
		this.fieldVariable = fieldVariable;
	}
}
