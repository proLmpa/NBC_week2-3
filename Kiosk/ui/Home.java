package Kiosk.ui;

import Kiosk.entity.*;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;

public class Home {
    private Scanner scanner;
    private ArrayList<Menu> menuList;
    private Map<Menu, ArrayList<Product>> productMap;
    private Order order;

    public Home(ArrayList<Menu> menuList, Map<Menu, ArrayList<Product>> productMap){
        this.scanner = new Scanner(System.in);
        this.menuList = menuList;
        this.productMap = productMap;
        order = new Order();
    }

    public void show() {
        int choice = 0;

        while(true){
            printMenu();
            choice = selectNum();
            switch(choice) {
                case 0:
                    order.printHistory();
                    selectNum();
                    break;
                case 1: case 2: case 3: case 4:
                    Menu menu = menuList.get(choice-1);
                    printProductMenu(menu);
                    selectProduct(menu);
                    break;
                case 5:
                    order.printOrders();
                    if(selectNum() == 1){
                        printComplete();
                    }
                    break;
                case 6:
                    cancelMessage();
                    if(selectNum() == 1){
                        printCancel();
                    }
                    break;
                default:
                    System.out.println("입력한 번호에 해당하는 옵션이 없습니다.");
                    break;
            }
        }
    }

    // 메뉴판 출력
    public void printMenu(){
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------\n");
        sb.append("\"SHAKESHACK BURGER 에 오신 걸 환영합니다.\"\n");
        sb.append("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n\n");

        sb.append("[ SHAKESHACK MENU ]\n");
        // 반복문으로 menuList 내용 불러오기
        for(int i = 0; i < menuList.size(); i++){
            Menu menu = menuList.get(i);
            sb.append(String.format("%d. %-17s | %s\n", (i+1), menu.getName(), menu.getDesc()));
        }

        sb.append("\n[ ORDER MENU ]\n");
        sb.append("5. Order \t\t | 장바구니를 확인 후 주문합니다.\n");
        sb.append("6. Cancel \t\t | 진행 중인 주문을 취소합니다.\n");
        sb.append("--------------------------------------------\n");
        System.out.print(sb);
    }

    // 메뉴 번호 & 상품 번호 선택
    public int selectNum() {
        System.out.print("번호를 선택해주세요 : ");
        return scanner.nextInt();
    }

    // 상품 메뉴판 출력
    public void printProductMenu(Menu menu){
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------\n");
        sb.append("\"SHAKESHACK BURGER 에 오신 걸 환영합니다.\"\n");
        sb.append("아래 상품메뉴판을 보시고 메뉴를 골라 입력해주세요.\n\n");

        sb.append("[ " + menu.getName() + " MENU ]\n");
        ArrayList<Product> productList = productMap.get(menu);
        for(int i = 0; i < productList.size(); i++){
            Product product = productList.get(i);
            sb.append(String.format("%d. %-17s | W %.1f | %s\n", (i+1), product.getName(), product.getPrice(), product.getDesc()));
        }
        sb.append("--------------------------------------------\n");
        System.out.print(sb);
    }

    // 상품 선택
    public void selectProduct(Menu menu) {
        ArrayList<Product> productList = productMap.get(menu);
        int choice = 0;
        while((choice = selectNum()) > productList.size()){
            System.out.println("입력한 번호에 해당하는 상품이 없습니다.");
        }

        Product chosen = productList.get(choice-1);
        if(chosen.getOption() > 0){
            String option = chooseOption(menu.getName(), chosen);
            if(selectNum() == 2)
                chosen = new Product(chosen.getName() + option,  chosen.getDesc(), chosen.getOption());
        }

        printOrder(chosen);
    }

    // 옵션 선택
    public String chooseOption(String menuName, Product chosen) {
        String option = "";

        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------\n");
        sb.append(String.format("\"%-17s | W %.1f | %s\"\n", chosen.getName(), chosen.getPrice(), chosen.getDesc()));
        sb.append("위 메뉴의 어떤 옵션으로 추가하시겠습니까?\n");
        if(menuName.equals("Burgers")){
            sb.append(String.format("1. Single(W %.1f) \t\t 2. Double(W %.1f)\n", chosen.getPrice(), chosen.getOption()));
            option = "(Double)";
        } else {
            sb.append(String.format("1. Regular(W %.1f) \t\t 2. Large(W %.1f)\n", chosen.getPrice(), chosen.getOption()));
            option = "(Large)";
        }
        sb.append("--------------------------------------------\n");
        System.out.print(sb);
        return option;
    }

    // 장바구니에 추가
    public void printOrder(Product chosen) {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------\n");
        sb.append(String.format("\"%-17s | W %.1f | %s\"\n", chosen.getName(), chosen.getPrice(), chosen.getDesc()));
        sb.append("위 메뉴를 장바구니에 추가하시겠습니까?\n");
        sb.append("1. 확인 \t\t 2. 취소\n");
        sb.append("--------------------------------------------\n");
        System.out.print(sb);

        if(selectNum() == 1){
            order.addOrder(chosen);
            System.out.println(chosen.getName() + " 가 장바구니에 추가되었습니다.\n");
        }
    }

    // 주문 완료
    public void printComplete() {
        // 주문 이력에 완료된 주문을 넣고, 주문 객체를 초기화.
        order.updateHistory();
        order.initOrder();

        // 주문 완료 메시지 출력
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------\n");
        sb.append("주문이 완료되었습니다!\n\n");
        sb.append("대기번호는 [ " + order.increaseOrderNum() + " ] 번 입니다.\n");
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
    public void printCancel() {
        order.initOrder();
        System.out.println("진행하던 주문이 취소되었습니다.");
    }
}
