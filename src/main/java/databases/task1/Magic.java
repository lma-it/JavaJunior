package databases.task1;

import javax.persistence.*;

@Entity
@Table(name="magic")
public class Magic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMagic;
    @Column(name = "name")
    private String name;
    @Column(name = "damage")
    private int damage;
    @Column(name = "attack")
    private int attBonus;
    @Column(name = "armor")
    private int armor;

    public Magic(String name, int damage, int attBonus, int armor) {
        this.name = name;
        this.damage = damage;
        this.attBonus = attBonus;
        this.armor = armor;
    }

    public Magic(){}

    public int getIdMagic() {
        return idMagic;
    }

    public void setIdMagic(int idMagic) {
        this.idMagic = idMagic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAttBonus() {
        return attBonus;
    }

    public void setAttBonus(int attBonus) {
        this.attBonus = attBonus;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public String toString() {
        return String.format("id: %s, Name: %s, Damage: %s, Attack Bonus: %s, Armor: %s",
                this.getIdMagic(),
                this.getName(),
                this.getDamage(),
                this.getAttBonus(),
                this.getArmor());
    }
}


