/**
 * @author Vrunda Patel
 * @version 0.1.0
 * @Since 1/29/26
 **/
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.*;

public class Jotto {
    private static final int WORD_SIZE = 5;
    private String currentWord;
    private int score;
    private ArrayList<String> playerGuesses = new ArrayList<>();
    private ArrayList<String> playWords = new ArrayList<>();
    private String filename;
    private ArrayList<String> wordList = new ArrayList<>();
    private static final boolean DEBUG = true;

    Scanner scan = new Scanner(System.in);

    public Jotto(String filename) {
        this.filename = filename;
        readWords();
    }

    public ArrayList<String> getPlayedWords() {
        return playWords;
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
                    score = guess();
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


    public String showWordList() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current word list:\n");
        for (int i = 0; i < wordList.size(); i++){
            sb.append(wordList.get(i)).append("\n");
        }
        return sb.toString();
    }


    public ArrayList<String> showPlayerGuesses() {
        if(playerGuesses.isEmpty()){
            System.out.println("No guesses yet");
        } else {
            System.out.println("Current player guesses:");
            for (int i = 0; i < playerGuesses.size(); i++){
                System.out.println(playerGuesses.get(i));
            }
        }
        System.out.println("Would you like to add the words to the word list? (y/n)");
        String answer = scan.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            updateWordList();
            System.out.println(showWordList());
        }
        return playerGuesses;
    }


    private int guess() {
        ArrayList<String> currentGuesses = new ArrayList<>();
        int letterCount = 0;
        int score = WORD_SIZE + 1;
        String wordGuess = " ";
        System.out.println("Current Score: " + score);
        System.out.println("What is your guess (q to quit):");
        wordGuess = scan.nextLine();
        if (wordGuess.equals("q")) {
            score = Math.min(score, 0);
            return score;
        }
        if (wordGuess.length() > WORD_SIZE || wordGuess.length() < WORD_SIZE){
            System.out.println("Word must be 5 characters" + "(is " + wordGuess.length() + ")" );
            return score;
        } else {
            addPlayerGuess(wordGuess);
        }
        if (wordGuess.equals(currentWord)){
            System.out.println("DINGDINGDING!!! the word was " + currentWord);
            return score;
        }
        return score;
    }


    public int getLetterCount(String wordGuess) {
        int count = 0;
        if (wordGuess.equals(currentWord)){
            return WORD_SIZE;
        }
        String currentWord2 = currentWord;
        for(int i = 0; i < wordGuess.length(); i++){
            char letter = wordGuess.charAt(i);
            for(int k = 0; k < currentWord2.length(); k++) {
                if (currentWord2.charAt(k) == letter) {
                    count++;

                    currentWord2 = currentWord2.substring(0, k) + currentWord2.substring(k + 1);
                    break;
                }
            }
        }
        return count;
    }


    private void updateWordList(){
        try {
            FileWriter fw = new FileWriter(filename);
            for (int i = 0; i < playerGuesses.size(); i++){
                String wordToUpdate = playerGuesses.get(i);

                if(!wordList.contains(wordToUpdate)){
                    wordList.add(wordToUpdate);
                }
            }
            for (int i = 0; i < wordList.size(); i++){
                String wordsToAdd = wordList.get(i);
                fw.write(wordsToAdd);
                fw.write("\n");
            }
            fw.close();
        } catch (Exception trouble){
            System.out.println("Cannot add words");
        }
    }


    public boolean pickWord() {
        Random rand = new Random();
        int randomWord = rand.nextInt(wordList.size());

        currentWord = wordList.get(randomWord);

        if(playWords.contains(currentWord) &&  ){


        }
        return


    }

    public boolean addPlayerGuess(String wordGuess){
        for (int i = 0; i < playerGuesses.size(); i++){
            if (playerGuesses.get(i).equals(wordGuess)){
                return false;
            }
        }
        playerGuesses.add(wordGuess);
        return true;
    }

    private void playerGuessScores(ArrayList<String> guesses){
        System.out.println("Guess\t\tScore");
        for(int i = 0; i < guesses.size(); i++){
            String printGuesses = guesses.get(i);
            int score = getLetterCount(printGuesses);
            System.out.println(printGuesses+"		"+ score);
        }
        System.out.println();
    }






























}
