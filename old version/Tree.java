package gitsimulator;

import java.io.*;

public class Tree extends KeyValueStorage {
	private String type = "tree";
	private String path;
	private String key;	
	private File file;
	private String vrtype = "100644";
	private String temp;
	
    public Tree(String path) throws Exception {
    	this.file = new File(path);
    	this.path = path;
    }
    
    public String ComputeKey() throws Exception{
	for(File f : file.listFiles()){
		if(f.isFile()){
			temp = temp + "\n" + vrtype + " blob" + new Blob(f.getPath()).GetKey() + " " + f.getName();
		}
		else if(f.isDirectory()){
			temp = temp + "\n" + vrtype + " tree" + new Tree(f.getPath()).GetKey() + " " + f.getName();
		}
	}
	this.key = GetKey(temp);
	return key;
}
    
	public String GetKey() {
		return key;
	}
}
