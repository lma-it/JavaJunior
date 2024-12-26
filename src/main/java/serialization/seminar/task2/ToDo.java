package serialization.seminar.task2;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ToDo implements Externalizable {

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

    public ToDo(){
    }

    public ToDo(String title){
        this.title = title;
        isDone = false;
    }

    //endregion


    // region Externalizable implementation

    @Override
    public void writeExternal(ObjectOutput out) throws IOException{
        out.writeObject(title);
        out.writeBoolean(isDone);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        title = (String) in.readObject();
        isDone = in.readBoolean();
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
