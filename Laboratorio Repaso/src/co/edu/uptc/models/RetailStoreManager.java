package co.edu.uptc.models;

import co.edu.uptc.structures.SimpleList;

public class RetailStoreManager {
    private SimpleList<RetailStore> storeSimpleList;

    public RetailStoreManager(SimpleList<RetailStore> storeSimpleList) {
        this.storeSimpleList = storeSimpleList;
    }

    public RetailStoreManager() {
        this.storeSimpleList = new SimpleList<>();
    }

    public SimpleList<RetailStore> getStoreSimpleList() {
        return storeSimpleList;
    }

    public void setStoreSimpleList(SimpleList<RetailStore> storeSimpleList) {
        this.storeSimpleList = storeSimpleList;
    }
}
