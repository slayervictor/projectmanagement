// Victor
package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class timetester {

    private static timetester instance;  // Singleton instance
    private ObservableList<String> items = FXCollections.observableArrayList();

    private timetester() {
        // Private constructor to prevent instantiation
    }

    public static timetester getInstance() {
        if (instance == null) {
            instance = new timetester();
        }
        return instance;
    }
    // Private constructor prevents instantiation from other classes

    public ObservableList<String> getItems()
    {
        return items;
    }

    public void addItem(String S)
    {
        items.add(S);
        System.out.println(items);
    }
    public ObservableList<String> getProjItems()
    {
        //get real projects here:
        return FXCollections.observableArrayList("proj1","proj2","proj3","proj4");
    }
}
