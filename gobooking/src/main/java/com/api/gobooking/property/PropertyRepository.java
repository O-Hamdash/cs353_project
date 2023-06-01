package com.api.gobooking.property;

//import com.api.gobooking.user.appuser.AppUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class PropertyRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /*
     * Adds user to database and returns the automatically generated id.
     */
    @Transactional
    public boolean save(Property property, PropertyRequest propertyRequest){
        boolean success = false;
        Integer generatedId = null;

        String propertySql = "INSERT INTO " +
                "\"property\" (title, status, price_per_night, added_date, max_people, bathroom_number, room_number, description, type, owner_id, city, district, neighborhood, building_no, apartment_no, wifi, kitchen, furnished, parking, ac, elevator, fire_alarm, floor) " +
                "VALUES (:title, :status, :price_per_night, :added_date, :max_people, :bathroom_number, :room_number, :description, :type, :owner_id, :city, :district, :neighborhood, :building_no, :apartment_no, :wifi, :kitchen, :furnished, :parking, :ac, :elevator, :fire_alarm, :floor)";

        Query propertyQuery = entityManager.createNativeQuery(propertySql);

        propertyQuery.setParameter("title", property.getTitle());
        propertyQuery.setParameter("status", property.getStatus().toString());
        propertyQuery.setParameter("price_per_night", property.getPrice_per_night());
        propertyQuery.setParameter("description", property.getDescription());
        propertyQuery.setParameter("max_people", property.getMax_people());
        propertyQuery.setParameter("added_date", property.getAdded_date());
        propertyQuery.setParameter("bathroom_number", property.getBathroom_number());
        propertyQuery.setParameter("room_number", property.getRoom_number());
        propertyQuery.setParameter("type", property.getType().toString());
        propertyQuery.setParameter("owner_id", property.getOwner_id());
        propertyQuery.setParameter("city", property.getCity());
        propertyQuery.setParameter("district", property.getDistrict());
        propertyQuery.setParameter("neighborhood", property.getNeighborhood());
        propertyQuery.setParameter("building_no", property.getBuildingNo());
        propertyQuery.setParameter("apartment_no", property.getApartmentNo());
        propertyQuery.setParameter("floor", property.getFloor());

        propertyQuery.executeUpdate();
        //generatedId = (Integer) propertyQuery.getSingleResult();
        success = true;
        return success;
    }



    public boolean saveServices(Integer id, String service){
        String serviceSql = "INSERT INTO " +
                "\"service\" (property_id, service_name) " +
                "VALUES (:property_id, :service_name)";

        Query serviceQuery = entityManager.createNativeQuery(serviceSql);

        serviceQuery.setParameter("property_id", id);
        serviceQuery.setParameter("service_name", service);

        serviceQuery.executeUpdate();

        return true;
    }

    public boolean saveServices(Integer id, Boolean[] list){
        String serviceSql = "INSERT INTO " +
                "\"service\" (property_id, service_name) " +
                "VALUES (:property_id, :service_name)";

        Query serviceQuery = entityManager.createNativeQuery(serviceSql);

        serviceQuery.setParameter("property_id", id);
        serviceQuery.setParameter("service_name", list[0]);
        serviceQuery.setParameter("service_name", list[1]);
        serviceQuery.setParameter("service_name", list[2]);
        serviceQuery.setParameter("service_name", list[3]);
        serviceQuery.setParameter("service_name", list[4]);
        serviceQuery.setParameter("service_name", list[5]);
        serviceQuery.setParameter("service_name", list[6]);

        serviceQuery.executeUpdate();

        return true;
    }


    public List<Property> findAll(){
        String jpql = "SELECT p FROM Property p";
        TypedQuery<Property> query = entityManager.createQuery(jpql, Property.class);
        return query.getResultList();
    }

    public Optional<Property> findById(Integer id){
        String jpql = "SELECT p FROM Property p WHERE p.id = :id";
        TypedQuery<Property> query = entityManager.createQuery(jpql, Property.class);
        query.setParameter("id", id);

        List<Property> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }



    @Transactional
    public void updateProperty(Property property, PropertyRequest propertyRequest){
        String updatePropertySql = "UPDATE \"property\" " +
                "SET title = :title, status = :status, added_date = :added_date, description = :description, price_per_night = :price_per_night, max_people = :max_people, bathroom_number = :bathroom_number, room_number = :room_number, type = :type, owner_id = :owner_id, city = :city, district = :district, neighborhood = :neighborhood, building_no = :building_no, apartment_no = :apartment_no, floor = :floor " +
                "WHERE property_id = :id";

        Query updateUserQuery = entityManager.createNativeQuery(updatePropertySql);
        updateUserQuery.setParameter("title", property.getTitle());
        updateUserQuery.setParameter("status", property.getStatus().toString());
        updateUserQuery.setParameter("price_per_night", property.getPrice_per_night());
        updateUserQuery.setParameter("description", property.getDescription());
        updateUserQuery.setParameter("max_people", property.getMax_people());
        updateUserQuery.setParameter("bathroom_number", property.getBathroom_number());
        updateUserQuery.setParameter("bathroom_number", property.getBathroom_number());
        updateUserQuery.setParameter("room_number", property.getRoom_number());
        updateUserQuery.setParameter("type", property.getType().toString());
        updateUserQuery.setParameter("added_date", property.getAdded_date());
        updateUserQuery.setParameter("owner_id", property.getOwner_id());
        updateUserQuery.setParameter("city", property.getCity());
        updateUserQuery.setParameter("district", property.getDistrict());
        updateUserQuery.setParameter("neighborhood", property.getNeighborhood());
        updateUserQuery.setParameter("building_no", property.getBuildingNo());
        updateUserQuery.setParameter("apartment_no", property.getApartmentNo());
        updateUserQuery.setParameter("floor", property.getFloor());
        updateUserQuery.setParameter("id", property.getId());
        updateUserQuery.executeUpdate();



        String updatePropertyServicesSql = "UPDATE \"property\" " +
                "SET wifi = :wifi, kitchen = :kitchen, furnished = :furnished, parking = :parking, ac = :ac, elevator = :elevator, fire_alarm = :fire_alarm" +
                "WHERE property_id = :id";

        Query updateServiceQuery = entityManager.createNativeQuery(updatePropertyServicesSql);
        updateServiceQuery.setParameter("wifi", propertyRequest.getWifi());
        updateServiceQuery.setParameter("kitchen", propertyRequest.getKitchen());
        updateServiceQuery.setParameter("furnished", propertyRequest.getFurnished());
        updateServiceQuery.setParameter("parking", propertyRequest.getParking());
        updateServiceQuery.setParameter("ac", propertyRequest.getAc());
        updateServiceQuery.setParameter("elevator", propertyRequest.getElevator());
        updateServiceQuery.setParameter("fire_alarm", propertyRequest.getFire_alarm());
    }

    @Transactional
    public void deleteById(Integer id) {
        String sql = "DELETE FROM \"property\" WHERE property_id = :id";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
