package serialization.seminar.task2;

import java.io.Serializable;

public class ToDoV1 implements Serializable {

    // region Поля

    /**
     * Наименование задачи
     */
    private String title;

    /**
     * Статус задачи
     * */
    private boolean isDone;

    //endregion

    // region Конструкторы

    public ToDoV1(){
    }

    public ToDoV1(String title){
        this.title = title;
        isDone = false;
    }

    //endregion

    // region Методы

    /**
     * Получить наименование задачи
     * @return наименование задачи
     * */
    public String getTitle(){ return this.title; }

    /**
     * Получить статус выполнения задачи
     * @return статус выполнения задачи
     * */
    public boolean isDone(){ return this.isDone; }

    /**
     * Изменить статус выполнения задачи
     * @param done новый статус задачи
     * */
    public void setDone(boolean done){
        this.isDone = done;
    }

    //endregion

}
