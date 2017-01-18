package user;

/** A class for an opponent in the Santa Fe gym. */
public class GymOpponent {
	private long hp; // hp, in 0..hpmax
	private long hpmax; // max hp, >=0

	/** Constructor: a gym opponent in short-range combat */
	public GymOpponent(Person p) {
		hp = p.getFightnum()*100 + Person.randInt(1,20);
		hpmax = hp;
	}

	/** Getter for hp */
	public long getHp() {
		return hp;
	}

	/** Setter for hp
	 * Precondition: hp is in 0..getHpmax() */
	public void setHp(long hp) {
		if (hp < 0 || hp > hpmax) throw new IllegalArgumentException();
		this.hp = hp;
	}

	/** Getter for max hp */
	public long getHpmax() {
		return hpmax;
	}

	/** Attack the player. */
	public void attack(Person p) {
		long damage = Person.randInt(1, 4)*Person.randLong(1, hpmax)/5+1; // initialize damage dealt
		damage = Math.min(p.getHp(), damage); // limit damage to no more than player's hp
		p.setHp(p.getHp() - damage); // deal the damage
	}

	/** Get the amount of money won from this opponent if this opponent is defeated. */
	public long winMoney(Person p) {
		return Person.randLong(1, hpmax*2);
	}

}
