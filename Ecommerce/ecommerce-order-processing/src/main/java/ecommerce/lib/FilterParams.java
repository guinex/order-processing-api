package ecommerce.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import java.io.IOException;


public class FilterParams {
    public static Long filterById(JSONObject entity) {
        System.out.println(entity.get("id"));
        return Long.parseLong((String) entity.get("id"));
    }

    public static PaymentData filterPaymentParams(JSONObject entity) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(entity.toString(), PaymentData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new PaymentData();
    }

    public static ListPaymentData filterListPaymentParams(JSONObject entity) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(entity.toString(), ListPaymentData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ListPaymentData();
    }
}
