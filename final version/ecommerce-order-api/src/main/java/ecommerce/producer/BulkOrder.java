package ecommerce.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ecommerce.lib.PaymentHash;
import ecommerce.service.CartService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BulkOrder {

    @Autowired
    private CartService cartService;

    private Long convertToLong(String id) {
        return Long.parseLong(id);
    }

    public void createBulkRequest() {
        cartService.addProduct(1L, 1L, 1);
        cartService.addProduct(2L, 2L, 2);
        cartService.addProduct(3L, 1L, 4);
        cartService.addProduct(4L, 2L, 3);
        cartService.addProduct(1L, 3L, 1);
        cartService.addProduct(2L, 4L, 6);
        cartService.addProduct(3L, 3L, 2);
        cartService.addProduct(4L, 4L, 1);
    }

    public String createBulkProcessRequest() throws JsonProcessingException {
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

                jsonString.append(mapper.writeValueAsString(paymentHash));
            }

            jsonString.append("]}");
        }
        jsonString.append("]");

        return jsonString.toString();
    }
}
