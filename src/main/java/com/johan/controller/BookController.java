package com.johan.controller;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/books")
public class BookController {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get(){
        return "hello,m world";
    } 
}
