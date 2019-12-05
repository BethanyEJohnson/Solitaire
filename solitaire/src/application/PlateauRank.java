package application;

public class PlateauRank implements RankBehavior {
	public boolean rankRule(Card orig, Card top) {
		if (orig.rank == "A" && top.rank == "2")
			return true;
		if (orig.rank == "2" && top.rank == "3")
			return true;
		if (orig.rank == "3" && top.rank == "4")
			return true;
		if (orig.rank == "4" && top.rank == "5")
			return true;
		if (orig.rank == "5" && top.rank == "6")
			return true;
		if (orig.rank == "6" && top.rank == "7")
			return true;
		if (orig.rank == "7" && top.rank == "8")
			return true;
		if (orig.rank == "8" && top.rank == "9")
			return true;
		if (orig.rank == "9" && top.rank == "10")
			return true;
		if (orig.rank == "10" && top.rank == "J")
			return true;
		if (orig.rank == "J" && top.rank == "Q")
			return true;
		if (orig.rank == "Q" && top.rank == "K")
			return true;
		if (orig.rank == "K" && top.rank == null)
			return true;
		return false;
	}
}
