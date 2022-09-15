package tests;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.SkateHelper;
import models.Skateboard;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Sep 10, 2022
 */
public class TestSkateHelper {

	private String testDeck = "DECK";
	private String testWheel = "WHEEL";
	private String testTruck = "TRUCK";
	private Skateboard testSkate;
	
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SkateboardShop");
	EntityManager em = emfactory.createEntityManager();
	private SkateHelper sh;
	
	TypedQuery<Skateboard> deckQuery;
	TypedQuery<Skateboard> idQuery;
	TypedQuery<Skateboard> allQuery;
	@Before
	public void setUp() throws Exception {
		sh = new SkateHelper();
		testSkate = new Skateboard(testDeck, testWheel, testTruck);
		//em.getTransaction().begin();
		deckQuery = em.createQuery("SELECT s FROM  Skateboard s WHERE s.deckBrand = :queryDeck", Skateboard.class);
		deckQuery.setParameter("queryDeck", testDeck);
		idQuery = em.createQuery("SELECT s FROM Skateboard s WHERE s.id = :queryId", Skateboard.class);
		allQuery = em.createQuery("SELECT s FROM Skateboard s", Skateboard.class);
	}
	/**
	 * 
	 */
	private void addTestSkate(Skateboard skate) {
		em.getTransaction().begin();
		em.persist(skate);
		em.getTransaction().commit();
		
	}
	public void removeTestEntries() {
		em = emfactory.createEntityManager();
		Query query = em.createQuery("DELETE FROM Skateboard");
		em.getTransaction().begin();
		query.executeUpdate();
		em.getTransaction().commit();
	}
	@After
	public void cleanUp() {
		em.close();
	}
	@Test
	public void testAddSkateboard() {
		
		assertEquals(0, deckQuery.getResultList().size());
		
		sh.addSkate(testSkate);
		
		assertEquals(testSkate, deckQuery.getSingleResult());
		
		removeTestEntries();
	}
	@Test
	public void testDeleteSkateboard() {
		addTestSkate(testSkate);
		
		assertEquals(1, deckQuery.getResultList().size());
		
		sh.deleteSkate(testSkate);
		
		assertEquals(0, deckQuery.getResultList().size());

		//removeTestEntries();
	}
	@Test
	public void testListAllSkates() {
		List<Skateboard> allSkates = sh.listAllSkates();
		assertEquals(0, allSkates.size());
		
		Skateboard skate1 = new Skateboard(testWheel, testTruck, testDeck);
		Skateboard skate2 = new Skateboard(testTruck, testDeck, testWheel);
		
		addTestSkate(testSkate);
		addTestSkate(skate1);
		addTestSkate(skate2);
		
		allSkates = sh.listAllSkates();		
		assertEquals(3, allSkates.size());
		
		sh.deleteSkate(testSkate);
		allSkates = sh.listAllSkates();		
		assertEquals(2, allSkates.size());
		
		sh.deleteSkate(skate1);
		allSkates = sh.listAllSkates();		
		assertEquals(1, allSkates.size());
		
		sh.deleteSkate(skate2);
		allSkates = sh.listAllSkates();		
		assertEquals(0, allSkates.size());

		//removeTestEntries();
	}
	@Test
	public void testGetSkateByDeck() {
		addTestSkate(testSkate);
		List<Skateboard> skateList = sh.getSkateByDeck(testDeck);
		assertTrue(skateList.contains(testSkate));
		

		removeTestEntries();
	}
	@Test
	public void testGetSkateByWheel() {
		addTestSkate(testSkate);
		List<Skateboard> skateList = sh.getSkateByWheel(testWheel);
		assertTrue(skateList.contains(testSkate));

		removeTestEntries();
	}
	@Test
	public void testGetSkateByTruck() {

		addTestSkate(testSkate);
		List<Skateboard> skateList = sh.getSkateByTruck(testTruck);
		assertTrue(skateList.contains(testSkate));

		removeTestEntries();
	}
	@Test
	public void testGetSkateById() {
		addTestSkate(testSkate);
		Skateboard skate = allQuery.getResultList().get(0);
		int id = skate.getId();	
		idQuery.setParameter("queryId", id);
		
		assertEquals(skate, sh.getSkateById(id));

		removeTestEntries();
	}
	@Test
	public void testUpdateSkate() {
		Skateboard skate = new Skateboard("A", "B", "C");
		addTestSkate(skate);		
		idQuery.setParameter("queryId", skate.getId());
		
		Skateboard addResult = idQuery.getSingleResult();
		assertEquals(skate, addResult);
		
		addResult.setWheelBrand("D");
		addResult.setTruckBrand("E");
		sh.updateSkate(addResult);
		
		Skateboard updateResult = idQuery.getSingleResult();
		assertEquals(addResult, updateResult);
		sh.deleteSkate(updateResult);

		removeTestEntries();
	}
}
