package data;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import models.SkateDeck;
import models.Skateboard;
/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Sep 10, 2022
 */
public class SkateHelper {
	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SkateboardShop");
	private SkateDeckHelper sdh = new SkateDeckHelper();
	public SkateHelper() {	}
	
	/**
	 * Check if a skateboard exists in Persistence
	 * @param skate
	 * @return boolean true if DB has skateboard with same properties
	 */
	public boolean skateExists(Skateboard skate) {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<Skateboard> query = em.createQuery("SELECT s FROM Skateboard s WHERE s.deck = :deck "
				+ "AND s.wheelBrand = :wheel AND s.truckBrand = :truck", Skateboard.class);
		query.setParameter("deck", skate.getDeck());
		query.setParameter("wheel", skate.getWheelBrand());
		query.setParameter("truck", skate.getTruckBrand());
		List<Skateboard> sl = query.getResultList();
		em.close();
		
		if(sl.size() > 1) {
			//error condition, delete duplicate?
		}
		if(sl.size() == 0) {return false;}
		else { return true; }
	}
	
	public void addSkate(Skateboard skate) {		
		
		//Add new skate when it doesn't exist
		if(!skateExists(skate)){
			SkateDeck sd = skate.getDeck();
			sd.addSkate(skate);
			sdh.updateSkateDeck(sd);
			
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
		
		SkateDeck sd = result.getDeck();
		sd.removeSkate(result);
		sdh.updateSkateDeck(sd);
		
		em.getTransaction().begin();
		em.remove(result);
		var transaction = em.getTransaction();
		transaction.commit();
		em.close();
	}

	public boolean updateSkate(Skateboard updatedSkate) {
		EntityManager em = emfactory.createEntityManager();
		boolean result;
		//if skate exists, delete updatedSkate, return existing
		if(skateExists(updatedSkate)) {
			Skateboard existing = getExisting(updatedSkate);
			if(existing.getId() == updatedSkate.getId()) {
				//Skateboard is unchanged
				result = true;
			//Skate exists as different id
			}else {
				result = false;
			}
		} else {
			em.getTransaction().begin();
			em.merge(updatedSkate);
			em.getTransaction().commit();
			result = true;			
		}
		em.close();	
		return result;
	}
	
	public List<Skateboard> listAllSkates() {
		EntityManager em = emfactory.createEntityManager();
		List<Skateboard> sl = em.createQuery("SELECT s FROM Skateboard s", Skateboard.class).getResultList();
		return sl;
	}

	public List<Skateboard> getSkateByDeck(SkateDeck deck) {
		List<Skateboard> sl = deck.getSkates();
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
		TypedQuery<Skateboard> query = em.createQuery("SELECT s FROM Skateboard s WHERE s.deck = :deck "
				+ "AND s.wheelBrand = :wheel AND s.truckBrand = :truck",Skateboard.class);
		query.setParameter("deck", existingSkate.getDeck());
		query.setParameter("wheel", existingSkate.getWheelBrand());
		query.setParameter("truck", existingSkate.getTruckBrand());
		Skateboard s = query.getSingleResult();
		return s;
	}
	
	public List<Skateboard> searchByBrand(String search) {
		List<String> searches = getSearchTerms(search);
		List<Skateboard> list = new ArrayList<Skateboard>();
		
		
		EntityManager em = emfactory.createEntityManager();
		if(searches.size() > 0) {
			for(String s : searches) {
				TypedQuery<Skateboard> query = em.createQuery("SELECT s FROM Skateboard s WHERE s.deck.brand LIKE :search OR s.wheelBrand LIKE :search OR s.truckBrand LIKE :search", Skateboard.class);
				String term = "%" + s + "%";
				query.setParameter("search", term);
				list.addAll(query.getResultList());		
			}		
			
			List<Integer> ids = new ArrayList<>();
			List<Integer> delIndex = new ArrayList<>();
			int index = 0;
			
			//track a list of ids encountered in the list and mark
			//duplicates for removal.
			for(Skateboard s : list) {
				if(ids.contains(s.getId())){
					delIndex.add(index);					
				} else { ids.add(s.getId()); }
				index++;
			}
			if(delIndex.size() > 0) {
				//remove duplicate results in reverse order to keep indices aligned
				for(int i = delIndex.size()-1; i >= 0; i--) {
					list.remove((int)delIndex.get(i));
				}
			}
		}else { list = listAllSkates(); }		
		return list;
	}
	
	public List<Skateboard> searchByPartBrand(String deck, String wheel, String truck) {
		EntityManager em = emfactory.createEntityManager();
		deck = "%" + deck.trim() + "%";
		wheel = "%" + wheel.trim() + "%";
		truck = "%" + truck.trim() + "%";
		String queryString = "SELECT s FROM Skateboard s WHERE";
		List<String> queryParams = new ArrayList<String>();
		List<String> queryValues = new ArrayList<String>();
		List<Skateboard> list = new ArrayList<Skateboard>();
		if(!deck.equals("%%")) {
			queryString += " s.deck.brand LIKE :deck";
			queryParams.add("deck");
			queryValues.add(deck);
		}
		if(!wheel.equals("%%")) {
			if(queryParams.size() > 0) { queryString += " AND";	}
			queryString += " s.wheelBrand LIKE :wheel";
			queryParams.add("wheel");
			queryValues.add(wheel);
		}
		if(!truck.equals("%%")) {
			if(queryParams.size() > 0) {queryString += " AND";}
			queryString += " s.truckBrand LIKE :truck";
			queryParams.add("truck");
			queryValues.add(truck);
		}
		TypedQuery<Skateboard> query = em.createQuery( queryString, Skateboard.class);
		int index = 0;
		for(String p : queryParams) {
			query.setParameter(p, queryValues.get(index++));
		}
		list.addAll(query.getResultList());
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
