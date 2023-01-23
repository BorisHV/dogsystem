
package dogsystem;

/**
 * @author Boris Holmgren boho8503
 */
import java.util.ArrayList;

public class DogSorter {
    private void swap(int indexOne, int indexTwo, ArrayList<Dog> list) {
        Dog temp = list.get(indexOne);
        list.set(indexOne, list.get(indexTwo));
        list.set(indexTwo, temp);
    }
    public void sort(ArrayList<Dog> list) {
        for (int i = 0; i < list.size(); i++) {
            int jMin = i;
            for (int j = i + 1; j < list.size(); j++) {
                double smallest = list.get(jMin).getTailLength();
                double check = list.get(j).getTailLength();
                if (check < smallest) {
                    jMin = j;
                } else if (check == smallest) {
                    Dog blue = list.get(j);
                    Dog red = list.get(jMin);
                    String blueName = blue.getName();
                    String redName = red.getName();
                    if (blueName.compareTo(redName) < 0) {
                        jMin = j;
                    }
                }
            }
            swap (jMin , i, list);
        }
    }
}


