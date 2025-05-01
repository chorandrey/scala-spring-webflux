package com.andy


import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.EnableWebFlux

@Configuration
@EnableWebFlux
@ComponentScan(basePackages = Array("com.andy.controllers"))
class WebConfig {


}
