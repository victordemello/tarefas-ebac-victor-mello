package com.mello.taskmod38.controller;

import com.mello.taskmod38.domain.Customer;
import com.mello.taskmod38.service.ICustomerService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class CustomerController {

    private ICustomerService customerService;

    public CustomerController() {

    }

    @Inject
    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @POST
    @Path("/create")
    public Response createCustomer(Customer customer) {
        customerService.registerCustomer(customer);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/listCustomer")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}
