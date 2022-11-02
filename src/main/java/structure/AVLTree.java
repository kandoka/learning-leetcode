package structure;

public class AVLTree extends BinarySearchTree{

	public AVLTree(int[] arr) {
		super(arr);
		// TODO Auto-generated constructor stub
	}
	
	public int getHeight(Node cur) {
		int leftCount = 0;
		int rightCount = 0;
		//System.out.println("当前节点=" + cur.getValue());
		if(null == cur) {
			return 0;
		}
		if(null == cur.getLeft() && null == cur.getRight()) {
			//System.out.println("叶子节点=" + cur.getValue());
			return 0;
		}
		if(null != cur.getLeft()) {
			leftCount = getHeight(cur.getLeft());
		}
		if(null != cur.getRight()) {
			rightCount = getHeight(cur.getRight()); 
		}
		return Math.max(leftCount, rightCount) + 1;
	}

	public void leftRotate(Node curNode) {
		//创建新节点
		Node newNode = new Node(curNode.getValue());
		newNode.setLocation(curNode.getLocation() + 1);
		//将新节点的左右子节点分别指向当前节点的左子节点和右子节点的左子节点
		newNode.setLeft(curNode.getLeft());
		newNode.setRight(curNode.getRight().getLeft());
		//把当前节点的值替换成其右子节点的值
		curNode.setValue(curNode.getRight().getValue());
		//把当前节点的左右子节点分别指向新节点和当前节点的右子节点的右子节点
		curNode.setLeft(newNode);
		curNode.setRight(curNode.getRight().getRight());
	}
	
	public void rightRotate(Node curNode) {
		//创建新节点
		Node newNode = new Node(curNode.getValue());
		newNode.setLocation(curNode.getLocation() + 1);
		//将新节点的左右子节点分别指向当前节点的左子节点的右子节点和右子节点
		newNode.setRight(curNode.getRight());
		newNode.setLeft(curNode.getLeft().getRight());
		//把当前节点的值替换成其左子节点的值
		curNode.setValue(curNode.getLeft().getValue());
		//把当前节点的左右子节点分别指向当前节点的左子节点的左子节点和新节点
		curNode.setRight(newNode);
		curNode.setLeft(curNode.getLeft().getLeft());
	}
	
	@Override
	public Node add(int value) {
		Node grandParent = super.add(value);
		if(getHeight(getRoot().getLeft()) - getHeight(getRoot().getRight()) > 1) {//左子树高两层及以上
			if(null != getRoot().getLeft() && //有左子树且左子节点的右子树较高
					getHeight(getRoot().getLeft().getRight()) > getHeight(getRoot().getLeft().getLeft())) {
				leftRotate(getRoot().getLeft());
			}
			rightRotate(getRoot());
		}
		else if(getHeight(getRoot().getRight()) - getHeight(getRoot().getLeft()) > 1) {//右子树高两层及以上
			if(null != getRoot().getRight() && //有右子树且右子节点的左子树较高
					getHeight(getRoot().getRight().getLeft()) > getHeight(getRoot().getRight().getRight())) {
				rightRotate(getRoot().getRight());
			}
			leftRotate(getRoot());
		}
		return grandParent;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 10, 11, 7, 6, 8, 9 };
		AVLTree avlTree = new AVLTree(arr);
		System.out.println(avlTree);
		//avlTree.rightRotate(avlTree.getRoot());
		System.out.println("树的高度" + avlTree.getHeight(avlTree.getRoot()));
		System.out.println("树的左子树高度" + avlTree.getHeight(avlTree.getRoot().getLeft()));
		System.out.println("树的右子树高度" + avlTree.getHeight(avlTree.getRoot().getRight()));
	}
}
