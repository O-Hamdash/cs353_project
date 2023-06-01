package com.api.gobooking.property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyRequest {
    private String title;
    private Integer max_people;
    private Integer room_number;
    private Integer bathroom_number;
    private String description;
    private Integer price_per_night;
    private PropertyType type;
    private Integer owner_id;
    private String city;
    private String district;
    private String neighborhood;
    private Integer buildingNo;
    private Integer apartmentNo;
    private Boolean wifi;
    private Boolean kitchen;
    private Boolean furnished;
    private Boolean parking;
    private Boolean ac;
    private Boolean elevator;
    private Boolean fire_alarm;
    private Integer floor;
}
