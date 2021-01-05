package Project;

import Project.keyValueStorage;
import java.io.File;
import java.io.FileInputStream;
public class keyValueStorageTest {

	public static void main(String[] args) throws Exception {
		keyValueStorage keyValueStorage = new keyValueStorage();
		//获取文件key
		String key = keyValueStorage.GetKey(new FileInputStream(new File("D:\\Java\\managebase\\content.txt")));
		//将文件重新写入 用于下面GetValue 获取到文件
		keyValueStorage.WriteToFile("D:\\Java\\managebase\\content.txt");
		//通过key获取文件
		String s = keyValueStorage.GetValue(key);
		//打印文件内容
		System.out.println(s);
	}

}
