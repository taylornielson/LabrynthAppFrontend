package com.example.labrynth.UI;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.labrynth.Models.Board;
import com.example.labrynth.Models.Card;
import com.example.labrynth.Models.Piece;
import com.example.labrynth.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends Fragment {
    private static final String TAG = "GAME Fragment";
    //Game Variables
    int numPlayers;
    Board boardVals = new Board(numPlayers);
    Piece currentTile = boardVals.currentTile;
    int turn = 1;
    boolean hasPlayed = false;
    boolean hasMoved = false;
    String turnString = "Player 1's turn";
    List<List<Card>> playersCards;

    //View Variables
    TextView textView;
    Button play;
    ImageView[][] board = new ImageView[7][7];
    ImageView currentCards;
    ImageButton leftRotate;
    ImageButton rightRotate;
    ImageView currentTileImage;
    ImageView player1Piece;
    ImageView player2Piece;
    ImageView player3Piece;
    ImageView player4Piece;
    ImageButton[] insertButtons = new ImageButton[12];
    int[][][] locations = new int[7][7][2];

    public Game(int i){
        numPlayers = i;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int tempx = dpToPx(64);
        int tempy = dpToPx(64);
        for (int i = 0; i < 7; ++i){
            for (int j = 0; j < 7; ++j){
                locations[i][j][0] = tempx;
                locations[i][j][1] = tempy;
                tempx += dpToPx(44);
            }
            tempx = dpToPx(64);
            tempy += dpToPx(44);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        //Binding View Variables to View ID's
        board[0][0] = v.findViewById(R.id.zero0);
        board[0][1] = v.findViewById(R.id.zero1);
        board[0][2] = v.findViewById(R.id.zero2);
        board[0][3] = v.findViewById(R.id.zero3);
        board[0][4] = v.findViewById(R.id.zero4);
        board[0][5] = v.findViewById(R.id.zero5);
        board[0][6] = v.findViewById(R.id.zero6);

        board[1][0] = v.findViewById(R.id.one0);
        board[1][1] = v.findViewById(R.id.one1);
        board[1][2] = v.findViewById(R.id.one2);
        board[1][3] = v.findViewById(R.id.one3);
        board[1][4] = v.findViewById(R.id.one4);
        board[1][5] = v.findViewById(R.id.one5);
        board[1][6] = v.findViewById(R.id.one6);

        board[2][0] = v.findViewById(R.id.two0);
        board[2][1] = v.findViewById(R.id.two1);
        board[2][2] = v.findViewById(R.id.two2);
        board[2][3] = v.findViewById(R.id.two3);
        board[2][4] = v.findViewById(R.id.two4);
        board[2][5] = v.findViewById(R.id.two5);
        board[2][6] = v.findViewById(R.id.two6);

        board[3][0] = v.findViewById(R.id.three0);
        board[3][1] = v.findViewById(R.id.three1);
        board[3][2] = v.findViewById(R.id.three2);
        board[3][3] = v.findViewById(R.id.three3);
        board[3][4] = v.findViewById(R.id.three4);
        board[3][5] = v.findViewById(R.id.three5);
        board[3][6] = v.findViewById(R.id.three6);

        board[4][0] = v.findViewById(R.id.four0);
        board[4][1] = v.findViewById(R.id.four1);
        board[4][2] = v.findViewById(R.id.four2);
        board[4][3] = v.findViewById(R.id.four3);
        board[4][4] = v.findViewById(R.id.four4);
        board[4][5] = v.findViewById(R.id.four5);
        board[4][6] = v.findViewById(R.id.four6);

        board[5][0] = v.findViewById(R.id.five0);
        board[5][1] = v.findViewById(R.id.five1);
        board[5][2] = v.findViewById(R.id.five2);
        board[5][3] = v.findViewById(R.id.five3);
        board[5][4] = v.findViewById(R.id.five4);
        board[5][5] = v.findViewById(R.id.five5);
        board[5][6] = v.findViewById(R.id.five6);

        board[6][0] = v.findViewById(R.id.six0);
        board[6][1] = v.findViewById(R.id.six1);
        board[6][2] = v.findViewById(R.id.six2);
        board[6][3] = v.findViewById(R.id.six3);
        board[6][4] = v.findViewById(R.id.six4);
        board[6][5] = v.findViewById(R.id.six5);
        board[6][6] = v.findViewById(R.id.six6);

        insertButtons[0] = v.findViewById(R.id.t1);
        insertButtons[1] = v.findViewById(R.id.t2);
        insertButtons[2] = v.findViewById(R.id.t3);

        insertButtons[3] = v.findViewById(R.id.l1);
        insertButtons[4] = v.findViewById(R.id.r1);
        insertButtons[5] = v.findViewById(R.id.l2);
        insertButtons[6] = v.findViewById(R.id.r2);
        insertButtons[7] = v.findViewById(R.id.l3);
        insertButtons[8] = v.findViewById(R.id.r3);
        insertButtons[9] = v.findViewById(R.id.b1);
        insertButtons[10] = v.findViewById(R.id.b2);
        insertButtons[11] = v.findViewById(R.id.b3);

        player1Piece = (ImageView) v.findViewById(R.id.player1piece);
        player2Piece = (ImageView) v.findViewById(R.id.player2piece);
        player3Piece = (ImageView) v.findViewById(R.id.player3piece);
        player4Piece = (ImageView) v.findViewById(R.id.player4piece);
        textView = v.findViewById(R.id.textView);
        play = (Button) v.findViewById(R.id.button);
        currentCards = (ImageView) v.findViewById(R.id.cardView);
        leftRotate = (ImageButton) v.findViewById(R.id.leftRotate);
        rightRotate = (ImageButton) v.findViewById(R.id.rightRotate);
        currentTileImage = (ImageView) v.findViewById(R.id.currentTile);

        //Value Setup
        playersCards = SetUpCards(numPlayers);
        textView.setText(turnString);

        //Set Initial Image Values
        drawBoard(1);


        //Set OnClickListeners
        leftRotate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                currentTileImage.setRotation(currentTileImage.getRotation()-90);
                ArrayList<Integer> newDirs = new ArrayList<Integer>();
                for (int i = 0; i < currentTile.getDirections().size(); ++i){
                    Log.i(TAG, "onClick: left rotate " + Integer.toString(currentTile.getDirections().get(i)));
                    newDirs.add(mod((currentTile.getDirections().get(i) - 1), 4));
                    Log.i(TAG, "onClick: left rotate " + Integer.toString(currentTile.getDirections().get(i)));
                }
                currentTile.setDirections(newDirs);
                currentTile.minusRotate();
            }
        });
        rightRotate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                currentTileImage.setRotation(currentTileImage.getRotation()+90);
                ArrayList<Integer> newDirs = new ArrayList<Integer>();
                for (int i = 0; i < currentTile.getDirections().size(); ++i){
                    newDirs.add((currentTile.getDirections().get(i) + 1) % 4);
                }
                currentTile.addRotate();
                currentTile.setDirections(newDirs);
            }
        });
        insertButtons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!hasPlayed) {
                    float tempRotation = currentTileImage.getRotation();
                    boardVals.shiftDown(1);
                    currentTile = boardVals.currentTile;
                    drawBoard(2);
                    hasPlayed = true;
                }
            }
        });

        insertButtons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasPlayed) {
                    float tempRotation = currentTileImage.getRotation();
                    boardVals.shiftDown(3);
                    currentTile = boardVals.currentTile;
                    drawBoard(2);
                    hasPlayed = true;
                }
            }
        });

        insertButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasPlayed) {
                    float tempRotation = currentTileImage.getRotation();
                    boardVals.shiftDown(5);
                    currentTile = boardVals.currentTile;
                    drawBoard(2);
                    hasPlayed = true;
                }
            }
        });

        insertButtons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasPlayed) {
                    float tempRotation = currentTileImage.getRotation();
                    boardVals.shiftLeft(1);
                    currentTile = boardVals.currentTile;
                    drawBoard(2);
                    hasPlayed = true;
                }
            }

        });
        insertButtons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasPlayed) {
                    float tempRotation = currentTileImage.getRotation();
                    boardVals.shiftLeft(3);
                    currentTile = boardVals.currentTile;
                    drawBoard(2);
                    hasPlayed = true;
                }
            }

        });
        insertButtons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasPlayed) {
                    float tempRotation = currentTileImage.getRotation();
                    boardVals.shiftLeft(5);
                    currentTile = boardVals.currentTile;
                    drawBoard(2);
                    hasPlayed = true;
                }
            }

        });

        insertButtons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasPlayed) {
                    float tempRotation = currentTileImage.getRotation();
                    boardVals.shiftRight(1);
                    currentTile = boardVals.currentTile;
                    drawBoard(2);
                    hasPlayed = true;
                }
            }
        });

        insertButtons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasPlayed) {
                    float tempRotation = currentTileImage.getRotation();
                    boardVals.shiftRight(3);
                    currentTile = boardVals.currentTile;
                    drawBoard(2);
                    hasPlayed = true;
                }
            }
        });
        insertButtons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasPlayed) {
                    float tempRotation = currentTileImage.getRotation();
                    boardVals.shiftRight(5);
                    currentTile = boardVals.currentTile;
                    drawBoard(2);
                    hasPlayed = true;
                }
            }

        });

        insertButtons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasPlayed) {
                    float tempRotation = currentTileImage.getRotation();
                    boardVals.shiftUp(1);
                    currentTile = boardVals.currentTile;
                    drawBoard(2);
                    hasPlayed = true;
                }
            }
        });
        insertButtons[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasPlayed) {
                    float tempRotation = currentTileImage.getRotation();
                    boardVals.shiftUp(3);
                    currentTile = boardVals.currentTile;
                    drawBoard(2);
                    hasPlayed = true;
                }
            }
        });
        insertButtons[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasPlayed) {
                    float tempRotation = currentTileImage.getRotation();
                    boardVals.shiftUp(5);
                    currentTile = boardVals.currentTile;
                    drawBoard(2);
                    hasPlayed = true;
                }
            }
        });
        for(int i = 0; i < 7; ++i){
            final int finalI = i;
            for(int j = 0; j < 7; ++j){
                final int finalJ = j;
                board[i][j].setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        if(!hasMoved && hasPlayed){
                            Log.i(TAG, "onClick: " + Integer.toString(boardVals.players.get(turn-1)[0]) + " " + Integer.toString(boardVals.players.get(turn-1)[1]));
                            boolean tempval = false;
                            boardVals.getMovingOptions(new int[]{boardVals.players.get(turn-1)[0],boardVals.players.get(turn-1)[1]});
                            for(int q = 0; q < boardVals.movingOptions.size(); ++q){
                                Log.i(TAG, "onClick: moving options " + Integer.toString(q) + " x:" +Integer.toString(boardVals.movingOptions.get(q)[0])+ " y:" + Integer.toString(boardVals.movingOptions.get(q)[1]));
                            }
                            for(int counter = 0; counter < boardVals.movingOptions.size(); ++counter){
                                if(boardVals.movingOptions.get(counter)[0] == finalI && boardVals.movingOptions.get(counter)[1] == finalJ){
                                    Log.i(TAG, "onClick: Position should work!");
                                    tempval = true;
                                    break;
                                }
                            }
                            if(tempval) {
                                Log.i(TAG, "onClick: inside shouldmove");
                                boardVals.players.set(turn - 1, new int[]{finalI, finalJ});
                                drawBoard(2);
                                hasMoved = true;
                            }
                            else{
                                Toast.makeText( getActivity().getApplicationContext() ,"No Path Exists to that tile", Toast.LENGTH_LONG).show();
                            }
                        }
                        else if (hasMoved){
                            Toast.makeText( getActivity().getApplicationContext() ,"You Already Moved, Hit the play button to end your turn", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText( getActivity().getApplicationContext() ,"You must insert a tile before you move", Toast.LENGTH_LONG).show();
                        }
                        Log.i(TAG, "onClick: clearing visitedvalues");
                        for(int i = 0; i < 7; ++i){
                            for(int j = 0; j < 7; ++j){
                                boardVals.board[i][j].visited = false;
                            }
                        }
                        boardVals.movingOptions.clear();
                    }
                });
            }
        }

        play.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasPlayed){
                    Toast.makeText( getActivity().getApplicationContext() ,"You must play before ending your turn", Toast.LENGTH_LONG).show();

                }
                else{
                    if(playersCards.get(turn-1).get(0).getImgValString().equals(boardVals.board[boardVals.players.get(turn-1)[0]][boardVals.players.get(turn-1)[1]].getValue())){
                        playersCards.get(turn-1).remove(0);
                        Toast.makeText( getActivity().getApplicationContext() ,"Player " + Integer.toString(turn) + " advanced 1 card!", Toast.LENGTH_LONG).show();

                    }
                    if(playersCards.get(turn-1).isEmpty()){
                        if((turn-1 == 0 && boardVals.players.get(turn-1)[0] == 0 && boardVals.players.get(turn-1)[1] ==0) || (turn-1 == 1 && boardVals.players.get(turn-1)[0] == 6 && boardVals.players.get(turn-1)[1] == 6) || /*player3*/ (turn-1 == 2 && boardVals.players.get(turn-1)[0] == 0 && boardVals.players.get(turn-1)[1] == 6) || /*player4*/(turn-1 == 3 && boardVals.players.get(turn-1)[0] == 6 && boardVals.players.get(turn-1)[1] == 6)) {
                            Toast.makeText(getActivity().getApplicationContext(), "Player " + Integer.toString(turn) + " won!", Toast.LENGTH_LONG).show();
                        }
                    }
                    hasMoved = false;
                    hasPlayed = false;
                    if (turn < numPlayers ){turn += 1;}
                    else { turn = 1;}
                    textView.setText("Player: " + Integer.toString(turn) + "'s turn");
                    int tempInt = getResources().getIdentifier("@drawable/" + playersCards.get(turn-1).get(0).getImgValString() + "card", null, getActivity().getPackageName());
                    currentCards.setImageResource(tempInt);
                }
            }
        });

        return v;
    }
    public List<List<Card>> SetUpCards(int numPlayers){
        String[] possibleCards = {"bat", "book", "bug", "candle", "chest", "crown", "dragon", "gem", "gnome", "key", "knight", "lizard", "magician", "map", "money", "owl", "rat", "ring", "scarab", "skull", "spider", "sword", "tallghost", "wideghost"};
        ArrayList<Card> allCards = new ArrayList<Card>();
        List<List<Card>> dividedCards = new ArrayList<List<Card>>();
        for (int i = 0; i < possibleCards.length; ++i){
            allCards.add(new Card(possibleCards[i]));
        }
        int z = 0;
        List<Card> list1 = new ArrayList<Card>();
        List<Card> list2 = new ArrayList<Card>();
        List<Card> list3 = new ArrayList<Card>();
        List<Card> list4 = new ArrayList<Card>();
        Random rand = new Random();
        while (allCards.size() != 0){
            int randInt = rand.nextInt(allCards.size());
            if (z == 0){list1.add(allCards.get(randInt));}
            else if (z == 1) {list2.add(allCards.get(randInt));}
            else if (z == 2) {list3.add(allCards.get(randInt));}
            else {list4.add(allCards.get(randInt));}
            allCards.remove(randInt);
            if (z < numPlayers - 1){ z +=1;}
            else{z = 0;}
        }
        dividedCards.add(list1);
        dividedCards.add(list2);
        if (list3.size() != 0) {dividedCards.add(list3);}
        if (list4.size() != 0) {dividedCards.add(list4);}
        return dividedCards;
    }

    public void drawBoard(int start){
        for(int i =0; i < currentTile.getDirections().size(); ++i){
            Log.i(TAG, "drawBoard: current tile orientation" + Integer.toString(currentTile.getDirections().get(i)));
        }

        int playerInt = getResources().getIdentifier("@drawable/p1piece", null, this.getActivity().getPackageName());
        player1Piece.setImageResource(playerInt);

        playerInt = getResources().getIdentifier("@drawable/p2piece", null, this.getActivity().getPackageName());
        player2Piece.setImageResource(playerInt);

        if(numPlayers > 2 && numPlayers < 4) {
            playerInt = getResources().getIdentifier("@drawable/p3piece", null, this.getActivity().getPackageName());
            player3Piece.setImageResource(playerInt);
        }
        else if(numPlayers > 3) {
            playerInt = getResources().getIdentifier("@drawable/p3piece", null, this.getActivity().getPackageName());
            player3Piece.setImageResource(playerInt);
            playerInt = getResources().getIdentifier("@drawable/p4piece", null, this.getActivity().getPackageName());
            player4Piece.setImageResource(playerInt);
        }

        int cardInt = getResources().getIdentifier("@drawable/" + playersCards.get(turn-1).get(0).getImgValString() + "card", null, this.getActivity().getPackageName());
        currentCards.setImageResource(cardInt);
        int tileInt = getResources().getIdentifier("@drawable/" + currentTile.getImgVal(), null, this.getActivity().getPackageName());
        currentTileImage.setImageResource(tileInt);
        currentTileImage.setRotation(boardVals.currentTile.getBeginrotate()*90);
        for(int i = 0; i < 7; ++i){
            for (int j = 0; j < 7; ++j){
                int imgInt = getResources().getIdentifier("@drawable/" + boardVals.getBoard()[i][j].getImgVal(), null, this.getActivity().getPackageName());
                if(boardVals.board[i][j].getBeginrotate() != 4){
                    board[i][j].setImageResource(imgInt);
                    board[i][j].setRotation(90*boardVals.board[i][j].getBeginrotate());
                }
                else {
                    board[i][j].setImageResource(imgInt);
                }
                for(int z = 0; z < numPlayers; ++z) {
                    if (boardVals.players.get(z)[0] == i && boardVals.players.get(z)[1] == j) {
                        if(z == 0) {
                            player1Piece.setX(locations[i][j][0]);
                            player1Piece.setY(locations[i][j][1]);
                        }
                        else if (z==1){
                            player2Piece.setX(locations[i][j][0]);
                            player2Piece.setY(locations[i][j][1]);
                        }
                        else if (z == 2){
                            player3Piece.setX(locations[i][j][0]);
                            player3Piece.setY(locations[i][j][1]);
                        }
                        else if (z == 3){
                            player4Piece.setX(locations[i][j][0]);
                            player4Piece.setY(locations[i][j][1]);
                        }
                    }
                }
            }
        }
    }

    public int dpToPx(int dp) {
        float density = getContext().getResources()
                .getDisplayMetrics()
                .density;
        return Math.round((float) dp * density);
    }
    public int mod(int x, int y)
    {
        int result = x % y;
        return result < 0? result + y : result;
    }




}


