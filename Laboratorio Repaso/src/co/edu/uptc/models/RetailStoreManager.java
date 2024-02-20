package co.edu.uptc.models;

import co.edu.uptc.structures.SimpleList;

public class RetailStoreManager {
    private SimpleList<RetailStore> storeSimpleList;

    public RetailStoreManager(SimpleList<RetailStore> storeSimpleList) {
        this.storeSimpleList = storeSimpleList;
    }

    public void createStore(String name, String address){
        RetailStore currentStore = new RetailStore(name, address);
        storeSimpleList.insert(currentStore);
    }

    public double seeChainValue(){

        double chainValue = 0;
        if (storeSimpleList != null){
            for (RetailStore currentStore : storeSimpleList){
                chainValue = chainValue + currentStore.totalCostOfProducts();
            }
        }

        return chainValue;
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
