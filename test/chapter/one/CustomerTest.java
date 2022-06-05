package chapter.one;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerTest {

    private Customer _customer1 = new Customer("Test chapter.one.Customer 1");
    private Customer _customer2 = new Customer("Test chapter.one.Customer 2");
    private Customer _customer3 = new Customer("Test chapter.one.Customer 3");
    private Customer _customer4 = new Customer("Test chapter.one.Customer 4");
    private Customer _customer5 = new Customer("Test chapter.one.Customer 5");
    private Customer _customer6 = new Customer("Test chapter.one.Customer 6");
    private Movie _movie1 = new Movie("chapter.one.Movie 1", 0);
    private Movie _movie2 = new Movie("chapter.one.Movie 2", 1);
    private Movie _movie3 = new Movie("chapter.one.Movie 3", 2);
    private Movie _movie4 = new Movie("chapter.one.Movie 4", 0);
    private Movie _movie5 = new Movie("chapter.one.Movie 5", 1);
    private Movie _movie6 = new Movie("chapter.one.Movie 6", 2);

    private Rental rental1 = new Rental(_movie1, 1);
    private Rental rental2 = new Rental(_movie2, 2);
    private Rental rental3 = new Rental(_movie3, 1);
    private Rental rental4 = new Rental(_movie4, 3);
    private Rental rental5 = new Rental(_movie5, 1);
    private Rental rental6 = new Rental(_movie6, 2);

    @BeforeAll
    public void setup()  {
        _customer1.addRental(rental1);
        _customer2.addRental(rental2);
        _customer3.addRental(rental3);
        _customer4.addRental(rental4);
        _customer5.addRental(rental5);
        _customer6.addRental(rental6);
    }

    @AfterAll
    public void teardown() {

    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    public void statement(Customer customer, Rental rental, double amount, int points) {
        String expected = "chapter.one.Rental Record for " + customer.getName() + "\n";
        expected += "\t" + rental.getMovie().getTitle() + "\t" +
                String.valueOf(amount) + "\n";
        expected += "Amount owed is " + String.valueOf(amount) +
                "\n";
        expected += "You earned " + String.valueOf(points) +
                " frequent renter points";


        String result = customer.statement();
        System.out.println(result);
        System.out.println(expected);

        Assertions.assertEquals(expected, result);
    }

    private Stream<org.junit.jupiter.params.provider.Arguments> provideParameters() {
        return Stream.of(
                Arguments.of(_customer1, rental1, 2.0, 1),
                Arguments.of(_customer2, rental2, 6.0, 2),
                Arguments.of(_customer3, rental3, 1.5, 1),
                Arguments.of(_customer4, rental4, 3.5, 1),
                Arguments.of(_customer5, rental5, 3.0, 1),
                Arguments.of(_customer6, rental6, 1.5, 1)
        );
    }
}
