package gitsimulator;

public class HEAD extends Ref{
    private String path = "D:\\Java\\managebase\\HEAD";

    public HEAD() throws Exception {
        String value = "ref: D:\\Java\\managebase\\refs\\head\\master";
        Write(path,value);
    }

    public void updateHEAD(String branchpath) throws Exception {
        String value = "ref: " + branchpath;
        Write(path, value);
    }

    public String getHEAD(){
        String value = Readfile(path);
        return value;
    }
}
