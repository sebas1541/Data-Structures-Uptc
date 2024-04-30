package co.edu.uptc.models;

import co.edu.uptc.structures.MyAvlTree;
import co.edu.uptc.structures.StringComparator;

import java.util.List;

public class Municipality {
    private String name;
    private MyAvlTree<String> inhabitants;

    public Municipality(String name) {
        this.name = name;
        this.inhabitants = new MyAvlTree<>(new StringComparator());
    }

    public String getName() {
        return name;
    }

    public void addInhabitant(String inhabitantName) throws Exception {
        this.inhabitants.insert(inhabitantName);
    }


    public List<String> getInhabitantNames() {
        return inhabitants.inOrder();
    }


    public int getInhabitantCount() {
        return inhabitants.inOrder().size();
    }
}
