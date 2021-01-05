package gitsimulator;

import java.util.LinkedList;
import java.io.*;

public class Commit extends GitObject {//继承自GitObject
    private String parent;//上一个commit对应的hash值
    private String author;//commit的作者
    private String committer;//commit的提交者
    private String comment;//提交commit时的评论
    private String key;//commit的hash值
    private File file;
    static LinkedList<String> CommitList = new LinkedList<String>();//commit链表
    private String value = "";//commit的全部内容
	private String path = "";

	public Commit(String path, String parent, String author, String committer, String comment) {
		this.path = path;
		this.file = new File(path);
		this.parent = parent;
		this.author = author;
		this.committer = committer;
		this.comment = comment;
	}
	
	public void ComputeCommit() throws Exception {
		String FileKey = "";
		if(this.file.isFile()){//文件类型
			FileKey = new Blob(this.file).GetKey();//按照Blob类进行hash值生成
		}
		if(this.file.isDirectory()){//文件夹类型
			FileKey = new Tree(this.file).GetKey();//按照Tree类进行hash值生成
		}
		if (CommitList.isEmpty()) {//如果此时链表为空，表明是第一个commit，默认parent为null
			value += "tree " + FileKey + "\n" + "parent null\n" + "author " + author + "\n" + "committer " + committer + "\n" + "comment " + comment;
			this.key = StringSHA1Checksum(value);//进行hash值计算
			CommitList.add(this.key);//向链表添加commit
		} else {
			if (CommitList.getFirst()!= FileKey) {//判断当前commit是否与前一个commit不同
				parent = CommitList.getFirst();//获取上一个commit的hash值
				value += "tree " + FileKey + "\n" + "parent " + parent + "\n" + "author " + author + "\n" + "committer " + committer + "\n" + "comment " + comment;
				this.key = StringSHA1Checksum(value);//进行hash值计算
				CommitList.addFirst(this.key);//向链表添加commit
			}
		}
	}

	public void write() throws Exception {
		WriteToString(this.key, this.value);//将commit的内容写入以commit的hash值命名的文件中
	}

	public String GetKey() {
		return key;
	}//获取commit的hash值

	public static LinkedList<String> ShowCommitList(){
		return CommitList;
	}//获取commit链表
		
	public String ShowCommit() {//显示commit
		parent = CommitList.get(2);
		String info = "NewCommit: " + CommitList.getFirst() + " Committer: " + committer + " Author: " + author + " LastCommit: " + parent;
		return info;
	}
		
}
