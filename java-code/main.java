
public class main {

	
	public static void main(String[] args){
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		BinarySearchTree<String> tree2 = new BinarySearchTree<String>();
		
		tree.add(5);
//		tree.add(1);
				
		System.out.println(tree.toString());
		System.out.println(tree.size());

		System.out.println(tree.toString());
		System.out.println(tree.depth());

		
	}
}
