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

    Scanner scan = new Scanner(System.in);


    public Jotto(String filename) {
        this.filename = filename;
        readWords();
    }

    public boolean pickWord() {


    }

    public String showWordList() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current word list:\n");
        for (int i = 0; i < wordList.size(); i++){
            sb.append(wordList.get(i)).append("\n");
        }
        return sb.toString();
    }

    public ArrayList<String> showPlayerGuesses() {
        if(playGuesses.isEmpty()){
            System.out.println("No guesses yet");
        } else {
            System.out.println("Current player guesses:");
            for (int i = 0; i < playGuesses.size(); i++){
                System.out.println(playGuesses.get(i));
            }
        }
        System.out.println("Would you like to add the words to the word list? (y/n)");
        String answer = scan.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            updateWordList();
            System.out.println(showWordList());
        }
        return playGuesses;
    }

    private void playerGuessesScores(ArrayList<String>) {


    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }


    public ArrayList<String> readWords() {
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
        } catch (Exception trouble) {
            System.out.println("Couldn't open " + filename);
            return wordList;
        }
        return wordList;
    }


    public void play() {
        System.out.println("Welcome to the game.");
        System.out.println("Current Score: 0");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Choose one of the following:\n");
        System.out.println("1:\t Start the game");
        System.out.println("2:\t See the word list");
        System.out.println("3:\t See the chosen words");
        System.out.println("4:\t Show Player guesses");
        System.out.println("zz to exit");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("What is your choice: ");
        String input = "";
        int score = 0;
        while (!input.equalsIgnoreCase("zz")) {
            System.out.println("What is your choice: ");
            input = scan.nextLine();
            if (input.equals("1") || input.equalsIgnoreCase("one")) {
                boolean pickWord = pickWord();
                if (!pickWord) {
                    showPlayerGuesses();
                } else if (pickWord) {
                    guess();
                    System.out.println("Score: " + score);
                }
            } else if (input.equals("2") || input.equalsIgnoreCase("two")) {
                showWordList();
            } else if (input.equals("3") || input.equalsIgnoreCase("three")) {
                showPlayedWords();
            } else if (input.equals("4") || input.equalsIgnoreCase("four")) {
                showPlayerGuesses();
            } else
                System.out.println("I don't know what " + input + " is.");
            System.out.println("Press enter to continue");
            scan.nextLine();
        }
        if (input.equalsIgnoreCase("zz")) {
            System.out.println("Final score: " + score);
            System.out.println("Thank you for playing");
        }
        System.out.println("Final score: " + score);
        System.out.println("Thank you for playing");
    }


    private int guess() {


    }

    public int getLetterCount(String) {
        return
    }

    public ArrayList<String> getPlayWords() {
        return playWords;
    }

    public String showPlayedWords() {
        StringBuilder sb = new StringBuilder();
        if (playWords.isEmpty()) {
            return "No words have been played";
        }
        sb.append("Current list of played words: ");
        if (!playWords.isEmpty()) {
            for (int i = 0; i < playWords.size(); i++) {
                sb.append(playWords.get(i)).append("\n");
            }
        }
        return sb.toString();
    }

    public boolean addPlayer(String){


    }

    private void updateWordList(){

    }








































}
