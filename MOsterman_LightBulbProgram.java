//Milo Osterman
//CIS129
//This program allows user to order products from an online light bulb company.
//The user inputs the product they would like, along with quantity.
//The program applies discounts and shipping based on purchase price and displays the receipt.

import java.util.*;

public class MOsterman_LightBulbProgram {
	//Declare variables used for prices of lightbulbs, discount values, product descriptions
	static Scanner keyboard = new Scanner(System.in);
	static double PRICE_13_WATT = 1.50;
	static double PRICE_18_WATT = 3.00;
	static double PRICE_23_WATT = 4.00;
	static int LOW_10_PERCENT_DISCOUNT = 5;
	static int HIGH_10_PERCENT_DISCOUNT = 7;
	static int LOW_15_PERCENT_DISCOUNT = 8;
	static int HIGH_15_PERCENT_DISCOUNT = 9;
	static int LOW_20_PERCENT_DISCOUNT = 10;
	static double DISCOUNT_5_TO_7 = 0.10;
	static double DISCOUNT_8_TO_9 = 0.15;
	static double DISCOUNT_10_PLUS = 0.20;
	static double SHIPPING_COST = 5.00;
	static double FREE_SHIPPING_THRESHOLD = 15.00;
	static String PRODUCT_ONE = "13 watt bulb";
	static String PRODUCT_TWO = "18 watt bulb";
	static String PRODUCT_THREE = "23 watt bulb";

	//Main
	public static void main(String[] args) {
		int productChoice = 0;
		int quantityWanted = 0;
		boolean wantsToAddMore = false;
		int totalThirteenWatt = 0;
		int totalEighteenWatt = 0;
		int totalTwentyThreeWatt = 0;
		int totalPurchased = 0;
		double totalCost = 0;
		double priceOfProducts = 0;
		double netPurchaseAmt = 0;
		double totalCostOfThirteenWatt = 0;
		double totalCostOfEighteenWatt = 0;
		double totalCostOfTwentyThreeWatt = 0;
		double discount = 0;
		double shipping = 0;

		displayIntro();
	//Do while loop that runs the program asking for products wanted until user says no
		do {
			productChoice = getProductChoice();
			quantityWanted = getQuantityWanted();
			switch (productChoice)
			{
			case 1: totalThirteenWatt += quantityWanted;
			break;
			case 2: totalEighteenWatt += quantityWanted;
			break;
			case 3: totalTwentyThreeWatt += quantityWanted;
			break;
			}
			totalPurchased += quantityWanted;
			wantsToAddMore = askToAddMoreProducts();
		} while (wantsToAddMore);
		//Assign cost of each lightbulb to different variables
		totalCostOfThirteenWatt = calcCostOfProduct(totalThirteenWatt, PRICE_13_WATT);
		totalCostOfEighteenWatt = calcCostOfProduct(totalEighteenWatt, PRICE_18_WATT);
		totalCostOfTwentyThreeWatt = calcCostOfProduct(totalTwentyThreeWatt, PRICE_23_WATT);
		priceOfProducts = totalCostOfThirteenWatt + totalCostOfEighteenWatt + totalCostOfTwentyThreeWatt; //Total price of products
		discount = applyDiscount(priceOfProducts, totalPurchased); //Apply discount based on amount purchased
		netPurchaseAmt = priceOfProducts - discount; //Calculate net cost
		shipping = applyShipping(netPurchaseAmt); //Apply shipping based on threshold
		totalCost = netPurchaseAmt + shipping; //Calculate total cost

		//Display results
		displayProductInfo("Product 1", PRODUCT_ONE, PRICE_13_WATT, totalThirteenWatt, totalCostOfThirteenWatt);
		displayProductInfo("Product 2", PRODUCT_ONE, PRICE_18_WATT, totalEighteenWatt, totalCostOfEighteenWatt);
		displayProductInfo("Product 3", PRODUCT_ONE, PRICE_23_WATT, totalTwentyThreeWatt, totalCostOfTwentyThreeWatt);
		displayPriceBreakdown(priceOfProducts, discount, netPurchaseAmt, shipping, totalCost);

	}

	//Intro
	private static void displayIntro() {
		System.out.printf("Welcome to Brighter Lights, a light bulb company. \n");

	}

	//Function for user to choose product
	private static int getProductChoice() {
		// TODO ask what product user wants
		int productChoice = 0;
		displayProductsOffered();

		while (productChoice != 1 && productChoice != 2 && productChoice != 3) {
			productChoice = getInput("Please enter the product number you would like. (1,2,3)");
		}

		return productChoice;

	}

	//Display the products available
	private static void displayProductsOffered() {
		System.out.printf("Product 1: %s which costs $%.2f. \n"
				+ "Product 2: %s which costs $%.2f. \n"
				+ "Product 3: %s which costs $%.2f. \n", PRODUCT_ONE, PRICE_13_WATT,
				PRODUCT_TWO, PRICE_18_WATT, PRODUCT_THREE, PRICE_18_WATT);

	}

	//Get input from user for how many products wanted
	private static int getQuantityWanted() {
		// TODO find out how many products user wants
		int quantityWanted = getInput("How many would you like to purchase?");
		while (quantityWanted < 0) { //Do not allow for negative amounts
			System.err.println("Quantity cannot be less than zero.");
			quantityWanted = getInput("How many would you like to purchase?");

		}

		return quantityWanted;

	}

	//Yes or no while loop. Checks to see if user wants to purchase more, returns true if they do.
	private static Boolean askToAddMoreProducts() {
		boolean wantsToBuyMore = false;

		System.out.println("Would you like to add more products? (Y/y or N/n)");

		String userInput = keyboard.nextLine();
		while (!userInput.equalsIgnoreCase("y") && !userInput.equalsIgnoreCase("n")) {
			System.err.println("Incorrect entry. Please enter Y/y or N/n.");
			userInput = keyboard.nextLine();

		}

		if (userInput.equalsIgnoreCase("y")) {
			wantsToBuyMore = true;
		}

		return wantsToBuyMore;

	}

	//Calculate cost of an amount of products. Can be used with each type of product, given arguments of quantity and price
	private static double calcCostOfProduct(int totalSold, double costPerBulb) {
		double totalCost = totalSold * costPerBulb;

		return totalCost;

	}

	//Check the range the quantity of total bulbs purchased is. Apply a discount based on amount
	private static double applyDiscount(double totalCost, int totalPurchased) {
		// TODO apply discount depending on num purchased
		double discount = 0;
		if (totalPurchased >= LOW_10_PERCENT_DISCOUNT && totalPurchased <= HIGH_10_PERCENT_DISCOUNT) {
			discount = totalCost * DISCOUNT_5_TO_7;

		} else if (totalPurchased >= LOW_15_PERCENT_DISCOUNT && totalPurchased <= HIGH_15_PERCENT_DISCOUNT) {
			discount = totalCost * DISCOUNT_8_TO_9;

		} else if (totalPurchased >= LOW_20_PERCENT_DISCOUNT) {
			discount = totalCost * DISCOUNT_10_PLUS;
		}

		return discount;

	}

	//Shipping is applied if the total cost is below shipping threshold.
	private static double applyShipping(double totalCost) {
		// TODO add $5 shipping if cost is less than 15
		double shipping = 0;
		if (totalCost < FREE_SHIPPING_THRESHOLD) {
			shipping = SHIPPING_COST;
		}

		return shipping;

	}

	//Function that can display information of any product given product number, description, and price
	private static void displayProductInfo(String productNum, String productDesc, double pricePerProduct,
			int numOfProducts, double totalPrice) {
		// TODO Auto-generated method stub
		if (numOfProducts > 0) {
			System.out.printf("\n%s: %s unit price: $%.2f quantity: %d extended price: $%.2f\n",
					productNum, productDesc, pricePerProduct, numOfProducts, totalPrice);
		}

	}

	//Displays the price of the users product choices, if discount was applied, net purchase amount, shipping, and total cost
	private static void displayPriceBreakdown(double priceOfProducts, double discount, double netPurchaseAmt,
			double shipping, double totalCost) {
		// TODO price breakdown
		System.out.printf("\n%-67s : $%.2f", "Price of products", priceOfProducts);
		System.out.printf("\n%-67s : $%.2f", "Discount", discount);
		System.out.printf("\n%-67s : $%.2f", "Net purchase amount", netPurchaseAmt);
		System.out.printf("\n%-67s : $%.2f", "Shipping cost", shipping);
		System.out.printf("\n%-67s : $%.2f", "Total", totalCost);

	}

	//Validates input for product number choice & quantity selection.
	private static int getInput(String msg) {
		System.out.println(msg);
		while (!keyboard.hasNextInt()) {
			keyboard.nextLine();
			System.err.println("Invalid entry.");
		}
		int number = keyboard.nextInt();
		keyboard.nextLine();
		return number;

	}
}
