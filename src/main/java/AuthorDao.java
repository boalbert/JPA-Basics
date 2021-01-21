import java.time.LocalDate;
import java.util.List;

public interface AuthorDao {



	// @return: list of all authors with same lastname
	// @return: empty list if no match is found
	List<Author> findByLastName(String lastName);

	boolean updateLastName(int id, String lastName);

	void create(Author a);

	boolean remove(int id);

	List<Author> findByBirthDate(LocalDate from, LocalDate to);

}
