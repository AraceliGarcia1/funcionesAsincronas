package mx.edu.utez.model.games;

import mx.edu.utez.model.category.BeanCategory;

import java.io.File;
import java.util.Date;

public class BeanGames {
    private int idGames;
    private String name;
    private String imgGame;
    private Date datePremiere;
    private BeanCategory idCategory;
    private int status;

    public BeanGames() {
    }

    public BeanGames(int idGames, String name, String imgGame, Date datePremiere, BeanCategory idCategory, int status) {
        this.idGames = idGames;
        this.name = name;
        this.imgGame = imgGame;
        this.datePremiere = datePremiere;
        this.idCategory = idCategory;
        this.status = status;
    }

    public int getIdGames() {
        return idGames;
    }

    public void setIdGames(int idGames) {
        this.idGames = idGames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgGame() {
        return imgGame;
    }

    public void setImgGame(String imgGame) {
        this.imgGame = imgGame;
    }

    public Date getDatePremiere() {
        return datePremiere;
    }

    public void setDatePremiere(Date datePremiere) {
        this.datePremiere = datePremiere;
    }

    public BeanCategory getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(BeanCategory idCategory) {
        this.idCategory = idCategory;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    }
