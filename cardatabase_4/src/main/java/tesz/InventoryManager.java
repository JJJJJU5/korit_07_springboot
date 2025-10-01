package tesz;

class OutOfStockException extends Exception{}

class Product {
    private String name;
    private int stock;
    public Product(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }
    public void decreaseStock(int quantity) throws OutOfStockException {
        // TODO: quantity가 stock보다 크면 OutOfStockException을 발생시키는 코드를 작성하시오.
        if(quantity > stock) {
            try {
                throw new OutOfStockException();
            } catch (OutOfStockException e) {
                System.out.println("재고 부족:"+quantity+"개 주문 불가.현재 재고:"+stock+"개.");
            }
        }else {
            stock -= quantity;
        }
        // 그렇지 않으면 stock을 quantity만큼 감소시킵니다.
    }
    public int getStock() {
        return stock;
    }
}
public class InventoryManager {
    public static void main(String[] args) {
        Product laptop = new Product("노트북", 10);
        int orderQuantity = 15;
        // TODO: try-catch 블록을 사용하여 laptop.decreaseStock()을 호출하고,
        // OutOfStockException을 처리하여 실행 예와 같이 출력하시오.
        try {
            laptop.decreaseStock(orderQuantity);
        } catch (OutOfStockException e) {
        }
    }
}