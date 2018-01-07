package de.lenic.serverservice.spigot.server;

import de.lenic.serverservice.spigot.config.ServerConfig;
import de.lenic.serverservice.spigot.server.handler.ServerServiceRoute;
import de.lenic.serverservice.spigot.server.resources.filters.AuthFilter;
import de.lenic.serverservice.spigot.server.resources.filters.ContentTypeFilter;
import spark.ExceptionHandler;
import spark.Filter;
import spark.ResponseTransformer;
import spark.Spark;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static spark.Spark.*;

public class ServerManager {

    private static final String CONTENT_TYPE = "application/json";

    private ServerConfig config;
    private Map<String, ServerServiceRoute> routes;


    public ServerManager(ServerConfig config) {
        this.config = config;
        this.routes = new ConcurrentHashMap<>();
    }


    /**
     * Start the server
     */
    public void startServer() {
        ipAddress(config.getAddress().getHostString());
        port(config.getAddress().getPort());
        init();

        // Add authentication filter
        Spark.after(new AuthFilter());

        // Add content type filter
        Spark.after(new ContentTypeFilter(CONTENT_TYPE));
    }

    /**
     * Stop the server
     */
    public void close() {
        stop();
    }


    private void putRoute(String path, ServerServiceRoute route) {
        routes.put(path, route);
    }


    public void get(String path, ServerServiceRoute route) {
        Spark.get(path, route);
        putRoute(path, route);
    }

    public void get(String path, ServerServiceRoute route, ResponseTransformer transformer) {
        Spark.get(path, route, transformer);
        putRoute(path, route);
    }

    public void head(String path, ServerServiceRoute route) {
        Spark.head(path, route);
        putRoute(path, route);
    }

    public void head(String path, ServerServiceRoute route, ResponseTransformer transformer) {
        Spark.head(path, route, transformer);
        putRoute(path, route);
    }

    public void post(String path, ServerServiceRoute route) {
        Spark.post(path, route);
        putRoute(path, route);
    }

    public void post(String path, ServerServiceRoute route, ResponseTransformer transformer) {
        Spark.post(path, route, transformer);
        putRoute(path, route);
    }

    public void put(String path, ServerServiceRoute route) {
        Spark.put(path, route);
        putRoute(path, route);
    }

    public void put(String path, ServerServiceRoute route, ResponseTransformer transformer) {
        Spark.put(path, route, transformer);
        putRoute(path, route);
    }

    public void delete(String path, ServerServiceRoute route) {
        Spark.delete(path, route);
        putRoute(path, route);
    }

    public void delete(String path, ServerServiceRoute route, ResponseTransformer transformer) {
        Spark.delete(path, route, transformer);
        putRoute(path, route);
    }

    public void connect(String path, ServerServiceRoute route) {
        Spark.connect(path, route);
        putRoute(path, route);
    }

    public void connect(String path, ServerServiceRoute route, ResponseTransformer transformer) {
        Spark.connect(path, route, transformer);
        putRoute(path, route);
    }

    public void options(String path, ServerServiceRoute route) {
        Spark.options(path, route);
        putRoute(path, route);
    }

    public void options(String path, ServerServiceRoute route, ResponseTransformer transformer) {
        Spark.options(path, route, transformer);
        putRoute(path, route);
    }

    public void trace(String path, ServerServiceRoute route) {
        Spark.trace(path, route);
        putRoute(path, route);
    }

    public void trace(String path, ServerServiceRoute route, ResponseTransformer transformer) {
        Spark.trace(path, route, transformer);
        putRoute(path, route);
    }

    public void patch(String path, ServerServiceRoute route) {
        Spark.patch(path, route);
        putRoute(path, route);
    }

    public void patch(String path, ServerServiceRoute route, ResponseTransformer transformer) {
        Spark.patch(path, route, transformer);
        putRoute(path, route);
    }


    public void addFilterBefore(String path, Filter filter) {
        Spark.before(path, filter);
    }

    public void addFiltersBefore(String path, Filter... filters) {
        Spark.before(path, filters);
    }

    public void addFilterAfter(String path, Filter filter) {
        Spark.after(path, filter);
    }

    public void addFiltersAfter(String path, Filter... filters) {
        Spark.after(path, filters);
    }


    public <T extends Exception> void addExceptionHandler(Class<T> exceptionClass, ExceptionHandler<? super T> handler) {
        Spark.exception(exceptionClass, handler);
    }


    public void addWebSocketHandler(String path, Class<?> handler) {
        Spark.webSocket(path, handler);
    }

    public void addWebSocketHandler(String path, Object handler) {
        Spark.webSocket(path, handler);
    }


    public ServerServiceRoute getRoute(String path) {
        return routes.get(path);
    }

}
