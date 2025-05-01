package com.andy

import org.eclipse.jetty.server.{Handler, Server, ServerConnector}
import org.eclipse.jetty.util.thread.QueuedThreadPool
import org.springframework.http.server.reactive.{HttpHandler, JettyCoreHttpHandlerAdapter}
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.server.adapter.WebHttpHandlerBuilder


object Main {

  private val serverPort = 8080

  def main(args: Array[String]): Unit = {
    println("Hello, World!")

    // Create and configure a ThreadPool.// Create and configure a ThreadPool.
    val threadPool = new QueuedThreadPool()
    threadPool.setName("jetty-thread-pool")

    // Create a Server instance.
    val server = new Server(threadPool)

    // Create a ServerConnector to accept connections from clients.
    val connector = new ServerConnector(server)
    connector.setPort(serverPort)
    connector.setHost("localhost")

    // Add the Connector to the Server
    server.addConnector(connector)

    // Spring context
    val webAppContext = new AnnotationConfigWebApplicationContext()
    webAppContext.register(classOf[WebConfig])
    webAppContext.refresh()

//    val servletContextHandler = new ServletContextHandler()
//    servletContextHandler.setContextPath("/")
//    servletContextHandler.addEventListener(new ContextLoaderListener(webAppContext))
//
//    val dispatcherServlet = new DispatcherServlet(webAppContext)
//    val servletHolder = new ServletHolder("dispatcher", dispatcherServlet)
//    servletContextHandler.addServlet(servletHolder, "/app1/*")
//
//    server.setHandler(servletContextHandler)

    val springHttpReactiveHandler: HttpHandler = WebHttpHandlerBuilder.applicationContext(webAppContext).build()
    val jettyHandler: Handler = new JettyCoreHttpHandlerAdapter(springHttpReactiveHandler)
    server.setHandler(jettyHandler)

    // Start the Server to start accepting connections from clients.
    server.start()

    try {
      server.join()
    } catch {
      case ex: InterruptedException =>
        ex.printStackTrace()
        println("Server shutting down.")
        server.stop()
    }
  }

}
