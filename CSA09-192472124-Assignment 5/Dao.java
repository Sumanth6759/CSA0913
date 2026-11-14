import java.util.List;
import java.util.Optional;

public interface Dao<T, K> {
    void insert(T value) throws Exception;
    Optional<T> findById(K id) throws Exception;
    List<T> findAll() throws Exception;
    boolean update(T value) throws Exception;
    boolean delete(K id) throws Exception;
}
