package ecommerce.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${KAFKA}")
    private String bootstrapAddress;

    @Value(value = "${orderCreate.topic.name}")
    private String orderCreateTopicName;

    @Value(value = "${orderCancel.topic.name}")
    private String orderCancelTopicName;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic1() {
        return new NewTopic(orderCreateTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic topic2() {
        return new NewTopic(orderCancelTopicName, 1, (short) 1);
    }

}
