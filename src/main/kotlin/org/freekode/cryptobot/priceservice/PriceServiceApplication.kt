package org.freekode.cryptobot.priceservice

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.apache.activemq.ActiveMQConnectionFactory
import org.freekode.cryptobot.priceservice.domain.alert.AlertTriggeredEvent
import org.freekode.cryptobot.priceservice.domain.price.PlatformPriceEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.config.JmsListenerContainerFactory
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.support.converter.MappingJackson2MessageConverter
import org.springframework.jms.support.converter.MessageConverter
import org.springframework.jms.support.converter.MessageType
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.jms.ConnectionFactory


@SpringBootApplication
@EnableJms
@EnableScheduling
@PropertySources(
    PropertySource("classpath:application.properties"),
    PropertySource(value = ["file:\${user.home}/price-service.properties"], ignoreResourceNotFound = true)
)
class PriceServiceApplication(
    @Value("\${event.broker-url}") private val brokerUrl: String
) {

    private val log: Logger = LoggerFactory.getLogger(PriceServiceApplication::class.java)

    @Bean
    fun jmsTemplate(
        connectionFactory: ConnectionFactory,
        messageConverter: MessageConverter
    ): JmsTemplate {
        val jmsTemplate = JmsTemplate()
        jmsTemplate.connectionFactory = connectionFactory
        jmsTemplate.messageConverter = messageConverter()
        jmsTemplate.isPubSubDomain = true
        return jmsTemplate
    }

    @Bean
    fun jmsListenerContainerFactory(
        connectionFactory: ConnectionFactory,
        configurer: DefaultJmsListenerContainerFactoryConfigurer
    ): JmsListenerContainerFactory<*> {
        val factory = DefaultJmsListenerContainerFactory()
        configurer.configure(factory, connectionFactory)
        factory.setPubSubDomain(true)
        return factory
    }

    @Bean
    fun connectionFactory(): ConnectionFactory {
        log.info("ActiveMQ Broker URL $brokerUrl")

        val factory = ActiveMQConnectionFactory()
        factory.brokerURL = brokerUrl
        return factory
    }

    @Bean
    fun messageConverter(): MessageConverter {
        val mapper = ObjectMapper().registerModule(KotlinModule())
        val converter = MappingJackson2MessageConverter()
        converter.setObjectMapper(mapper)
        converter.setTargetType(MessageType.TEXT)
        converter.setTypeIdPropertyName("_type")
        converter.setTypeIdMappings(getMessageConverterTypeMappings())
        return converter
    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
            }
        }
    }

    private fun getMessageConverterTypeMappings(): Map<String?, Class<*>> {
        return mapOf(
            PlatformPriceEvent::class.simpleName to PlatformPriceEvent::class.java,
            AlertTriggeredEvent::class.simpleName to AlertTriggeredEvent::class.java
        )
    }
}

fun main(args: Array<String>) {
    runApplication<PriceServiceApplication>(*args)
}
