package entities;


import dtos.WalkerDTO;
import java.util.List;
import javax.ws.rs.WebApplicationException;

public interface WalkerRepository {

    public WalkerDTO getById(int id) throws WebApplicationException;

    public List<WalkerDTO> getAll() throws WebApplicationException;

    public WalkerDTO createWalker(WalkerDTO walkerDTO) throws WebApplicationException;
    
    public void populate() throws WebApplicationException;

    
}
