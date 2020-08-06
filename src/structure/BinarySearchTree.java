package structure;

public class BinarySearchTree implements ITree{
	private Node root;
	public BinarySearchTree(int[] arr) {
		this.root = null;
		if(arr.length > 0) {
			this.root = new Node(arr[0]);
			this.getRoot().setLocation(0);
			for(int i=1; i<arr.length; i++) {
				add(arr[i]);
			}
		}
	}

	/**
	 * @return 新添加节点的祖父节点
	 */
	@Override
	public Node add(int value) {
		if(this.getRoot() == null) {
			System.out.println("根节点为空，请先初始化树");
			return null;
		}
		Node grandParent = null;
		Node temp = this.getRoot();
		while(null != temp) {
			if(value < temp.getValue()) {
				if(null == temp.getLeft()) {
					Node left = new Node(value);
					left.setLocation(temp.getLocation() + 1);
					temp.setLeft(left);
					break;
				}
				else {
					grandParent = temp;
					temp = temp.getLeft();
				}
			}
			else {
				if(null == temp.getRight()) {
					Node right = new Node(value);
					right.setLocation(temp.getLocation() + 1);
					temp.setRight(right);
					break;
				}
				else {
					grandParent = temp;
					temp = temp.getRight();
				}
			}
		}
		return grandParent;
	}

	@Override
	public Node search(int value) {
		if(null == this.getRoot()) {
			System.out.println("根节点为空，请先初始化树");
			return null;
		}		
		return searchByValue(value, this.getRoot());
	}

	@Override
	public void delete(int value) {
		//找到要删除的节点
		Node nodeToDelete = search(value);
		if(null == nodeToDelete) {//如果没有找到这个节点
			System.out.println("没有找到节点");
			return;
		}
		Node parent = searchForParent(value, this.getRoot());
			
		//分3种情况讨论
		if(null == nodeToDelete.getLeft() && null == nodeToDelete.getRight()) {//目标节点是叶子节点
			if(null == parent) {//没有父节点，说明是根节点；
				this.root = null;
				return;
			}
			if(null != parent.getLeft() && nodeToDelete == parent.getLeft()) {
				parent.setLeft(null);
			}
			else if(null != parent.getRight() && nodeToDelete == parent.getRight()) {
				parent.setRight(null);
			}
		}
		else if(null != nodeToDelete.getLeft() && null != nodeToDelete.getRight()) {//目标节点有左右子节点
			Node min = searchMin(nodeToDelete.getRight());
			int minValue = min.getValue();
			delete(min.getValue());
			nodeToDelete.setValue(minValue);
		}
		else {//目标节点有一颗子树
			if(null != nodeToDelete.getLeft()) {//目标节点有左子树
				if(null == parent) {//没有父节点，说明是根节点；
					this.root = nodeToDelete.getLeft();
					return;
				}
				if(nodeToDelete == parent.getLeft()) {//接到父节点的左节点上
					parent.setLeft(nodeToDelete.getLeft());
				}
				else {//接到父节点的右节点上
					parent.setRight(nodeToDelete.getLeft());
				}
			}
			else {//目标节点有右子树
				if(null == parent) {//没有父节点，说明是根节点；
					this.root = nodeToDelete.getRight();
					return;
				}
				if(nodeToDelete == parent.getLeft()) {//接到父节点的左节点上
					parent.setLeft(nodeToDelete.getRight());
				}
				else {//接到父节点的右节点上
					parent.setRight(nodeToDelete.getRight());
				}
			}
		}
	}

	@Override
	public String toString() {
		if(null == this.getRoot()) {
			return "根节点为空，请先初始化树";
		}
		System.out.println("=============树==================");
		infixOrder(this.getRoot());
		return "=======================================";		
	}	
	
	private Node searchByValue(int value, Node cur) {
		if(value == cur.getValue()) {//找到
			return cur;
		}
		else if(value < cur.getValue()) {//目标值小于当前节点
			if(null == cur.getLeft()) {//没有左子节点
				return null;
			}
			return searchByValue(value, cur.getLeft());
		}
		else {//目标值大于或等于当前节点
			if(null == cur.getRight()) {//没有右子节点
				return null;
			}
			return searchByValue(value, cur.getRight());
		}
	}
	
	private Node searchForParent(int value, Node cur) {
		if((null != cur.getLeft() && value == cur.getLeft().getValue()) ||
				(null != cur.getRight() && value == cur.getRight().getValue())) {
			return cur;
		}
		else {
			if(value < cur.getValue() && null != cur.getLeft()) {
				return searchForParent(value, cur.getLeft());
			}
			else if(value >= cur.getValue() && null != cur.getRight()) {
				return searchForParent(value, cur.getRight());
			}
			else {
				return null;
			}
		}
	}
	
	private Node searchMin(Node cur) {
		while(null != cur.getLeft()) {
			cur = cur.getLeft();
		}
		return cur;
	}
	
	private void infixOrder(Node cur) {
		if(null != cur.getLeft()) {
			infixOrder(cur.getLeft());
		}
		System.out.println(cur);
		if(null != cur.getRight()) {
			infixOrder(cur.getRight());
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
		BinarySearchTree bst = new BinarySearchTree(arr);
		//System.out.println(bst.searchForParent(10, bst.root));
		bst.delete(10);
		//System.out.println("搜索结果=" + bst.search(1));
		bst.toString();
	}
	public Node getRoot() {
		return root;
	}
}
