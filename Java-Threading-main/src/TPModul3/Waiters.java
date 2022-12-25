package TPModul3;
public class Waiters implements Runnable {
    
    private final int orderQty;
    private final int customerID;
    static int coffeePrice = 30000;

    public Waiters(int customerID, int orderQty) {
        this.customerID = customerID;
        this.orderQty = orderQty;
    }



    @Override
    public void run() {
        // call getCoffee method and pending it to 5000 ms
        while (true) {
            getFood();
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void orderInfo() {
        System.out.println("==========================================================");
        System.out.println("Customer ID: " + this.customerID);
        System.out.println("Numbeer of Food: " + this.orderQty);
        System.out.println("Total Price: " + this.orderQty * coffeePrice);
        System.out.println("==========================================================");
    }
    // create synchronized method getCoffee

    public void getFood() {
        synchronized(Restaurant.getLock()) {

            System.out.println("Waiters: Food is ready to deliver");
            Restaurant resto = new Restaurant();
            resto.setWaitingForPickup(false);

            // check if value of getCoffeeNumber form CoffeeMachine class is equal to orderQty
            // if same, call method orderInfo() to print order info and exit the program
            if (resto.getFoodNumber() == this.orderQty + 1) {
                orderInfo();
                System.exit(0);
            }
            resto.getLock().notifyAll();
            System.out.println("Waiters: Tell the coffee machine to make another food\n");

        }
    }
    
    
}
