package Project;

import Project.Blob;

public class BlobTest {

	public static void main(String[] args) throws Exception {
		Blob blob = new Blob("D:\\Java\\managebase\\content.txt");
		//将文件重新写入
		blob.write();
		//获取key
		String key = blob.GetKey();
		System.out.println(key);
	}

    
}
