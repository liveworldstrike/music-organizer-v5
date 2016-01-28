import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Collections;

/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2011.07.31
 */
public class MusicOrganizer
{
    // An ArrayList for storing music tracks.
    private ArrayList<Track> tracks;
    // A player for the music tracks.
    private MusicPlayer player;
    // A reader that can read music files and load them as tracks.
    private TrackReader reader;
    //para saber si una cancion es completa o no 
    private boolean saberCompleta;
    //guarda si la cancion esta reproduciendose
    private boolean repruOk;

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer(String Carpeta)
    {
        tracks = new ArrayList<Track>();
        player = new MusicPlayer();
        reader = new TrackReader();
        saberCompleta = false;
        repruOk = false;
        readLibrary(Carpeta);
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();

    }

    /**
     * Add a track file to the collection.
     * @param filename The file name of the track to be added.
     */
    public void addFile(String filename)
    {
        tracks.add(new Track(filename));
    }

    /**
     * Add a track to the collection.
     * @param track The track to be added.
     */
    public void addTrack(Track track)
    {
        tracks.add(track);
    }

    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    public void playTrack(int index,boolean saberCompleta1)
    {
        if(indexValid(index)) {
            saberCompleta = saberCompleta1;
            isPlaying();
            if(!repruOk){
                Track track = tracks.get(index);
                player.startPlaying(track.getFilename());
                System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
                track.incrementCount();
                repruOk = true;
            }
            else{
                System.out.println("La cancion esta reproduciendose");
            }
        }
    }

    /**
     * Return the number of tracks in the collection.
     * @return The number of tracks in the collection.
     */
    public int getNumberOfTracks()
    {
        return tracks.size();
    }

    /**
     * List a track from the collection.
     * @param index The index of the track to be listed.
     */
    public void listTrack(int index)
    {
        System.out.print("Track " + index + ": ");
        Track track = tracks.get(index);
        System.out.println(track.getDetails());
    }

    /**
     * Show a list of all the tracks in the collection.
     */
    public void listAllTracks()
    {
        System.out.println("Track listing: ");

        for(Track track : tracks) {
            System.out.println(track.getDetails());
        }
        System.out.println();
    }

    /**
     * List all tracks by the given artist.
     * @param artist The artist's name.
     */
    public void listByArtist(String artist)
    {
        for(Track track : tracks) {
            if(track.getArtist().contains(artist)) {
                System.out.println(track.getDetails());
            }
        }
    }

    /**
     * Remove a track from the collection.
     * @param index The index of the track to be removed.
     */
    public void removeTrack(int index)
    {
        if(indexValid(index)) {
            tracks.remove(index);
        }
    }

    /**
     * Play the first track in the collection, if there is one.
     */
    public void playFirst(boolean saberCompleta1)
    {
        saberCompleta = saberCompleta1;
        if(tracks.size() > 0) {
            isPlaying();
            player.startPlaying(tracks.get(0).getFilename());
            tracks.get(0).incrementCount();
        }
    }

    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
        repruOk = false;
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean indexValid(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;

        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= tracks.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }

    private void readLibrary(String folderName)
    {
        ArrayList<Track> tempTracks = reader.readTracks(folderName, ".mp3");

        // Put all thetracks into the organizer.
        for(Track track : tempTracks) {
            addTrack(track);
        }
    }

    /**
     * metodo que muestra por pantalla la información de los tracks que contienen dicha cadena en el título de la canción.
     */
    public void findInTitle(String caracterABuscar)
    {
        for(Track track : tracks) {
            if(track.getTitle().contains(caracterABuscar)) {
                System.out.println(track.getDetails());
            }
        }

    }

    /**
     * metodo que muestra por pantalla si se esta reproduciendo una cancion completa 
     * 
     */
    public void isPlaying()
    {
        if (saberCompleta){
            System.out.println("la cancion que se esta reproduciendo es completa ");
        }
        else{
            System.out.println("la cancion que se esta reproduciendo es no completa ");
        }

    }

    /**
     * metodo que muestra todos los tracks almacenados
     */
    public void listAllTrackWithIterator()
    {
        Iterator<Track> it = tracks.iterator();
        while (it.hasNext()){
            Track guarda = it.next();
            System.out.println(guarda.getDetails());
        }
    }

    /**
     * metodo para eliminar una cancion segun el artista 
     *
     */
    public void removeByArtist(String artist)
    {
        Iterator<Track> it = tracks.iterator();
        while (it.hasNext()){
            Track guarda = it.next();
            if (guarda.getArtist().contains(artist)){
                it.remove();
            }
        }
    }

    /**
     * metodo para eliminar una cancion segun el titulo
     */
    public void removeByTitle(String title)
    {
        Iterator<Track> it = tracks.iterator();
        while (it.hasNext()){
            Track guarda = it.next();
            if (guarda.getTitle().contains(title)){
                it.remove();
            }
        }
    }
    
    /**
     * metodo para que reproducca una cacion aleatoria 
     */
    public void playRamdom()
    {
        Random cualquiera = new Random();
        int numeroIntroducido = cualquiera.nextInt(tracks.size());
        playTrack(numeroIntroducido,true);
        
    }
    
    /**
     * metodo para reproducir el arraylist aleatoriamente y solo unos segundos,incrementar el contador de cada cancion,mostrar todos los detalles de cada cancion
     */
    public void playShuffle()
    {
        Collections.shuffle(tracks);
        for (Track track : tracks){
            track.incrementCount();
            System.out.println(track.getDetails());
            player.playSample(track.getFilename());
        }
        
    }
}