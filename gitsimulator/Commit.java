package gitsimulator;

import java.util.LinkedList;
import java.io.*;

public class Commit extends GitObject {
    private String parent;
    private String author;
    private String committer;
    private String comment;
    private String key;
    private File file;
    static LinkedList<String> CommitList = new LinkedList<String>();
    private String value = "";

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
		if(this.file.isFile()){
			FileKey = new Blob(this.file).GetKey();
		}
		if(this.file.isDirectory()){
			FileKey = new Tree(this.file).GetKey();
		}
		if (CommitList.isEmpty()) {
			value += "tree " + FileKey + "\n" + "parent null\n" + "author " + author + "\n" + "committer " + committer + "\n" + "comment " + comment;
			this.key = StringSHA1Checksum(value);
			CommitList.add(this.key);
		} else {
			if (CommitList.getFirst()!= FileKey) {
				parent = CommitList.getFirst();
				value += "tree " + FileKey + "\n" + "parent " + parent + "\n" + "author " + author + "\n" + "committer " + committer + "\n" + "comment " + comment;
				this.key = StringSHA1Checksum(value);
				CommitList.addFirst(this.key);
			}
		}
	}

	public void write() throws Exception {
		WriteToString(this.key, this.value);
	}

	public String GetKey() {
		return key;
	}

	public static LinkedList<String> ShowCommitList(){
		return CommitList;
	}
		
	public String ShowCommit() {
		parent = CommitList.get(2);
		String info = "NewCommit: " + CommitList.getFirst() + " Committer: " + committer + " Author: " + author + " LastCommit: " + parent;
		return info;
	}
		
}
