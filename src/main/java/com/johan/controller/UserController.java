package com.johan.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/users")
public class UserController {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get(){
        return "hello,m world";
    }
}
