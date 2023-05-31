package Kiosk.entity;

import java.util.*;
public class Order {
    private ArrayList<Product> orders;  // 주문서
    private Map<Product, Integer> productCount; // 상품 개수
    private Map<String, Sold> history; // 주문 이력
    private int orderNum; // 대기 번호

    public Order(){
        orders = new ArrayList<>();
        productCount = new HashMap<>();
        history = new HashMap<>();
        orderNum = 0;
    }

    // 상품 장바구니 추가
    public void addOrder(Product product){
        if(!productCount.containsKey(product)){ orders.add(product); }

        productCount.put(product, productCount.getOrDefault(product, 0) + 1);
    }

    // 장바구니 출력
    public void printOrders() {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------\n");
        sb.append("아래와 같이 주문 하시겠습니까?\n");
        sb.append("\n[ Orders ]\n");

        float total = 0f;
        for(int i = 0; i < orders.size(); i++){
            Product product = orders.get(i);
            int pc = productCount.get(product.getName());
            float price = product.getPrice();

            sb.append(String.format("%d. %-17s | W %.1f | %d개 | %s\n", (i+1), product.getName(), price, pc, product.getDesc()));
            total += (price * pc);
        }

        sb.append("\n[ Total ]\n");
        sb.append(String.format("W  %.1f\n", total));
        sb.append("\n1. 주문 \t\t 2. 메뉴판\n");
        sb.append("--------------------------------------------\n");
        System.out.print(sb);
    }

    // 주문 완료 & 장바구니 초기화
    public void printComplete() {
        // 주문 이력에 완료된 주문을 넣고, 주문 객체를 초기화.
        updateHistory(orders, productCount);
        orders = new ArrayList<>();
        productCount = new HashMap<>();

        // 주문 완료 메시지 출력
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------\n");
        sb.append("주문이 완료되었습니다!\n\n");
        sb.append("대기번호는 [ " + ++orderNum + " ] 번 입니다.\n");
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

    // 주문 이력(history) 최신화
    public void updateHistory(ArrayList<Product> orders, Map<Product, Integer> productCount) {
        for(int i = 0; i < orders.size(); i++){
            Product product = orders.get(i);
            String productName = product.getName();
            float productPrice = product.getPrice();
            int pc = productCount.get(product);

            if(!history.containsKey(productName)){
                history.put(productName, new Sold(productPrice, pc));
            } else {
                Sold sold = history.get(productName);
                sold.updateSaledCount(pc);
            }
        }

    }

    // 주문 이력(history)과 총 판매액 출력
    public void printHistory() {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------\n");
        sb.append("[ 총 판매상품 목록 및 판매금액 현황 ]\n\n");

        float totalSale = 0f;
        for(String key : history.keySet()){
            Sold sold = history.get(key);
            sb.append(String.format(" - %-20s | W %.1f | %d개\n", key, sold.getProductPrice(), sold.getSaledCount()));
            totalSale += (sold.getProductPrice() * sold.getSaledCount());
        }

        sb.append(String.format("\n현재까지 총 판매된 금액은 [ W %.1f ] 입니다.\n", totalSale));
        sb.append("1. 돌아가기\n");
        sb.append("--------------------------------------------\n");
        System.out.print(sb);
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
        productCount = new HashMap<>();
        System.out.println("진행하던 주문이 취소되었습니다.");
    }
}
