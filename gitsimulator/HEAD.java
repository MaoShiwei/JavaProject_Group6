package gitsimulator;

public class HEAD extends Ref{
    private String path = "D:\\Java\\managebase\\HEAD";//HEADĬ�ϴ洢·��
    private String branchpath = "D:\\Java\\managebase\\refs\\head\\";//branchĬ�ϴ洢·��

    public HEAD() throws Exception {//��ʼ��HEAD�ļ�
        String value = "ref: " + branchpath + "master";
        Write(path,value);
    }

    public void updateHEAD(String branchname) throws Exception {//����HEAD�ļ�
        String value = "ref: " + branchpath + branchname;
        Write(path, value);
    }

    public String getHEAD(){//��ȡHEADָ��ķ�֧·��
        String value = Readfile(path);
        return value;
    }
}
