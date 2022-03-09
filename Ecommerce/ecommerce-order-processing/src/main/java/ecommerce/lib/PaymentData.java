package ecommerce.lib;

import java.util.ArrayList;
import java.util.List;

public class PaymentData {
    public String id;
    public PaymentHash paymentDetails;

    public PaymentData(){}

    public PaymentHash getPaymentDetails() {
        return this.paymentDetails;
    }

}
