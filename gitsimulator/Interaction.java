package gitsimulator;

import java.io.File;
import java.util.Scanner;

public class Interaction {
    public static void main(String[] args) throws Exception{
        Scanner input = new Scanner(System.in);
        //unitTest.createUser();
        //System.out.println("git-hash:��ȡ�ļ����ļ��еĹ�ϣֵ��git-commit:�ɹ��ύcommit�����ɹ�ϣֵ��git-value:��ȡ�ļ����ļ������ݣ�git-log:�����ǰ��ȫ���ύ��¼��git-branch:���ɲ��л���֧;git-reset���ع���ָ��ID");
        System.out.println("�����뵱ǰ��Ŀ���ڹ���·����");
        String path = input.next();//��ȡ����ָ�����õ��ļ����ļ���
        while(path != null) {
            System.out.println("���������Ĳ�����");
            String command = input.next();//��ȡ�û���ָ��
            if(!command.equals("git-quit")) {
                //�����û������ָ����벻ͬ��if��֧��ɲ���
                if (command.equals("git-hash")) {
                    unitTest.testSingleKey(path);//git-hash����ļ����ļ��еĹ�ϣֵ
                } else if (command.equals("git-commit")) {
                    String commitkey = unitTest.testCommit(path);//git-commit����û��ɹ��ύcommit��commitID����ʾ
                } else if (command.equals("git-value")) {
                    unitTest.testGitObject(new File(path));//git-value����ļ������ݻ��ļ��е�Ŀ¼
                } else if (command.equals("git-branch")) {
                    unitTest.testBranch();
                }else if(command.equals("git-log")){
                    //System.out.println(commitID);
                    unitTest.testshowCommit();
                } else if (command.equals("git-reset")) {
                    System.out.println("��������Ҫ�ع���ID��");
                    String ID = input.next();//�û�����Ҫ�ع���commitID����ɻع�����
                    unitTest.testReset(ID, path);//����commit��hashֵ������reset����
                    System.out.println("�ѳɹ��ع�");
                } else {
                    System.out.println("��������ȷ��ָ��");//��ֹ�û��������ָ������û�
                    System.out.println("�����Գ�������git-hash,git-commit,git-value,git-branch,git-log,git-reset,git-quit�Ȳ�������");
                }
            }else if(command.equals("git-quit")){
                System.out.println("�����˳�����");
                break;
            }
        }
        input.close();

    }
}
