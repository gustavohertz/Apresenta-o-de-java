package org.example;

public class Customer {
    private String name = null;
    private String postcode = null;
    private int telephone = 0;
    private int customerId = 0;

    private static int customerCount = 001;

    public Customer(String cName, String pcode, int tel){
            name = cName;
            postcode = pcode;
            telephone = tel;
            customerId = customerCount++;
    }
    public int getCustomerId(){
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPostcode() {
        return postcode;
    }

    public int getTelephone() { return telephone;}
}
