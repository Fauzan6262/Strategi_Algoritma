import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WarungTour {

    public static void main(String[] args) {
        int[] restStops = {10, 25, 30, 40, 50, 75, 80, 110, 130};
        int[] bestRoute = findBestRoute(restStops);
        
        System.out.println("Best route with the fewest stops:");
        for (int stop : bestRoute) {
            System.out.println("- Stop at " + stop + " km");
        }
    }

    public static int[] findBestRoute(int[] restStops) {
        List<int[]> permutations = generatePermutations(restStops);
        int[] bestRoute = null;
        int minStops = Integer.MAX_VALUE;

        for (int[] perm : permutations) {
            int[] stops = new int[perm.length + 2];
            stops[0] = 0;
            System.arraycopy(perm, 0, stops, 1, perm.length);
            stops[stops.length - 1] = 140;

            int numStops = stops.length - 2;
            if (numStops < minStops) {
                minStops = numStops;
                bestRoute = stops;
            }
        }

        return bestRoute;
    }

    public static List<int[]> generatePermutations(int[] restStops) {
        List<int[]> permutations = new ArrayList<>();
        permute(restStops, 0, permutations);
        return permutations;
    }

    private static void permute(int[] arr, int start, List<int[]> permutations) {
        if (start == arr.length - 1) {
            int[] perm = Arrays.copyOf(arr, arr.length);
            permutations.add(perm);
        } else {
            for (int i = start; i < arr.length; i++) {
                swap(arr, start, i);
                permute(arr, start + 1, permutations);
                swap(arr, start, i);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
