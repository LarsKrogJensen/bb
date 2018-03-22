package se.lars;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class Blackbox {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        Router router = Router.router(vertx);
        router.get("/monitor/status").handler(rc -> {
            JsonObject json = new JsonObject();
            json.put("status", "RUNNING");

            rc.response().putHeader("Content-Type", "application/json").end(json.toBuffer());

        });

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080, ar -> {
                    if (ar.succeeded()) {
                        System.out.println("Successfully listening to port: " + ar.result().actualPort());
                    } else {
                        System.err.println("Failed to start, casue: " + ar.cause().getMessage());
                    }
                });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown");
        }));
    }
}
