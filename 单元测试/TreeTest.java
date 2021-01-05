package Project;


import Project.Tree;

public class TreeTest {


	public static void main(String[] args) throws Exception {
		Tree tree = new Tree("D:\\Java\\managebase");
		tree.ComputeKey();
		System.out.println(tree.ComputeKey());
	}

}
