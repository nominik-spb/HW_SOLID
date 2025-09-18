import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/* Программа предостовляет пользователю список продовольственных и
    непродовольственных товаров к заказу. Заказ формируется в виде корзины
    с возможностью добавления товара, удаления товара,
    поиск просроченных продовольственных товаром, оценки товаров в корзине,
    а также оплаты заказа (очистки корзины)
*/

public class Main {

    public static void main(String[] args) {

        //создаем продуктовые товары
        ArrayList<Goods> goods = new ArrayList<>();
        goods.add(new FoodGoods("154786", "Колбаса докторская. 400гр", 325.50, setDate("2025,09,28")));
        goods.add(new FoodGoods("154754", "Сыр Российский. 180гр", 220.00, setDate("2025,09,01")));
        goods.add(new FoodGoods("154786", "Масло сливочное. 180гр", 255.90, setDate("2026,01,12")));
        //создаем не продуктовые товары
        goods.add(new HouseGoods("129562", "Шампунь-кондиционер. 450мл", 412.20));
        goods.add(new HouseGoods("124002", "Туалетная бумага. 4рул", 160.20));

        //создаем экземпляр корзины
        Basket myBasket = new Basket();
        //myBasket.sum = 200;

        //выводим на экран товары в магазине
        // принцип DRY
        printGoods(goods);

        //выводим на экран доступные действия
        // принцип DRY
        printMenu(goods, myBasket);

    }

    // метод выводит на экран меню для действий с корзиной
    private static void printMenu(ArrayList<Goods> goods, Basket myBasket) {

        if (myBasket.sum > 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("############### Действия с товарами в корзине ###############");
            System.out.print("|1-Добавить|");
            System.out.print("2-Удалить|");
            System.out.print("3-Найти просроченный (только прод.)|");
            System.out.print("4-Оценить|");
            System.out.print("5-Оплатить все|");
            System.out.print("Введите номер действия: => ");

            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    addToBasket(goods, myBasket);
                    printBasket(myBasket);
                    printMenu(goods, myBasket);
                    break;
                case 2:
                    delFromBasket(myBasket);
                    printBasket(myBasket);
                    printMenu(goods, myBasket);
                    break;
                case 3:
                    findDelay(myBasket);
                    printMenu(goods, myBasket);
                    break;
                case 4:
                    addGradle(myBasket, goods);
                    printGoods(goods);
                    printMenu(goods, myBasket);
                    break;
                case 5:
                    payBasket(myBasket);
                    printGoods(goods);
                    printMenu(goods, myBasket);
                    break;
                default:
                    System.out.println("--------------- некорректный ввод ---------------");
                    printMenu(goods, myBasket);
            }
        } else {
            System.out.println("--------------- Корзина пуста ---------------");
            addToBasket(goods, myBasket);
            printBasket(myBasket);
            printMenu(goods, myBasket);
        }
    }

    // метод очистки корзины
    private static void payBasket(Basket myBasket) {
        myBasket.clear();
        System.out.println("--------------- Оплачено ---------------");
        System.out.println("--------------- Корзина очищена ---------------");
    }

    // метод записи оценки в товары
    private static void addGradle(Basket myBasket, ArrayList<Goods> goods) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ОЦЕНКА: Введите номер продукта в корзине (N) и оценку в формате 'N/+' или 'N/-': => ");

        String action = scanner.nextLine();
        String[] parts = action.split("/");
        int number = Integer.parseInt(parts[0]) - 1;
        char gradle = parts[1].charAt(0);

        if (number < 0 || number > myBasket.getBasketGoods().size() - 1) {
            System.out.println("--------------- введен некорректный номер товара в корзине");
            addGradle(myBasket, goods);
        } else if (gradle != '+' && gradle != '-') {
            System.out.println("--------------- введена некорректная оценка товара в корзине (только + или -)");
            addGradle(myBasket, goods);
        } else {
            if ((gradle == '+')) {
                goods.get(number).setRating(1);
            } else {
                goods.get(number).setRating(-1);
            }
            System.out.println("--------------- Оценка добавлена ---------------");
        }
    }

    // метод удаления товара из корзины
    private static void delFromBasket(Basket myBasket) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("УДАЛЕНИЕ: Введите номер продукта в корзине 'N': => ");

        int numGood = scanner.nextInt() - 1;
        if (numGood < 0 || numGood > myBasket.goods.size() - 1) {
            System.out.println("--------------- введен некорректный номер товара в корзине...");
            delFromBasket(myBasket);
        } else {
            myBasket.delGood(numGood);
            System.out.println("--------------- Товар удален ---------------");
        }
    }

    // метод добавления товара в корзину
    private static void addToBasket(ArrayList<Goods> goods, Basket myBasket) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("ДОБАВЛЕНИЕ: Введите номер продукта (N) и количество (Q) в формате 'N-Q': => ");

        String action = scanner.nextLine();
        String[] parts = action.split("-");
        int number = Integer.parseInt(parts[0]) - 1;
        int quantity = Integer.parseInt(parts[1]);

        // принцип избегания магических чисел
        if (number < 0 || number > goods.size() - 1) {
            System.out.println("--------------- введен некорректный номер товара в магазине");
            addToBasket(goods, myBasket);
        } else {
            myBasket.addGood(goods.get(number), quantity);
            System.out.println("--------------- Товар добавлен ---------------");
        }
    }

    // метод выводит на экран товары в магазине
    public static void printGoods(ArrayList<Goods> goods) {
        System.out.println("############### Товары в нашем магазине ###############");
        // принцип избегания магических чисел
        for (int i = 0; i < goods.size(); i++) {
            Goods str = goods.get(i);
            printGood(str, i, 0);
        }
    }

    // метод выводит на экран товары в корзине
    public static void printBasket(Basket myBasket) {
        System.out.println("############### Товары в Вашей корзине ###############");
        // принцип избегания магических чисел
        for (int i = 0; i < myBasket.getBasketGoods().size(); i++) {
            printGood(myBasket.getBasketGoods().get(i), i, myBasket.getBasketQuantity().get(i));
        }
        System.out.println("_____________________________________________________________________");
        System.out.println("                                                  ИТОГО: " + myBasket.sum + " руб.");
    }

    // метод выводит на экран i-ю строку из передаваемого массива строк
    // принцип DRY для методов printGoods и printBasket
    public static void printGood(Goods str, int i, int q) {
        StringBuilder result = new StringBuilder();
        result.append(i + 1)
                .append(" - ")
                .append(str.getArticle())
                .append(" / ")
                .append(str.getName())
                .append(" / ")
                .append(str.getPrice())
                .append("руб. / рейтинг ")
                .append(str.getRating());
        if (q > 0) {
            result.append(" -- ")
                    .append(q)
                    .append("шт.");
        }
        System.out.println(result);
    }

    // метод выводит на экран прод.товары из корзины с просроченной датой на текущий момент
    public static void findDelay(Basket myBasket) {

        List<GoodsDelay> foodGoodsBasket = myBasket.getBasketGoods().stream()
                .filter(obj -> obj instanceof GoodsDelay)
                .map(obj -> (GoodsDelay) obj)
                .collect(Collectors.toList());

        int j = 0;
        StringBuilder text = new StringBuilder().append("ПРОСРОЧКА: ");
        for (int i = 0; i < foodGoodsBasket.size(); i++) {
            if (foodGoodsBasket.get(i).getDate().isBefore(LocalDate.now())) {
                j++;
                text.append(foodGoodsBasket.get(i).getName()).append(";");
            }
        }
        if (j == 0) {
            text.append("нет");
        }
        ;
        System.out.println(text);
    }

    // метод преобразует дату в String формате при начальном заполнении магазина в формат LocalDate
    public static LocalDate setDate(String strDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy,MM,dd");

        return LocalDate.parse(strDate, formatter);

    }

}