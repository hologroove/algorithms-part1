import java.util.Scanner;

/**
 * Created by hologroove on 19/02/17.
 */
public class Permutation {
    public static void main(String[] args) {
        Integer k = Integer.valueOf(args[0]);
        Scanner sc = new Scanner(System.in);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        for (int i = 0; i < k; i++) {
            String str = sc.next();
            rq.enqueue(str);
        }

        for (String str : rq) {
            System.out.println(str);
        }
    }
}
