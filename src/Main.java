import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //создаем продуктовые товары
        ArrayList goods = new ArrayList<Goods>();
        goods.add(new foodGoods("154786", "Колбаса докторская. 400гр", 325.56, setDate("2025-11-15")));
        goods.add(new foodGoods ("154754","Сыр Российский. 180гр",220.00, setDate("2025-09-01")));
        goods.add(new foodGoods ("154786","Масло сливочное. 180гр",255.99, setDate("2026-01-12")));
        //создаем не продуктовые товары
        goods.add(new houseGoods ("129562","Шампунь-кондиционер. 450мл",412.20));
        goods.add(new houseGoods ("124002","Туалетная бумага. 4рул",160.20));

        //создаем экземпляр корзины
        Basket myBasket = new Basket();

        //выводим на экран все доступные к заказу товары
        printGoods(goods);

        //выводим на экран все доступные действия
        printMenu(myBasket);

//        Scanner scanner = new Scanner(System.in);

//        System.out.print("Введите номер продукта (№) и количество (К) в формате '№-К':");
        //int price = scanner.nextInt();

//        System.out.print("Введите вес товара (в кг.):");
//        int weight = scanner.nextInt();
//
//        int duty = CustomService.dutyCalculate (price, weight);
//        System.out.print("Размер пошлины (в руб.) составит: " + duty);
    }

    private static void printMenu(Basket myBasket) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер следующего действия");
        System.out.println("1. Добавить в корзину");
        System.out.println("2. Удалить из корзины");
        System.out.println("3. Найти просрочку в корзине (только прод.товары)");
        System.out.println("4. Оценить товар в корзине");
        System.out.println("5. Оплатить товар в корзине");
        System.out.print(  "   Введите номер действия:");
        int action = scanner.nextInt();
        switch (action) {
            case 1:
                addToBasket();
                break;
            case 2:
                delFromBasket();
                break;
            case 3:
                findDelay();
                break;
            case 4:
                addGradle();
                break;
            case 5:
                payBasket(myBasket);
                break;
            default:
                System.out.println("некорректный ввод...");
                printMenu(myBasket);
        }
    }

    private static void payBasket(Basket myBasket) {
        myBasket.clear();
    }

    private static void addGradle() {
    }

    private static void findDelay() {
    }

    private static void delFromBasket() {
    }

    private static void addToBasket() {
    }

    public static void printGoods(ArrayList goods){
        for (int i = 0; i < goods.size(); i++) {
            Goods str = (Goods) goods.get(i);
            StringBuilder result = new StringBuilder();
            result.append(i+1)
                    .append(" - ")
                    .append(str.getArticle())
                    .append(" / ")
                    .append(str.getName())
                    .append(" / ")
                    .append(str.getPrice())
                    .append("руб. / рейтинг ")
                    .append(str.getRating());
            System.out.println(result);
        }
    }

    public static Date setDate(String expDate) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String str = expDate.length() == 0 ? "0000-00-00" : expDate;
        Date parsingDate = null;
        try {
            parsingDate = ft.parse(str);
        } catch (ParseException e) {
            System.out.println("не получилось распарсить " + ft);
        }
        return parsingDate;
    }

    public static String getDate(Date expDate) {
        SimpleDateFormat formatForDate = new SimpleDateFormat("dd.MM.yy");
        return formatForDate.format(expDate);
    }

}
