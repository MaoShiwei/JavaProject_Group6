package gitsimulator;

public class HEAD extends Ref{
    private String path = "D:\\Java\\managebase\\HEAD";//HEAD默认存储路径
    private String branchpath = "D:\\Java\\managebase\\refs\\head\\";//branch默认存储路径

    public HEAD() throws Exception {//初始化HEAD文件
        String value = "ref: " + branchpath + "master";
        Write(path,value);
    }

    public void updateHEAD(String branchname) throws Exception {//更新HEAD文件
        String value = "ref: " + branchpath + branchname;
        Write(path, value);
    }

    public String getHEAD(){//获取HEAD指向的分支路径
        String value = Readfile(path);
        return value;
    }
}
