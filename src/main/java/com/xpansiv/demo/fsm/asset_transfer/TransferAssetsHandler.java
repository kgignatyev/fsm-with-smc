package com.xpansiv.demo.fsm.asset_transfer;

public class TransferAssetsHandler {

    public String fromId;
    public String toId;
    public String ein;
    public Long quantity;


    public TransferAssetsHandler( String fromId, String toId, String ein, Long quantity) {
        this.fromId = fromId;
        this.toId = toId;
        this.ein = ein;
        this.quantity = quantity;
    }

    public void doSenderFundsLock(){

    }

    public void doRequestReceiverApproval(){

    }

    public boolean hasSufficientFunds(){
        return true;
    }

    public void onPlacementRejection() {
    }

    public void onPlacementToCp(){

    }

    public void removeAssetsFromSender() {
    }

    public void returnAssetsToSender() {
    }

    public Boolean needsCPapproval() { return false; }

}
