package facades;

import dtos.DogDTO;
import dtos.OwnerDTO;
import dtos.RenameMeDTO;
import dtos.WalkerDTO;
import entities.Dog;
import entities.DogRepository;
import entities.Owner;
import entities.OwnerRepository;
import entities.WalkerRepository;
import entities.Walker;
import entities.renameme.RenameMe;
import entities.renameme.RenameMeRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class OwnerFacade implements OwnerRepository {

    private static OwnerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private OwnerFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static OwnerFacade getOwnerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OwnerFacade();
        }
        return instance;
    }

//    @Override
//    public OwnerDTO addDog(List<DogDTO> dogdtos) throws WebApplicationException {
//        OwnerDTO ownerDTO;
//        for (DogDTO x: dogdtos) {
//            dogdtos.add(x);
//        }
//        return 
//        
//    }

    @Override
    public void populate() {

        EntityManager em = emf.createEntityManager();
        Owner owner1 = new Owner("Owner1", "address1", "address1", 12345678);
        Owner owner2 = new Owner("Owner2", "address2", "address2", 12345678);
        Owner owner3 = new Owner("Owner3", "address3", "address3", 12345678);

        try {
            em.getTransaction().begin();
            em.persist(owner1);
            em.persist(owner2);
            em.persist(owner3);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new WebApplicationException("Populate went wrong");
        } finally {
            em.close();
        }
    }

//    @Override
//    public DogDTO edit(DogDTO dogDTO) throws WebApplicationException {
//        
//        EntityManager em = emf.createEntityManager();
//        Dog dog;
//        
//        System.out.println("ID: " + dogDTO.getId());
//         
//        try {
//            em.getTransaction().begin();
//            
//            dog = em.find(Dog.class, dogDTO.getId());
//            System.out.println("Dog: " + dog);
//            
//            dog.setName(dogDTO.getName());
//            dog.setBreed(dogDTO.getBreed());
//            dog.setImage(dogDTO.getImage());
//            dog.setGender(dogDTO.getGender());
//            dog.setBirthdate(dogDTO.getBirthdate());
//            
//            em.persist(dog);
//            em.getTransaction().commit();
//        } catch (RuntimeException e) {
//            throw new WebApplicationException("not found - NÅ");
//        } finally {
//            em.close();
//        }
//        return new DogDTO(dog);
//        
//    }
//    @Override
//    public void delete(int id) throws WebApplicationException {
//        
//        EntityManager em = emf.createEntityManager();
//        Dog dog;
//                 
//        try {
//            em.getTransaction().begin();
//            dog = em.find(Dog.class, id);
//            em.remove(dog);
//            em.getTransaction().commit();
//        } catch (RuntimeException e) {
//            throw new WebApplicationException("not found - NÅ");
//        } finally {
//            em.close();
//        }
//       
//    }

    @Override
    public OwnerDTO addDog(DogDTO dogDTO) throws WebApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
