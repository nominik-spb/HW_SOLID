import java.util.ArrayList;

public class Basket {

    public ArrayList<Goods> goods = new ArrayList<>();
    public ArrayList<Integer> quantity = new ArrayList<>();
    public double sum = 0;

    public void addGood(Goods good, Integer quantity) {
        if(goods.contains(good)) {
            int i = this.goods.lastIndexOf(good);
            this.quantity.set(i, this.quantity.get(i)+quantity);
        } else {
            this.goods.add(good);
            this.quantity.add(quantity);
        }
        calcSum();
    }

    public void delGood(Goods good) {
        int i= this.goods.lastIndexOf(good);
        this.goods.remove(i);
        this.quantity.remove(i);
        calcSum();
    }

    public void findDelay() {

    }

    public void clear() {
        goods.clear();
        quantity.clear();
        sum = 0;
    }

    public void calcSum() {
        sum = 0;
        for (int i = 0; i < goods.size(); i++) {
            sum = sum + (goods.get(i).getPrice() * quantity.get(i));
        }
    }

    public ArrayList getBasketGoods() {
return this.goods;
    }

    public ArrayList getBasketQuantity() {
        return this.quantity;
    }

}
