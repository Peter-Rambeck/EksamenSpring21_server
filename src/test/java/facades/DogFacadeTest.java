/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RenameMeDTO;
import dtos.DogDTO;
import entities.Dog;
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
public class DogFacadeTest {

    private static EntityManagerFactory emf;
    private static DogFacade facade;

    Dog dog1;
    Dog dog2;

    public DogFacadeTest() {
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
        facade = DogFacade.getDogFacade(emf);

        EntityManager em = emf.createEntityManager();

        dog1 = new Dog("name1", "breed1", "image1", "gender1", "birthdate1");
        dog2 = new Dog("name2", "breed2", "image2", "gender2", "birthdate2");

        try {
            em.getTransaction().begin();
            em.persist(dog1);
            em.persist(dog2);
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
            // em.createNamedQuery("Dog.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testGetById() {
        DogDTO dogDTO = facade.getById(dog1.getId());
        String exp = "name1";
        String res = dogDTO.getName();
        assertEquals(exp, res);
    }

    @Test
    public void testGetAll() {

//        List<DogDTO> expected = new ArrayList<>();
//        expected.add(new DogDTO(dog1));
//        expected.add(new DogDTO(dog2));
//
//        List<DogDTO> actual = facade.getAll();
//        assertEquals(expected.size(), actual.size());

//        
//        System.out.println("getAll");
//        DogFacade instance = null;
//        List<DogDTO> expResult = null;
//        List<DogDTO> result = instance.getAll();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateDog() {

        Dog dog = new Dog("name1", "breed1", "image1", "gender1", "birthdate1");
        DogDTO dogDTO = new DogDTO(dog);
        DogDTO newDogDTO = facade.createDog(dogDTO);
        assertEquals("name1", newDogDTO.getName());

    }
    
      @Test
    public void testEditdog() {
        
//        dog1 = new Dog(1, "name2", "breed1", "image1", "gender1", "birthdate1");
//
//        DogDTO dogDTO = new DogDTO(dog1);
//        DogDTO edit = facade.edit(dogDTO);
//        System.out.println(edit);
//        assertNotEquals(dogDTO, edit);
//        
    }
    
    @Test
    public void testDeletedog() {

        
        
        
    }

}
