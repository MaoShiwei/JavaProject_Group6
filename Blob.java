package gitsimulator;

import java.io.*;

public class Blob extends KeyValueStorage {
	private String type = "blob";
	private String path;
	private String key;	
	private File file;
	private String vrtype = "100644";
	
    public Blob(String path) throws Exception {
    	this.file = new File(path);
        this.key = GetKey(new FileInputStream(file));
        this.path = path;
    } 
    
    public void write() throws Exception {
        WriteToFile(path);
    }
    
	public String GetKey() {
		return key;
	}
    
}
