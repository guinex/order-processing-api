package ecommerce.kafka;

import ecommerce.lib.ListPaymentData;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${KAFKA}")
    private String bootstrapAddress;

    // Listner for create orders
    public ConsumerFactory<String, ListPaymentData> orderCreateConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "orders");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(ListPaymentData.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ListPaymentData> orderCreateKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ListPaymentData> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderCreateConsumerFactory());
        return factory;
    }

    // Listner for cancel orders
    public ConsumerFactory<String, Long> orderCancelConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "cancel");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Long.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Long> orderCancelKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Long> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderCancelConsumerFactory());
        return factory;
    }

}
