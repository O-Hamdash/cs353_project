package com.api.gobooking.property;


import com.api.gobooking.user.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.*;


import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Table(name = "`property`")
@Entity

@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)

@PrimaryKeyJoinColumn(name = "id")

public class Property {
    @Id
    @Column(name = "property_id")
    private Integer id;
    private String title;
    private Integer location_id;
    private Integer max_people;
    private Integer room_number;
    private Integer bathroom_number;
    private String description;
    private Integer price_per_night;
    private Integer owner_id;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp added_date;
    @Enumerated(value = EnumType.STRING)
    private PropertyType type;
    @Enumerated(value = EnumType.STRING)
    private Status status;




    public Property(String title, Status status, Integer  location_id, Integer max_people, Integer bathroom_number, Integer room_number, String description, Integer  price_per_night, PropertyType type, Integer owner_id) {
        this.title = title;
        this.status = Status.ACTIVE;
        this.location_id =  location_id;
        this.max_people = max_people;
        this.description = description;
        this.price_per_night = price_per_night;
        this.added_date = Timestamp.from(Instant.now());
        this.bathroom_number = bathroom_number;
        this.room_number = room_number;
        this.type = type;
        this.owner_id = owner_id;
    }

    public Property(PropertyRequest propertyRequest) {
        this.id = propertyRequest.getId();
        this.title = propertyRequest.getTitle();
        this.status = Status.ACTIVE;
        this.location_id = propertyRequest.getLocation_id();
        this.max_people = propertyRequest.getMax_people();
        this.description = propertyRequest.getDescription();
        this.price_per_night = propertyRequest.getPrice_per_night();
        this.added_date = Timestamp.from(Instant.now());
        this.bathroom_number = propertyRequest.getBathroom_number();
        this.room_number = propertyRequest.getRoom_number();
        this.type = propertyRequest.getType();
        this.owner_id = propertyRequest.getOwner_id();
    }
}
