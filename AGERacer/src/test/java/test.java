import java.util.Arrays;
import java.util.Comparator;
public class test {
    public static void main(String[] args) {
        double[][] array = {
            {1, 5},
            {13, 1.55},
            {12, 100.6},
            {12.1, .85} };
            Arrays.sort(array, Comparator.comparingDouble(o -> o[0]));

            System.out.println(Arrays.toString(array));
    }
}
