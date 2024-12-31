package databases.seminar.task1;

import ch.qos.logback.classic.Logger;
import databases.seminar.task1.model.Student;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Program {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Program.class);
    private static final String mySQL_DB = "studentsDB";
    private static final String tableName = "students";
    private static final String url = "jdbc:mysql://localhost:3307/" + mySQL_DB;
    private static final String user = "michael";
    private static final String password = "wisp0423";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(url, user, password)){

            if(!useTestDB(connection)){

                createTableInDatabase(connection);
                try(Statement statement = connection.createStatement()){
                    ResultSet resultSet = statement.executeQuery("SELECT DATABASE()");
                    String currentDB = "";
                    if(resultSet.next()){
                        currentDB = resultSet.getString(1);
                        logger.info("База данных {} используется.", currentDB);
                    }
                    ResultSet tables = connection.getMetaData().getTables(null, null, "%", new String[]{"TABLE"});
                    while (tables.next()){
                        String tableName1 = tables.getString("TABLE_NAME");
                        logger.info("Таблица {}, из базы данных: {}", tableName1, currentDB);
                    }
                }
                int count = new Random().nextInt(5, 11);
                for (int i = 0; i < count; i++){
                    insertData(connection, Student.createNewStudent());
                }
                List<Student> students =  readData(connection);
                students.forEach(System.out::println);

                for (Student student : students){
                    student.setName(Student.updateName());
                    student.setAge(Student.updateAge());
                    updateData(connection, student);
                }

                readData(connection).forEach(System.out::println);

                readData(connection).stream().filter(student -> student.getId() % 2 == 0).toList().forEach(student -> deleteData(connection, student.getId()));


                readData(connection).forEach(System.out::println);

            }else {
                logger.debug("DEBUG: Во время попытки выполнения SQL запроса: 'USE <my_SQL_DB>'. Состояние statement: {} (должно быть 'false').", useTestDB(connection));
            }
        }catch (SQLException e){
            logger.error("EXCEPTION в методе main: {}", e.getMessage());
        }

    }

    //region Вспомогательные методы

    private static void createTableInDatabase(Connection connection) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + Program.tableName + " (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), age INT);";
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)){
            try(Statement statement1 = connection.createStatement()){
                statement.execute();
                ResultSet resultSet = statement.executeQuery("SELECT DATABASE()");
                String currentDB = "";
                while (resultSet.next()){
                    currentDB = resultSet.getString(1);
                }
                logger.info("Таблица {} создана (или уже присутствует) в базе данных {}, состояние statement {}", Program.tableName, currentDB, statement.execute());
            }

        }catch (SQLException e){
            logger.error("EXCEPTION в методе createTableInDatabase: {}", e.getMessage());
        }
    }

    private static boolean useTestDB(Connection connection){
        String useDatabaseSQL = "USE " + Program.mySQL_DB;
        try(PreparedStatement statement = connection.prepareStatement(useDatabaseSQL)){
            logger.debug("Состояние statement {}", statement.execute());
            return statement.execute();
        }catch (SQLException e){
            logger.error("EXCEPTION в методе useTestDB: {}", e.getMessage());
        }
        return false;
    }

    private static void insertData(Connection connection, Student student){
        String insertDataSQL = "INSERT INTO students (name, age) VALUES (?, ?);";
        try(PreparedStatement statement = connection.prepareStatement(insertDataSQL)){
            // Поле Id не берется в расчет, потому что оно не помечено как @Column, а имеет пометку @Id, и еще и AUTO_INCREMENT, в результате, мы не можем его менять сами.
            // В данном случае, мы берем первый столбец, а именно name из таблицы students и в него записываем имя студента.
            statement.setString(1, student.getName());
            // В данном случае мы берем второй столбец, а именно поле age и помещаем в него данные о возрасте нашего студента.
            statement.setInt(2, student.getAge());
            statement.executeUpdate();
            logger.info("Добавили в базу данных: {}, в таблицу: {} объект Student: {}", mySQL_DB, tableName, student);
        }catch (SQLException e){
            logger.error("ERROR: При попытке вставить объект Student: {} в таблицу: {}, вышло исключение: {}", student, tableName, e.getMessage());
        }
    }

    private static List<Student> readData(Connection connection){
        List<Student> students = new ArrayList<>();
        String readDataSQL = "SELECT * FROM " + tableName;
        try(PreparedStatement statement = connection.prepareStatement(readDataSQL)){
            ResultSet resultSet = statement.executeQuery();
            int index = 0;
            while (resultSet.next()){
                students.add(new Student(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")
                ));
                logger.info("Считан объект: {} из базы данных: {}", students.get(index++), mySQL_DB);
            }
            return students;
        }catch (SQLException e){
            logger.error("ERROR при попытке получить данные из базы данных: {}", mySQL_DB);
            return null;
        }
    }

    private static void updateData(Connection connection, Student student){
        // Создаем SQL запрос на обновление в таблице по id и заменяем параметры name и age.
        String updateDataSQL = "UPDATE " + tableName + " SET name=?, age=? WHERE id=?;";
        // Создаем SQL запрос на получение данных из таблицы по id
        String findById = "SELECT * FROM " + tableName + " WHERE id=?;";
        // Создаем новый объект студента, которого нужно получить по id
        Student student1 = new Student();
        // Создаем экземпляр statement и передаем в качестве параметра наш запрос на поиск по id
        try(PreparedStatement statement = connection.prepareStatement(findById)){
            // В statement передаем в первый столбец (id) значение id, данные которые хотим получить
            statement.setInt(1, student.getId());
            // Получаем результат и записываем его в переменную ResultSet.
            ResultSet resultSet = statement.executeQuery();
            // Далее пока есть экземпляры в результатах, в данном случае там всего один результат, так как по id может находиться только один объект
            // итерируемся по результату (в данном случае всего одна итерация)
            while (resultSet.next()){
                // Присваиваем переменной student1 все данные из таблицы, которые мы получили по id
                student1 = new Student(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"));
            }
            // Создаем новый экземпляр statement и передаем в него запрос на обновление данных, которые имеют id нашего student
            try(PreparedStatement statement1 = connection.prepareStatement(updateDataSQL)){
                // Заполняем statement данными, которые мы обновили в соответствии со столбцами: 1-id, 2-age, 3-name
                statement1.setString(1, student.getName());
                statement1.setInt(2, student.getAge());
                statement1.setInt(3, student.getId());
                // Выполняем обновление данных.
                statement1.executeUpdate();
                logger.info("Обновили данные студента name: {}, age: {} по индексу: {}, на новые данные: {}", student1.getName(), student1.getAge(), student1.getId(), student);
            }
        }catch (SQLException | NullPointerException e){
            logger.error("ERROR при попытке обновить данные объекта {}, на новые данные: {}, из-за ошибки: {}", student1, student, e.getMessage());
        }
    }

    private static void deleteData(Connection connection, int id){
        String deleteDataSQL = "DELETE FROM " + tableName + " WHERE id=?;";
        String findById = "SELECT * FROM " + tableName + " WHERE id=?;";
        try(PreparedStatement statement1 = connection.prepareStatement(findById)){
            statement1.setInt(1, id);
            ResultSet resultSet = statement1.executeQuery();
            Student student = new Student();
            while (resultSet.next()){
                student = new Student(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"));
            }
            try(PreparedStatement statement = connection.prepareStatement(deleteDataSQL)){
                statement.setInt(1, id);
                logger.info("Удаляем из базы данных объект: {}, по его id: {}", student, id);
                statement.executeUpdate();
            }
        }catch (SQLException e){
            logger.error("ERROR при попытке удалить данные из базы по id: {}. Сообщение: {}", id, e.getMessage());
        }


    }
    //endregion
}
