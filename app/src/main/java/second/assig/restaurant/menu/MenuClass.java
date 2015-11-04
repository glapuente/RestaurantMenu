package second.assig.restaurant.menu;

import java.io.Serializable;

import java.io.Serializable;

public class MenuClass implements Serializable {

    private static final long serialVersionUID = 1L;
    String firstcourse;
    String secondcourse;
    String dessert;

    public void setfirst(String course) {
        firstcourse= course;
    }

    public String firstc() {
        return firstcourse;
    }

    public void setsecond(String course) {
        secondcourse= course;
    }

    public String secondc() {
        return secondcourse;
    }

    public void setdessert(String course) {
        dessert= course;
    }

    public String dessertc() {
        return dessert;
    }
}
