package beans;

/**
 * @author Super tortas
 */
public class Book {
    private String GroupName, BookTitle, PublisherName;
    private int yearpublished, numberofpages;
    /**
     * Returns the name of the group
     * @return String of the group's name associated with the book
     */
    public String getGroupName() {
        return GroupName;
    }
    /**
     * Returns the title of the book
     * @return String of the book's name
     */
    public String getBookTitle() {
        return BookTitle;
    }
    /**
     * Returns the publisher associated with the book
     * @return String, book's publisher's name
     */
    public String getPublisherName() {
        return PublisherName;
    }
    /**
     * Returns the year the book was published
     * @return Int, published year
     */
    public int getYearpublished() {
        return yearpublished;
    }
    /**
     * Returns the number of pages of the book
     * @return Int, number of pages
     */
    public int getNumberofpages() {
        return numberofpages;
    }
    /**
     * Sets the name of the group associated with the book
     * @param GroupName, String of the name
     */
    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }
    /**
     * Sets the book's name
     * @param BookTitle, String of the book name
     */
    public void setBookTitle(String BookTitle) {
        this.BookTitle = BookTitle;
    }
    /**
     * Sets the publisher's name of the book
     * @param PublisherName, String of publisher's name
     */
    public void setPublisherName(String PublisherName) {
        this.PublisherName = PublisherName;
    }
    /**
     * Sets the year of when the book was published
     * @param yearpublished, Int of the year published
     */
    public void setYearpublished(int yearpublished) {
        this.yearpublished = yearpublished;
    }
    /**
     * Sets the number of pages in the book
     * @param numberofpages, Int total pages in the book
     */
    public void setNumberofpages(int numberofpages) {
        this.numberofpages = numberofpages;
    }
}