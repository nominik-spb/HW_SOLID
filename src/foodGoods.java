import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class foodGoods implements Goods{

    public String article;
    public String name;
    public double price;
    public int[] rating;
    public Date expDate;

    public foodGoods(String article, String name, double price, Date expDate) {
        this.article = article;
        this.name = name;
        this.price = price;
        this.rating = new int[2];
        this.expDate = expDate;

    }

    public Date getDate() {
        return this.expDate;
    }

    @Override
    public String getArticle() {
       return this.article;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getRating() {
        StringBuilder strRat = new StringBuilder()
                .append('+')
                .append(this.rating[0])
                .append("/-")
                .append(this.rating[1]);
        return strRat.toString();
    }

    @Override
    public void setRating (int grade) {
        if ((grade > 0)) {
            this.rating[0] = this.rating[0] + grade;
        } else {
            this.rating[1] = this.rating[1] + grade;
        }
    }

//    @Override
//    public String toString(){
//        StringBuilder result = new StringBuilder();
//        result.append(this.getArticle()).append("/").append(this.getName()).append("/").append(this.getPrice()).append("/").append(getRating());
//        return result.toString();
//    }
}
