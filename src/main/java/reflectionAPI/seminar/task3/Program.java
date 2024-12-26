package reflectionAPI.seminar.task3;

import reflectionAPI.seminar.task3.models.Entity;

import java.util.UUID;

public class Program extends Entity {

    public static void main(String[] args) throws IllegalAccessException {
        Employee user = new Employee("Michael", "myemail@myemail.com");
        UUID pk = UUID.randomUUID();
        user.setId(pk);

        QueryBuilder queryBuilder = new QueryBuilder();
        String insertQuery = queryBuilder.buildInsertQuery(user);
        System.out.printf("INSERT: %s\n", insertQuery);

        String selectQuery = queryBuilder.buildSelectQuery(Employee.class, pk);
        System.out.printf("SELECT: %s\n", selectQuery);

        String updateQuery = queryBuilder.buildUpdateQuery(user);
        System.out.printf("UPDATE: %s\n", updateQuery);

        String deleteQuery = queryBuilder.buildDeleteQuery(Employee.class, pk);
        System.out.printf("DELETE: %s\n", deleteQuery);
    }
}
