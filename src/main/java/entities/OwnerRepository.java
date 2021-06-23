package entities;


import dtos.DogDTO;
import dtos.OwnerDTO;
import java.util.List;
import javax.ws.rs.WebApplicationException;

public interface OwnerRepository {

    // public DogDTO getById(int id) throws WebApplicationException;

    public List<OwnerDTO> getAll() throws WebApplicationException;

    // public DogDTO createDog(DogDTO dogDTO) throws WebApplicationException;
    
    // public DogDTO edit(DogDTO dogDTO) throws WebApplicationException;
    
    public void populate() throws WebApplicationException;
    
    // public void delete(int id) throws WebApplicationException;

    public OwnerDTO addDog(DogDTO dogDTO) throws WebApplicationException;
    
    
}
