package data;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import models.Skateboard;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Sep 10, 2022
 */
public class SkateHelper {
	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SkateboardShop");
	
	public SkateHelper() {	}
	
	public boolean skateExists(Skateboard skate) {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<Skateboard> query = em.createQuery("SELECT s FROM Skateboard s WHERE s.deckBrand = :deck "
				+ "AND s.wheelBrand = :wheel AND s.truckBrand = :truck", Skateboard.class);
		query.setParameter("deck", skate.getDeckBrand());
		query.setParameter("wheel", skate.getWheelBrand());
		query.setParameter("truck", skate.getTruckBrand());
		List<Skateboard> sl = query.getResultList();
		em.close();
		
		if(sl.size() > 1) {
			//error condition
		}
		if(sl.size() == 0) {return false;}
		else { return true; }
	}
	
	public void addSkate(Skateboard skate) {		
		
		//Add new skate when it doesn't exist
		if(!skateExists(skate)){
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			em.persist(skate);
			em.getTransaction().commit();
			em.close();
		}else {
			// no action if there is an existing skate with matching properties.
			System.out.println("The entered skateboard already exists.");			
		}
	}

	public void deleteSkate(Skateboard deleteSkate) {
		EntityManager em = emfactory.createEntityManager();
				
		Skateboard result = em.find(Skateboard.class, deleteSkate.getId());
		
		em.getTransaction().begin();
		em.remove(result);
		var transaction = em.getTransaction();
		transaction.commit();
		em.close();
	}

	public Skateboard updateSkate(Skateboard updatedSkate) {
		EntityManager em = emfactory.createEntityManager();
		if(!skateExists(updatedSkate)) {
			em.getTransaction().begin();
			em.merge(updatedSkate);
			em.getTransaction().commit();
			em.close();	
			return updatedSkate;
		} else {
			//if update will create duplicate, delete the old skate
			Skateboard existing = getExisting(updatedSkate);
			deleteSkate(updatedSkate);
			return existing;
		}
	}
	
	public List<Skateboard> listAllSkates() {
		EntityManager em = emfactory.createEntityManager();
		List<Skateboard> sl = em.createQuery("SELECT s FROM Skateboard s", Skateboard.class).getResultList();
		return sl;
	}

	public List<Skateboard> getSkateByDeck(String deck) {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<Skateboard> queryDeck = em.createQuery("SELECT s FROM Skateboard s WHERE s.deckBrand = :deck", Skateboard.class);
		queryDeck.setParameter("deck", deck);
		List<Skateboard> sl = queryDeck.getResultList();
		return sl;
	}

	public List<Skateboard> getSkateByWheel(String wheel) {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<Skateboard> queryWheel = em.createQuery("SELECT s FROM Skateboard s WHERE s.wheelBrand = :wheel", Skateboard.class);
		queryWheel.setParameter("wheel", wheel);
		List<Skateboard> sl = queryWheel.getResultList();
		return sl;
	}

	public List<Skateboard> getSkateByTruck(String truck) {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<Skateboard> queryTruck = em.createQuery("SELECT s FROM Skateboard s WHERE s.truckBrand = :truck", Skateboard.class);
		queryTruck.setParameter("truck", truck);
		List<Skateboard> sl = queryTruck.getResultList();
		return sl;
	}

	public Skateboard getSkateById(int id) {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<Skateboard> queryId = em.createQuery("SELECT s FROM Skateboard s WHERE s.id = :id", Skateboard.class);
		queryId.setParameter("id", id);
		List<Skateboard> list = queryId.getResultList();
		Skateboard s;
		if(list.size()> 1)
		{
			//error condition
		}
		if(list.size()==1) { s = list.get(0); }
		else {s = null;}
		return s;
	}

	private Skateboard getExisting(Skateboard existingSkate) {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<Skateboard> query = em.createQuery("SELECT s FROM Skateboard s WHERE s.deckBrand = :deck "
				+ "AND s.wheelBrand = :wheel AND s.truckBrand = :truck",Skateboard.class);
		query.setParameter("deck", existingSkate.getDeckBrand());
		query.setParameter("wheel", existingSkate.getWheelBrand());
		query.setParameter("truck", existingSkate.getTruckBrand());
		Skateboard s = query.getSingleResult();
		return s;
	}
	
	public List<Skateboard> searchByBrand(String search) {
		//search = search.trim();
		List<String> searches = getSearchTerms(search);
		List<Skateboard> list = new ArrayList<Skateboard>();
		
		
		EntityManager em = emfactory.createEntityManager();
		if(searches.size() > 0) {
			for(String s : searches) {
				TypedQuery<Skateboard> query = em.createQuery("SELECT s FROM Skateboard s WHERE s.deckBrand LIKE :search OR s.wheelBrand LIKE :search OR s.truckBrand LIKE :search", Skateboard.class);
				String term = "%" + s + "%";
				query.setParameter("search", term);
				list.addAll(query.getResultList());		
			}		
			List<Integer> ids = new ArrayList<>();
			List<Integer> delIndex = new ArrayList<>();
			int index = 0;
			for(Skateboard s : list) {
				if(ids.contains(s.getId())){
					delIndex.add(index);
				} else { ids.add(s.getId()); }
				index++;
			}
			if(delIndex.size() > 0) {
				//remove duplicate entries in reverse order.
				for(int i = delIndex.size()-1; i >= 0; i--) {
					list.remove((int)delIndex.get(i));
				}
			}
		}else { list = listAllSkates(); }		
		return list;
	}
	
	public List<String> getSearchTerms(String search){
		List<String> terms = new ArrayList<String>();
		do{
			String term;
			search = search.trim();	
			if(search.length() > 0) {				
				if ( search.contains(" ")) {
					int index = search.indexOf(" ");
					term = search.substring(0, index); // get the first term
				
					//remove first term from search string
					//include leading space to trigger last term to loop.
					search = search.substring(index); 
				}else { term = search; }
					terms.add(term); //add term to searches list
				}
			}while(search.contains(" "));
			return terms;
	}

	public void cleanUp() {
		emfactory.close();		
	}	
}
