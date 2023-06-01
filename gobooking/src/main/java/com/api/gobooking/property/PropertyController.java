package com.api.gobooking.property;

import com.api.gobooking.user.appuser.AppUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("gobooking/property")
@AllArgsConstructor
@CrossOrigin("*")
public class PropertyController {
    private final PropertyService propertyService;

    @GetMapping
    public List<Property> getProperties(){
        return propertyService.getProperties();
    }

    @GetMapping(path = "sort_by_rating")
    public List<Property> getPropertiesSortByRating(){
        return propertyService.getPropertiesSort(1);
    }

    @GetMapping(path = "sort_by_date")
    public List<Property> getPropertiesSortByDate(){
        return propertyService.getPropertiesSort(2);
    }

    @GetMapping(path = "{propertyId}")
    public Property getPropertyById(@PathVariable(name = "propertyId") int propertyId){
        return propertyService.getProperty(propertyId);
    }


    @DeleteMapping(path = "{property_id}")
    public void deleteProperty(@PathVariable("property_id") Integer property_id){
        propertyService.deleteProperty(property_id);
    }


    @PutMapping(path = "{property_id}")
    public void updateProperty( @PathVariable("property_id") Integer id,
                              @RequestParam(required = false) String title,
                              @RequestParam(required = false) Status status,
                              @RequestParam(required = false) String description
    )
    {
        propertyService.updateProperty(id, title, status, description);
    }

    @PostMapping
    public void addNewProperty(@RequestBody PropertyRequest propertyRequest){
        propertyService.addProperty(propertyRequest);
    }


}
