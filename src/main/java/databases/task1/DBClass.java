package databases.task1;

import ch.qos.logback.classic.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.LoggerFactory;

import java.util.List;


public class DBClass {

    private static final String URL = "jdbc:mysql://localhost:3307";
    private static final String USER = "michael";
    private static final String PASSWORD = "wisp0423";

    private static final Logger logger = (Logger) LoggerFactory.getLogger(DBClass.class);



    public static void connection(){
        Connector connector = new Connector();
        try (Session session = connector.getSession()){
            Transaction t = session.beginTransaction();
            List<Magic> magics = session.createQuery("FROM Magic", Magic.class).getResultList();
            magics.forEach(magic -> {
                logger.info("Удаление объекта:{}", magic.toString());
                System.out.println(magic);
                session.delete(magic);
            });
            t.commit();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }



//    public static void connection(){
//        Connector connector = new Connector();
//        try (Session session = connector.getSession()){
//            String hql = "FROM Magic WHERE id = :id";
//            Query<Magic> query = session.createQuery(hql, Magic.class);
//            query.setParameter("id", 4);
//            Magic magic = query.getSingleResult();
//            logger.info("Работа с объектом:{}", magic.toString());
//            System.out.println(magic);
//            magic.setAttBonus(100);
//            magic.setName("Rage");
//            session.beginTransaction();
//            logger.info("Обновить объект:{}", magic.toString());
//            session.update(magic);
//            session.getTransaction().commit();
//        }catch (Exception e){
//            logger.error(e.getMessage());
//        }
//    }

//    public static void connection(){
//        Connector connector = new Connector();
//
//        try(Session session = connector.getSession()){
//
//            List<Magic> books = session.createQuery("FROM Magic", Magic.class).getResultList();
//            Runnable runnable = () -> books.forEach(magic -> logger.info("Работа с объектом:{}", magic.toString()));
//            new Thread(runnable).start();
//            books.forEach(System.out::println);
//        }catch (Exception e){
//            logger.error(e.getMessage());
//        }
//
//    }


//    private static List<Magic> createMagics(){
//
//        List<Magic> magics = new ArrayList<>(List.of(
//                    new Magic("Lightning", 25, 0, 0),
//                    new Magic("Stone skin", 0, 0, 6),
//                    new Magic("Bloodlust", 0, 6, 0),
//                    new Magic("Curse", 0, -3, 0),
//                    new Magic("Heal", -30, 0, 0)
//            ));
//
//            for(Magic magic : magics){
//                try{
//                    session.save(magic);
//                }catch (HibernateException e){
//                    logger.error(e.getMessage());
//                }
//            }
//            return magics;
//    }




//    public static void connection(){
//        try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
//            Statement statement = con.createStatement();
//            statement.execute("DROP SCHEMA test");
//            statement.execute("CREATE SCHEMA test");
//            statement.execute("CREATE TABLE `test` . `table` (`id` INT NOT NULL, `firstName` VARCHAR(45) NULL, `lastName` VARCHAR(45) NULL, PRIMARY KEY(`id`));");
//            statement.execute("INSERT INTO test . `table` (`id`, `firstName`, `lastName`)\n" +
//                    "VALUES (1, 'Иванов', 'Иван');");
//            statement.execute("INSERT INTO test . `table` (`id`, `firstName`, `lastName`)\n" +
//                    "VALUES (2, 'Петров', 'Петр');");
//            ResultSet set = statement.executeQuery("SELECT * FROM test.table;");
//            while (set.next()){
//                System.out.println(set.getString(3) + " " + set.getString(2) + " " + set.getInt(1));
//            }
//
//        }catch (SQLException e){
//            System.out.println(e.getMessage());
//        }
//    }

}
