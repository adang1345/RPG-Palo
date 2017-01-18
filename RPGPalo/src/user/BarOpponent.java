package user;

/** A class for an opponent in the bar. */
public class BarOpponent {
	private long hp; // hp, in 0..hpmax
	private long hpmax; // max hp, >=0

	/** Constructor: an opponent in short-range combat */
	public BarOpponent(Person p) {
		hp = p.getFightnum()*70 + Person.randInt(1,10);
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
		long damage = Person.randLong(1, hpmax) / 5 + 1; // initialize damage dealt
		damage = Math.min(p.getHp(), damage); // limit damage to no more than player's hp
		p.setHp(p.getHp() - damage); // deal the damage
	}

	/** Get the amount of money won from this opponent if this opponent is defeated. */
	public long winMoney(Person p) {
		long money = Person.randLong(1, hpmax/2);
		if (p.getDag() == 7) {
			money *= 10;
		}
		return money;
	}

}
