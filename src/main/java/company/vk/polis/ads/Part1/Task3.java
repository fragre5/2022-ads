import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        while(true){
            switch (in.next()){
                case "push":
                    int input = in.nextInt();
                    stack.push(input);
                    out.println("ok");
                    break;
                case "pop":
                    if(stack.size() == 0){
                        out.println("error");
                    }else{
                        out.println(stack.pop());
                    }
                    break;
                case "back":
                    if(stack.size() == 0){
                        out.println("error");
                    }else{
                        out.println(stack.back());
                    }
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    return;
            }
        }
    }

    static class Stack{
        private final ArrayList<Integer> arrayList;
        Stack(){
            arrayList = new ArrayList<>();
        }
        public void push(int n){
            arrayList.add(n);
        }
        public int pop(){
            int topElement = arrayList.get(arrayList.size() - 1);
            arrayList.remove(arrayList.size() - 1);
            return topElement;
        }
        public int back(){
            int topElement = arrayList.get(arrayList.size() - 1);
            return topElement;
        }
        public void clear(){
            arrayList.clear();
        }
        public int size(){
            return arrayList.size();
        }
    }

    private static final class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
