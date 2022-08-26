package ob.assignments.bankmarketplaceconsoleapplication;

import ob.assignments.bankmarketplaceconsoleapplication.card_factories.CardFactory;
import ob.assignments.bankmarketplaceconsoleapplication.card_factories.ElcartFactory;
import ob.assignments.bankmarketplaceconsoleapplication.card_factories.VisaFactory;
import ob.assignments.bankmarketplaceconsoleapplication.general.Client;
import ob.assignments.bankmarketplaceconsoleapplication.general.Product;
import ob.assignments.bankmarketplaceconsoleapplication.general.Order;
import ob.assignments.bankmarketplaceconsoleapplication.payment_methods.PaymentMethod;
import ob.assignments.bankmarketplaceconsoleapplication.processing_strategies.IPCProcessing;
import ob.assignments.bankmarketplaceconsoleapplication.processing_strategies.PaymentProcessingStrategy;
import ob.assignments.bankmarketplaceconsoleapplication.processing_strategies.Way4Processing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class BankMarketplaceConsoleApplication implements CommandLineRunner {

	public static final String OUTPUT_TAG = "-- ";
	private static final Scanner scanner = new Scanner(System.in);

	/* Clients map and products list */
	public HashMap<Integer, Client> clients = new HashMap<>();
	public ArrayList<Product> products = new ArrayList<>(10);

	public static void main(String[] args) {
		SpringApplication.run(BankMarketplaceConsoleApplication.class, args);
	}

	@Override
	public void run(String[] args) throws IOException {
		generateClients(6);
		generateProducts(10);

		/* Login and registration */
		System.out.print(OUTPUT_TAG + "Enter user ID to login: ");
		int clientId = scanner.nextInt();
		while (!clients.containsKey(clientId)) {
			System.out.println(OUTPUT_TAG + "Client with given ID not found");
			System.out.print(OUTPUT_TAG + "Do you want to register this client or try to login (1/2): ");
			scanner.nextLine();
			String choice = scanner.nextLine();
			if (choice.equals("1")) {
				registerClient(clientId);
			} else {
				System.out.print(OUTPUT_TAG + "Enter user ID to login: ");
				clientId = scanner.nextInt();
			}
		}
		Client currentClient = clients.get(clientId);
		System.out.format(OUTPUT_TAG + "Client name: %s, retired: %b\n", currentClient.getFullname(), currentClient.isRetired());

		/* Product selection */
		System.out.println(OUTPUT_TAG + "Select products for purchase: ");
		products.forEach(product ->
			System.out.format("%d: %s\n", products.indexOf(product), product.toString())
		);

		/* Order creation and total cost calculation */
		Order order = new Order();
		while (!order.isClosed()) {
			System.out.print(OUTPUT_TAG + "Add to cart (id): ");
			int id = scanner.nextInt();
			scanner.nextLine();
			order.addToCart(products.get(id));

			System.out.print(OUTPUT_TAG + "Do you wish to continue selecting? (y/n): ");
			order.setClosed(scanner.nextLine().equals("n"));
		}
		order.calculateTotalCost();

		System.out.println(OUTPUT_TAG + "Order result: ");
		order.getShoppingCart().forEach(product ->
			System.out.format("%d: %s\n", order.getShoppingCart().indexOf(product), product.toString())
		);
		System.out.format(OUTPUT_TAG + "Total cost = %.2f\n", order.getTotalCost());

		/* Payment type selection and PaymentProcessing type determination */
		System.out.println(OUTPUT_TAG + "Select payment type Visa/Elcart (1/2): ");
		CardFactory cardFactory = scanner.nextLine().equals("1") ? new VisaFactory() : new ElcartFactory();
		System.out.println(OUTPUT_TAG + "Enter card number: ");
		PaymentMethod card = cardFactory.createCard(scanner.nextLine());
		currentClient.setCard(card);
		PaymentProcessingStrategy processingStrategy = currentClient.isRetired() ? new IPCProcessing() : new Way4Processing();

		/* Process order */
		System.out.format(OUTPUT_TAG + "Before checkout balance: %.2f\n", currentClient.getCard().getAmount());
		order.processOrder(card, processingStrategy);
		System.out.format(OUTPUT_TAG + "After checkout balance: %.2f\n", currentClient.getCard().getAmount());
	}

	/**
	 * Register new client
	 * @param clientId Client ID
	 */
	private void registerClient(int clientId) {
		Client newClient = new Client();
		System.out.print(OUTPUT_TAG + "Enter fullname: ");
		newClient.setFullname(scanner.nextLine());
		System.out.print(OUTPUT_TAG + "Enter retired status (y/n): ");
		newClient.setRetired(scanner.nextLine().equals("y"));
		newClient.setId(clientId);
		clients.put(clientId, newClient);
	}

	/**
	 * Generate a dummy list of phone product for purchase
	 * @param size Size of the product list
	 */
	private void generateProducts(int size) {
		Random random = new Random();

		for (int i = 0; i < size; i++) {
			Product newProduct = new Product(random.nextInt(20)*100, String.format("youPhone%d", i));
			products.add(newProduct);
		}
	}

	/**
	 * Generate a dummy map of clients
	 * @param numberOfClients Number of Clients
	 */
	private void generateClients(int numberOfClients) {
		Random random = new Random();

		for (int i = 1; i < numberOfClients; i++) {
			boolean retired = random.nextBoolean();
			Client newClient = new Client(i, String.format("Client%d", i), retired);
			clients.put(i, newClient);
		}
	}
}
