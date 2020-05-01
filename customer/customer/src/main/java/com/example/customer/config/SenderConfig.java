package com.example.customer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;



import com.example.customer.model.Customer;
import com.example.customer.producer.Sender;

/**
	 * Here we configured to send messages to Kafka server on localhost:9092 (bootstrapServers – taken from cloud config server).
	 */

@Configuration
public class SenderConfig {

	    @Value("${spring.kafka.bootstrap-servers}")
	    private String bootstrapServers;

	    @Bean
	    public Map<String, Object> producerConfigs() {
	        Map<String, Object> props = new HashMap<>();
	        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

	        return props;
	    }

	    // sets strategy for creating instance of Producer
	    @Bean
	    public ProducerFactory<String, Customer> producerFactory() {
	        return new DefaultKafkaProducerFactory<>(producerConfigs());
	    }

	    @Bean
	    public KafkaTemplate<String, Customer> kafkaTemplate() {
	        return new KafkaTemplate<>(producerFactory());
	    }

	    @Bean
	    public Sender sender() {
	        return new Sender();
	    }
	}