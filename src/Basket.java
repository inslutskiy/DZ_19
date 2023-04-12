import java.io.*;
import java.util.Arrays;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class Basket implements Serializable{
    @Serial
    private static  final long serialVersionUID = 1L;
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

    public static Basket loadFile(File file) {
        Basket basket = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            basket = (Basket) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }

    public void saveFile(File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}