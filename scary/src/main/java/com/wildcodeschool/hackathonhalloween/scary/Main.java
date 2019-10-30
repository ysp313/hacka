/*
 * Main class offering examples of how to use Monster & Movie classes
 * 
 * It has been created to abstract the use of a web API for the Wild Code School Halloween Hackathon
 * 
 * @author : Romain Clair
 */
package com.wildcodeschool.hackathonhalloween.scary;

public class Main
{
    public static void main(String[] args)
    {
        // Getting a monster and a movie
        Monster monster = Monster.getMonster(2);
        Movie movie = Movie.getMovie(12);

        // Accessing attributes
        System.out.println("The monster name is " + monster.getName());
        System.out.println(movie.getTitle() + " is directed by " + movie.getDirector());
        
        // Display another movie and monster
        System.out.println(Movie.getMovie(1));
        System.out.println(Monster.getMonster(10));
        
    }
}
