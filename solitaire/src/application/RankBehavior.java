package application;

public interface RankBehavior {
	public boolean cacheRank(Card ognl, Card top);
	public boolean plateauRank(Card ognl, Card top);
}
