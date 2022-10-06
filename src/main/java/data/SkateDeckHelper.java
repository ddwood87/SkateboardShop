package data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import models.SkateDeck;
import models.Skateboard;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 2, 2022
 */
public class SkateDeckHelper {
	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SkateboardShop");
	
	public SkateDeckHelper() {}
	
	/**
	 * Check if a skateboard exists in Persistence
	 * @param skate
	 * @return boolean true if DB has skateboard with same properties
	 */
	public boolean skateDeckExists(SkateDeck skateDeck) {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<SkateDeck> query = em.createQuery("SELECT d FROM SkateDeck d WHERE d.brand = :brand "
				+ "AND d.width = :width", SkateDeck.class);
		query.setParameter("brand", skateDeck.getBrand());
		query.setParameter("width", skateDeck.getWidth());
		List<SkateDeck> sdl = query.getResultList();
		em.close();
		
		if(sdl.size() > 1) {
			//error condition, delete duplicate?
		}
		if(sdl.size() == 0) {return false;}
		else { return true; }
	}
	public void addSkateDeck(SkateDeck skateDeck) {
		//Add new skate when it doesn't exist
		if(!skateDeckExists(skateDeck)){
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			em.persist(skateDeck);
			em.getTransaction().commit();
			em.close();
		}else {
			// no action if there is an existing skate with matching properties.
			System.out.println("The entered skateboard already exists.");			
		}
	}
	public void deleteSkateDeck(SkateDeck deleteSkateDeck) {
		EntityManager em = emfactory.createEntityManager();
		
		SkateDeck result = em.find(SkateDeck.class, deleteSkateDeck.getId());
				
		var transaction = em.getTransaction();		
		transaction.begin();
		em.remove(result);
		transaction.commit();
		em.close();
	}
	public boolean updateSkateDeck(SkateDeck updatedSkateDeck) {
		EntityManager em = emfactory.createEntityManager();
		boolean result;
		//if skate does not exist, update the deck
		if(!skateDeckExists(updatedSkateDeck)) {
			em = emfactory.createEntityManager();
			em.getTransaction().begin();
			em.merge(updatedSkateDeck);
			em.getTransaction().commit();
			em.close();	
			result = true;
		}else {
			SkateDeck existing = getExisting(updatedSkateDeck);
			//if skate exists and id matches, no changes. result = true
			if(updatedSkateDeck.getId() == existing.getId()){			
				result = true;
			}
			//if a skate already exists, don't update. result = false
			else {
				result = false;
			}
		}
		return result;
	}
	public SkateDeck getSkateDeckById(int id) {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<SkateDeck> queryId = em.createQuery("SELECT d FROM SkateDeck d WHERE d.id = :id", SkateDeck.class);
		queryId.setParameter("id", id);
		List<SkateDeck> list = queryId.getResultList();
		SkateDeck sd;
		if(list.size()> 1)
		{
			//error condition
		}
		if(list.size()==1) { sd = list.get(0); }
		else {sd = null;}
		return sd;
	}
	public List<SkateDeck> listAllSkateDecks(){
		EntityManager em = emfactory.createEntityManager();
		List<SkateDeck> dl = em.createQuery("SELECT d FROM SkateDeck d", SkateDeck.class).getResultList();
		return dl;
	}
	public SkateDeck getExisting(SkateDeck existingSkateDeck) {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<SkateDeck> query = em.createQuery("SELECT d FROM SkateDeck d WHERE d.brand = :brand "
				+ "AND d.width = :width", SkateDeck.class);
		query.setParameter("brand", existingSkateDeck.getBrand());
		query.setParameter("width", existingSkateDeck.getWidth());
		SkateDeck sd = query.getSingleResult();
		return sd;
	}
	public List<SkateDeck> searchByDeckBrand(String brand) {
		EntityManager em = emfactory.createEntityManager();
		brand = "%" + brand.trim() + "%";
		//String queryString;
		List<SkateDeck> list = new ArrayList<SkateDeck>();
		TypedQuery<SkateDeck> query = em.createQuery("SELECT d FROM SkateDeck d WHERE d.brand LIKE :brand", SkateDeck.class);
		query.setParameter("brand", brand);
		list.addAll(query.getResultList());
		return list;
	}
	public List<SkateDeck> searchByDeckWidth(double width) {
		EntityManager em = emfactory.createEntityManager();
		List<SkateDeck> list = new ArrayList<SkateDeck>();
		TypedQuery<SkateDeck> query = em.createQuery("SELECT d FROM SkateDeck d WHERE d.width = :width", SkateDeck.class);
		query.setParameter("width", width);
		list.addAll(query.getResultList());
		return list;
	}
	public List<SkateDeck> searchForSkateDeck(String brand, double width){
		List<SkateDeck> brandList = searchByDeckBrand(brand);
		List<SkateDeck> resultList = new ArrayList<SkateDeck>();
		for(SkateDeck sd : brandList) {
			if(sd.getWidth() == width) {
				resultList.add(sd);
			}
		}
		return resultList;
	}
}
