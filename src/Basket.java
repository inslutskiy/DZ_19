import java.io.*;
import java.util.Arrays;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class Basket {
    private String[] name;
    private int[] prise;
    private int[] amount;
    private long[] totalPrise;

    public Basket() {

    }

    public Basket(String[] products, int[] prices, int[] cost, long[] totalPrise) {
        this.name = products;
        this.prise = prices;
        this.amount = cost;
        this.totalPrise = totalPrise;
    }

    public void addToBasket(int productNumber, int productCont) {
        int x = productCont * prise[productNumber];
        amount[productNumber] += productCont;
        totalPrise[productNumber] += x;
    }

    public void range() {
        for (int i = 0; i < name.length; i++) {
            int number = i + 1;
            System.out.println(number + ". " + name[i] + " по цене " + prise[i]);
        }
    }

    public void basket() {
        System.out.println("Ваша корзина");
        for (int i = 0; i < name.length; i++) {
            System.out.println(name[i] + " " + amount[i] + " шт. по цене " + prise[i] + " за штуку на общую стоимость " + totalPrise[i] + " руб.");
        }
    }

    public static Basket loadFile(File textFile) throws IOException {
        Basket basket = new Basket();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile))) {
            String goodsStr = bufferedReader.readLine();
            String pricesStr = bufferedReader.readLine();
            String quantitiesStr = bufferedReader.readLine();

            basket.name = goodsStr.split(" ");

            basket.prise = Arrays.stream(pricesStr.split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();

            basket.amount = Arrays.stream(quantitiesStr.split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();

            basket.totalPrise = Arrays.stream(quantitiesStr.split(" "))
                    .map(Integer::parseInt)
                    .mapToLong(Integer::longValue)
                    .toArray();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }

    public void saveFile(File textFile) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(textFile)) {
            out.println(String.join( " ", name));

            out.println(String.join(" ", Arrays.stream(prise)
                    .mapToObj(String::valueOf)
                    .toArray(String []::new)));

            out.println(String.join(" ", Arrays.stream(amount)
                    .mapToObj(String::valueOf)
                    .toArray(String []::new)));

            out.println(String.join(" ", Arrays.stream(totalPrise)
                    .mapToObj(String::valueOf)
                    .toArray(String []::new)));
        }
    }
}