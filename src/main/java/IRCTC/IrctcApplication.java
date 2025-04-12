package IRCTC;

import IRCTC.model.Ticket;
import IRCTC.model.Train;
import IRCTC.model.User;
import IRCTC.services.UserBookingService;
import IRCTC.util.UserServiceUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class IrctcApplication {

	public static void main(String[] args) {
		SpringApplication.run(IrctcApplication.class, args);

		/*BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		String rawPass = "123";

		String encode = bCryptPasswordEncoder.encode(rawPass);

		System.out.println("|||||||||||||||||||||||||||||||||||||||||||| Encoded Password = " + encode );

*/
		System.out.println("Running Train Booking System App");

		Scanner sc= new Scanner(System.in);

		int option= 0;

		UserBookingService userBookingService;

		try {
			userBookingService = new UserBookingService();
		}catch (IOException io){
			System.out.println("Something Went Wrong");
			return;
		}

		while (option != 7){
			System.out.println("Choose Option");
			System.out.println("Press 1. To Signup");
			System.out.println("Press 2. To Login");
			System.out.println("Press 3. To Fetch Booking");
			System.out.println("Press 4. To Search Booking");
			System.out.println("Press 5. To Book A Seat");
			System.out.println("Press 6. To Cancel the Booking");
			System.out.println("Press 7. To Exit the App");

			option = sc.nextInt();

			Train selectTrainBooking = new Train();

			switch (option){

				case 1:
					System.out.println("Enter the Username for SingUo");
					String nameToSignup= sc.next();
					System.out.println("Enter the Password for Signup");
					String passwordToSignUp= sc.next();

					User userForSignUp = new User(nameToSignup,passwordToSignUp,
							new ArrayList<>(), UserServiceUtil.generateHashPassword(passwordToSignUp),
							UUID.randomUUID().toString());

					userBookingService.signUp(userForSignUp);
					break;

				case 2:
					System.out.println("Enter the Username for Login");
					String nameToLogin = sc.next();
					System.out.println("Enter the Password for Login");
					String passToLogin = sc.next();

					User userForLogin = new User(nameToLogin,passToLogin,
							new ArrayList<>(),UserServiceUtil.generateHashPassword(passToLogin),
							UUID.randomUUID().toString());

					try{
						userBookingService = new UserBookingService(userForLogin);
					}catch (IOException e){
						System.out.println("User not found");
						return;
					}
					break;

				case 3:

					System.out.println("Fetch your Bookings");
					userBookingService.fetchTicket();

					break;

				case 4:

					System.out.println("Enter the Source");
					String source = sc.next();
					System.out.println("Enter the Destination");
					String destination = sc.next();

					List<Train> trains = userBookingService.getTrain(source, destination);

					int index = 1;

					for (Train t : trains){

						System.out.println(index + " Train ID " + t.getTrainId());

						for (Map.Entry entry : t.getTrainStationTimes().entrySet()){

							System.out.println("Station"+ entry.getKey() + " Times " + entry.getValue());
						}

					}

					System.out.println("Select A train by typing 1 2 3....." );
					selectTrainBooking = trains.get(sc.nextInt());
					break;


				case 5:
					System.out.println("Select a Seat Outof all seats");

				List<List<Integer>> fetchedSeat= userBookingService.fetchSeats(selectTrainBooking);

				if (fetchedSeat == null){

					System.out.println("Seats are null - cannot iterate");

				}else {
					for (List<Integer> row : fetchedSeat) {

						for (Integer val : row) {
							System.out.println(val + " ");
						}
						System.out.println();
					}
				}


					System.out.println("Select Row and Coloumn by Pressing key");

					System.out.println("Select Row");
					int seletedRow = sc.nextInt();

					System.out.println("Select Column");

					int selectedColoumn = sc.nextInt();

					Boolean booked = userBookingService.bookTrainSeats(selectTrainBooking, seletedRow, selectedColoumn);

					if (booked.equals(Boolean.TRUE)){
						System.out.println("Booked ! enjoy Your Journey");
					}else {
						System.out.println("Cant book this seat");
					}
					break;
				default:
					break;

			}

		}
	}

}
