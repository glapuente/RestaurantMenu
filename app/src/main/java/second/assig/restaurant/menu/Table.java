package second.assig.restaurant.menu;

/**
 * Created by Guillermo on 04/11/2015.
 */
public class Table {
    private int served;
    private int commensals;
    private int tbl_num;
    private MenuClass menu;

    public Table(int tbl_num, int commensals, String menuText, int i) {
        tbl_num = tbl_num;
        commensals = commensals;
        served = i;
    }


    public int getServed() {
        return served;
    }

    public void setServed(int served) {
        this.served = served;
    }

    public int getCommensals() {
        return commensals;
    }

    public void setCommensals(int commensals) {
        this.commensals = commensals;
    }

    public MenuClass getMenu() {
        return menu;
    }

    public String getContent(){
        String content = "First course: "+getMenu().firstcourse +"\n"+
                "Second course: "+getMenu().secondcourse +"\n"+
                "Dessert: "+getMenu().dessert;
        return content;
    }

    public int getTbl_num() {
        return tbl_num;
    }

    public void setTbl_num(int tbl_num) {
        this.tbl_num = tbl_num;
    }
}
