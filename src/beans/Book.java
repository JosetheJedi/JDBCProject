/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Josef
 */
public class Book {
    private String GroupName, BookTitle, PublisherName;
    private int yearpublished, numberofpages;

    public String getGroupName() {
        return GroupName;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public String getPublisherName() {
        return PublisherName;
    }

    public int getYearpublished() {
        return yearpublished;
    }

    public int getNumberofpages() {
        return numberofpages;
    }

    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }

    public void setBookTitle(String BookTitle) {
        this.BookTitle = BookTitle;
    }

    public void setPublisherName(String PublisherName) {
        this.PublisherName = PublisherName;
    }

    public void setYearpublished(int yearpublished) {
        this.yearpublished = yearpublished;
    }

    public void setNumberofpages(int numberofpages) {
        this.numberofpages = numberofpages;
    }
    
    
}
