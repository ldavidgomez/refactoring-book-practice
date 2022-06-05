package chapter.one;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerTest {

    private Customer _customer1 = new Customer("Test Customer 1");
    private Customer _customer2 = new Customer("Test Customer 2");
    private Customer _customer3 = new Customer("Test Customer 3");
    private Customer _customer4 = new Customer("Test Customer 4");
    private Customer _customer5 = new Customer("Test Customer 5");
    private Customer _customer6 = new Customer("Test Customer 6");
    private Movie _movie1 = new Movie("Movie 1", 0);
    private Movie _movie2 = new Movie("Movie 2", 1);
    private Movie _movie3 = new Movie("Movie 3", 2);
    private Movie _movie4 = new Movie("Movie 4", 0);
    private Movie _movie5 = new Movie("Movie 5", 1);
    private Movie _movie6 = new Movie("Movie 6", 2);

    private Rental _rental1 = new Rental(_movie1, 1);
    private Rental _rental2 = new Rental(_movie2, 2);
    private Rental _rental3 = new Rental(_movie3, 1);
    private Rental _rental4 = new Rental(_movie4, 3);
    private Rental _rental5 = new Rental(_movie5, 1);
    private Rental _rental6 = new Rental(_movie6, 2);

    @BeforeAll
    public void setup()  {
        _customer1.addRental(_rental1);
        _customer2.addRental(_rental2);
        _customer3.addRental(_rental3);
        _customer4.addRental(_rental4);
        _customer5.addRental(_rental5);
        _customer6.addRental(_rental6);
    }

    @AfterAll
    public void teardown() {

    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    public void single_statement(Customer customer, Rental rental, double amount, int points) {
        String expected = "Rental Record for " + customer.getName() + "\n";
        expected += "\t" + rental.getMovie().getTitle() + "\t" +
                String.valueOf(amount) + "\n";
        expected += "Amount owed is " + String.valueOf(amount) +
                "\n";
        expected += "You earned " + String.valueOf(points) +
                " frequent renter points";


        String result = customer.statement();
        System.out.println(result);
        System.out.println(expected);

        assertEquals(expected, result);
    }

    @Test
    public void multiple_statement() {
        Customer customer = new Customer("Test Customer 7");
        customer.addRental(new Rental(_movie1, 1));
        customer.addRental(new Rental(_movie2, 2));
        String expected = "Rental Record for " + customer.getName() + "\n";
        expected += "\t" + _movie1.getTitle() + "\t" +
                "2.0\n";
        expected += "\t" + _movie2.getTitle() + "\t" +
                "6.0\n";
        expected += "Amount owed is 8.0" +
                "\n";
        expected += "You earned 2"+
                " frequent renter points";


        String result = customer.statement();
        System.out.println(result);
        System.out.println(expected);

        assertEquals(expected, result);
    }

    private Stream<org.junit.jupiter.params.provider.Arguments> provideParameters() {
        return Stream.of(
                Arguments.of(_customer1, _rental1, 2.0, 1),
                Arguments.of(_customer2, _rental2, 6.0, 2),
                Arguments.of(_customer3, _rental3, 1.5, 1),
                Arguments.of(_customer4, _rental4, 3.5, 1),
                Arguments.of(_customer5, _rental5, 3.0, 1),
                Arguments.of(_customer6, _rental6, 1.5, 1),
                Arguments.of(_customer6, _rental6, 1.5, 1)
        );
    }
}
