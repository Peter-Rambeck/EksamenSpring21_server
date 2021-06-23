package facades;

import dtos.RenameMeDTO;
import dtos.WalkerDTO;
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
public class WalkerFacade implements WalkerRepository {

    private static WalkerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private WalkerFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static WalkerFacade getWalkerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new WalkerFacade();
        }
        return instance;
    }

    //TODO Remove/Change this before use
//            public long getRenameMeCount(){
//                EntityManager em = emf.createEntityManager();
//                try{
//                    long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
//                    return renameMeCount;
//                }finally{  
//                    em.close();
//                }   
//            }
    @Override
    public WalkerDTO getById(int id) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        Walker walker = em.find(Walker.class, id);
        return new WalkerDTO(walker);
    }

    @Override
    public List<WalkerDTO> getAll() throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        List<Walker> walkers = em.createQuery("SELECT w FROM Walker w", Walker.class).getResultList();
        return WalkerDTO.getDtos(walkers);
    }

    @Override
    public WalkerDTO createWalker(WalkerDTO walkerDTO) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        Walker walker = new Walker(walkerDTO.getName(), walkerDTO.getAddress(), walkerDTO.getPhone());
        try {
            em.getTransaction().begin();
            em.persist(walker);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new WalkerDTO(walker);
    }
    
    
     @Override
    public void populate() {

        EntityManager em = emf.createEntityManager();
        Walker walker1 = new Walker("Walker1", "Address1", 1111);
        Walker walker2 = new Walker("Walker2", "Address2", 2222);
        Walker walker3 = new Walker("Walker3", "Address3", 3333);
        
        try {
            em.getTransaction().begin();
            em.persist(walker1);
            em.persist(walker2);
            em.persist(walker3);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new WebApplicationException("Populate went wrong");
        } finally {
            em.close();
        }
    }
    
   
    
    
    }
