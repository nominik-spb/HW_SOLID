public interface Goods {
    String article = "" ;
    String name = "";
    double price = 0;
    int[] rating = new int[2];

    String getArticle();
    String getName();
    double getPrice();
    String getRating();
    void setRating(int gradle);

}
