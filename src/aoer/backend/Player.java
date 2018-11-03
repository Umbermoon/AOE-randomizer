package aoer.backend;
import aoer.information.*;

public class Player {
	private Civs assignedCiv;
	private String name;
	
	public Player(String name) {
		this.name = name;
		assignedCiv = null;
	}
	
	public Player() {
		name = null;
		assignedCiv = null;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setAssignedCiv(Civs civ) {
		assignedCiv = civ;
	}
	
	public String getName() {
		return name;
	}
	
	public Civs getAssignedCiv() {
		return assignedCiv;
	}
	
	public boolean isNull() {
		return name == null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assignedCiv == null) ? 0 : assignedCiv.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
