package gitsimulator;

import java.io.*;

public class Blob extends GitObject {//继承自GitObject
	private String type = "blob";//文件类型为blob
	private String key;	//文件的hash值
	private File file;//文件
	private String vrtype = "100644";//文件访问权限

	public Blob(File file) throws Exception {//通过file新建Blob类
		this.file = file;//更新file的内容
		this.key = FileSHA1Checksum(new FileInputStream(file));//对file进行hash值计算
	}

	public Blob(String path) throws Exception {//通过文件路径新建Blob类
    	this.file = new File(path);
        this.key = FileSHA1Checksum(new FileInputStream(file));
    } 
    
    public void write(String path) throws Exception {//path：源文件的路径，被写入路径为objects存储目录，在GitObject中有定义
        WriteToFile(this.key, path);//以文件的hash作为名字进行value写入的操作
    }
    
	public String GetKey() {
		return key;
	}//获取文件的hash值

	public String Getvrtype() {return vrtype; }//获取文件的访问权限

	public String GetType() {return type;}//获取文件的类型
    
}
