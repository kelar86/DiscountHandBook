package com.example.alexey.discounthandbook.data;

/**
 * Created by alexey on 24.08.17.
 */

public class Client {
    private String cardNumber;
    private String name;
    private String discountReason;
    private int discount;

    private Client(){}

    public class ClientBuilder {
        private ClientBuilder() {
        }

        public ClientBuilder setCardNumber(String cardNumber){
            Client.this.cardNumber = cardNumber;
            return this;
        }
        public ClientBuilder setName(String name){
            Client.this.name = name;
            return this;
        }
        public ClientBuilder setDiscountReason(String discountReason){
            Client.this.discountReason = discountReason;
            return this;
        }
        public ClientBuilder setDiscount(int discount){
            Client.this.discount = discount;
            return this;
        }

        public ClientBuilder build(){
            return ClientBuilder.this;
        }


    }


    public String getCardNumber() {
        return cardNumber;
    }

    public String getName() {
        return name;
    }

    public String getDiscountReason() {
        return discountReason;
    }

    public int getDiscount() {
        return discount;
    }
}
