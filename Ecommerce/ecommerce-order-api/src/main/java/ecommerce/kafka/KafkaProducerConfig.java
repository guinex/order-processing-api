package ecommerce.kafka;

import ecommerce.lib.ListPaymentData;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// KAFKA CONFIG
// Kafka producer settings
@Configuration
public class KafkaProducerConfig {

    @Value(value = "${KAFKA}")
    private String bootstrapAddress;
    @Bean
    public ProducerFactory<String, ListPaymentData> paymentInfoProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public ProducerFactory<String, Long> bulkOrderCancelFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    // generating a new templates

    @Bean
    public KafkaTemplate<String, ListPaymentData> bulkCreateOrderKafkaTemplate() {
        return new KafkaTemplate<>(paymentInfoProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, Long> bulkOrderCancelKafkaTemplate(){
        return new KafkaTemplate<>(bulkOrderCancelFactory());
    }

}
