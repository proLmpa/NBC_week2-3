package Kiosk.ui;

import Kiosk.entity.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Home {
    private Scanner scanner;
    private ArrayList<Menu> menuList;
    private Map<String, ArrayList<Product>> productMap;
    private Order order;

    public Home(ArrayList<Menu> menuList, Map<String, ArrayList<Product>> productMap){
        this.scanner = new Scanner(System.in);
        this.menuList = menuList;
        this.productMap = productMap;
        order = new Order();
    }

    public void show() {
        int choice = 0;

        while(true){
            printMenu();
            choice = selectMenu();
            switch(choice) {
                case 0:
                    break;
                case 1: case 2: case 3: case 4:
                    String menuName = menuList.get(choice-1).getName();
                    printProductMenu(menuName);
                    selectProduct(menuName);
                    break;
                case 5:
                    order.printOrders();
                    if(selectMenu() == 1){
                        order.printComplete();
                    }
                    break;
                case 6:
                    order.cancelMessage();
                    if(selectMenu() == 1){
                        order.cancelOrders();
                    }
                    break;
                default:
                    System.out.println("입력한 번호에 해당하는 옵션이 없습니다.");
                    break;
            }

        }
    }

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

    public int selectMenu() {
        System.out.print("번호를 선택해주세요 : ");
        return scanner.nextInt();
    }

    public void printProductMenu(String menuName){
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------\n");
        sb.append("\"SHAKESHACK BURGER 에 오신 걸 환영합니다.\"\n");
        sb.append("아래 상품메뉴판을 보시고 메뉴를 골라 입력해주세요.\n\n");

        sb.append("[ " + menuName + " MENU ]\n");
        ArrayList<Product> productList = productMap.get(menuName);
        for(int i = 0; i < productList.size(); i++){
            Product product = productList.get(i);
            sb.append(String.format("%d. %-17s | %5s | %s\n", (i+1), product.getName(), product.getPrice(), product.getDesc()));
        }
        sb.append("--------------------------------------------\n");
        System.out.print(sb);
    }

    public void selectProduct(String menuName) {
        ArrayList<Product> productList = productMap.get(menuName);
        int choice = 0;
        while((choice = selectMenu()) > productList.size()){
            System.out.println("입력한 번호에 해당하는 상품이 없습니다.");
        }

        Product chosen = productList.get(choice-1);

        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------\n");
        sb.append(String.format("\"%-17s | %5s | %s\"\n", chosen.getName(), chosen.getPrice(), chosen.getDesc()));
        sb.append("위 메뉴를 장바구니에 추가하시겠습니까?\n");
        sb.append("1. 확인 \t\t 2. 취소\n");
        sb.append("--------------------------------------------\n");
        System.out.print(sb);

        if(selectMenu() == 1){
            order.addOrder(chosen);
            System.out.println(chosen.getName() + " 가 장바구니에 추가되었습니다.\n");
        }
    }
}