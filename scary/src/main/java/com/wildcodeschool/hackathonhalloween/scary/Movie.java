/*
 * The Movie class
 * 
 * Allows access to an online database of movies via Movie objects
 *
 * It has been created to abstract the use of a web API for the Wild Code School Halloween Hackathon
 * 
 * Each movie object reprensents a movie
 * You can only obtain a movie object using the getMovie(int id) static method
 * The id given is the number of the movie in the database.
 * It can be a value between 1 and 82 (included)
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

public class Movie
{
    // Movie attributes
    private int id; // The movie number in the database
    private String title;
    private String director;
    private int year;
    private String country;
    private String posterUrl;
    private Date createdAt;
    private Date updatedAt;
    
    /*
     * Get a movie object by id
     * id must be a value between 1 and 82 (both included)
     * If an invalid id is provided, you'll get null
     */
    public static Movie getMovie(int id)
    {
        if(id < 1 || id > 82)
        {
            return null;
        }
        
        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create("https://hackathon-wild-hackoween.herokuapp.com/movies/" + id))
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
                Movie movie = objectMapper.convertValue(root.get("movie"), Movie.class);
                return movie;
            }
        } catch (Exception ex)
        {
            System.out.println("There was a problem recovering the movie. Check you network connexion or try again later...");
        }
        return null;  
    }
    
    /*
     * the getMovie(int) static method is the only way to create a movie object
     */
    private Movie(){}

    // Getters & Setters (automatic)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDirector()
    {
        return director;
    }

    public void setDirector(String director)
    {
        this.director = director;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getPosterUrl()
    {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl)
    {
        this.posterUrl = posterUrl;
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
    
    /*
     * This method provides a String representation of the movie Object
     */
    @Override
    public String toString()
    {
        return title + " (" + year + " - " + country + ") directed by : " + director;
    }
    
}
