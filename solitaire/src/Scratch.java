
public class Scratch {

	public static void main(String[] args) {
	
/*		String[] suit = {"Heart","Spade","Diamond","Club"};
		String[] rank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		for(int i = 0; i < suit.length; i++) {
			for(int j=0; j < rank.length; j++) {
				System.out.println(suit[i] + rank[j]);
			} 
		}
		*/
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 13; j++) {
				System.out.print(j+ (13*i)+" ");
			} 
		}

	}

}
