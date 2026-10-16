import java.util.*;
public class AuctionSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Item> items = new ArrayList<>();
    static class Item {
        String name, bidder = "nobody";
        double price;
        boolean open = true;
        Item(String n, double p) { name = n; price = p; }
    }
 
    static void addItem() {
        System.out.print("Item name: ");
        String name = sc.next();
        System.out.print("Starting price: ");
        double price = sc.nextDouble();
        items.add(new Item(name, price));
        System.out.println("Added. Item id = " + items.size());
    }
 
    static Item getItem() {
        System.out.print("Item id: ");
        int id = sc.nextInt();
        if (id < 1 || id > items.size()) {
            System.out.println("No such item");
            return null;
        }
        return items.get(id - 1);
    }
 
    static void placeBid() {
        Item item = getItem();
        if (item == null) return;
        if (!item.open) { System.out.println("Auction is closed"); return; }
        System.out.print("Your name: ");
        String who = sc.next();
        System.out.print("Your bid: ");
        double bid = sc.nextDouble();
        if (bid > item.price) {
            item.price = bid;
            item.bidder = who;
            System.out.println("Bid accepted!");
        } else
            System.out.println("Bid must be higher than " + item.price);
    }
    static void showItems() {
        for (int i = 0; i < items.size(); i++) {
            Item it = items.get(i);
            System.out.println((i + 1) + ". " + it.name + "  price: "
                + it.price + "  " + (it.open ? "OPEN" : "CLOSED"));
        }
    }
 
    static void closeAuction() {
        Item item = getItem();
        if (item == null) return;
        item.open = false;
        if (item.bidder.equals("nobody"))
            System.out.println("No bids - item unsold");
        else
            System.out.println("Winner: " + item.bidder + " with " + item.price);
    }
 
    public static void main(String[] args) {
        while (true) {
            System.out.print("\n1.Add 2.Bid 3.Show 4.Close 0.Exit: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: addItem(); break;
                case 2: placeBid(); break;
                case 3: showItems(); break;
                case 4: closeAuction(); break;
                case 0: return;
                default: System.out.println("Invalid choice");
            }
        }
    }
}
