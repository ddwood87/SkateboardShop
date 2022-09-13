package views;

import java.util.List;
import java.util.Scanner;

import data.SkateHelper;
import models.Skateboard;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Sep 11, 2022
 */
public class StartMenu {

	static Scanner scan = new Scanner(System.in);
	static SkateHelper sh = new SkateHelper();
	
	public static void main(String[] args) {
		runMenu();
		scan.close();
	}
	public static void runMenu() {
		boolean quit = false;
		while(!quit) {
			System.out.println("-------------------");
			System.out.println("---__SKATESHOP__---");
			System.out.println("-------------------");
			System.out.println("-- Select an option:");
			System.out.println("--- 1 - Add a skateboard");
			System.out.println("--- 2 - Edit a skateboard");
			System.out.println("--- 3 - Delete a skateboard");
			System.out.println("--- 4 - View all skateboards");
			System.out.println("--- 5 - Exit the SkateShop");
			int select = getIntInput();
			scan.nextLine();
			switch(select) {
				case 1:
					addAnItem();
					break;
				case 2:
					editAnItem();
					break;
				case 3:
					deleteAnItem();
					break;
				case 4:
					printSkateboards();
					break;
				case 5:
					sh.cleanUp();
					System.out.println("Closing Time....");
					quit = true;
					break;
				default:
					
					break;
			}
		}
	}
	
	private static void addAnItem(){
		System.out.print("Enter the deck brand: ");
		String deck = scan.nextLine();
		System.out.print("Enter the wheel brand: ");
		String wheel = scan.nextLine();
		System.out.print("Enter the truck brand: ");
		String truck = scan.nextLine();
		Skateboard skate = new Skateboard(deck, wheel, truck);
		sh.addSkate(skate);
		System.out.println("Skateboard added");
	}
	
	private static void deleteAnItem() {
		searchSkateboards();
		System.out.print("Enter an ID to delete that skateboard: ");
		int id = getIntInput();
		Skateboard s = sh.getSkateById(id);
		sh.deleteSkate(s);
		System.out.println(s.toString() + " has been deleted.");
	}
	
	private static void editAnItem() {
		searchSkateboards();
		System.out.print("Enter an ID to edit that skateboard:");
		int id = getIntInput();
		Skateboard s = sh.getSkateById(id);
		if(s == null) { 
			System.out.println("This ID doesn't exist.");
		}else {
			System.out.println(s.toString());
			System.out.println("Choose an item to change: ");
			System.out.println("1. Deck");
			System.out.println("2. Wheel");
			System.out.println("3. Truck");
			int select = getIntInput();
			scan.nextLine();
			String choice = "";
			switch(select) {
				case 1:
					choice = "deck";
					break;
				case 2:
					choice = "wheel";
					break;
				case 3:
					choice = "truck";
					break;
				default:
					
					break;
			}
			System.out.println("Enter the new brand of " + choice + ": ");
			String newBrand = scan.nextLine();
			switch(select) {
				case 1:
					s.setDeckBrand(newBrand);;
					break;
				case 2:
					s.setWheelBrand(newBrand);
					break;
				case 3:
					s.setTruckBrand(newBrand);
					break;
				default:
					
					break;
			}
			s = sh.updateSkate(s);
			System.out.println("The edited skateboard is now :");
			System.out.println(s.toString());
		}
	}
	
	private static int getIntInput() {
		while(!scan.hasNextInt()) {
			System.out.print("Enter a number: ");
			scan.nextLine(); //wait for next line while not int
		}
		return scan.nextInt();
	}
	
	private static void searchSkateboards() {
		List<Skateboard> searchResult;
		System.out.println("Search to find a skateboard: ");
		String search = scan.nextLine();
		if(search == "") {
			//print all skateboards
			searchResult = sh.listAllSkates();
		} else {
			searchResult = sh.searchByBrand(search);			
		}
		printSkateboards(searchResult);
	}
	
	private static void printSkateboards(List<Skateboard> list) {
		for(Skateboard s : list){
			System.out.println(s.toString());
		}
	}
	
	private static void printSkateboards() {
		printSkateboards(sh.listAllSkates());
	}
}
