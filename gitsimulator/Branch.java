package gitsimulator;

import java.io.*;

public class Branch extends KeyValueStorage{
	
    private String BranchName = "master";
    private String path = "D:\\Java\\managebase\\refs\\head";
    private String CommitID;


    public Branch(String BranchName, String CommitID) throws IOException {
    	this.BranchName = BranchName;
    	this.CommitID =  CommitID;
    	writeCommitID();
    }
    
    public Branch(String BranchName) throws Exception {
        this.BranchName = BranchName;
        CommitID = super.GetValue(BranchName);
        writeCommitID();
    }

    
    public boolean IsBranchExist(String BranchName) {
        File f = new File(path + File.separator + BranchName);
        return f.exists();
    }

    public void checkoutBranch(String BranchName) throws Exception {
        if (IsBranchExist(BranchName)) {
        	this.CommitID = super.GetValue(BranchName);
        	writeCommitID();
        }
        else System.out.println("The branch doesn't exist");
    }
    

    public void writeCommitID() throws IOException {
        File f = new File(path + File.separator + BranchName);
        FileWriter fw = new FileWriter(f);
        fw.write(CommitID);
        fw.close();
    }
    
    public String getBranchName(){
        return BranchName;
    }
    
    public String getCommitId(){
        return CommitID;
    }

}
