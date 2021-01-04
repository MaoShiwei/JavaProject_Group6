package gitsimulator;

import java.io.*;

public class Blob extends KeyValueStorage {
	private String type = "blob";
	private String key;	
	private File file;
	private String vrtype = "040000";

	public Blob(File file) throws Exception {
		this.file = file;
		this.key = FileSHA1Checksum(new FileInputStream(file));
	}

	public Blob(String path) throws Exception {
    	this.file = new File(path);
        this.key = FileSHA1Checksum(new FileInputStream(file));
    } 
    
    public void write(String path) throws Exception {
        WriteToFile(this.key, path);
    }
    
	public String GetKey() {
		return key;
	}

	public String Getvrtype() {return vrtype; }

	public String GetType() {return type;}
    
}
