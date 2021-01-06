package gitsimulator;

import java.io.*;

public class Tree extends GitObject {//继承自GitObject
	private String type = "tree";//文件夹类型
	private String path;
	private String key;	//文件夹hash值
	private File file;
	private String vrtype = "100644";//文件夹访问权限
	private String temp = "";//文件夹包含的目录内容
	
    public Tree(String path) throws Exception {//通过路径生成tree类型
    	this.file = new File(path);
    	this.path = path;
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
    
    public Tree(File file) throws Exception{//通过file生成tree类型
    	this.file = file;
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

	public void write() throws Exception {//写入操作
		WriteToString(this.key, this.temp);
	}
    
	public String GetKey() {
		return key;
	}//获得文件夹的hash值

	public String GetValue() {return temp;}//获得文件夹的目录内容
}
