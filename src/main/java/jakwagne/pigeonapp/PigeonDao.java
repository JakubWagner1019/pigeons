package jakwagne.pigeonapp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class PigeonDao {

    @Column(length = 30)
    private String id;

    private String description;

    private int gender;

    @Column(length = 30)
    private String motherId;

    @Column(length = 30)
    private String fatherId;

    public PigeonDao() {
    }

    public PigeonDao(String id, String description, int gender, String motherId, String fatherId) {
        this.id = id;
        this.description = description;
        this.gender = gender;
        this.motherId = motherId;
        this.fatherId = fatherId;
    }

    public PigeonDto toDto(){
        return new PigeonDto(this.id, this.description, this.gender);
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMotherId() {
        return motherId;
    }

    public void setMotherId(String motherId) {
        this.motherId = motherId;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }





    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PigeonDao) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.description, that.description) &&
                Objects.equals(this.motherId, that.motherId) &&
                Objects.equals(this.fatherId, that.fatherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, motherId, fatherId);
    }

    @Override
    public String toString() {
        return "PigeonDao[" +
                "id=" + id + ", " +
                "description=" + description + ", " +
                "motherId=" + motherId + ", " +
                "fatherId=" + fatherId + ']';
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
