package gitsimulator;

import java.io.File;

public class Log extends Ref{
    private String commitlist = "";
    private String branchpath = "D:\\Java\\managebase\\refs\\head";
    private String objectpath = "D:\\Java\\managebase\\objects";

    public Log(){}

    public void showCommit(String branchname) throws Exception {
        String path = branchpath + File.separator + branchname;
        String commithash = Readfile(path);
        showCommitParent(commithash);
    }

    public void showCommitParent(String commithash) throws Exception {
        String path = objectpath + File.separator + commithash;
        String commit = Readfile(path);
        commitlist += commit + "\n";
        String[] commitcontent = commit.split("\n");
        String[] parentID = commitcontent[1].split(" ");
        if (parentID[1].equals("null")){
            commitlist = commitlist;
        }
        else{
            showCommitParent(parentID[1]);
        }
    }

    public String getCommitlist() {
        return commitlist;
    }
}
