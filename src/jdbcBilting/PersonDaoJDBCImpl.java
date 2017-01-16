package jdbcBilting;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoJDBCImpl implements PersonDao {

    Connection connection;
    PreparedStatement insert, findByName, findByCity,findById, findByBirth, findAll, delete, update;

    public PersonDaoJDBCImpl() {
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/Lexicon", "root", "lexicon");
            insert = connection.prepareStatement("insert into persons (name,birth,city) values (? , ? , ?)");
            findByName = connection.prepareStatement("SELECT * FROM persons where name like ?");
            findByCity = connection.prepareStatement("SELECT * FROM persons where city like ?");
            findByBirth = connection.prepareStatement("SELECT * FROM persons where birth >= ? AND birth <= ?");
            findById = connection.prepareStatement("SELECT * FROM persons where id = ?");
            findAll = connection.prepareStatement("SELECT * FROM persons");
            update = connection.prepareStatement("UPDATE persons set city = ? where id = ?");
            delete = connection.prepareStatement("DELETE from persons where id = ?");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("PersonDao contructor problem: " + e);
        }

    }

    @Override
    public void create(String name, int birth, String city) {
        try {
            insert.setString(1, name);
            insert.setInt(2, birth);
            insert.setString(3, city);
            insert.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("PersonDao create problem: " + e);
        }

    }

    private List<Person> listFromStatement(PreparedStatement stmt) throws SQLException {
        List<Person> list = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            list.add(new Person(rs.getInt("id"), rs.getString("name"),
                    rs.getInt("birth"), rs.getString("city")));
        }
        return list;
    }

    @Override
    public List<Person> getAll() {
        try {
            return listFromStatement(findAll);
        } catch (SQLException e) {
            throw new RuntimeException("PersonDao findAll problem: " + e);
        }
    }

    @Override
    public List<Person> getByCity(String city) {
        try {
            findByCity.setString(1, city + "%");
            return listFromStatement(findByName);
        } catch (SQLException e) {
            throw new RuntimeException("PersonDao findByCity problem: " + e);
        }
    }

    @Override
    public List<Person> getByBirth(int start, int end) {
              try {
            findByBirth.setInt(1, start);
            findByBirth.setInt(2, end);
            return listFromStatement(findByBirth);
        } catch (SQLException e) {
            throw new RuntimeException("PersonDao findByBirth problem: " + e);
        }
    }

    @Override
    public List<Person> getByName(String name) {
        try {
            findByName.setString(1, name + "%");
            return listFromStatement(findByName);
        } catch (SQLException e) {
            throw new RuntimeException("PersonDao findByName problem: " + e);
        }
    }

    @Override
    public Person getById(int id) {
        try {
            findById.setInt(1, id);
            List<Person> list = listFromStatement(findById);
            if (list.size() > 0) {
                return list.get(0);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("PersonDao findById problem: " + e);
        }
    }

    @Override
    public boolean update(String city, int id) {
        try {
            update.setString(1, city);
            update.setInt(2, id);
            if (update.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("PersonDao update problem: " + e);
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            delete.setInt(1, id);
            if (delete.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("PersonDao delete problem: " + e);
        }
    }

}
