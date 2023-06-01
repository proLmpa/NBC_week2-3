package Kiosk;

import Kiosk.ui.Home;
import Kiosk.entity.Menu;
import Kiosk.entity.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Kiosk {
    public static void main(String[] args) {
        Kiosk kiosk = new Kiosk();
        kiosk.startKiosk();
    }
    private void startKiosk() {
        ArrayList<Menu> menuList = initMenu();
        Map<Menu, ArrayList<Product>> productMap = initProduct(menuList);
        Home home = new Home(menuList, productMap);
        home.show();
    }

    // 메뉴 카테고리 초기화
    private ArrayList<Menu> initMenu(){
        ArrayList<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu("Burgers", "항생제와 호르몬제를 사용하지 않은 100% 앵거스 비프 통살을 다져 만든 패티와 쫄깃한 식감의 포테이토 번을 사용한 버거"));
        menuList.add(new Menu("Flat-Top Dogs", "참나무 칩으로 훈연한 소시지와 포테이토 번을 사용한 핫 도그"));
        menuList.add(new Menu("Frozen Custard", "매일 매장에서 신선하게 직접 만드는 부드럽고 진한 맛의 쫀득한 아이스크림"));
        menuList.add(new Menu("Drinks", "매장에서 직접 만드는 신선한 음료"));
        return menuList;
    }
    // 각 메뉴별 상품 초기화
    private Map<Menu, ArrayList<Product>> initProduct(ArrayList<Menu> menuList) {
        Map<Menu, ArrayList<Product>> productMap = new HashMap<>();
        ArrayList<Product> productList = new ArrayList<>(); // input: String  Menu.name -> output: ArrayList<Product> productList
        productList.add(new Product("ShackBurger", "토마토, 양상추, 쉑소스가 토핑된 치즈버거", 6.9f, 10.9f));
        productList.add(new Product("SmokeShack", "애플 우드 칩으로 훈연한 짭짤한 베이컨, 매콤한 체리 페퍼에 쉑소스가 토핑된 치즈 버거", 8.9f, 12.9f));
        productList.add(new Product("Shroom Burger", "몬스터 치즈와 체다 치즈로 속을 채우고 바삭하게 튀겨낸 포토벨로 버섯 패티에 양상추, 토마토, 쉑소스를 올린 베지테리안 버거", 9.4f));
        productList.add(new Product("ShackStack", "슈룸 버거와 쉑버거의 맛을 한번에 즐길 수 있는 메뉴", 12.4f));
        productMap.put(menuList.get(0), productList);      // "Burgers" -> Burger's productList

        productList = new ArrayList<>();
        productList.add(new Product("Shack-cago Dog", "쉑 랠리쉬 토핑과 다진 양파, 오이, 피클, 토마토, 스포트 페퍼, 샐러리 솔트, 머스터드 토핑을 풍성하게 올린 시카고식 핫 도그", 5.3f));
        productList.add(new Product("Hot Dog", "비프 소시지가 들어간 담백한 핫 도그", 4.4f));
        productList.add(new Product("Pork Dog", "포크 소시지가 들어간 담백한 핫 도그", 4.4f));
        productMap.put(menuList.get(1), productList);

        productList = new ArrayList<>();
        productList.add(new Product("Shakes", "바닐라, 초콜렛, 솔티드 카라멜, 블랙 & 화이트, 스트로베리, 피넛버터, 커피", 5.9f));
        productList.add(new Product("Shake of the Week", "특별한 커스터드 플레이버", 6.5f));
        productList.add(new Product("Red Bean Shake", "신선한 커스터드와 함께 우유와 레드번이 블렌딩 된 시즈널 쉐이크", 6.5f));
        productList.add(new Product("Floats", "루트 비어, 퍼플 카우, 크림시클", 5.9f));
        productMap.put(menuList.get(2), productList);

        productList = new ArrayList<>();
        productList.add(new Product("Shack-made Lemonade", "매장에서 직접 만드는 상큼한 레몬에이드(오리지널/시즈널)", 3.9f, 4.5f));
        productList.add(new Product("Fresh Brewed Iced Tea", "직접 유기농 홍차를 우려낸 아이스티", 3.4f, 3.9f));
        productList.add(new Product("Fifty/Fifty", "레몬에이드와 아이스티의 만남", 3.5f, 4.4f));
        productList.add(new Product("Fountain Soda", "코카콜라, 코카콜라 제로, 스프라이트, 환타 오렌지", 2.7f, 3.3f));
        productMap.put(menuList.get(3), productList);
        return productMap;
    }

//    ArrayList<Menu> menuList => {
//        0 -> Menu("Burgers", "항생제와 호르몬제를 사용하지 않은 100% 앵거스 비프 통살을 다져 만든 패티와 쫄깃한 식감의 포테이토 번을 사용한 버거")
//        1 -> Menu("Flat-Top Dogs", "참나무 칩으로 훈연한 소시지와 포테이토 번을 사용한 핫 도그")
//        2 -> Menu("Frozen Custard", "매일 매장에서 신선하게 직접 만드는 부드럽고 진한 맛의 쫀득한 아이스크림")
//        3 -> Menu("Drinks", "매장에서 직접 만드는 신선한 음료")
//    }

//    Map<Menu, ArrayList<Product>> => {
//        Menu("Burgers", "...")      -> ArrayList<Product> = { Product("ShackBurger", "..", ".."), Product("SmokeShack", "..", ".."), ...}
//        Menu("Flat-top Dogs", "..") -> ArrayList<Product> = { Product("Shack-cago Dog", "..", ".."), Product("Hot Dog", "..", ".."), ...}
//        Menu("Shakes", "..")       -> ArrayList<Product> = { Product("Shakes", "..", ".."), Product("Shake of the Week", "..", ".."),  ...}
//        Menu("Drinks", "..") -> ArrayList<Product> = { Product("Fountain Soda", "..", ".."), Product("Fifty/Fifty", "..", ".."), ...}
//    }

}
