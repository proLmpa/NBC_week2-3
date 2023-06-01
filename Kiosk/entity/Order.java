package Kiosk.entity;

import java.util.*;
public class Order {
    private int orderNum; // 대기 번호
    private ArrayList<Product> orders;  // 주문서
    private Map<Product, Integer> productCount; // 상품 개수
    private Map<String, Sold> history; // 주문 이력

    public Order(){
        orderNum = 0;
        orders = new ArrayList<>();
        productCount = new HashMap<>();
        history = new HashMap<>();
    }

    public int increaseOrderNum() { return ++orderNum; }

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
            int pc = productCount.get(product);
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

    // 장바구니 초기화
    public void initOrder() {
        orders = new ArrayList<>();
        productCount = new HashMap<>();
    }

    // 주문 이력(history) 최신화
    public void updateHistory() {
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
}
