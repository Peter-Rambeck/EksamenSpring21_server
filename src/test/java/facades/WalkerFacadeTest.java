/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RenameMeDTO;
import dtos.WalkerDTO;
import entities.Walker;
import static facades.FacadeExampleTest.renameMe1;
import static facades.FacadeExampleTest.renameMe2;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

/**
 *
 * @author peter
 */
public class WalkerFacadeTest {

    private static EntityManagerFactory emf;
    private static WalkerFacade facade;

    Walker walker1;
    Walker walker2;

    public WalkerFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = WalkerFacade.getWalkerFacade(emf);

        EntityManager em = emf.createEntityManager();

        walker1 = new Walker("Peter", "Raballerstræde", 12345678);
        walker2 = new Walker("Peter2", "Raballerstræde2", 12345678);

        try {
            em.getTransaction().begin();
            em.persist(walker1);
            em.persist(walker2);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new WebApplicationException("noget gik galt");
        } finally {
            em.close();
        }

    }

    @AfterEach
    public void tearDown() {

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Walker.deleteAllRows").executeUpdate();
            // em.createNamedQuery("Boat.deleteAllRows").executeUpdate();

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testGetById() {

        WalkerDTO walkerDTO = facade.getById(walker1.getId());
        int exp = 12345678;
        int res = walkerDTO.getPhone();
        assertEquals(exp, res);

    }

    @Test
    public void testGetAll() {

        List<WalkerDTO> expected = new ArrayList<>();
        expected.add(new WalkerDTO(walker1));
        expected.add(new WalkerDTO(walker2));

        List<WalkerDTO> actual = facade.getAll();
        assertEquals(expected.size(), actual.size());

//        
//        System.out.println("getAll");
//        WalkerFacade instance = null;
//        List<WalkerDTO> expResult = null;
//        List<WalkerDTO> result = instance.getAll();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateWalker() {

        Walker walker = new Walker("TestWalker", "TestAddress", 2);
        WalkerDTO walkerDTO = new WalkerDTO(walker);
        WalkerDTO newWalkerDTO = facade.createWalker(walkerDTO);
        assertEquals("TestWalker", newWalkerDTO.getName());

    }

}
