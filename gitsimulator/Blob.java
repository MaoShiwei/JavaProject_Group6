package gitsimulator;

import java.io.*;

public class Blob extends GitObject {//�̳���GitObject
	private String type = "blob";//�ļ�����Ϊblob
	private String key;	//�ļ���hashֵ
	private File file;//�ļ�
	private String vrtype = "100644";//�ļ�����Ȩ��

	public Blob(File file) throws Exception {//ͨ��file�½�Blob��
		this.file = file;//����file������
		this.key = FileSHA1Checksum(new FileInputStream(file));//��file����hashֵ����
	}

	public Blob(String path) throws Exception {//ͨ���ļ�·���½�Blob��
    	this.file = new File(path);
        this.key = FileSHA1Checksum(new FileInputStream(file));
    } 
    
    public void write(String path) throws Exception {//path��Դ�ļ���·������д��·��Ϊobjects�洢Ŀ¼����GitObject���ж���
        WriteToFile(this.key, path);//���ļ���hash��Ϊ���ֽ���valueд��Ĳ���
    }
    
	public String GetKey() {
		return key;
	}//��ȡ�ļ���hashֵ

	public String Getvrtype() {return vrtype; }//��ȡ�ļ��ķ���Ȩ��

	public String GetType() {return type;}//��ȡ�ļ�������
    
}
