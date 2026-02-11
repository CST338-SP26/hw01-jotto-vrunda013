/**
 * @author Vrunda Patel
 * @version 0.1.0
 * @Since 1/29/26
 **/
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;

public class Jotto {
    private static final int WORD_SIZE = 5;
    private String currentWord;
    private int score;
    private ArrayList<String> playGuesses = new ArrayList<>();
    private ArrayList<String> playWords = new ArrayList<>();
    private String filename;
    private ArrayList<String> wordList = new ArrayList<>();
    private static final boolean DEBUG = true;


    public Jotto(String filename){
        this.filename = filename;
        readWords();
    }

    public boolean pickWord(){


    }

    public String showWordList(){

    }

    public ArrayList<String> showPlayerGuesses(){

    }

    private void playerGuessesScores(ArrayList<String>){


    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }



    public ArrayList<String> readWords()  {
        try {
            FileReader fr = new FileReader(filename);
            Scanner fs = new Scanner(fr);

            while (fs.hasNextLine()) {
                String word = fs.nextLine();

                boolean duplicate = false;
                for (int i = 0; i < wordList.size(); i++) {
                    if (wordList.get(i).equals(word)) {
                        duplicate = true;
                        break;
                    }
                }
                if (!duplicate) {
                    wordList.add(word);
                }
            }
            fs.close();
            fr.close();
        } catch (Exception trouble){
            System.out.println("Couldn't open " + filename);
            return wordList;
        }
        return wordList;
    }

    public void play(){
        System.out.println("Welcome to the game.");
        System.out.println("Current Score: 0");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=");
        




    }

    private int guess(){

    }

    public int getLetterCount(String ){
        return
    }

    public ArrayList<String> getPlayWords() {
        return playWords;
    }

    public String showPlayedWords(){

    }

    public boolean addPlayer(String){

    }

    private void updateWordList(){

    }








































}
