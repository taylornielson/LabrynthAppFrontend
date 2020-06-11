package com.example.labrynth.Models;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.example.labrynth.UI.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Board{
    static final String TAG = "BOARD";
    public Piece[][] board;
    public Piece currentTile;
    List<Piece> allPieces = new ArrayList<Piece>();
    List<Piece> movingPieces;
    ArrayList<Integer> straightDirs = new ArrayList<Integer>(Arrays.asList(0,2));
    ArrayList<Integer> triDirs = new ArrayList<Integer>(Arrays.asList(0,1,3));
    ArrayList<Integer> elbowDirs = new ArrayList<Integer>(Arrays.asList(0,1));
    ArrayList<Integer> owlDirs = new ArrayList<Integer>(Arrays.asList(1,2));
    ArrayList<Integer> spiderDirs = new ArrayList<Integer>(Arrays.asList(0,3));
    ArrayList<Integer> ratDirs = new ArrayList<Integer>(Arrays.asList(2,3));
    ArrayList<Integer> upsidedown3 = new ArrayList<Integer>(Arrays.asList(1,2,3));
    ArrayList<Integer> right3 = new ArrayList<Integer>(Arrays.asList(0,1,2));
    ArrayList<Integer> left3 = new ArrayList<Integer>(Arrays.asList(0,2,3));
    public ArrayList<int[]> movingOptions = new ArrayList<>();
    public ArrayList<int[]> players = new ArrayList<>();

    public Board (int numPlayers){
        players.add(new int[]{0,0});
        players.add(new int[]{6,6});
        if(numPlayers == 3) {
            players.add(new int[]{0, 6});
        }
        else {
            players.add(new int[]{0, 6});
            players.add(new int[]{6, 0});
        }
        board = new Piece[7][7];
        fillPieces();
        board[0][0] = allPieces.get(0);
        board[0][2] = allPieces.get(1);
        board[0][4] = allPieces.get(2);
        board[0][6] = allPieces.get(3);

        board[2][0] = allPieces.get(4);
        board[2][2] = allPieces.get(5);
        board[2][4] = allPieces.get(6);
        board[2][6] = allPieces.get(7);

        board[4][0] = allPieces.get(8);
        board[4][2] = allPieces.get(9);
        board[4][4] = allPieces.get(10);
        board[4][6] = allPieces.get(11);

        board[6][0] = allPieces.get(12);
        board[6][2] = allPieces.get(13);
        board[6][4] = allPieces.get(14);
        board[6][6] = allPieces.get(15);
        fillBoard();
    }

    public Piece[][] getBoard() {
        return board;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public Piece getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Piece currentTile) {
        this.currentTile = currentTile;
    }

    public List<Piece> getAllPieces() {
        return allPieces;
    }

    public void setAllPieces(List<Piece> allPieces) {
        this.allPieces = allPieces;
    }

    private void fillPieces(){
        //fixedPieces
        allPieces.add(new Piece("redhome", new ArrayList<Integer>(Arrays.asList(1,2)), "redhometile",4));
        allPieces.add(new Piece("book", new ArrayList<Integer>(Arrays.asList(1,2,3)), "booktile",4));
        allPieces.add(new Piece("money", new ArrayList<Integer>(Arrays.asList(1,2,3)), "moneytile",4));
        allPieces.add(new Piece("yellowhome", new ArrayList<Integer>(Arrays.asList(2,3)), "yellowhometile",4));
        allPieces.add(new Piece("map", new ArrayList<Integer>(Arrays.asList(0,1,2)), "maptile",4));
        allPieces.add(new Piece("crown", new ArrayList<Integer>(Arrays.asList(0,1,2)), "crowntile",4));
        allPieces.add(new Piece("key", new ArrayList<Integer>(Arrays.asList(1,2,3)), "keytile",4));
        allPieces.add(new Piece("skull", new ArrayList<Integer>(Arrays.asList(0,2,3)), "skulltile",4));
        allPieces.add(new Piece("ring", new ArrayList<Integer>(Arrays.asList(0,1,2)), "ringtile",4));
        allPieces.add(new Piece("chest", new ArrayList<Integer>(Arrays.asList(0,1,3)), "chesttile",4));
        allPieces.add(new Piece("gem", new ArrayList<Integer>(Arrays.asList(0,2,3)), "gemtile",4));
        allPieces.add(new Piece("sword", new ArrayList<Integer>(Arrays.asList(0,2,3)), "swordtile",4));
        allPieces.add(new Piece("greenhome", new ArrayList<Integer>(Arrays.asList(0,1)), "greenhometile",4));
        allPieces.add(new Piece("candle", new ArrayList<Integer>(Arrays.asList(0,1,3)), "candletile",4));
        allPieces.add(new Piece("knight", new ArrayList<Integer>(Arrays.asList(0,1,3)), "knighttile",4));
        allPieces.add(new Piece("bluehome", new ArrayList<Integer>(Arrays.asList(0,3)), "bluehometile",4));

        //Nonfixed Pieces
        allPieces.add(new Piece("bat", new ArrayList<Integer>(Arrays.asList(0,1,3)), "battile"));
        allPieces.add(new Piece("bug", new ArrayList<Integer>(Arrays.asList(0,3)), "bugtile"));
        allPieces.add(new Piece("dragon", new ArrayList<Integer>(Arrays.asList(0,1,3)), "dragontile"));
        allPieces.add(new Piece("gnome", new ArrayList<Integer>(Arrays.asList(0,1,3)), "gnometile"));
        allPieces.add(new Piece("lizard", new ArrayList<Integer>(Arrays.asList(0,3)), "lizardtile"));
        allPieces.add(new Piece("magician", new ArrayList<Integer>(Arrays.asList(0,1,3)), "magiciantile"));
        allPieces.add(new Piece("owl", new ArrayList<Integer>(Arrays.asList(1,2)), "owltile"));
        allPieces.add(new Piece("rat", new ArrayList<Integer>(Arrays.asList(2,3)), "rattile"));
        allPieces.add(new Piece("scarab", new ArrayList<Integer>(Arrays.asList(1,2)), "scarabtile"));
        allPieces.add(new Piece("spider", new ArrayList<Integer>(Arrays.asList(0,3)), "spidertile"));
        allPieces.add(new Piece("tallghost", new ArrayList<Integer>(Arrays.asList(0,1,3)), "tallghosttile"));
        allPieces.add(new Piece("wideghost", new ArrayList<Integer>(Arrays.asList(0,1,3)), "wideghosttile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,2)), "straighttile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,2)), "straighttile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,2)), "straighttile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,2)), "straighttile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,2)), "straighttile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,2)), "straighttile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,2)), "straighttile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,2)), "straighttile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,2)), "straighttile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,2)), "straighttile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,2)), "straighttile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,2)), "straighttile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,2)), "straighttile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,1)), "righttoptile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,1)), "righttoptile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,1)), "righttoptile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,1)), "righttoptile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,1)), "righttoptile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,1)), "righttoptile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,1)), "righttoptile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,1)), "righttoptile"));
        allPieces.add(new Piece("", new ArrayList<Integer>(Arrays.asList(0,1)), "righttoptile"));

        //assigns random rotate value
        Random rand = new Random();
        for(int i = 16; i < allPieces.size(); ++i){
            allPieces.get(i).setBeginrotate(rand.nextInt(4));
        }

        movingPieces = allPieces.subList(16,allPieces.size());
    }

    private void fillBoard(){
        Random rand = new Random();
        for(int i =0; i < 7; ++i){
            for (int j = 0; j < 7; ++j){
                if(board[i][j] == null){
                    int x = rand.nextInt(movingPieces.size());
                    board[i][j] = movingPieces.get(x);
                    board[i][j].rotateVals();
                    movingPieces.remove(x);
                }
                for(int z = 0; z < board[i][j].getDirections().size(); ++z){
                    Log.i(TAG, Integer.toString(i) + " " + Integer.toString(j) + " " + Integer.toString(board[i][j].getDirections().get(z)) + " " +Integer.toString(board[i][j].getBeginrotate()));
                }
            }
        }
        currentTile = movingPieces.get(0);
        currentTile.rotateVals();
        movingPieces.remove(0);
        Log.i(TAG, "fillBoard: " + currentTile.getValue());
        Log.i(TAG, "fillBoard: "+ Integer.toString(movingPieces.size()));
    }

    public void shiftLeft(int rowIndex){
        Piece tempPiece = board[rowIndex][0];
        for(int z = 0; z < players.size(); ++z) {
            if (players.get(z)[0] == rowIndex) {
                players.get(z)[1] = mod((players.get(z)[1] - 1), 7);
                Log.i(TAG, "shiftLeft: left shift x v");
            }
        }
        Log.i(TAG, "shiftLeft: " + Integer.toString(players.get(0)[0]));
        Log.i(TAG, "shiftLeft: " + Integer.toString(players.get(0)[1]));
        for (int i = 0; i < board[rowIndex].length - 1; ++i){
            board[rowIndex][i] = board[rowIndex][i+1];
        }

        board[rowIndex][6] = currentTile;
        setCurrentTile(tempPiece);
        for (int i = 0; i < 7; ++i) {
            for(int j = 0; j<7; ++j) {
                for (int z = 0; z < board[i][j].getDirections().size(); ++z) {
                    Log.i(TAG, Integer.toString(i) + " " + Integer.toString(j) + " " + Integer.toString(board[i][j].getDirections().get(z)) + " " + Integer.toString(board[i][j].getBeginrotate()));
                }
            }
        }
    }

    public void shiftRight(int rowIndex){
        Piece tempPiece = board[rowIndex][6];
        for(int z = 0; z < players.size(); ++z) {
            if (players.get(z)[0] == rowIndex) {
                players.get(z)[1] = mod(players.get(z)[1] + 1, 7);
            }
        }
        for (int i = 6; i > 0; --i){
            board[rowIndex][i] = board[rowIndex][i-1];
        }
        board[rowIndex][0] = currentTile;
        setCurrentTile(tempPiece);
        for (int i = 0; i < 7; ++i) {
            for(int j = 0; j<7; ++j) {
                for (int z = 0; z < board[i][j].getDirections().size(); ++z) {
                    Log.i(TAG, Integer.toString(i) + " " + Integer.toString(j) + " " + Integer.toString(board[i][j].getDirections().get(z)) + " " + Integer.toString(board[i][j].getBeginrotate()));
                }
            }
        }
    }

    public void shiftDown(int colIndex){
        Piece tempPiece = board[6][colIndex];
        for(int z = 0; z < players.size(); ++z) {
            if (players.get(z)[1] == colIndex) {
                players.get(z)[0] = mod(players.get(z)[0] + 1, 7);
            }
        }
        for (int i = 6; i > 0; --i){
            board[i][colIndex] = board[i-1][colIndex];
        }
        board[0][colIndex] = currentTile;
        setCurrentTile(tempPiece);
        for (int i = 0; i < 7; ++i) {
            for(int j = 0; j<7; ++j) {
                for (int z = 0; z < board[i][j].getDirections().size(); ++z) {
                    Log.i(TAG, Integer.toString(i) + " " + Integer.toString(j) + " " + Integer.toString(board[i][j].getDirections().get(z)) + " " + Integer.toString(board[i][j].getBeginrotate()));
                }
            }
        }
    }

    public void shiftUp(int colIndex){
        Piece tempPiece = board[0][colIndex];
        for(int z = 0; z < players.size(); ++z) {
            if (players.get(z)[1] == colIndex) {
                players.get(z)[0] = mod(players.get(z)[0] - 1, 7);
            }
        }
        for (int i = 0; i < 6; ++i){
            board[i][colIndex] = board[i+1][colIndex];
        }
        board[6][colIndex] = currentTile;
        setCurrentTile(tempPiece);
        for (int i = 0; i < 7; ++i) {
            for(int j = 0; j<7; ++j) {
                for (int z = 0; z < board[i][j].getDirections().size(); ++z) {
                    Log.i(TAG, Integer.toString(i) + " " + Integer.toString(j) + " " + Integer.toString(board[i][j].getDirections().get(z)) + " " + Integer.toString(board[i][j].getBeginrotate()));
                }
            }
        }
    }
    public int mod(int x, int y)
    {
        int result = x % y;
        return result < 0? result + y : result;
    }

    public void getMovingOptions(int[]a){
        Stack<int[]> stack = new Stack<>();
        stack.push(a);
        while(!stack.empty()){
            Log.i(TAG, "getMovingOptions: StackloopStarting");
            int x = stack.peek()[0];
            int y = stack.peek()[1];
            board[x][y].visited = true;
            ArrayList dirs = board[x][y].getDirections();
            stack.pop();
            if (dirs.contains(0) && x != 0){
                if(board[x-1][y].getDirections().contains(2) && !board[x-1][y].visited) {
                    Log.i(TAG, "getMovingOptions: Added up to stack");
                    movingOptions.add(new int[]{x - 1, y});
                    stack.push(new int[]{x - 1, y});
                }
            }
            if (dirs.contains(1) && y != 6){
                if(board[x][y+1].getDirections().contains(3) && !board[x][y+1].visited) {
                    Log.i(TAG, "getMovingOptions: Added right to the stack");
                    movingOptions.add(new int[]{x, y + 1});
                    stack.push(new int[]{x, y + 1});
                }
            }
            if(dirs.contains(2) && x != 6){
                if(board[x+1][y].getDirections().contains(0) && !board[x+1][y].visited) {
                    Log.i(TAG, "getMovingOptions: Adding Down to stack");
                    movingOptions.add(new int[]{x + 1, y});
                    stack.push(new int[]{x + 1, y});
                }
            }
            if (dirs.contains(3) && y != 0){
                if(board[x][y-1].getDirections().contains(1) && !board[x][y-1].visited) {
                    Log.i(TAG, "getMovingOptions: Added left to the stack");
                    movingOptions.add(new int[]{x, y - 1});
                    stack.push(new int[]{x, y - 1});
                }
            }

        }
    }
}
