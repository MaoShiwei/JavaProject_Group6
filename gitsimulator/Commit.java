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
    private String vrtype = "100644";
    private LinkedList<String> CommitList = new LinkedList<String>();  
    
	public Commit(String path,String parent, String author, String committer, String comment) {
		super();
		this.path = path;
		this.file = new File(path);
		this.parent = parent;
		this.author = author;
		this.committer = committer;
		this.comment = comment;
	}
	
	public String GetKey() throws Exception {
		Tree t = new Tree(path);
		return t.GetKey();
	}
	
	public void ComputeCommit() throws Exception {
		String value = "";
		if (CommitList.isEmpty()) {
			value += GetKey() + "\n" + author + "\n" + committer + "\n" + comment;
			key = super.GetKey(value);
			CommitList.add(key);
		} else {
			if (CommitList.getFirst()!= GetKey()) {
				parent = CommitList.getFirst();
				value += GetKey() + "\n" + parent + "\n" + author + "\n" + committer + "n" +comment;
				CommitList.addFirst(super.GetKey(value));
			}
		}
	}
		
	public String ShowCommit() {
		parent = CommitList.get(2);
		String info = "NewCommit: " + CommitList.getFirst() + " Committer: " + committer + " Author: " + author + " LastCommit: " + parent;
		return info;
	}
		
}
