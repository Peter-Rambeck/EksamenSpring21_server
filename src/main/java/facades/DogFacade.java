package facades;

import dtos.DogDTO;
import dtos.RenameMeDTO;
import dtos.WalkerDTO;
import entities.Dog;
import entities.DogRepository;
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
        Dog dog = new Dog(dogDTO.getName(), dogDTO.getBreed(), dogDTO.getImage(), dogDTO.getImage(), dogDTO.getBirthdate());
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
    public void populate() {

        EntityManager em = emf.createEntityManager();
        Dog dog1 = new Dog("Dog1", "breed1", "image1", "gender1", "birthdate1");
        Dog dog2 = new Dog("Dog2", "breed2", "image2", "gender2", "birthdate2");

        try {
            em.getTransaction().begin();
            em.persist(dog1);
            em.persist(dog2);
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
            
//            owner = (Owner) em
//                .createQuery("SELECT o FROM owner o WHERE o.name = :name")
//                .setParameter("name", ownerDTO.getName())
//                .getSingleResult();
                        
            
            dog.setName(dogDTO.getName());
            dog.setBreed(dogDTO.getBreed());
            dog.setImage(dogDTO.getImage());
            dog.setGender(dog.getGender());
            dog.setBirthdate(dog.getBirthdate());
            
            em.persist(dog);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            throw new WebApplicationException("not found - NÅ");
        } finally {
            em.close();
        }
        return new DogDTO(dog);
        
    }
        
        
        
        
        
        
        
        
    

}
