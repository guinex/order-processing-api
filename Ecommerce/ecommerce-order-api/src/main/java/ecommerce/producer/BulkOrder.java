package ecommerce.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ecommerce.lib.PaymentHash;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

// KAFKA CONFIG
// service related to send bulk request to kafka
@Service
public class BulkOrder {

    // converts to Long from string
    private Long convertToLong(String id) {
        return Long.parseLong(id);
    }

    // generates payment data for the request
    public String createBulkProcessRequest(){
        ObjectMapper mapper = new ObjectMapper();

        StringBuilder jsonString = new StringBuilder("[");
        List<JSONObject> obj = new ArrayList<JSONObject>();

        for (int i = 1; i <= 4; i++) {
            jsonString.append("{");
            jsonString.append("cartId: ").append(i).append(",");
            jsonString.append("paymentDetails: [");
            for (int j = 1; j <= 2; j++) {
                if (j == 2) {
                    jsonString.append(",");
                }
                PaymentHash paymentHash = new PaymentHash();
                paymentHash.setMethodName("asdsadasd");
                paymentHash.setConfirmationDate("12/02/11");
                paymentHash.setPayType("card");
                paymentHash.setTnxNumber("2343dsfsfsdf");
                paymentHash.setTotal(100L);
                paymentHash.setUserId((long) i);

                try {
                    jsonString.append(mapper.writeValueAsString(paymentHash));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            jsonString.append("]}");
        }
        jsonString.append("]");
        System.out.println(jsonString.toString());
        return jsonString.toString();
    }
}
