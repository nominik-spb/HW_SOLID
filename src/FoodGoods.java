import java.time.LocalDate;

// Класс хранит данные и выполняет методы исключительно относящиеся
// к данному экземпляру прод.товаров
// принцип single-responsibility principle

// принцип Interface segregation
// принцип Dependency inversion
public class FoodGoods implements Goods, GoodsDelay {

    // принцип Open-closed
    private final String article;
    private final String name;
    private final double price;
    private final int[] rating;
    private final LocalDate expDate;

    public FoodGoods(String article, String name, double price, LocalDate expDate) {
        this.article = article;
        this.name = name;
        this.price = price;
        this.rating = new int[2];
        this.expDate = expDate;

    }

    @Override
    public LocalDate getDate() {
        return expDate;
    }

    @Override
    public String getArticle() {
        return article;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getRating() {
        StringBuilder strRat = new StringBuilder()
                .append('+')
                .append(rating[0])
                .append("/-")
                .append(rating[1]);
        return strRat.toString();
    }

    @Override
    public void setRating(int grade) {
        if ((grade > 0)) {
            rating[0] = rating[0] + grade;
        } else {
            rating[1] = rating[1] - grade;
        }
    }
}
