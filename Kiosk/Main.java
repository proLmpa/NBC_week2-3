///*
//package Kiosk;
//
//import Kiosk.entity.Menu;
//import Kiosk.entity.Order;
//import Kiosk.entity.Product;
//
//import java.util.*;
//
//public class Main {
//    static Scanner sc = new Scanner(System.in);
//    static ArrayList<Menu> menuList = new ArrayList<Menu>();
//    static HashMap<String, ArrayList<Product>> productMap = new HashMap<>();
//    // key - value
//
//    public static void main(String[] args){
//        // Menu 초기화
//        init();
//
//        Order order = new Order();
//        // 주문 반복
//        while(true){
//
//            // 1. 메뉴 출력
//            printMenu();
//            int choice = sc.nextInt();
//            switch(choice) {
//                // 1-1. 메뉴 번호 선택 시 상품 메뉴로 이동
//                case 0:
//                    break;
//                // 1-2. 주문 확인(5) -> 1.로 복귀
//                case 5:
//                    order.printOrders();
//                    if(sc.nextInt() == 1){
//                        order.printComplete();
//                    }
//                    break;
//                // 1-3. 주문 취소(6) -> 1-1.로 복귀
//                case 6:
//                    order.cancelMessage();
//                    if(sc.nextInt() == 1){
//                        order.cancelOrders();
//                        System.out.println("진행하던 주문이 취소되었습니다.");
//                    }
//                    break;
//                default:
//                    while(choice > 4) {
//                        System.out.println("입력한 번호에 해당하는 옵션이 없습니다.");
//                        choice = sc.nextInt();
//                    }
//
//                    // 2. 상품 메뉴판 출력
//                    String menuName = menuList.get(choice-1).getName();
//                    printProductMenu(menuName);
//
//                    // 3. 선택한 상품 확인
//                    ArrayList<Product> productList = productMap.get(menuName);
//                    while((choice = sc.nextInt())> productList.size()) {
//                        System.out.println("입력한 번호에 해당하는 상품이 없습니다.");
//                    }
//                    Product chosen = productList.get(choice-1);
//                    printAddOrder(chosen.getMenu());
//                    if((choice = sc.nextInt()) == 1){
//                        order.addOrder(chosen);
//                        System.out.println(chosen.getName() + " 가 장바구니에 추가되었습니다.\n");
//                    }
//
//                    break;
//            }
//            // 3. 취소
//        }
//    }
//
//    static void init() {
//        // 메뉴 카테고리 초기화
//        menuList.add(new Menu("Burgers", "항생제와 호르몬제를 사용하지 않은 100% 앵거스 비프 통살을 다져 만든 패티와 쫄깃한 식감의 포테이토 번을 사용한 버거"));
//        menuList.add(new Menu("Flat-Top Dogs", "참나무 칩으로 훈연한 소시지와 포테이토 번을 사용한 핫 도그"));
//        menuList.add(new Menu("Frozen Custard", "매일 매장에서 신선하게 직접 만드는 부드럽고 진한 맛의 쫀득한 아이스크림"));
//        menuList.add(new Menu("Drinks", "매장에서 직접 만드는 신선한 음료"));
//
//        // 각 메뉴별 상품 초기화
//        ArrayList<Product> productList = new ArrayList<>();
//        productList.add(new Product("ShackBurger", "토마토, 양상추, 쉑소스가 토핑된 치즈버거", "W 6.9"));
//        productList.add(new Product("SmokeShack", "애플 우드 칩으로 훈연한 짭짤한 베이컨, 매콤한 체리 페퍼에 쉑소스가 토핑된 치즈 버거", "W 8.9"));
//        productList.add(new Product("Shroom Burger", "몬스터 치즈와 체다 치즈로 속을 채우고 바삭하게 튀겨낸 포토벨로 버섯 패티에 양상추, 토마토, 쉑소스를 올린 베지테리안 버거", "W 9.4"));
//        productList.add(new Product("ShackStack", "슈룸 버거와 쉑버거의 맛을 한번에 즐길 수 있는 메뉴", "W 12.4"));
//        productMap.put("Burgers", productList);
//
//        productList = new ArrayList<>();
//        productList.add(new Product("Shack-cago Dog", "쉑 랠리쉬 토핑과 다진 양파, 오이, 피클, 토마토, 스포트 페퍼, 샐러리 솔트, 머스터드 토핑을 풍성하게 올린 시카고식 핫 도그", "W 5.3"));
//        productList.add(new Product("Hot Dog", "비프 소시지가 들어간 담백한 핫 도그", "W 4.4"));
//        productList.add(new Product("Pork Dog", "포크 소시지가 들어간 담백한 핫 도그", "W 4.4"));
//        productMap.put("Flat-Top Dogs", productList);
//
//        productList = new ArrayList<>();
//        productList.add(new Product("Shakes", "바닐라, 초콜렛, 솔티드 카라멜, 블랙 & 화이트, 스트로베리, 피넛버터, 커피", "W 5.9"));
//        productList.add(new Product("Shake of the Week", "특별한 커스터드 플레이버", "W 6.5"));
//        productList.add(new Product("Red Bean Shake", "신선한 커스터드와 함께 우유와 레드번이 블렌딩 된 시즈널 쉐이크", "W 6.5"));
//        productList.add(new Product("Floats", "루트 비어, 퍼플 카우, 크림시클", "W 5.9"));
//        productMap.put("Frozen Custard", productList);
//
//        productList = new ArrayList<>();
//        productList.add(new Product("Shack-made Lemonade", "매장에서 직접 만드는 상큼한 레몬에이드(오리지널/시즈널)", "W 3.9"));
//        productList.add(new Product("Fresh Brewed Iced Tea", "직접 유기농 홍차를 우려낸 아이스티", "W 3.4"));
//        productList.add(new Product("Fifty/Fifty", "레몬에이드와 아이스티의 만남", "W 3.5"));
//        productList.add(new Product("Fountain Soda", "코카콜라, 코카콜라 제로, 스프라이트, 환타 오렌지", "W 2.7"));
//        productMap.put("Drinks", productList);
//    }
//
//    static void printMenu(){
//        StringBuilder sb = new StringBuilder();
//        sb.append("\"SHAKESHACK BURGER 에 오신 걸 환영합니다.\"\n");
//        sb.append("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n\n");
//
//        sb.append("[ SHAKESHACK MENU ]\n");
//        // 반복문으로 menuList 내용 불러오기
//        for(int i = 0; i < menuList.size(); i++){
//            sb.append((i+1)+ ". " + menuList.get(i).getMenu() + "\n");
//        }
//
//        sb.append("\n[ ORDER MENU ]\n");
//        sb.append("5. Order \t\t | 장바구니를 확인 후 주문합니다.\n");
//        sb.append("6. Cancel \t\t | 진행 중인 주문을 취소합니다.\n");
//        System.out.print(sb);
//    }
//
//    static void printProductMenu(String menuName){
//        StringBuilder sb = new StringBuilder();
//        sb.append("\"SHAKESHACK BURGER 에 오신 걸 환영합니다.\"\n");
//        sb.append("아래 상품메뉴판을 보시고 메뉴를 골라 입력해주세요.\n\n");
//
//        sb.append("[ " + menuName + " MENU ]\n");
//        ArrayList<Product> productList = productMap.get(menuName);
//        for(int i = 0; i < productList.size(); i++){
//            sb.append((i+1) + ". " + productList.get(i).getMenu() + "\n");
//        }
//        System.out.print(sb);
//    }
//
//    static void printAddOrder(String productInfo) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("\"" + productInfo + "\"\n");
//        sb.append("위 메뉴를 장바구니에 추가하시겠습니까?\n");
//        sb.append("1. 확인 \t\t 2. 취소\n");
//        System.out.print(sb);
//    }
//}
//*/
