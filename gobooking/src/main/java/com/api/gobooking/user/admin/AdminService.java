package com.api.gobooking.user.admin;

import com.api.gobooking.user.appuser.AppUser;
import com.api.gobooking.user.appuser.AppUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final UserService userService;

    public List<Admin> getAdmins(){
        return adminRepository.findAll();
    }

    public Admin getAdmin(Integer id){
        Optional<Admin> optionalAdmin = adminRepository.findById(id);

        if (optionalAdmin.isEmpty()){
            throw new IllegalStateException(String.format("getAdmin: Admin with id (%s) does not exist", id));
        }

        return optionalAdmin.get();
    }

    public boolean addAdmin(AdminRequest adminRequest){
        Optional<Admin> optionalAdmin = adminRepository.findByEmail(adminRequest.getEmail());

        if (optionalAdmin.isPresent()){
            throw new IllegalStateException(String.format("addAdmin: Admin with email (%s) already exists", adminRequest.getEmail()));
        }

        adminRequest.setPassword(userService.encodePassword(adminRequest.getPassword()));
        Admin admin = new Admin(adminRequest);

        adminRepository.save(admin);

        return true;
    }

    public boolean deleteAdmin(Integer id){
        Optional<Admin> optionalAdmin = adminRepository.findById(id);

        if (optionalAdmin.isEmpty()){
            throw new IllegalStateException(String.format("deleteAdmin: Admin with id (%s) does not exist", id));
        }

        adminRepository.deleteById(id);

        return true;
    }

    public boolean updateAdmin(Integer id, String name, String surname, String password, LocalDateTime birthDate, AdminRole adminRole){
        Optional<Admin> optionalAdmin = adminRepository.findById(id);

        if (optionalAdmin.isEmpty()){
            throw new IllegalStateException(String.format("updateAdmin: Admin with id (%s) does not exist", id));
        }

        Admin admin = optionalAdmin.get();

        if (name != null){
            admin.setName(name);
        }
        if (surname != null){
            admin.setSurname(surname);
        }
        if (password != null){
            admin.setPassword(userService.encodePassword(password));
        }
        if (birthDate != null){
            admin.setBirthDate(birthDate);
        }
        if (adminRole != null){
            admin.setAdminRole(adminRole);
        }

        adminRepository.updateAdmin(admin);

        return true;
    }
}
