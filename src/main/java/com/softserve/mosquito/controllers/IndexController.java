package com.softserve.mosquito.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
public class IndexController {

    @GET
    @Produces("text/plain")
    public String testIndex() {
        return "Hello Mosquito";
    }

}
