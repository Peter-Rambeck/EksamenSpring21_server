package entities;


import dtos.DogDTO;
import java.util.List;
import javax.ws.rs.WebApplicationException;

public interface DogRepository {

    public DogDTO getById(int id) throws WebApplicationException;

    public List<DogDTO> getAll() throws WebApplicationException;

    public DogDTO createDog(DogDTO dogDTO) throws WebApplicationException;
    
    public DogDTO edit(DogDTO dogDTO) throws WebApplicationException;
    
    public void populate() throws WebApplicationException;

    
    
}
