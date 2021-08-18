package hellojpa.section7;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
@DiscriminatorValue("A")
public class Album extends Item {

    private String artist;
}
