public class houseGoods implements Goods{
    private String article;
    private String name;
    private double price;
    private int[] rating;

    public houseGoods(String article, String name, double price) {
        this.article = article;
        this.name = name;
        this.price = price;
        this.rating = new int[2];
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
    public void setRating(int grade) {
        if ((grade > 0)) {
            this.rating[0] = this.rating[0] + grade;
        } else {
            this.rating[1] = this.rating[1] + grade;
        }
    }

    //@Override
//    public void setRating (int grade) {
//        if ((grade > 0)) {
//            this.rating[0] = this.rating[0] + grade;
//        } else {
//            this.rating[1] = this.rating[1] + grade;
//        }
//    }

//    @Override
//    public String toString(){
//        StringBuilder result = new StringBuilder();
//        result.append(this.getArticle()).append("/").append(this.getName()).append("/").append(this.getPrice()).append("/").append(getRating());
//        return result.toString();
//    }
}
