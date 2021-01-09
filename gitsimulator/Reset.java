package gitsimulator;

import java.io.File;

public class Reset extends Ref{//�̳���Ref
    private String objectpath = "D:\\Java\\managebase\\objects";//objectsĬ�ϴ洢·��

    public Reset(){}//��ʼ��reset

    public void Resetcommit(String commitkey, String resetpath) throws Exception {//��ȡ��commit��Ӧ��tree��hashֵ
        String path = objectpath + File.separator + commitkey;
        String str = Readfile(path);
        String[] s = str.split("\n");
        String[] value = s[0].split(" ");
        ResetObjects(value[1], resetpath);
    }

    public void ResetObjects(String objectkey, String resetpath) throws Exception {//����tree��hashֵ�������ļ��ָ�
        String objectstr = Readfile(objectpath + File.separator + objectkey);
        String[] objects = objectstr.split("\n");//ͨ�����з����tree�е�ÿһ���ļ����ļ�����Ϣ
        int a = objects.length;
        for (int i = 0; i < a; i++){
            String[] object = objects[i].split(" ");//ͨ���ո������Ϣ���н�һ�����֣�object[0]��ʾ�ļ�����Ȩ�ޣ�object[1]��ʾ�ļ����ͣ�object[2]��ʾ�ļ�hashֵ��object[3]��ʾ�ļ���
            if (object[1].equals("tree")){  //������equals�ж�
                File file = new File (resetpath + File.separator + object[3]);
                file.mkdirs();//����tree���ͣ������ļ������ɶ�Ӧ��·��
                ResetObjects(object[2], resetpath + File.separator + object[3]);//�ݹ�����ļ��ָ�
            }
            else if (object[1].equals("blob")){
                String path = resetpath + File.separator + object[3];
                WriteToFile(path, objectpath + File.separator + object[2]);//����blob���ͣ��ָ��ļ�
            }
        }
    }
}
