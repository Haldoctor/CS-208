import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] p = new int[200001];
        int[] q = new int[200001];
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int c = scanner.nextInt();
        int t = scanner.nextInt();

        for (int i=1; i<=n; i++)
            p[i] = scanner.nextInt();
        for (int j=1; j<=m; j++)
            q[j] = scanner.nextInt();

        Arrays.sort(q, 1, m + 1);
        Arrays.sort(p, 1, n + 1);

        int k=1,sum=0,x=0;
        for (int j=1; j<=m; j++)
        {
            x=0;
            while (k<=n && q[j]+t>=p[k] && x<c)
            {
                if (q[j]-t<=p[k]) {
                    sum=sum+1; x=x+1;
                }
                k=k+1;
            }
        }

        System.out.println(sum);
    }
}
