package gitsimulator;

import java.io.*;

public class Branch extends Ref{//�̳���Ref
	
    private String BranchName;//branch����
    private String path = "D:\\Java\\managebase\\refs\\head";//branchĬ�ϴ洢·��
    private String CommitID;//branch���ݣ�����һ��commit��hashֵ


    public Branch(String BranchName, String CommitID) throws Exception {//����branch
    	this.BranchName = BranchName;
    	this.CommitID =  CommitID;
    	writeCommitID();
    }

    public void updateBranch(String CommitID) throws Exception {//����branch
        this.CommitID =  CommitID;
        writeCommitID();
    }
    
    public boolean IsBranchExist(String BranchName) {//�ж�branch�Ƿ����
        File f = new File(path + File.separator + BranchName);
        return f.exists();
    }

    public void checkoutBranch(String BranchName, String workpath) throws Exception {//�л�branch
        if (IsBranchExist(BranchName)) {
        	String branchpath = path + File.separator + BranchName;
            String commitID = Readfile(branchpath);
            Reset checkoutBranch = new Reset();
            checkoutBranch.Resetcommit(commitID, workpath);
        }
        else System.out.println("The branch doesn't exist");
    }
    

    public void writeCommitID() throws Exception {//��commit��hashֵд����branch�����������ļ���
        Write(path + File.separator + BranchName,CommitID);
    }

    public String getBranchName(){
        return BranchName;
    }//��ȡbranch����
    
    public String getCommitId(){
        return CommitID;
    }//��ȡbranch������commit��hashֵ

}
