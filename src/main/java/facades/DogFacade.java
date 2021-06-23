package facades;

import dtos.DogDTO;
import dtos.RenameMeDTO;
import dtos.WalkerDTO;
import entities.Dog;
import entities.DogRepository;
import entities.Owner;
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
public class DogFacade implements DogRepository {

    private static DogFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private DogFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static DogFacade getDogFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DogFacade();
        }
        return instance;
    }

    @Override
    public DogDTO getById(int id) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        Dog dog = em.find(Dog.class, id);
        return new DogDTO(dog);
    }

    @Override
    public List<DogDTO> getAll() throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        List<Dog> dogs = em.createQuery("SELECT d FROM Dog d", Dog.class).getResultList();
        return DogDTO.getDtos(dogs);
    }

    @Override
    public DogDTO createDog(DogDTO dogDTO) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        Dog dog = new Dog(dogDTO.getName(), dogDTO.getBreed(), dogDTO.getImage(), dogDTO.getGender(), dogDTO.getBirthdate());
        try {
            em.getTransaction().begin();
            em.persist(dog);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new DogDTO(dog);
    }
    
    @Override
    public DogDTO addOwner(int id, DogDTO dogdto) {
        EntityManager em = emf.createEntityManager();        
        Dog dog;
        Owner owner;
        try {
            em.getTransaction().begin();
            dog = em.find(Dog.class, dogdto.getId());
            owner = em.find(Owner.class, id);
            dog.setOwner(owner);
            em.persist(dog);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new DogDTO(dog);
        
    }

    @Override
    public void populate() {

        EntityManager em = emf.createEntityManager();
        Dog dog1 = new Dog("Dog1", "breed1", "image1", "gender1", "birthdate1");
        Dog dog2 = new Dog("Dog2", "breed2", "image2", "gender2", "birthdate2");
        
        Owner owner1 = new Owner("Owner1", "address1", "address1", 1);
        Owner owner2 = new Owner("Owner2", "address2", "address2", 12);
        Owner owner3 = new Owner("Owner3", "address3", "address3", 12);

        try {
            em.getTransaction().begin();
            em.persist(dog1);
            em.persist(dog2);
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

    @Override
    public DogDTO edit(DogDTO dogDTO) throws WebApplicationException {
        
        EntityManager em = emf.createEntityManager();
        Dog dog;
        
        System.out.println("ID: " + dogDTO.getId());
         
        try {
            em.getTransaction().begin();
            
            dog = em.find(Dog.class, dogDTO.getId());
            System.out.println("Dog: " + dog);
            
            dog.setName(dogDTO.getName());
            dog.setBreed(dogDTO.getBreed());
            dog.setImage(dogDTO.getImage());
            dog.setGender(dogDTO.getGender());
            dog.setBirthdate(dogDTO.getBirthdate());
            
            em.persist(dog);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            throw new WebApplicationException("not found - NÅ");
        } finally {
            em.close();
        }
        return new DogDTO(dog);
        
    }
    
    
    @Override
    public void delete(int id) throws WebApplicationException {
        
        EntityManager em = emf.createEntityManager();
        Dog dog;
                 
        try {
            em.getTransaction().begin();
            dog = em.find(Dog.class, id);
            em.remove(dog);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            throw new WebApplicationException("not found - NÅ");
        } finally {
            em.close();
        }
       
    }
        
        
        
        
        
        
        
        
    

}
