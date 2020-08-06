package structure;

public class HashTabDemo {
	public static void main(String[] args) {
		
	}
}

class HashTab{
	private LinkedEmp[] linkedEmps;
	public HashTab(int size) {
		super();
		this.linkedEmps = new LinkedEmp[size];
		for(int i = 0; i < size; i++) {
			linkedEmps[i] = new LinkedEmp();
		}
	}
	
	public void add(Emp emp) {
		int no = getHash(emp.id);
		linkedEmps[no].add(emp);
	}
	
	private int getHash(int id) {
		return id % linkedEmps.length;
	}
}

class LinkedEmp {
	private Emp head;
	public void add(Emp emp) {		
		Emp cur = head;
		while(cur != null) {
			cur = cur.next;
		}
		cur = emp;
	}
	
	public void list(int no) {
		Emp cur = head;
		if(cur == null) {
			System.out.println("第 "+(no)+" 链表为空");
			return;
		}
		while(cur != null) {
			System.out.printf(" => id=%d name=%s\t", cur.id, cur.name);
			cur = cur.next;
		}
		System.out.println();
	}
}

class Emp {
	public int id;
	public int name;
	public Emp next;
	public Emp(int id, int name) {
		super();
		this.id = id;
		this.name = name;
	}
}
