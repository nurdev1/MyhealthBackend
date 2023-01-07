package Myhealth.myhealth.services;


import Myhealth.myhealth.modeles.Collaborateurs;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CollaborateurDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 1L;
  private Long id;
  private String username;

  private String email;

  @JsonIgnore
  private String password;

/*
  GrantedAuthorityest une autorité accordée au mandant. Ces autorités sont généralement
  des "rôles", tels que ROLE_ADMIN, ROLE_PM, ROLE_USER…
 */
  private Collection<? extends GrantedAuthority> authorities;

  public CollaborateurDetailsImpl(Long id, String username, String email, String password,
                                  Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  //return l'user avec tous ces droits et toutes ces informations
  public static CollaborateurDetailsImpl build(Collaborateurs user) {

    /*
    * Dans Spring Security, nous pouvons considérer chaque GrantedAuthority comme un privilège individuel.
    *  Les exemples peuvent inclure READ_AUTHORITY , WRITE_PRIVILEGE
    * De même, dans Spring Security, nous pouvons considérer chaque rôle comme une GrantedAuthority à
    * gros grains représentée sous la forme d'une chaîne et préfixée par « ROLE »
     * */

    //Stream est utilisée pour traiter des collections d'objets
    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());

    //on cree retourne un objet CollaborateurDetailsImpl
    return new CollaborateurDetailsImpl(
        user.getId(), 
        user.getUsername(), 
        user.getEmail(),
        user.getPassword(), 
        authorities
    );
  }

  //recupere les information l'user de l'utilisateur
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    CollaborateurDetailsImpl user = (CollaborateurDetailsImpl) o;
    return Objects.equals(id, user.id);
  }
}
