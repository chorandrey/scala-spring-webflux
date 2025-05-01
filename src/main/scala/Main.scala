package com.andy

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter
import org.springframework.web.server.adapter.WebHttpHandlerBuilder
import reactor.netty.http.server.HttpServer


object Main {

  private val serverPort = 8080

  def main(args: Array[String]): Unit = {

    val context = new AnnotationConfigApplicationContext()
    context.register(classOf[WebConfig])
    context.refresh()

    val dispatcherHandler = WebHttpHandlerBuilder
      .applicationContext(context)
      .build()

    val adapter = new ReactorHttpHandlerAdapter(dispatcherHandler)

    HttpServer.create()
      .host("localhost")
      .port(serverPort)
      .handle(adapter)
      .bindNow()

    System.out.println(s"Netty server running at port ${serverPort}")
    Thread.currentThread().join()
  }
}
