package com.andy
package controllers

import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}
import reactor.core.publisher.Mono

@RestController
@RequestMapping(Array("/reactive"))
class SampleReactiveController {

  @GetMapping(Array("/hello"))
  def sayHello(): Mono[String] = {

    Mono.just("Hello from Spring WebFlux with Jetty!")
  }

}
