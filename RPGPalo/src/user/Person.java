package user;

import java.io.Serializable;
import java.util.Random;
import javax.swing.JLabel;

import gui.ChoiceDialog;
import gui.Menu;
import gui.MessageDialog;
import gui.RPG;

/** An instance represents a person who is the avatar of the player. */
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String[] DRUG_PLACES = new String[] {"Australia", "New Zealand",
			"Bahamas", "Bermuda", "Canada", "Costa Rica", "Cuba", "El Salvador",
			"Guatemala", "Haiti", "Honduras", "Jamaica", "Mexico", "Argentina",
			"Brazil", "Chile", "Ecuador", "Paraguay", "Peru", "Uruguay",
			"Venezuela", "Albania", "Austria", "Denmark", "Finland", "France",
			"Germany", "Greece", "Hungary", "Iceland", "Ireland", "Italy",
			"Netherlands", "Poland", "Portugal", "Romania", "Russia", "Spain",
			"Sweden", "Switzerland", "Ukraine", "UK", "Chad", "Egypt", "Kenya",
			"Liberia", "Madagascar", "Morocco", "Niger", "Nigeria", "Sudan",
			"Zimbabwe", "Afghanistan", "China", "India", "Iran", "Iraq",
			"Israel", "Japan", "N. Korea", "S. Korea", "Mongolia", "Nepal",
			"Pakistan", "Phillipines", "Russia", "Saudi Arabia", "Taiwan",
			"Thailand", "Vietnam"}; // potential places to go for drug dealing
	public static final int CAFFEINE_HP_LOSS = 10; // hp loss for using 1 caffeine pill
	public static final int BED0_HP_GAIN = 20; // hp gain for sleeping in type 0 bed
	public static final int BED1_HP_GAIN = 30; // hp gain for sleeping in type 1 bed
	public static final int DEFAULT_HOUR = 16; // default number of hours in full day
	public static final int CAFFEINE_HOUR = 24; // number of hours in full day after caffeine use
	public static final int TIMEWARP_HOUR = 100; // number of hours in full day after time warp use
	public static final int MAX_LOAN_INTEREST_RATE = 7; // max percent interest per day on a loan
	public static final int STOCK_MAX_CHANGE = 5; // max change in stock price per day
	public static final int STOCK_MAX_PRICE = 1000; // max price of a stock
	public static final int STOCK_MIN_PRICE = 1; // min price of a stock
	public static final int STOCK_MAX_START_PRICE = 50; // max starting price of stock
	public static final int MAX_BANK_INTEREST_RATE = 2; // max percent interest per day for bank
	public static final int INTELLIGENCE_GAIN_BOOKCASE = 1; // daily intelligence gain from bookcase
	public static final int HPMAX_GAIN_TREADMILL = 1; // daily hpmax gain from treadmill
	public static final int MAX_LOANDAY = 20; // max days to pay off loan
	public static final int MIN_LOAN = 100; // min loan that can be taken from bank
	public static final int MAX_LOAN = 1000000; // largest loan that can be taken from bank
	public static final int NEW_ID_MAX_BANK = 2000; // max bank after ID theft
	public static final int NEW_ID_MAX_MONEY = 1000; // max money after ID theft
	public static final int TV_BILL_TIMES = 90; // number of TV half-hour watches before TV bill must be paid
	public static final int TV_SMART_INCREASE = 3; // intelligence increase after 1 news watch on TV
	public static final int TV_SMART_EXTRA_INCREASE = 7; // bonus intelligence increase after 1 special news watch on TV
	public static final int TV_HPMAX_INCREASE = 3; // hpmax increase after half-hour of watching fitness channel on TV
	public static final int TV_HPMAX_EXTRA_INCREASE = 7; // bonus hpmax increase half hour of fitness channel
	public static final int CARTOON_LOSS = 20; // intelligence lost after watching cartoon
	public static final int NEW_GUN_PRICE = 200; // price of new gun
	public static final int UPGRADE_GUN_PRICE = 400; // cost of upgrading gun
	public static final int BULLET_PRICE = 4; // price of each bullet
	public static final int WOOD_DAG_PRICE = 100; // price of wooden dagger
	public static final int BRONZE_DAG_PRICE = 500; // price of bronze dagger
	public static final int IRON_DAG_PRICE = 1000; // price of iron dagger
	public static final int STEEL_DAG_PRICE = 3000; // price of steel dagger
	public static final int SILVER_DAG_PRICE = 500000; // price of silver dagger
	public static final int GOLD_DAG_PRICE = 2000000; // price of silver dagger
	public static final long DIVINE_DAG_PRICE = 1000000000000L; // price of silver dagger
	public static final int FAT_FOODS_SALARY = 8; // amount earned per hour at Fat Foods
	// prices and HP gains for purchases in Fat Foods are in menus.FatFoodsM.java
	public static final int HOME_UPGRADE_PRICE = 500; // cost of upgrading home
	public static final int BED_PRICE = 200; // cost of bed
	public static final int TV_PRICE = 500; // cost of TV
	public static final int COMPUTER_PRICE = 700; // cost of computer
	public static final int TREADMILL_PRICE = 700; // cost of treadmill
	public static final int BOOKCASE_PRICE = 350; // cost of bookcase
	public static final int BEER_PRICE = 10; // price of beer
	public static final int BEER_GAIN = 20; // hp gain from beer
	public static final int CAFFEINE_PRICE = 50; // cost of caffeine pill
	public static final int LOTTERY_PRICE = 10; // cost of lottery ticket
	public static final int POWER2_WIN = 500; // monetary gain for winning Power 2
	public static final int POWER3_WIN = 5000; // monetary gain for winning Power 3
	public static final int CASH5_WIN1 = 180; // monetary gain for getting at least 1 winning number in Cash 5
	public static final long CASH5_WIN5 = 1000000000000000L; // monetary gain for getting 5 winning numbers in Cash 5
	public static final int COLLEGE_CLASS_PRICE = 20; // price of taking class in college for an hour
	public static final int GYM_CLASS_PRICE = 20; // price of taking gym class for an hour
	public static final int DD_CLUB_PRICE = 10000; // price of joining DD Club
	public static final int HEROIN_PRICE = 300; // price of 1g heroin
	public static final int CRACK_PRICE = 500; // price of 1g heroin
	public static final int BEER_BOTTLE_PRICE = 50; // price of bottle of beer
	public static final int CHICAGO_PRICE = 500; // price of going to Chicago
	// menus.ChicagoMallM.java contains constants pertaining to the mall in Chicago
	public static final int VEGAS_PRICE = 1600; // price of going to Las Vegas
	public static final int BOSTON_PRICE = 200; // price of going to Boston
	// menus.HarVerdM.java contains constants pertaining to Har-Verd
	public static final int SANTA_FE_PRICE = 1400; // price of going to Santa Fe

	private long bank; // amount of money in the bank, >=0
	private long money; // amount of cash in pocket, >=0
	private long day; // current day, >=0
	private double hour; // number of hours left in day, >=0
	private long hp; // hitpoints, >= 0
	private long hpmax; // max hitpoints, >=0
	private long loan; // amount of money owed to bank, >=0
	private int gun; // type of gun owned, in 0..5
	private long bullet; // number of bullets owned, >=0
	private int robnum; // number of times this person has successfully robbed, >=0
	private long smart; // intelligence level
	private int job; // job level, >=0
	private int fightnum; // number of times this person has fought, >=0
	private int hupgrade; // home level owned, in 0..3
	private int bed; // bed level owned, in 0..2
	private int tv; // TV level owned, in 0..2
	private boolean com; // whether computer is owned
	private int aij; // price of AIJ stock, >0
	private int dce; // price of DCE stock, >0
	private int nid; // price of NID stock, >0
	private int abc; // price of ABC stock, >0
	private long baij; // number of AIJ stocks owned, >=0
	private long bdce; // number of DCE stocks owned, >=0
	private long bnid; // number of NID stocks owned, >=0
	private long babc; // number of ABC stocks owned, >=0
	private int loanday; // number of days since a loan was taken out, 0 if no loan
	private boolean bjail; // whether this person has been to jail
	private int tvt; // number of times TV was watched since last paid TV bill, >=0
	private long beer; // amount of beer owned, >=0
	private long crack; // amount of crack owned, >=0
	private long heroin; // amount of heroin owned, >=0
	private boolean club; // whether this person is in the drug deal club
	private boolean time; // whether time warp is owned
	private int hjob; // job level at Harvard, in 0..6
	private boolean tread; // whether treadmill is owned
	private boolean book; // whether bookcase is owned
	private int dag; // level of dagger, in 0..7
	private long caffeine; // amount of caffeine owned, >=0

	/** Return a random integer in min..max.
	 * If max is negative (perhaps due to an overflow), then it is replaced with
	 * the max integer value. */
	public static int randInt(int min, int max) {
		if (max < 0) max = Integer.MAX_VALUE;
		return (new Random()).nextInt(max - min + 1) + min;
	}

	/** Return a random long in min..max.
	 * If max is negative (perhaps due to an overflow), then it is replaced with
	 * the max long value. */
	public static long randLong(long min, long max) {
		if (max < 0) max = Long.MAX_VALUE;
		Random r = new Random();
		long number = min + ((long) (r.nextDouble()*(max - min + 1)));
		return number;
	}

	/** Constructor: a new Person just starting the game */
	public Person() {
		bank = 0;
		money = 100;
		day = 1;
		hour = 16;
		hp = 100;
		hpmax = 100;
		loan = 0;
		gun = 0;
		bullet = 0;
		robnum = 0;
		smart = 0;
		job = 0;
		fightnum = 0;
		hupgrade = 0;
		bed = 0;
		tv = 0;
		com = false;
		aij = randInt(STOCK_MIN_PRICE, STOCK_MAX_START_PRICE);
		dce = randInt(STOCK_MIN_PRICE, STOCK_MAX_START_PRICE);
		nid = randInt(STOCK_MIN_PRICE, STOCK_MAX_START_PRICE);
		abc = randInt(STOCK_MIN_PRICE, STOCK_MAX_START_PRICE);
		baij = 0;
		bdce = 0;
		bnid = 0;
		babc = 0;
		loanday = 0;
		bjail = false;
		tvt = 0;
		beer = 0;
		crack = 0;
		heroin = 0;
		club = false;
		time = false;
		hjob = 0;
		tread = false;
		book = false;
		dag = 0;
		caffeine = 0;
	}

	/** Getter for amount of money in the bank. */
	public long getBank() {
		return bank;
	}

	/** Getter for amount of cash in pocket. */
	public long getMoney() {
		return money;
	}

	/** Getter for current day. */
	public long getDay() {
		return day;
	}

	/** Getter for number of hours left in day. */
	public double getHour() {
		return hour;
	}

	/** Getter for hitpoints. */
	public long getHp() {
		return hp;
	}

	/** Getter for max hitpoints. */
	public long getHpmax() {
		return hpmax;
	}

	/** Getter for amount of money owed to bank. */
	public long getLoan() {
		return loan;
	}

	/** Getter for type of gun owned. */
	public int getGun() {
		return gun;
	}

	/** Getter for number of bullets owned. */
	public long getBullet() {
		return bullet;
	}

	/** Getter for number of times this person has robbed successfully. */
	public int getRobnum() {
		return robnum;
	}

	/** Getter for intelligence level. */
	public long getSmart() {
		return smart;
	}

	/** Getter for job level. */
	public int getJob() {
		return job;
	}

	/** Getter for number of times this person has fought. */
	public int getFightnum() {
		return fightnum;
	}

	/** Getter for home level owned. */
	public int getHupgrade() {
		return hupgrade;
	}

	/** Getter for bed level owned. */
	public int getBed() {
		return bed;
	}

	/** Getter for TV level owned. */
	public int getTv() {
		return tv;
	}

	/** Getter for whether computer is owned. */
	public boolean getCom() {
		return com;
	}

	/** Getter for price of AIJ stock. */
	public int getAij() {
		return aij;
	}

	/** Getter for price of DCE stock. */
	public int getDce() {
		return dce;
	}

	/** Getter for price of NID stock. */
	public int getNid() {
		return nid;
	}

	/** Getter for price of ABC stock. */
	public int getAbc() {
		return abc;
	}

	/** Getter for number of AIJ stocks owned. */
	public long getBaij() {
		return baij;
	}

	/** Getter for number of DCE stocks owned. */
	public long getBdce() {
		return bdce;
	}

	/** Getter for number of NID stocks owned. */
	public long getBnid() {
		return bnid;
	}

	/** Getter for number of ABC stocks owned. */
	public long getBabc() {
		return babc;
	}

	/** Getter for number of days since a loan was taken out. */
	public int getLoanday() {
		return loanday;
	}

	/** Getter for whether this person has been to jail. */
	public boolean getBjail() {
		return bjail;
	}

	/** Getter for number of times TV was watched since last paid TV bill. */
	public int getTvt() {
		return tvt;
	}

	/** Getter for amount of beer owned. */
	public long getBeer() {
		return beer;
	}

	/** Getter for amount of crack owned. */
	public long getCrack() {
		return crack;
	}

	/** Getter for amount of heroin owned. */
	public long getHeroin() {
		return heroin;
	}

	/** Getter for whether this person is in the drug deal club. */
	public boolean getClub() {
		return club;
	}

	/** Getter for whether time warp is owned. */
	public boolean getTime() {
		return time;
	}

	/** Getter for job level at Harvard. */
	public int getHjob() {
		return hjob;
	}

	/** Getter for whether treadmill is owned. */
	public boolean getTread() {
		return tread;
	}

	/** Getter for whether bookcase is owned. */
	public boolean getBook() {
		return book;
	}

	/** Getter for level of dagger. */
	public int getDag() {
		return dag;
	}

	/** Getter for amount of caffeine owned. */
	public long getCaffeine() {
		return caffeine;
	}

	/** Setter for amount of money in the bank.
	 * Precondition: bank >= 0 */
	public void setBank(long bank) {
		if (bank < 0) throw new IllegalArgumentException();
		this.bank = bank;
	}

	/** Setter for amount of cash in pocket.
	 * Precondition: money >= 0 */
	public void setMoney(long money) {
		if (money < 0) throw new IllegalArgumentException();
		this.money = money;
	}

	/** Setter for current day.
	 * Precondition: day >= 0 */
	public void setDay(long day) {
		if (day < 0) throw new IllegalArgumentException();
		this.day = day;
	}

	/** Setter for number of hours left in day.
	 * Precondition: hour >= 0 */
	public void setHour(double hour) {
		if (hour < 0) throw new IllegalArgumentException();
		this.hour = hour;
	}

	/** Setter for hitpoints.
	 * Precondition: hp >= 0 */
	public void setHp(long hp) {
		if (hp < 0) throw new IllegalArgumentException();
		this.hp = hp;
	}

	/** Setter for max hitpoints.
	 * Precondition: hpmax > 0 */
	public void setHpmax(long hpmax) {
		if (hpmax <= 0) throw new IllegalArgumentException();
		this.hpmax = hpmax;
	}

	/** Setter for amount of money owed to bank.
	 * Precondition: loan >= 0 */
	public void setLoan(long loan) {
		if (loan < 0) throw new IllegalArgumentException();
		this.loan = loan;
	}

	/** Setter for type of gun owned.
	 * Precondition: gun is in 0..5 */
	public void setGun(int gun) {
		if (gun < 0 || gun > 5) throw new IllegalArgumentException();
		this.gun = gun;
	}

	/** Setter for number of bullets owned.
	 * Precondition: bullet >= 0 */
	public void setBullet(long bullet) {
		if (bullet < 0) throw new IllegalArgumentException();
		this.bullet = bullet;
	}

	/** Setter for number of times this person has successfully robbed.
	 * Precondition: robnum >= 0 */
	public void setRobnum(int robnum) {
		if (robnum < 0) throw new IllegalArgumentException();
		this.robnum = robnum;
	}

	/** Setter for intelligence level. */
	public void setSmart(long smart) {
		this.smart = smart;
	}

	/** Setter for job level.
	 * Precondition: job >= 0 */
	public void setJob(int job) {
		if (job < 0) throw new IllegalArgumentException();
		this.job = job;
	}

	/** Setter for number of times this person has fought.
	 * Precondition: fightnum >= 0 */
	public void setFightnum(int fightnum) {
		if (fightnum < 0) throw new IllegalArgumentException();
		this.fightnum = fightnum;
	}

	/** Setter for home level owned.
	 * Precondition: hupgrade is in 0..3 */
	public void setHupgrade(int hupgrade) {
		if (hupgrade < 0 || hupgrade > 3) throw new IllegalArgumentException();
		this.hupgrade = hupgrade;
	}

	/** Setter for bed level owned.
	 * Precondition: bed is in 0..2 */
	public void setBed(int bed) {
		if (bed < 0 || bed > 2) throw new IllegalArgumentException();
		this.bed = bed;
	}

	/** Setter for TV level owned.
	 * Precondition: tv is in 0..2 */
	public void setTv(int tv) {
		if (tv < 0 || tv > 2) throw new IllegalArgumentException();
		this.tv = tv;
	}

	/** Setter for whether computer is owned. */
	public void setCom(boolean com) {
		this.com = com;
	}

	/** Setter for price of AIJ stock.
	 * Precondition: aij is in STOCK_MIN_PRICE..STOCK_MAX_PRICE */
	public void setAij(int aij) {
		if (aij < STOCK_MIN_PRICE || aij > STOCK_MAX_PRICE) throw new IllegalArgumentException();
		this.aij = aij;
	}

	/** Setter for price of DCE stock.
	 * Precondition: dce is in STOCK_MIN_PRICE..STOCK_MAX_PRICE */
	public void setDce(int dce) {
		if (dce < STOCK_MIN_PRICE || dce > STOCK_MAX_PRICE) throw new IllegalArgumentException();
		this.dce = dce;
	}

	/** Setter for price of NID stock.
	 * Precondition: nid is in STOCK_MIN_PRICE..STOCK_MAX_PRICE */
	public void setNid(int nid) {
		if (nid < STOCK_MIN_PRICE || nid > STOCK_MAX_PRICE) throw new IllegalArgumentException();
		this.nid = nid;
	}

	/** Setter for price of ABC stock.
	 * Precondition: abc is in STOCK_MIN_PRICE..STOCK_MAX_PRICE */
	public void setAbc(int abc) {
		if (abc < STOCK_MIN_PRICE || abc > STOCK_MAX_PRICE) throw new IllegalArgumentException();
		this.abc = abc;
	}

	/** Setter for number of AIJ stocks owned.
	 * Precondition: baij >= 0 */
	public void setBaij(long baij) {
		if (baij < 0) throw new IllegalArgumentException();
		this.baij = baij;
	}

	/** Setter for number of DCE stocks owned.
	 * Precondition: bdce >= 0 */
	public void setBdce(long bdce) {
		if (bdce < 0) throw new IllegalArgumentException();
		this.bdce = bdce;
	}

	/** Setter for number of NID stocks owned.
	 * Precondition: bnid >= 0 */
	public void setBnid(long bnid) {
		if (bnid < 0) throw new IllegalArgumentException();
		this.bnid = bnid;
	}

	/** Setter for number of ABC stocks owned.
	 * Precondition: babc >= 0 */
	public void setBabc(long babc) {
		if (babc < 0) throw new IllegalArgumentException();
		this.babc = babc;
	}

	/** Setter for number of days since a loan was taken out.
	 * Precondition: loanday is in 0..MAX_LOANDAY */
	public void setLoanday(int loanday) {
		if (loanday < 0 || loanday > MAX_LOANDAY) throw new IllegalArgumentException();
		this.loanday = loanday;
	}

	/** Setter for whether this person has been to jail. */
	public void setBjail(boolean bjail) {
		this.bjail = bjail;
	}

	/** Setter for number of times TV was watched since last paid TV bill.
	 * Precondition: tvt >= 0 */
	public void setTvt(int tvt) {
		if (tvt < 0) throw new IllegalArgumentException();
		this.tvt = tvt;
	}

	/** Setter for amount of beer owned.
	 * Precondition: beer >= 0 */
	public void setBeer(long beer) {
		if (beer < 0) throw new IllegalArgumentException();
		this.beer = beer;
	}

	/** Setter for amount of crack owned.
	 * Precondition: crack >= 0 */
	public void setCrack(long crack) {
		if (crack < 0) throw new IllegalArgumentException();
		this.crack = crack;
	}

	/** Setter for amount of heroin owned.
	 * Precondition: heroin >= 0 */
	public void setHeroin(long heroin) {
		if (heroin < 0) throw new IllegalArgumentException();
		this.heroin = heroin;
	}

	/** Setter for whether this person is in the drug deal club. */
	public void setClub(boolean club) {
		this.club = club;
	}

	/** Setter for whether time warp is owned. */
	public void setTime(boolean time) {
		this.time = time;
	}

	/** Setter for job level at Harvard.
	 * Precondition: hjob is in 0..6 */
	public void setHjob(int hjob) {
		if (hjob < 0 || hjob > 6) throw new IllegalArgumentException();
		this.hjob = hjob;
	}

	/** Setter for whether treadmill is owned. */
	public void setTread(boolean tread) {
		this.tread = tread;
	}

	/** Setter for whether bookcase is owned. */
	public void setBook(boolean book) {
		this.book = book;
	}

	/** Setter for level of dagger.
	 * Precondition: dag is in 0..7 */
	public void setDag(int dag) {
		if (dag < 0 || dag > 7) throw new IllegalArgumentException();
		this.dag = dag;
	}

	/** Setter for amount of caffeine owned.
	 * Precondition: caffeine >= 0 */
	public void setCaffeine(long caffeine) {
		if (caffeine < 0) throw new IllegalArgumentException();
		this.caffeine = caffeine;
	}

	/** Sleep regularly for x days. Return an int describing what happened during sleep.
	 * 0: Sleep was normal.
	 * 1: Health was reduced to 0.
	 * 2: The loan was not paid back in time, causing a penalty. */
	public int sleep(int x) {
		day += x;
		if (caffeine > 0) hp = hp - CAFFEINE_HP_LOSS*x;

		switch (bed) {
		case 0:
			hp = Math.min(hp + BED0_HP_GAIN*x, hpmax);
			break;
		case 1:
			hp = Math.min(hp + BED1_HP_GAIN*x, hpmax);
			break;
		case 2:
			hp = hpmax;
		}

		hour = DEFAULT_HOUR;
		if (caffeine > 0) {
			hour = CAFFEINE_HOUR;
			caffeine -= 1;
		}
		if (time) hour = TIMEWARP_HOUR;

		if (loan != 0) { // add interest to loan
			for (int i = 0; i < x; i++) {
				loan += Math.ceil(loan * randInt(1, MAX_LOAN_INTEREST_RATE*100) / 10000.0);
				loanday += 1;
			}
		}

		if (com) {
			// Update stock prices. For each stock per day, there is a 1/3 chance the
			// price will not change, 1/3 chance the price will increase by up to
			// STOCK_MAX_CHANGE, and 1/3 chance the price will decrease by up to
			// STOCK_MAX_CHANGE.
			for (int i = 0; i < x; i++) {
				int a = randInt(1, 3);
				switch (a) {
				case 1:
					aij = Math.min(aij+randInt(1, STOCK_MAX_CHANGE), STOCK_MAX_PRICE);
					break;
				case 2:
					aij = Math.max(aij-randInt(1, STOCK_MAX_CHANGE), STOCK_MIN_PRICE);
				}

				a = randInt(1, 3);
				switch (a) {
				case 1:
					dce = Math.min(dce+randInt(1, STOCK_MAX_CHANGE), STOCK_MAX_PRICE);
					break;
				case 2:
					dce = Math.max(dce-randInt(1, STOCK_MAX_CHANGE), STOCK_MIN_PRICE);
				}

				a = randInt(1, 3);
				switch (a) {
				case 1:
					nid = Math.min(nid+randInt(1, STOCK_MAX_CHANGE), STOCK_MAX_PRICE);
					break;
				case 2:
					nid = Math.max(nid-randInt(1, STOCK_MAX_CHANGE), STOCK_MIN_PRICE);
				}

				a = randInt(1, 3);
				switch (a) {
				case 1:
					abc = Math.min(abc+randInt(1, STOCK_MAX_CHANGE), STOCK_MAX_PRICE);
					break;
				case 2:
					abc = Math.max(abc-randInt(1, STOCK_MAX_CHANGE), STOCK_MIN_PRICE);
				}
			}
		}

		if (bank < 10000000) { // add interest to bank if balance is less than $10 million
			for (int i = 0; i < x; i++) {
				bank += bank * randInt(1, MAX_BANK_INTEREST_RATE*100) / 10000.0;
			}
		}
		if (book) smart += x * INTELLIGENCE_GAIN_BOOKCASE;
		if (tread) hpmax += x * HPMAX_GAIN_TREADMILL;

		if (hp <= 0) {
			hp = 0;
			return 1;
		} else if (loanday >= MAX_LOANDAY) {
			return 2;
		}
		return 0;
	}

	/** Sleep in jail for x days. Return an int describing what happened during sleep.
	 * 0: Sleep was normal.
	 * 1: The loan was not paid back in time, causing a penalty. */
	public int sleepJail(int x) {
		day += x;
		hour = DEFAULT_HOUR;
		if (time) hour = TIMEWARP_HOUR;

		if (loan != 0) { // add interest to loan
			for (int i = 0; i < x; i++) {
				loan += Math.ceil(loan * randInt(1, MAX_LOAN_INTEREST_RATE*100) / 10000.0);
				loanday += 1;
				if (loanday >= MAX_LOANDAY) break;
			}
		}

		if (com) {
			// Update stock prices. For each stock per day, there is a 1/3 chance the
			// price will not change, 1/3 chance the price will increase by up to
			// STOCK_MAX_CHANGE, and 1/3 chance the price will decrease by up to
			// STOCK_MAX_CHANGE.
			for (int i = 0; i < x; i++) {
				int a = randInt(1, 3);
				switch (a) {
				case 1:
					aij = Math.min(aij+randInt(1, STOCK_MAX_CHANGE), STOCK_MAX_PRICE);
					break;
				case 2:
					aij = Math.max(aij-randInt(1, STOCK_MAX_CHANGE), STOCK_MIN_PRICE);
				}

				a = randInt(1, 3);
				switch (a) {
				case 1:
					dce = Math.min(dce+randInt(1, STOCK_MAX_CHANGE), STOCK_MAX_PRICE);
					break;
				case 2:
					dce = Math.max(dce-randInt(1, STOCK_MAX_CHANGE), STOCK_MIN_PRICE);
				}

				a = randInt(1, 3);
				switch (a) {
				case 1:
					nid = Math.min(nid+randInt(1, STOCK_MAX_CHANGE), STOCK_MAX_PRICE);
					break;
				case 2:
					nid = Math.max(nid-randInt(1, STOCK_MAX_CHANGE), STOCK_MIN_PRICE);
				}

				a = randInt(1, 3);
				switch (a) {
				case 1:
					abc = Math.min(abc+randInt(1, STOCK_MAX_CHANGE), STOCK_MAX_PRICE);
					break;
				case 2:
					abc = Math.max(abc-randInt(1, STOCK_MAX_CHANGE), STOCK_MIN_PRICE);
				}
			}
		}

		if (bank < 10000000) {
			for (int i = 0; i < x; i++) { // add interest to bank
				bank += bank * randInt(1, MAX_BANK_INTEREST_RATE*100) / 10000.0;
			}
		}

		if (loanday >= MAX_LOANDAY) {
			return 1;
		}
		return 0;
	}

	/** Sleep in hotel for 1 night. Return an int describing what happened during sleep.
	 * 0: Sleep was normal.
	 * 1: Health was reduced to 0
	 * 2: The loan was not paid back in time, causing a penalty. */
	public int sleepHotel() {
		day += 1;
		if (caffeine > 0) hp = hp - CAFFEINE_HP_LOSS;
		hp = Math.min(hp + BED0_HP_GAIN, hpmax);

		hour = DEFAULT_HOUR;
		if (caffeine > 0) {
			hour = CAFFEINE_HOUR;
			caffeine -= 1;
		}
		if (time) hour = TIMEWARP_HOUR;

		if (loan != 0) {
			loan += Math.ceil(loan * randInt(1, MAX_LOAN_INTEREST_RATE*100) / 10000.0);
			loanday += 1;
		}

		if (com) {
			int a = randInt(1, 3);
			switch (a) {
			case 1:
				aij = Math.min(aij+randInt(1, STOCK_MAX_CHANGE), STOCK_MAX_PRICE);
				break;
			case 2:
				aij = Math.max(aij-randInt(1, STOCK_MAX_CHANGE), STOCK_MIN_PRICE);
			}

			a = randInt(1, 3);
			switch (a) {
			case 1:
				dce = Math.min(dce+randInt(1, STOCK_MAX_CHANGE), STOCK_MAX_PRICE);
				break;
			case 2:
				dce = Math.max(dce-randInt(1, STOCK_MAX_CHANGE), STOCK_MIN_PRICE);
			}

			a = randInt(1, 3);
			switch (a) {
			case 1:
				nid = Math.min(nid+randInt(1, STOCK_MAX_CHANGE), STOCK_MAX_PRICE);
				break;
			case 2:
				nid = Math.max(nid-randInt(1, STOCK_MAX_CHANGE), STOCK_MIN_PRICE);
			}

			a = randInt(1, 3);
			switch (a) {
			case 1:
				abc = Math.min(abc+randInt(1, STOCK_MAX_CHANGE), STOCK_MAX_PRICE);
				break;
			case 2:
				abc = Math.max(abc-randInt(1, STOCK_MAX_CHANGE), STOCK_MIN_PRICE);
			}
		}

		if (bank < 10000000) {
			bank += bank * randInt(1, MAX_BANK_INTEREST_RATE*100) / 10000.0;
		}

		if (hp <= 0) {
			hp = 0;
			return 1;
		} else if (loanday >= MAX_LOANDAY) {
			return 2;
		}
		return 0;
	}

	/** Sleep outside for 1 night. Return an int describing what happened during sleep.
	 * 0: Sleep was normal.
	 * 1: Health was reduced to 0
	 * 2: The loan was not paid back in time, causing a penalty. */
	public int sleepOutside() {
		day += 1;
		if (caffeine > 0) hp = hp - CAFFEINE_HP_LOSS;
		hp = Math.max(hp / 2 - 20, 0);

		hour = DEFAULT_HOUR;
		if (caffeine > 0) {
			hour = CAFFEINE_HOUR;
			caffeine -= 1;
		}
		if (time) hour = TIMEWARP_HOUR;

		if (loan != 0) {
			loan += Math.ceil(loan * randInt(1, MAX_LOAN_INTEREST_RATE*100) / 10000.0);
			loanday += 1;
		}

		if (com) {
			int a = randInt(1, 3);
			switch (a) {
			case 1:
				aij = Math.min(aij+randInt(1, STOCK_MAX_CHANGE), STOCK_MAX_PRICE);
				break;
			case 2:
				aij = Math.max(aij-randInt(1, STOCK_MAX_CHANGE), STOCK_MIN_PRICE);
			}

			a = randInt(1, 3);
			switch (a) {
			case 1:
				dce = Math.min(dce+randInt(1, STOCK_MAX_CHANGE), STOCK_MAX_PRICE);
				break;
			case 2:
				dce = Math.max(dce-randInt(1, STOCK_MAX_CHANGE), STOCK_MIN_PRICE);
			}

			a = randInt(1, 3);
			switch (a) {
			case 1:
				nid = Math.min(nid+randInt(1, STOCK_MAX_CHANGE), STOCK_MAX_PRICE);
				break;
			case 2:
				nid = Math.max(nid-randInt(1, STOCK_MAX_CHANGE), STOCK_MIN_PRICE);
			}

			a = randInt(1, 3);
			switch (a) {
			case 1:
				abc = Math.min(abc+randInt(1, STOCK_MAX_CHANGE), STOCK_MAX_PRICE);
				break;
			case 2:
				abc = Math.max(abc-randInt(1, STOCK_MAX_CHANGE), STOCK_MIN_PRICE);
			}
		}

		if (bank < 10000000) {
			bank += bank * randInt(1, MAX_BANK_INTEREST_RATE*100) / 10000.0;
		}

		if (hp <= 0) {
			hp = 0;
			return 1;
		} else if (loanday >= MAX_LOANDAY) {
			return 2;
		}
		return 0;
	}

	/** Sleep in life imprisonment. Return false if this person dies, true otherwise. */
	public boolean sleepPrison() {
		day += 1;
		hp -= 1;
		if (hp <= 0) {
			hp = 0;
			bank = 0;
			money = 0;
			return false;
		}
		return true;
	}

	/** Go through x days of activity as a student or worker in Har-Verd.
	 * Precondition: No loan is active. */
	public void sleepHarVerd(int x) {
		day += x;
		hp = Math.min(hp + BED0_HP_GAIN*x, hpmax);
		hour = DEFAULT_HOUR;
		if (time) hour = TIMEWARP_HOUR;
	}

	/** Give this person a new identify after successful escape from prison. */
	public void newIdentity() {
		bank = randInt(0, NEW_ID_MAX_BANK);
		money = randInt(0, NEW_ID_MAX_MONEY);
		day += 1;
		hour = DEFAULT_HOUR;
		loan = 0;
		gun = randInt(0, 5);
		bullet = randInt(0, 100);
		job = 0;
		bed = randInt(0, 1);
		tv = randInt(0, 1);
		com = randInt(0, 1) == 0;
		hupgrade = 0;
		baij = 0;
		bdce = 0;
		bnid = 0;
		babc = 0;
		loanday = 0;
		bjail = true;
		tvt = 0;
		beer = 0;
		crack = 0;
		heroin = 0;
		dag = randInt(0, 2);
		caffeine = 0;
	}

	/** Get penalized for not paying back loan. */
	public void loanPenalty(RPG rpg) {
		// ask whether penalty should be death or life imprisonment
		JLabel l = new JLabel("<html>You fail to pay back your loan in " +
				Person.MAX_LOANDAY + " days. You go to court and are given a<br />"
				+ "choice: the death penalty or life imprisonment.");
		ChoiceDialog c = new ChoiceDialog(rpg, "Loan Not Paid", l, "Death", "Prison", 420, 105);

		if (c.getChoice()) { // choice is die
			die(rpg);
		} else if (bjail) { // die because this is player's 2nd offense
			l = new JLabel("<html>When you enter the prison, the guards remember you from your<br />"
					+ "previous visit. They decide that execution is the best punishment for<br />"
					+ "two counts of failure to pay back a bank loan and one count of identity<br />"
					+ "theft.</html>");
			new MessageDialog(rpg, "Prison", l, 420, 135);
			die(rpg);
		} else { // go to life imprisonment
			rpg.setMenu(new menus.PrisonM(rpg));
			rpg.setDisplay(new displays.PrisonD(this));
		}
	}

	/** Display a message saying that the player has died, and return to the
	 * main menu and display. */
	public void die(RPG rpg) {
		JLabel l = new JLabel("<html>You have died. Your total monetary worth is<br />$"
				+ Menu.commas(bank+money-loan) + ".");
		new MessageDialog(rpg, "Dead", l, 260, 105);
		rpg.resetPerson();
		rpg.setMenu(new menus.StartM(rpg));
		rpg.setDisplay(new displays.StartD(rpg.getPerson()));
	}

	/** Attack an opponent in short-range combat in the bar. */
	public void barAttack(BarOpponent o) {
		long damage = randLong(1, hpmax) / 5; // initialize damage dealt
		switch (dag) { // update damage depending on dagger owned
		case 1:
			damage += randInt(0, 4);
			break;
		case 2:
			damage += 10;
			break;
		case 3:
			damage += 50;
			break;
		case 4:
			damage += 80;
			break;
		case 5:
			damage += 500;
			break;
		case 6:
			damage += 5000;
			break;
		case 7:
			damage = o.getHp();
		}
		damage = Math.min(o.getHp(), damage); // limit damage to no more than opponent's hp
		o.setHp(o.getHp() - damage); // deal the damage
	}

	/** Attack an opponent in the Santa Fe gym with an attack that uses i attack points.
	 * Precondition: i is in 1..4. */
	public void gymAttack(GymOpponent o, int i) {
		long damage = i * randLong(1, hpmax) / 5; // initialize damage dealt
		switch (dag) { // update damage depending on dagger owned
		case 1:
			damage += randInt(0, 4);
			break;
		case 2:
			damage += 10;
			break;
		case 3:
			damage += 50;
			break;
		case 4:
			damage += 80;
			break;
		case 5:
			damage += 500;
			break;
		case 6:
			damage += 5000;
			break;
		case 7:
			damage = o.getHp();
		}
		damage = Math.min(o.getHp(), damage); // limit damage to no more than opponent's hp
		o.setHp(o.getHp() - damage); // deal the damage
	}

}
