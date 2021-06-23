package rest;

import com.google.gson.Gson;
import dtos.WalkerDTO;
import dtos.WalkerRepository;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Response;
import rest.provider.Provider;
import facades.WalkerFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

//Todo Remove or change relevant parts before ACTUAL use
@Path("walker")
public class WalkerResource extends Provider {
    
     Gson gson = new Gson();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final WalkerRepository REPO = WalkerFacade.getWalkerFacade(EMF);
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello Walker-man\"}";
    }

    @Override
    public Response getById(int id) {
        WalkerDTO walkerDTO = REPO.getById(id);
        return Response.ok(GSON.toJson(walkerDTO)).build();
    }

    @GET
        @Path("/all")
    @Override
    public Response getAll() {
        List<WalkerDTO> walkerDTOS = REPO.getAll();
        return Response.ok(GSON.toJson(walkerDTOS)).build();
    }

    @Override
    public Response create(String jsonBody) {
        WalkerDTO walkerDTO = gson.fromJson(jsonBody, WalkerDTO.class);
        WalkerDTO createdWalker = REPO.createWalker(walkerDTO);
        return Response.ok(createdWalker).build();
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
        return Response.ok("a few walkers - Hurra").build();
    }
}
