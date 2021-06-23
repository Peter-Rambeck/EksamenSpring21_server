package rest;

import com.google.gson.Gson;
import dtos.DogDTO;
import dtos.OwnerDTO;
import dtos.WalkerDTO;
import entities.DogRepository;
import entities.OwnerRepository;
import entities.WalkerRepository;
import facades.DogFacade;
import facades.OwnerFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Response;
import rest.provider.Provider;
import facades.WalkerFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

//Todo Remove or change relevant parts before ACTUAL use
@Path("owner")
public class OwnerResource extends Provider {

    Gson gson = new Gson();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final OwnerRepository REPO = OwnerFacade.getOwnerFacade(EMF);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello Owner-man\"}";
    }

//    @Override
//    public Response getById(int id) {
//        DogDTO dogDTO = REPO.getById(id);
//        return Response.ok(GSON.toJson(dogDTO)).build();
//    }

    @GET
    @Path("/all")
    @Override
    public Response getAll() {
        List<OwnerDTO> ownerDTOS = REPO.getAll();
        return Response.ok(GSON.toJson(ownerDTOS)).build();
    }

//    @Override
//    public Response create(String jsonBody) {
//        DogDTO dogDTO = gson.fromJson(jsonBody, DogDTO.class);
//        DogDTO createdDog = REPO.createDog(dogDTO);
//        return Response.ok(createdDog).build();
//    }

//    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/edit")
//    public Response edit(String jsonBody) {
//        DogDTO dogDTO = gson.fromJson(jsonBody, DogDTO.class);
//        DogDTO editDog = REPO.edit(dogDTO);
//        return Response.ok(editDog).build();
//    }
//
//    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/add/{id}")
//    public Response addOwner(@PathParam("id") int id, String jsonBody) {
//        DogDTO dogDTO = gson.fromJson(jsonBody, DogDTO.class);
//        DogDTO addOwner = REPO.addOwner(id, dogDTO);
//        return Response.ok(addOwner).build(); 
//    }

    @Override
    @RolesAllowed("admin")
    public Response update(int id, String jsonBody) {
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }

//    @Override
//    // @RolesAllowed("admin")
//    public Response delete(int id) {
//        REPO.delete(id);
//        return Response.ok().build();
//    }

    @GET
    @Path("/populate")
    public Response poplate() {
        REPO.populate();
        return Response.ok("a few doggies - Hurra").build();
    }

    @Override
    public Response getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response create(String jsonBody) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
