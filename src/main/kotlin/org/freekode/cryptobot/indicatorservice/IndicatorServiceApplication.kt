package org.freekode.cryptobot.indicatorservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@SpringBootApplication
@EnableScheduling
@PropertySources(
    PropertySource("classpath:application.yaml"),
    PropertySource(value = ["file:\${user.home}/price-service.properties"], ignoreResourceNotFound = true)
)
class PriceServiceApplication {

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<PriceServiceApplication>(*args)
}
