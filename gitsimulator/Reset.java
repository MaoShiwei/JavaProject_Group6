package gitsimulator;

import java.io.File;

public class Reset extends Ref{
    private String objectpath = "D:\\Java\\managebase\\objects";

    public Reset(){}

    public void Resetcommit(String commitkey, String resetpath) throws Exception {
        String path = objectpath + File.separator + commitkey;
        String str = Readfile(path);
        String[] s = str.split("\n");
        String[] value = s[0].split(" ");
        ResetObjects(value[1], resetpath);
    }

    public void ResetObjects(String objectkey, String resetpath) throws Exception {
        String objectstr = Readfile(objectpath + File.separator + objectkey);
        String[] objects = objectstr.split("\n");
        int a = objects.length;
        for (int i = 0; i < a; i++){
            String[] object = objects[i].split(" ");
            if (object[1].equals("tree")){  //必须用equals判定
                File file = new File (resetpath + File.separator + object[3]);
                file.mkdirs();
                ResetObjects(object[2], resetpath + File.separator + object[3]);
            }
            else if (object[1].equals("blob")){
                String path = resetpath + File.separator + object[3];
                WriteToFile(path, objectpath + File.separator + object[2]);
            }
        }
    }
}
