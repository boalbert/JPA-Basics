import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {

	EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("Author");

	@Override
	public void create(Author author) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.persist(author);

		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Author> findByLastName(String lastName) {

		List<Author> list;

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		list = em.createQuery("FROM Author a WHERE a.lastName = : lastName", Author.class)
				.setParameter("lastName", lastName)
				.getResultList();

		if (list.isEmpty()) {
			System.out.println("No results for " + lastName);
		}

		em.getTransaction().commit();
		em.close();
		return list;
	}

	public List<Author> findByBirthDate(LocalDate from, LocalDate to) {

		List<Author> authors;
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		authors = em.createQuery("FROM Author a where a.birthDate >= :from and a.birthDate <= :to", Author.class)
				.setParameter("from", from)
				.setParameter("to", to)
				.getResultList();
		em.getTransaction().commit();
		em.close();
		return authors;

	}

	@Override
	public boolean updateLastName(int id, String newLastName) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = em.find(Author.class, id);
		if (a != null) {
			a.setLastName(newLastName);
			em.getTransaction().commit();
			return true;
		}

		em.close();
		return false;
	}

	@Override
	public boolean remove(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Author a = em.find(Author.class, id);

		if (a != null) {
			System.out.println("Removing " + a);
			em.remove(a);
			em.getTransaction().commit();

			return true;
		}

		em.close();
		return false;
	}

	@Override
	public void removeByLastName(String lastName) {

		List<Author> list;

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		list = em.createQuery("FROM Author a WHERE a.lastName = : lastName", Author.class)
				.setParameter("lastName", lastName)
				.getResultList();

		if (list.isEmpty()) {
			System.out.println("No results for " + lastName);
		} else {
			for (Author author : list) {
				em.remove(em.find(Author.class, author.getId()));
			}
		}
		System.out.println("Removed " + list.size() + " objects. ");

		em.getTransaction().commit();
		em.close();

	}
}
