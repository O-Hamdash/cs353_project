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
    private Service service;
    private Integer owner_id;
    private String city;
    private String district;
    private String neighborhood;
    private Integer buildingNo;
    private Integer apartmentNo;
    private Integer floor;
}
