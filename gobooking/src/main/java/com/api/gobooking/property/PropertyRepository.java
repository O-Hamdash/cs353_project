package com.api.gobooking.property;

//import com.api.gobooking.user.appuser.AppUser;
import com.api.gobooking.http.DoubleTimeData;
import com.api.gobooking.http.TimeData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    public boolean save(Property property){
        boolean success = false;

        String propertySql = "INSERT INTO " +
                "\"property\" (title, status, price_per_night, added_date, max_people, bathroom_number, room_number, description, type, owner_id, city, district, neighborhood, building_no, apartment_no, floor) " +
                "VALUES (:title, :status, :price_per_night, :added_date, :max_people, :bathroom_number, :room_number, :description, :type, :owner_id, :city, :district, :neighborhood, :building_no, :apartment_no, :floor)";

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

        success = true;

        return success;
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

    /*public Optional<Property> findByEmail(String email){
        String jpql = "SELECT p FROM Property p WHERE p.email = :email";
        TypedQuery<Property> query = entityManager.createQuery(jpql, Property.class);
        query.setParameter("email", email);

        List<Property> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }*/

    @Transactional
    public void updateProperty(Property property){
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
    }

    @Transactional
    public void deleteById(Integer id) {
        String sql = "DELETE FROM \"property\" WHERE property_id = :id";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public List<PropertyResponse> getPropertiesSort(Integer sortMode) {
        String sql = "select * from property";

        if (sortMode == 1){
            sql = "SELECT " +
                    "   property.property_id as property_id, " +
                    "   property.title as title, " +
                    "   property.description as description, " +
                    "   property.owner_id as owner_id, " +
                    "   (select name from \"user\" where user_id = property.owner_id) as owner_name, " +
                    "   (select surname from \"user\" where user_id = property.owner_id) as owner_surname, " +
                    "   property.city as city, " +
                    "   property.district as district, " +
                    "   property.neighborhood as neighborhood, " +
                    "   property.added_date as added_date, " +
                    "   COALESCE(AVG(review.rating), 0) AS avg_rating, " +
                    "   0 as times_booked " +
                    "FROM " +
                    "    property " +
                    "LEFT JOIN " +
                    "    booking ON property.property_id = booking.property_id " +
                    "LEFT JOIN " +
                    "    review ON booking.booking_id = review.booking_id " +
                    "GROUP BY " +
                    "    property.property_id " +
                    "having COALESCE(AVG(review.rating), 0) > 0 " +
                    "ORDER BY " +
                    "    avg_rating desc " +
                    " limit 5";
        } else if (sortMode == 2){
            sql = "SELECT " +
                    "   property.property_id as property_id, " +
                    "   property.title as title, " +
                    "   property.description as description, " +
                    "   property.owner_id as owner_id, " +
                    "   (select name from \"user\" where user_id = property.owner_id) as owner_name, " +
                    "   (select surname from \"user\" where user_id = property.owner_id) as owner_surname, " +
                    "   property.city as city, " +
                    "   property.district as district, " +
                    "   property.neighborhood as neighborhood, " +
                    "   property.added_date as added_date, " +
                    "   0 as avg_rating, " +
                    "   0 as times_booked " +
                    "FROM property " +
                    "ORDER BY " +
                    "   property.added_date desc " +
                    "limit 5";
        } else if (sortMode == 3){
            sql = "SELECT " +
                    "   property.property_id as property_id, " +
                    "   property.title as title, " +
                    "   property.description as description, " +
                    "   property.owner_id as owner_id, " +
                    "   (select name from \"user\" where user_id = property.owner_id) as owner_name, " +
                    "   (select surname from \"user\" where user_id = property.owner_id) as owner_surname, " +
                    "   property.city as city, " +
                    "   property.district as district, " +
                    "   property.neighborhood as neighborhood, " +
                    "   property.added_date as added_date, " +
                    "   0 AS avg_rating, " +
                    "   COALESCE(count(booking.property_id), 0) as times_booked " +
                    "FROM " +
                    "    property " +
                    "LEFT JOIN " +
                    "    booking ON property.property_id = booking.property_id " +
                    "GROUP BY " +
                    "    property.property_id " +
                    "having COALESCE(COUNT(booking.property_id), 0) > 0 " +
                    "ORDER BY " +
                    "    times_booked desc " +
                    " limit 5 ";
        }

        Query query = entityManager.createNativeQuery(sql, PropertyResponse.class);

        return query.getResultList();
    }

    public List<DoubleTimeData> countProperty(Integer mode) {
        Integer count = null;
        String interval = null;
        if (mode == 3){
            count = 12;
            interval = "month";
        }else if (mode == 2){
            count = 30;
            interval = "day";
        }else if (mode == 1){
            count = 7;
            interval = "day";
        }else if (mode == 4){
            count = 5;
            interval = "year";
        }

        List<DoubleTimeData> result = new ArrayList<>();

        ArrayList<String> times = new ArrayList<>();
        times.add("today");
        times.add("1");
        for (int i = 2; i < count; i++) {
            times.add(String.format("%s", i));
        }

        String sql1;
        String sql2;
        Query query1 = null;
        Query query2 = null;
        DoubleTimeData timeData;
        Integer ads;
        Integer bookings;
        String s1 = "SELECT COUNT(*) AS property_count FROM property WHERE added_date < CURRENT_DATE - INTERVAL '%s %s'";
        String s2 = "SELECT COUNT(*) AS booking_count FROM booking WHERE start_date < CURRENT_DATE - INTERVAL '%s %s'";
        for (int i = count - 1; i >= 0; i--){
            sql1 = String.format(s1, i, interval);
            sql2 = String.format(s2, i, interval);

            query1 = entityManager.createNativeQuery(sql1);
            query2 = entityManager.createNativeQuery(sql2);

            ads = ((Number) query1.getSingleResult()).intValue();
            bookings = ((Number) query2.getSingleResult()).intValue();
            timeData = new DoubleTimeData(times.get(i), ads, bookings);

            result.add(timeData);
        }

        return result;
    }
}
