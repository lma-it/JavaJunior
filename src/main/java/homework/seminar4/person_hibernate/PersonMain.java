package homework.seminar4.person_hibernate;

import homework.seminar4.person_hibernate.service.PersonService;


public class PersonMain {

    public static void main(String[] args) {
        while (true){
            menu();
            PersonService.connection();
        }
    }


    private static void menu(){
        System.out.println("Введите номер операции: ");
        System.out.println("1. Создать новую персону.");
        System.out.println("2. Получить всех персон из БД.");
        System.out.println("3. Обновить данные персоны по ее Id.");
        System.out.println("4. Удалить персону по ее Id.");
        System.out.println("5. Выход.");
    }
}
