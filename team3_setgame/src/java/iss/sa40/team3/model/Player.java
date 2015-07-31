package iss.sa40.team3.model;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player {
    
    @Id
    private String email;
    private String password;
    private String name;
    private int highscore;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
    
    public JsonObject toJson(){
        return (Json.createObjectBuilder()
                .add("email", email)
                .add("password", password)
                .add("name",name)
                .add("highscore", highscore)
                .build());
    }

    @Override
    public String toString() {
        return "Player{" + "email=" + email + ", password=" + password + ", name=" + name + ", highscore=" + highscore + '}';
    }
    
    
            
}
