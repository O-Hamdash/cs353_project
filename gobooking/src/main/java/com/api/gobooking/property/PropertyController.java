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

    @GetMapping(path = "doesownerhaveproperty/{propertyId}")
    public boolean doesOwnerHaveProperty(@PathVariable(name = "propertyId") int propertyId){
        return propertyService.propertyExistsbyOwnerId(propertyId);
    }

    @GetMapping(path = "{propertyId}")
    public Property getPropertyById(@PathVariable(name = "propertyId") int propertyId){
        return propertyService.getProperty(propertyId);
    }

    @GetMapping(path = "getbyownerid/{ownerId}")
    public List<Property> getPropertyByOwnerId(@PathVariable(name = "ownerId") int ownerId){
        return propertyService.getPropertyByOId(ownerId);
    }

    @DeleteMapping(path = "{property_id}")
    public void deleteProperty(@PathVariable("property_id") Integer property_id){
        propertyService.deleteProperty(property_id);
    }

    @PutMapping(path = "{property_id}")
    public void updateProperty( @PathVariable("property_id") Integer id,
                                @RequestBody PropertyRequest propertyRequest)
    {
        propertyService.updateProperty(id, propertyRequest);
    }

    @PostMapping
    public void addNewProperty(@RequestBody PropertyRequest propertyRequest){
        propertyService.addProperty(propertyRequest);
    }
}
