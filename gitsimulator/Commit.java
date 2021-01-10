package gitsimulator;

import java.util.Date;
import java.util.LinkedList;
import java.io.*;
import java.util.TimeZone;

public class Commit extends GitObject {//�̳���GitObject
    private String parent;//��һ��commit��Ӧ��hashֵ
    private String author;//commit������
    private String committer;//commit���ύ��
    private String comment;//�ύcommitʱ������
    private String key;//commit��hashֵ
    private File file;
    static LinkedList<String> CommitList = new LinkedList<String>();//commit����
	static LinkedList<String> TreeIDList = new LinkedList<String>();//commitָ���tree��keyֵ����
    private String value = "";//commit��ȫ������
	private String path = "";
	private long date = new Date().getTime();//��ȡ�ύcommit��ʱ��
	private String timezone = "+0800";//�趨�й�ʱ��

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
		if(this.file.isFile()){//�ļ�����
			FileKey = new Blob(this.file).GetKey();//����Blob�����hashֵ����
		}
		if(this.file.isDirectory()){//�ļ�������
			FileKey = new Tree(this.file).GetKey();//����Tree�����hashֵ����
		}
		TreeIDList.add(FileKey);
		if (CommitList.isEmpty()) {//�����ʱ����Ϊ�գ������ǵ�һ��commit��Ĭ��parentΪnull
			value += "tree " + FileKey + "\n" + "parent null\n" + "author " + author + " " + date + " " + timezone + "\n" + "committer " + committer + " " + date + " " + timezone + "\n" + "comment " + comment;
			this.key = StringSHA1Checksum(value);//����hashֵ����
			CommitList.add(this.key);//���������commit
			write();
		} else {
			if (TreeIDList.getFirst().equals(FileKey)) {//�жϵ�ǰcommit�Ƿ���ǰһ��commit��ͬ
				System.out.println("����Ч�޸ģ��޷��ύcommit");
				this.key = CommitList.getFirst();
			}else {
				parent = CommitList.getFirst();//��ȡ��һ��commit��hashֵ
				value += "tree " + FileKey + "\n" + "parent " + parent + "\n" + "author " + author + " " + date + " " + timezone + "\n" + "committer " + committer + " " + date + " " + timezone + "\n" + "comment " + comment;
				this.key = StringSHA1Checksum(value);//����hashֵ����
				CommitList.addFirst(this.key);//���������commit
				write();
			}
		}
	}

	public void write() throws Exception {
		WriteToString(this.key, this.value);//��commit������д����commit��hashֵ�������ļ���
	}

	public String GetKey() {
		return key;
	}//��ȡcommit��hashֵ

	public static LinkedList<String> ShowCommitList(){
		return CommitList;
	}//��ȡcommit����
		
}
