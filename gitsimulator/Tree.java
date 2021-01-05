package gitsimulator;

import java.io.*;

public class Tree extends KeyValueStorage {
	private String type = "tree";
	private String path;
	private String key;	
	private File file;
	private String vrtype = "100644";
	private String temp = "";
	protected String savepath = "D:\\Java\\managebase\\files";
	
    public Tree(String path) throws Exception {
    	this.file = new File(path);
    	this.path = path;
		for(File f : file.listFiles()){
			if(f.isFile()){
				temp = temp + "040000 blob" + new Blob(f.getPath()).GetKey() + " " + f.getName() + "\n";
			}
			else if(f.isDirectory()){
				temp = temp + "100644 tree" + new Tree(f.getPath()).GetKey() + " " + f.getName() + "\n";
			}
		}
		this.key = StringSHA1Checksum(temp);
    }
    
    public Tree(File file) throws Exception{
    	for(File f : file.listFiles()){
    		if(f.isFile()){
    			temp = temp + "040000 blob " + new Blob(f.getPath()).GetKey() + " " + f.getName() + "\n";
    		}
    		else if(f.isDirectory()){
    			temp = temp + "100644 tree " + new Tree(f.getPath()).GetKey() + " " + f.getName() + "\n";
    		}
    	}
	this.key = StringSHA1Checksum(temp);
    }

	public void write() throws Exception {
		WriteToString(this.key, this.temp, savepath);
	}
    
	public String GetKey() {
		return key;
	}

	public String GetValue() {return temp;}
}
