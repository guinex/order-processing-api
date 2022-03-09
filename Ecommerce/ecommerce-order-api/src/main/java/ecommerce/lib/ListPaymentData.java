package ecommerce.lib;

import java.util.ArrayList;
import java.util.List;

// ListPaymentData class helps defining the payment request
public class ListPaymentData {
    public Long id;
    public List<PaymentHash> paymentDetails;
    public Long getId(){
        return this.id;
    }

    public List<PaymentHash> getPaymentDetails(){
        return this.paymentDetails;
    }

    public String getPaymentHash(){
        return this.paymentDetails.toString();
    }

    // converts List<PaymentHash> object to string
    public String stringify(List<PaymentHash> paymentHashes){
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (PaymentHash paymentHash : paymentHashes) {
            str.append(paymentHash.toString());
        }
        str.append("]");
        return str.toString();
    }

    // KAFKA CONFIG
    // generate dummy payment data for orders
    public ListPaymentData generateData(Long id){
        ListPaymentData data = new ListPaymentData();
        data.id = id;
        List<PaymentHash> payments = new ArrayList<>();
        for(int i =0 ; i < 2; i++){
            PaymentHash paymentHash = new PaymentHash();
            paymentHash.setConfirmationDate("some-date");
            paymentHash.setMethodName("some-method");
            paymentHash.setPayType("some-type");
            paymentHash.setTotal(125L);
            paymentHash.setTnxNumber("some-tnx-number");
            paymentHash.setUserId(id);
            payments.add(paymentHash);
        }
        data.paymentDetails = payments;
        return data;
    }

    @Override
    public String toString() {
        return "{ id: " + id + ", paymentDetails: " + stringify(paymentDetails) + "}";
    }

    public ListPaymentData(){}

    public ListPaymentData(Long id, List<PaymentHash> paymentDetails) {
        this.id = id;
        this.paymentDetails = paymentDetails;
    }
}