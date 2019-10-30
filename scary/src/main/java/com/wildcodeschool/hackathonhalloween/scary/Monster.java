/*
 * The Monster class
 * 
 * Allows access to an online database of monsters via Monster objects
 *
 * It has been created to abstract the use of a web API for the Wild Code School Halloween Hackathon
 * 
 * Each monster object reprensents a monster
 * You can only obtain a monster object using the getMonster(int id) static method
 * The id given is the number of the monster in the database.
 * It can be a value between 1 and 20 (included)
 *
 * @author : Romain Clair
 *
 */
package com.wildcodeschool.hackathonhalloween.scary;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Date;

public class Monster
{
    // Monster attributes
    private int id; // The monster number in the database
    private String name;
    private int level;
    private int attack;
    private int defense;
    private String special;
    private String picture; // URL of the Monster picture
    private String description;
    private Date createdAt;
    private Date updatedAt;

    /*
     * Get a monster object by id
     * id must be a value between 1 and 10 (both included)
     * If an invalid id is provided, you'll get null
     */
    public static Monster getMonster(int id)
    {
        if(id < 1 || id > 20)
        {
            return null;
        }
        
        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create("https://hackathon-wild-hackoween.herokuapp.com/monsters/" + id))
                                         .build();
        HttpClient client = HttpClient.newBuilder()
                                      .version(HttpClient.Version.HTTP_1_1)
                                      .followRedirects(HttpClient.Redirect.NORMAL)
                                      .connectTimeout(Duration.ofSeconds(20))
                                      .build();
        try
        {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(response.body());
                Monster monster = objectMapper.convertValue(root.get("monster"), Monster.class);
                return monster;
            }
        } catch (Exception ex)
        {
            System.out.println("There was a problem recovering the monster. Check you network connexion or try again later...");
        }
        return null;  
    }

    /*
     * the getMonster(int) static method is the only way to create a monster object
     */
    private Monster(){}

    
    // Getters & Setters (automatic)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getAttack()
    {
        return attack;
    }

    public void setAttack(int attack)
    {
        this.attack = attack;
    }

    public int getDefense()
    {
        return defense;
    }

    public void setDefense(int defense)
    {
        this.defense = defense;
    }

    public String getPicture()
    {
        return picture;
    }

    public void setPicture(String picture)
    {
        this.picture = picture;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public String getSpecial()
    {
        return special;
    }

    public void setSpecial(String special)
    {
        this.special = special;
    }
    
    
    /*
     * This method provides a String representation of the monster Object
     */
    @Override
    public String toString()
    {
        return name + " (level " + level + ") - " + " attack/defense : " + attack + "/" + defense + " - special : " + special;
    }
    
    
}
