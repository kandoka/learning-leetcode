package structure;

public interface ITree {
	public Node add(int value);
	public Node search(int value);
	public void delete(int value);
	public String toString();
}
