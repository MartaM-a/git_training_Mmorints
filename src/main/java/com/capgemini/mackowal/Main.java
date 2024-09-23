package com.capgemini.mackowal;
import io.javalin.Javalin;
public class Main {
    public static void main(String[] args){
        Javalin app = Javalin.create().start(8000);
                                                                    !
        app.get("/user1", ctx->ctx.result("Adam Nowak"));
        app.get("/user2",ctx->ctx.result("Jan Nowak"));
		app.get("/user3",ctx->ctx.result("Jan Kowalski"));
        app.get("/user4",ctx->ctx.result("Adam Nowak"));


    }
}
