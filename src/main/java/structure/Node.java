package structure;

public class Node {
	private int value;
	private int location;
	private Node left;
	private Node right;
	
	public Node(int value) {
		super();
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	@Override
	public String toString() {
		String result = "Node [value=" + value + " location=" + location + "]";
		if(null != left) {
			result = result + " left:" + left.getValue();
		}
		if(null != right) {
			result = result + " right:" + right.getValue();
		}
		return result;
	}
	
}
