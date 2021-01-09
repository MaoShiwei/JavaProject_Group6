package gitsimulator;

import java.io.File;

public class Reset extends Ref{//继承自Ref
    private String objectpath = "D:\\Java\\managebase\\objects";//objects默认存储路径

    public Reset(){}//初始化reset

    public void Resetcommit(String commitkey, String resetpath) throws Exception {//读取到commit对应的tree的hash值
        String path = objectpath + File.separator + commitkey;
        String str = Readfile(path);
        String[] s = str.split("\n");
        String[] value = s[0].split(" ");
        ResetObjects(value[1], resetpath);
    }

    public void ResetObjects(String objectkey, String resetpath) throws Exception {//根据tree的hash值，进行文件恢复
        String objectstr = Readfile(objectpath + File.separator + objectkey);
        String[] objects = objectstr.split("\n");//通过换行符获得tree中的每一个文件或文件夹信息
        int a = objects.length;
        for (int i = 0; i < a; i++){
            String[] object = objects[i].split(" ");//通过空格符对信息进行进一步划分，object[0]表示文件访问权限，object[1]表示文件类型，object[2]表示文件hash值，object[3]表示文件名
            if (object[1].equals("tree")){  //必须用equals判定
                File file = new File (resetpath + File.separator + object[3]);
                file.mkdirs();//对于tree类型，根据文件夹生成对应的路径
                ResetObjects(object[2], resetpath + File.separator + object[3]);//递归调用文件恢复
            }
            else if (object[1].equals("blob")){
                String path = resetpath + File.separator + object[3];
                WriteToFile(path, objectpath + File.separator + object[2]);//对于blob类型，恢复文件
            }
        }
    }
}
