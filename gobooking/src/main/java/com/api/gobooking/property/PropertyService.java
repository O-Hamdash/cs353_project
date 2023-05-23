package com.api.gobooking.property;


import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public boolean propertyExists(Integer id){
        return propertyRepository.findById(id).isPresent();
    }

    public List<Property> getProperties(){
        return propertyRepository.findAll();
    }

    public Property getProperty(Integer id){

        if (!propertyExists(id)){
            throw new IllegalStateException(String.format("getProperty: property with id (%s) does not exist", id));
        }

        return propertyRepository.findById(id).get();
    }

    public void setPrice(Integer id, Integer price){
        Property property = getProperty(id);

        property.setPrice_per_night(price);

        propertyRepository.updateProperty(property);
    }





    public boolean addProperty(PropertyRequest propertyRequest){
        boolean success = false;
        Property property = new Property(propertyRequest);
        propertyRepository.save(property);
        success = true;
        return success;
    }

    public boolean deleteProperty(Integer id){
        boolean success = false;
        Optional<Property> optionalReview = propertyRepository.findById(id);

        if (optionalReview.isEmpty()){
            throw new IllegalStateException(String.format("deleteProperty: property with id (%s) does not exist", id));
        }

        propertyRepository.deleteById(id);

        success = true;
        return success;
    }


    public boolean updateProperty(Integer id, String title, Status status, String description){
        boolean success = false;
        Optional<Property> optionalReview = propertyRepository.findById(id);

        if (optionalReview.isEmpty()){
            throw new IllegalStateException(String.format("updateProperty: Property with id (%s) does not exist", id));
        }

        Property property = optionalReview.get();

        if (title != null){
            property.setTitle(title);
        }
        if (status != null){
            property.setStatus(status);
        }
        if (description != null){
            property.setDescription(description);
        }


        propertyRepository.updateProperty(property);

        success = true;
        return success;
    }




    public void setStatus(Integer id,Status status ){
        Property property = getProperty(id);

        property.setStatus(status);

        propertyRepository.updateProperty(property);
    }
}
