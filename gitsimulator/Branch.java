package gitsimulator;

import java.io.*;

public class Branch extends Ref{
	
    private String BranchName = "master";
    private String path = "D:\\Java\\managebase\\refs\\head";
    private String CommitID;


    public Branch(String BranchName, String CommitID) throws Exception {
    	this.BranchName = BranchName;
    	this.CommitID =  CommitID;
    	writeCommitID();
    }
    
    public Branch(String BranchName) throws Exception {
        this.BranchName = BranchName;
        writeCommitID();
    }

    
    public boolean IsBranchExist(String BranchName) {
        File f = new File(path + File.separator + BranchName);
        return f.exists();
    }

    public void checkoutBranch(String BranchName) throws Exception {
        if (IsBranchExist(BranchName)) {
        	writeCommitID();
        }
        else System.out.println("The branch doesn't exist");
    }
    

    public void writeCommitID() throws Exception {
        Write(path + File.separator + BranchName,CommitID);
    }
    
    public String getBranchName(){
        return BranchName;
    }
    
    public String getCommitId(){
        return CommitID;
    }

}
