

import java.io.*;
import java.util.*;

public class Main {
    static long mol = 1000000007;
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();

        int n = in.nextInt();
        int m = in.nextInt();
        Animal[] animals = new Animal[n + 1];

        for (int i = 1; i <= n; i++) {
            animals[i] = new Animal();
            animals[i].name = i;
        }

        for (int i = 0 ; i < m ; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            animals[x].foods.add(animals[y]);
            animals[y].predators.add(animals[x]);
        }

        List<List<Integer>> adj = new ArrayList<>();
        int[] ins = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 1 ; i <= n ; i++) {
            for (Animal food : animals[i].foods) {
                adj.get(i).add(food.name);
            }
            ins[i] = animals[i].predators.size();
        }

        List<List<Integer>> readj = new ArrayList<>();
        int[] rein = new int[n + 1];
        for (int i = 0 ; i <= n ; i++) {
            readj.add(new ArrayList<>());
        }
        for (int i = 1 ; i <= n ; i++) {
            for (Animal predator : animals[i].predators) {
                readj.get(i).add(predator.name);
            }
            rein[i] = animals[i].foods.size();
        }

        long[] f = new long[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1 ; i <= n ; i++) {
            if (ins[i] == 0) {
                queue.offer(i);
                f[i] = 1;
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj.get(u)) {
                f[v] = (f[v] + f[u]) % mol;
                ins[v]--;
                if (ins[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        long[] g = new long[n + 1];
        Queue<Integer> reverseQueue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (rein[i] == 0) {
                reverseQueue.offer(i);
                g[i] = 1;
            }
        }
        while (!reverseQueue.isEmpty()) {
            int u = reverseQueue.poll();
            for (int v : readj.get(u)) {
                g[v] = (g[v] + g[u]) % mol;
                rein[v]--;
                if (rein[v] == 0) {
                    reverseQueue.offer(v);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            long res = (f[i] * g[i]) % mol;
            if (i == n){
                out.print(res);
                break;
            }
            out.print(res);
            out.print(" ");
        }
        out.close();
    }

    static class Animal {
        int name;
        List<Animal> foods = new ArrayList<>();
        List<Animal> predators = new ArrayList<>();
    }
}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write(" ");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}
