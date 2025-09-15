import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Basket {
    public Map<Goods, Integer> myBasket = new TreeMap<>();
    public double sum = 0;

    public void addGood(Goods good, Integer quantity) {

    }

    public void delGood(Goods good) {

    }

    public void findDelay() {

    }

    public void clear() {
        myBasket.clear();
        sum = 0;
    }
}
