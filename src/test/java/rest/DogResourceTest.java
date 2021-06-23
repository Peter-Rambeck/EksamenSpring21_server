package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.DogDTO;
import dtos.RenameMeDTO;
import entities.Dog;
import entities.Owner;
import entities.renameme.RenameMe;
import facades.DogFacade;
import io.restassured.http.ContentType;
import java.util.List;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import io.restassured.path.json.mapper.factory.JsonbObjectMapperFactory;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test
@Disabled

public class DogResourceTest extends SetupRestTests {

    Dog dog1;
    Dog dog2;
    Owner owner;

    @BeforeAll
    public static void setUpClass() {
        setupServer();
    }

    @AfterAll
    public static void closeTestServer() {
        shutdownServer();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {

//         emf = EMF_Creator.createEntityManagerFactoryForTest();
//        facade = DogFacade.getDogFacade(emf);
        EntityManager em = emf.createEntityManager();

        dog1 = new Dog("name1", "breed1", "image1", "gender1", "birthdate1");
        dog2 = new Dog("name2", "breed2", "image2", "gender2", "birthdate2");
        owner = new Owner("OwnerName", "OwnerAddress", "OwnerAddress2", 1234);

        try {
            em.getTransaction().begin();
            em.persist(dog1);
            em.persist(dog2);
            em.persist(owner);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new WebApplicationException("noget gik galt");
        } finally {
            em.close();
        }

    }

//        EntityManager em = emf.createEntityManager();
//        r1 = new RenameMe("Some txt", "More text");
//        r2 = new RenameMe("aaa", "bbb");
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("RenameMe.deleteAllRows").executeUpdate();
//            em.persist(r1);
//            em.persist(r2);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
    @Test
    public void testServerIsUp() {
        given().when().get("/xxx").then().statusCode(200);
    }

    //This test assumes the database contains two rows
    @Test
    public void testGetById() throws Exception {
        given()
                .contentType("application/json")
                .pathParam("id", dog1.getId())
                .get("/dog/{id}").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
    }

    @Test
    public void testAddOwner() throws Exception {

//        Dog dog = new Dog(1, "name1", "breed1", "image1", "gender1", "birthdate1");
//        DogDTO requestBody = new DogDTO(dog1);
//
//        given()
//                .contentType("application/json")
//                .body(requestBody)
//                .pathParam("id", 1)
//                .when()
//                .put("/dog/{id}").then()
//                .assertThat()
//                .statusCode(HttpStatus.OK_200.getStatusCode());
    }

    @Test
    public void testGetAll() throws Exception {
//        List<RenameMeDTO> foundRenameMes;
//
//        foundRenameMes = given()
//            .contentType(ContentType.JSON)
//            .get("/xxx/").then()
//            .assertThat()
//            .statusCode(HttpStatus.OK_200.getStatusCode())
//            .extract().body().jsonPath().getList("", RenameMeDTO.class);
//
//        assertThat(foundRenameMes, hasItems(
//            new RenameMeDTO(r1),
//            new RenameMeDTO(r2)
//        ));
    }

    @Test
    public void testCreateRenameMe() throws Exception {
//        RenameMeDTO requestBody = new RenameMeDTO("create", "me");
//        given()
//            .contentType(ContentType.JSON)
//            .body(requestBody)
//            .when()
//            .post("xxx")
//            .then()
//            .assertThat()
//            .statusCode(HttpStatus.OK_200.getStatusCode());    }
    }
}
