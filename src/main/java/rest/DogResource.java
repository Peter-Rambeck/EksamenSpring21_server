package rest;

import com.google.gson.Gson;
import dtos.DogDTO;
import dtos.WalkerDTO;
import entities.DogRepository;
import entities.WalkerRepository;
import facades.DogFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Response;
import rest.provider.Provider;
import facades.WalkerFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

//Todo Remove or change relevant parts before ACTUAL use
@Path("dog")
public class DogResource extends Provider {

    Gson gson = new Gson();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final DogRepository REPO = DogFacade.getDogFacade(EMF);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello Dog-man\"}";
    }

    @Override
    public Response getById(int id) {
        DogDTO dogDTO = REPO.getById(id);
        return Response.ok(GSON.toJson(dogDTO)).build();
    }

    @GET
    @Path("/all")
    @Override
    public Response getAll() {
        List<DogDTO> dogDTOS = REPO.getAll();
        return Response.ok(GSON.toJson(dogDTOS)).build();
    }

    @Override
    public Response create(String jsonBody) {
        DogDTO dogDTO = gson.fromJson(jsonBody, DogDTO.class);
        DogDTO createdDog = REPO.createDog(dogDTO);
        return Response.ok(createdDog).build();
    }
    
    
    @PUT
    @Path("/edit")
    public Response edit(String jsonBody) {
        DogDTO dogDTO = gson.fromJson(jsonBody, DogDTO.class);
        DogDTO editDog = REPO.edit(dogDTO);
        return Response.ok(editDog).build();        
    }

    @Override
    @RolesAllowed("admin")
    public Response update(int id, String jsonBody) {
        
        
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    @RolesAllowed("admin")
    public Response delete(int id) {
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @GET
    @Path("/populate")
    public Response poplate() {
        REPO.populate();
        return Response.ok("a few doggies - Hurra").build();
    }
}
