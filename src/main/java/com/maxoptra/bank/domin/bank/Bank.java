package com.maxoptra.bank.domin.bank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxoptra.bank.domin.utils.CardUtils;

import java.util.Comparator;
import java.util.Date;

public class Bank implements Comparable<Bank>{

    private String bankName;

    private Long cardNumber;

    private Date expiryDate;

    private String obfuscatedCardNumber;

    public Bank(String bankName, Long cardNumber, Date expiryDate){
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.obfuscatedCardNumber = CardUtils.obfuscateCardNumber(cardNumber);
    }

    public String getBankName() {
        return this.bankName;
    }

    @JsonIgnore
    @JsonProperty(value = "cardNumber")
    public long getCardNumber() {
        return this.cardNumber;
    }

    public String getObfuscatedCardNumber(){
        return this.obfuscatedCardNumber;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setObfuscatedCardNumber(String obfuscatedCardNumber) {
        this.obfuscatedCardNumber = obfuscatedCardNumber;
        this.obfuscatedCardNumber = CardUtils.obfuscateCardNumber(cardNumber);
    }

    @Override
    public int hashCode() {
        return cardNumber.intValue() * bankName.hashCode() * expiryDate.hashCode();
    }

    @Override
    public boolean equals(Object b) {
        if (this == b) return true;
        if (b == null) return false;
        if (this.getClass() != b.getClass()) return false;
        return cardNumber.equals(((Bank) b).cardNumber)
                && expiryDate == ((Bank) b).expiryDate
                && bankName.equals(((Bank) b).bankName);
    }

    @Override
    public int compareTo(Bank bank) {
        return Comparator.comparing(Bank::getBankName)
                .thenComparing(Bank::getCardNumber)
                .thenComparing(Bank::getExpiryDate)
                .compare(this, bank);
    }
}
