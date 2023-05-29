package Kiosk.entity;

import java.util.*;
public class Order {
    private int count;
    private int historyCount;
    private ArrayList<Product> orders;  // 주문서
    private Map<Integer, ArrayList<Product>> history; // 주문 이력

    public Order(){
        count = 0; historyCount = 0;
        orders = new ArrayList<>();
        history = new HashMap<>();
    }

    // 상품 장바구니 추가
    public void addOrder(Product product){
        orders.add(product);
        count++;
    }

    // 장바구니 출력
    public void printOrders() {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------\n");
        sb.append("아래와 같이 주문 하시겠습니까?\n");
        sb.append("\n[ Orders ]\n");

        float total = 0f;
        for(int i = 0; i < count; i++){
            Product product = orders.get(i);
            float price = Float.parseFloat(product.getPrice().split(" ")[1]);
            sb.append(String.format("%d. %-17s | %5s | %s\n", (i+1), product.getName(), product.getPrice(), product.getDesc()));
            total += price;
        }

        sb.append("\n[ Total ]\n");
        sb.append("W " + total + "\n");
        sb.append("\n1. 주문 \t\t 2. 메뉴판\n");
        sb.append("--------------------------------------------\n");
        System.out.print(sb);
    }

    // 주문 완료 & 장바구니 초기화
    public void printComplete() {
        // 주문 이력에 완료된 주문을 넣고, 주문 객체를 초기화.
        history.put(historyCount++, orders);
        count = 0;
        orders = new ArrayList<>();

        // 주문 완료 메시지 출력
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------\n");
        sb.append("주문이 완료되었습니다!\n\n");
        sb.append("대기번호는 [ " + historyCount + " ] 번 입니다.\n");
        sb.append("3초후 메뉴판으로 돌아갑니다.\n");
        sb.append("--------------------------------------------\n");
        System.out.print(sb);

        // 3초 기다리기 (sleep)
        try{
            Thread.sleep(3000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    // 취소 확인 메시지 출력
    public void cancelMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("------------------------------------\n");
        sb.append("진행하던 주문을 취소하시겠습니까?\n");
        sb.append("1. 확인 \t\t 2. 취소\n");
        sb.append("------------------------------------\n");
        System.out.print(sb);
    }

    // 주문 취소 (장바구니 초기화)
    public void cancelOrders() {
        orders = new ArrayList<>();
        count = 0;
        System.out.println("진행하던 주문이 취소되었습니다.");
    }
}
