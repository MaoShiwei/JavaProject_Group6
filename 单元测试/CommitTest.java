package Project;

import Project.Commit;

public class CommitTest {

	public static void main(String[] args) throws Exception {
		//构建提交对象
		Commit commit = new Commit("D:\\Java\\managebase", "parent", "author", "committer", "comment");
		//获取key
		String key = commit.GetKey();
		System.out.println(key);
		//计算提交
		commit.ComputeCommit();
		//显示提交记录
		String showCommit = commit.ShowCommit();
		System.out.println(showCommit);

	}

}
