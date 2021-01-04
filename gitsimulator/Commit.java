package gitsimulator;

import java.util.LinkedList;
import java.io.*;

public class Commit extends KeyValueStorage {
    private String parent;
    private String author;
    private String committer;
    private String comment;
    private String key;
    private File file;
    private LinkedList<String> CommitList = new LinkedList<String>();
    private String value = "";
	protected String savepath = "D:\\Java\\managebase\\commits";

	public Commit(String path, String parent, String author, String committer, String comment) {
		this.path = path;
		this.file = new File(path);
		this.parent = parent;
		this.author = author;
		this.committer = committer;
		this.comment = comment;
	}
	
	public void ComputeCommit() throws Exception {
		if (CommitList.isEmpty()) {
			value += "tree " + new Tree(this.file).GetKey() + "\n" + "parent null\n" + "author " + author + "\n" + "committer " + committer + "\n" + "comment " + comment;
			this.key = StringSHA1Checksum(value);
			CommitList.add(this.key);
		} else {
			if (CommitList.getFirst()!= new Tree(this.file).GetKey()) {
				parent = CommitList.getFirst();
				value += "tree " + new Tree(this.file).GetKey() + "\n" + "parent " + parent + "\n" + "author " + author + "\n" + "committer " + committer + "\n" + "comment " + comment;
				this.key = StringSHA1Checksum(value);
				CommitList.addFirst(this.key);
			}
		}
	}

	public void write() throws Exception {
		WriteToString(this.key, this.value, savepath);
	}

	public String GetKey() {
		return key;
	}

	public LinkedList<String> ShowCommitList(){
		return CommitList;
	}
		
	public String ShowCommit() {
		parent = CommitList.get(2);
		String info = "NewCommit: " + CommitList.getFirst() + " Committer: " + committer + " Author: " + author + " LastCommit: " + parent;
		return info;
	}
		
}
