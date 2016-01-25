/**
 * Store the details of a music track,
 * such as the artist, title, and file name.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Track
{
    // The artist.
    private String artist;
    // The track's title.
    private String title;
    // Where the track is stored.
    private String filename;
    //cuenta las veces que se reproduce una cancion
    private int playCount;
    //discobrafica a la que pertenece 
    private String discografica;

    /**
     * Constructor for objects of class Track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    public Track(String artist, String title, String filename, String nombreDiscografica)
    {
        setDetails(artist, title, filename);
        setDiscografica(nombreDiscografica);
        playCount = 0;
        

    }

    /**
     * Constructor for objects of class Track.
     * It is assumed that the file name cannot be
     * decoded to extract artist and title details.
     * @param filename The track file. 
     */
    public Track(String filename)
    {
        setDetails("unknown", "unknown", filename);
    }

    /**
     * Return the artist.
     * @return The artist.
     */
    public String getArtist()
    {
        return artist;
    }

    /**
     * Return the title.
     * @return The title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Return the file name.
     * @return The file name.
     */
    public String getFilename()
    {
        return filename;
    }

    /**
     * devuelve la discografica 
     */
    public String getDiscografica()
    {
        return discografica ;
    }

    /**
     * Return details of the track: artist, title and file name.
     * @return The track's details.
     */
    public String getDetails()
    {
        return artist + ": " + title + "  (file: " + filename + " discografica "+ discografica + ")" + playCount ;
    }

    /**
     * Set details of the track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    private void setDetails(String artist, String title, String filename)
    {
        this.artist = artist;
        this.title = title;
        this.filename = filename;
        
    }

    /**
     * metodo para resetear playCount 
     */
    public  void  resetCount()
    {
        playCount = 0;

    }

    /**
     * metodo para incrementar en uno  playCount 
     */
    public void  incrementCount()
    {
        playCount = playCount + 1;

    }

    /**
     * metodo para dar discografica a una cancion 
     */
    public void setDiscografica(String nombreDiscografica)
    {
        this.discografica = nombreDiscografica ;

    }

    
}
