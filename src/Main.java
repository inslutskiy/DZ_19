import java.io.File;
import java.util.Scanner;

public class Main {
    static File saveFile = new File("basket.txt");
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);

        String[] products = {"Бумага", "Сок", "Суши"};
        int[] prices = {218, 25, 179};
        int[] cost = new int[products.length];
        long[] totalPrise = new long[products.length];

        Basket basket;

        if (saveFile.exists()) {
            basket = Basket.loadFile(saveFile);
        } else {
            basket = new Basket(products, prices, cost, totalPrise);
        }

        while (true) {

            basket.range();
            System.out.println("Введите номер товара и количество через пробел или stop: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("stop")) {
                break;
            }

            String[] parts = input.split(" ");
            int productNumber = Integer.parseInt(parts[0]) - 1;
            int productCont = Integer.parseInt(parts[1]);
            basket.addToBasket(productNumber, productCont);
            basket.saveFile(saveFile);
        }
        basket.basket();
    }
}