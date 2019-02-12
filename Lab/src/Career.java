import java.util.ArrayList;
import java.util.List;

public class Career {

	private int experience;
	private List<Career> parent;
	private List<Career> children;
	
	public Career() {
		super();
		this.parent = new ArrayList<>();
	}
	
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public List<Career> getParent() {
		return parent;
	}
	public void setParent(List<Career> parent) {
		this.parent = parent;
	}
	public List<Career> getChildren() {
		return children;
	}
	public void setChildren(List<Career> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Career [experience=" + experience + ", parent=" + parent + ", children=" + children + "]";
	}
	
}
