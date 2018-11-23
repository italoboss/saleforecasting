package br.ufc.smd.italoboss.saleforecasting;

import java.util.HashMap;

/**
 *
 * @author italoboss
 */
public class SFInstance {
    
    // Parameters
    private int paymentMethod;
    private String type;
    private double discountValue;
    private String discountType;
    private String clientType;
    private String clientProfile;
    private String clientRegisterDate;
    private double totalCost;
    // Result column
    private String deal;

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientProfile() {
        return clientProfile;
    }

    public void setClientProfile(String clientProfile) {
        this.clientProfile = clientProfile;
    }

    public String getClientRegisterDate() {
        return clientRegisterDate;
    }

    public void setClientRegisterDate(String clientRegisterDate) {
        this.clientRegisterDate = clientRegisterDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }
    
    
    private static HashMap<String, Integer> paymentMethodMap = null;
    
    public static HashMap<String, Integer> getPaymentMethodMap() {
        if (paymentMethodMap != null)
            return paymentMethodMap;
        
        paymentMethodMap = new HashMap<>();
        paymentMethodMap.put("50% ANTECIPADO + 50% APÓS EVENTO", 0);
        paymentMethodMap.put("A COMBINAR", 1);
        paymentMethodMap.put("A PRAZO", 2);
        paymentMethodMap.put("A VISTA", 3);
        paymentMethodMap.put("A VISTA - AO TERMINO DO EVENTO", 4);
        paymentMethodMap.put("BOLETO", 5);
        paymentMethodMap.put("BOLETO - 05 DIAS APÓS EVENTO", 6);
        paymentMethodMap.put("BOLETO - 10 DIAS APÓS EVENTO", 7);
        paymentMethodMap.put("BOLETO - 15 DIAS APÓS EVENTO", 8);
        paymentMethodMap.put("BOLETO - 20 DIAS APÓS EVENTO", 9);
        paymentMethodMap.put("BOLETO - 25 DIAS APÓS EVENTO", 10);
        paymentMethodMap.put("BOLETO - 30 DIAS APÓS EVENTO", 11);
        paymentMethodMap.put("BOLETO - 60 DIAS APÓS EVENTO", 12);
        paymentMethodMap.put("BOLETO - 90 DIAS APÓS EVENTO", 13);
        paymentMethodMap.put("CHEQUE", 14);
        paymentMethodMap.put("CORTESIA", 15);
        paymentMethodMap.put("DÉBITO", 16);
        paymentMethodMap.put("DEPÓSITO", 17);
        paymentMethodMap.put("DEPÓSITO - 20 DIAS APÓS EVENTO", 18);
        paymentMethodMap.put("DEPÓSITO - 30 DIAS APÓS EVENTO", 19);
        paymentMethodMap.put("EMPENHO", 20);
        paymentMethodMap.put("PERMUTA", 21);
        paymentMethodMap.put("TRANSFERÊNCIA BANCÁRIA", 22);
        return paymentMethodMap;
    }
    
}
