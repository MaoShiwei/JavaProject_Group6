package gitsimulator;

import java.io.*;

public class Branch extends Ref{//继承自Ref
	
    private String BranchName;//branch名称
    private String path = "D:\\Java\\managebase\\refs\\head";//branch默认存储路径
    private String CommitID;//branch内容：最新一次commit的hash值


    public Branch(String BranchName, String CommitID) throws Exception {//构建branch
    	this.BranchName = BranchName;
    	this.CommitID =  CommitID;
    	writeCommitID();
    }

    public void updateBranch(String CommitID) throws Exception {//更新branch
        this.CommitID =  CommitID;
        writeCommitID();
    }
    
    public boolean IsBranchExist(String BranchName) {//判断branch是否存在
        File f = new File(path + File.separator + BranchName);
        return f.exists();
    }

    public void checkoutBranch(String BranchName, String workpath) throws Exception {//切换branch
        if (IsBranchExist(BranchName)) {
        	String branchpath = path + File.separator + BranchName;
            String commitID = Readfile(branchpath);
            Reset checkoutBranch = new Reset();
            checkoutBranch.Resetcommit(commitID, workpath);
        }
        else System.out.println("The branch doesn't exist");
    }
    

    public void writeCommitID() throws Exception {//将commit的hash值写入以branch名字命名的文件中
        Write(path + File.separator + BranchName,CommitID);
    }

    public String getBranchName(){
        return BranchName;
    }//获取branch名字
    
    public String getCommitId(){
        return CommitID;
    }//获取branch包含的commit的hash值

}
