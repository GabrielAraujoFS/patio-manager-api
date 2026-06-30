package com.carros.data.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
@Entity
@Data
public class Usuario implements UserDetails {
    @Id
            @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    private String login;
    @NotBlank
    private String senha;
    @Override
    public String getUsername(){
        return login;
    }
    @Override
    public String getPassword(){
        return senha;
    }
    @Override
    public Collection <? extends GrantedAuthority>  getAuthorities(){
        return List.of();
    }
}
