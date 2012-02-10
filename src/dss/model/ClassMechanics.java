package dss.model;

public abstract class ClassMechanics {
	protected int ressource;
	protected int maxRessource;
	
	public ClassMechanics() {
	}
	
	public int getRessource() {
		return ressource;
	}
	
	public abstract void init ();
	
	public abstract boolean spendRessource(int amount);
	
	public abstract void regen ();

	public abstract void onAbilityUse();
	
	public abstract void onAttack();
}
