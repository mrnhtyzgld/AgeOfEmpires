import java.io.*;
import java.util.Scanner;

public class Game implements GameInterface, Serializable {
    private Player[] players;
    private Map map;
    private Player whoseTurn;
    public Game(int playerCount) throws AgeOfEmpiresException{

        map = new Map();
        map.setGame(this);


        if (playerCount < 2 || playerCount > 4) {
            throw new AgeOfEmpiresException();
        }

        players = new Player[playerCount];
        for (int a = 0; a < players.length; a++) {
            players[a] = new Player();
            players[a].setGame(this);
            players[a].getMainBuilding().setPlayer(players[a]);  
            MainBuilding m = players[a].getMainBuilding();

            whoseTurn = players[a];
            if (a == 0) {
                players[a].getGame().getMap().create(m, 0, 0);
            }
            if (a == 1) {
                players[a].getGame().getMap().create(m, 99, 49);

            }
            if (a == 2) {
                players[a].getGame().getMap().create(m, 0, 49);
            }
            if (a == 3) {
                players[a].getGame().getMap().create(m, 99, 0);
            }


            players[a].purchase(new Worker());

        }
        whoseTurn = players[0];

    }
    public Game(String fileName, Boolean isBinary) throws AgeOfEmpiresException {  
        if (isBinary) {

            ObjectInputStream o = null;
            try {
                o = new ObjectInputStream(new FileInputStream(fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                map = (Map) o.readObject();
                map.setGame(this);

                players = (Player[]) o.readObject();
                whoseTurn = (Player) o.readObject();

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            Scanner o = null;
            try {
                o = new Scanner(new FileInputStream(fileName));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }











            map = new Map();
            map.setGame(this);

            int playerCount = o.nextInt();
            int nextPlayer = o.nextInt();

            players = new Player[playerCount];
            for (int a = 0; a < playerCount; a++)  
            {
                players[a] = new Player();
                players[a].setGame(this);

                players[a].setGoldCount(o.nextInt());
                players[a].setStoneCount(o.nextInt());
                players[a].setWoodCount(o.nextInt());

                for (int b = 0; b < 100; b++)  
                {
                    for (int c = 0; c < 50; c++)  
                    {
                        int e = o.nextInt();
                        for (int d = 0; d < e; d++)  
                        {
                            int itemType = o.nextInt();
                            if (itemType == 1) {  
                                Worker temp = new Worker();
                                temp.setPlayer(players[a]);

                                players[a].getGame().getMap().create(temp, b, c);
                                players[a].add(temp);
                                temp.setLifePoints(o.nextInt());

                            }
                            if (itemType == 2) {  
                                Archer temp = new Archer();
                                temp.setPlayer(players[a]);

                                players[a].getGame().getMap().create(temp, b, c);
                                players[a].add(temp);
                                temp.setLifePoints(o.nextInt());

                            }
                            if (itemType == 3) {  
                                Catapult temp = new Catapult();
                                temp.setPlayer(players[a]);

                                players[a].getGame().getMap().create(temp, b, c);
                                players[a].add(temp);
                                temp.setLifePoints(o.nextInt());

                            }
                            if (itemType == 4) {  
                                Cavalry temp = new Cavalry();
                                temp.setPlayer(players[a]);

                                players[a].getGame().getMap().create(temp, b, c);
                                players[a].add(temp);
                                temp.setLifePoints(o.nextInt());

                            }
                            if (itemType == 5) {  
                                Spearman temp = new Spearman();
                                temp.setPlayer(players[a]);

                                players[a].getGame().getMap().create(temp, b, c);
                                players[a].add(temp);
                                temp.setLifePoints(o.nextInt());

                            }
                            if (itemType == 6) {  
                                Swordsman temp = new Swordsman();
                                temp.setPlayer(players[a]);

                                players[a].getGame().getMap().create(temp, b, c);
                                players[a].add(temp);
                                temp.setLifePoints(o.nextInt());

                            }
                            if (itemType == 7) {  
                                Tower temp = new Tower();
                                temp.setPlayer(players[a]);

                                players[a].getGame().getMap().create(temp, b, c);
                                players[a].add(temp);
                                temp.setLifePoints(o.nextInt());

                            }
                            if (itemType == 8) {  
                                University temp = new University();
                                temp.setPlayer(players[a]);

                                players[a].getGame().getMap().create(temp, b, c);
                                players[a].setUniversity(temp);
                                temp.setLifePoints(o.nextInt());

                            }
                            if (itemType == 9) {  
                                players[a].getMainBuilding().setPlayer(players[a]);

                                players[a].getMainBuilding().setLifePoints(o.nextInt());
                                players[a].getGame().getMap().create(players[a].getMainBuilding(), b, c);

                            }

                        }
                    }
                }

                if (o.nextInt() == 1) {
                    int infLev = o.nextInt(), catLev = o.nextInt(), cavLev = o.nextInt();
                    for (int j = 0; j < infLev; j++)
                        players[j].getUniversity().trainInfantry();
                    for (int j = 0; j < catLev; j++)
                        players[j].getUniversity().trainCatapult();
                    for (int j = 0; j < cavLev; j++)
                        players[j].getUniversity().trainCavalry();
                }
            }
            whoseTurn = players[nextPlayer];



















        }
    }

    @Override
    public void save_text(String fileName) {
        PrintWriter o = null;
        try {
            o = new PrintWriter(new FileOutputStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        o.print(players.length + " ");
        int nextPlayer = -1;
        for (int a = 0; a < players.length; a++) {
            if (players[a] == whoseTurn) nextPlayer = a;
        }
        o.println(nextPlayer);
        for (Player player: players) {
            o.println(player.getGold() + " " + player.getStone() + " " + player.getWood());
            for (int a = 0; a < 100; a++) {
                for (int b = 0; b < 50; b++) {
                    if (getMap().responsibleInCell(a, b) != null && getMap().responsibleInCell(a, b).getPlayer() == player) {
                        o.print(getMap().getElementOfMapWhole(a, b).size() + " ");
                        for (int c = 0; c < getMap().getElementOfMapWhole(a, b).size(); c++) {
                            Item temp = getMap().getElementOfMapWhole(a, b).get(c);

                            if (temp instanceof Worker)
                                o.print(1 + " ");
                            if (temp instanceof Archer)
                                o.print(2 + " ");
                            if (temp instanceof Catapult)
                                o.print(3 + " ");
                            if (temp instanceof Cavalry)
                                o.print(4 + " ");
                            if (temp instanceof Spearman)
                                o.print(5 + " ");
                            if (temp instanceof Swordsman)
                                o.print(6 + " ");
                            if (temp instanceof Tower)
                                o.print(7 + " ");
                            if (temp instanceof University)
                                o.print(8 + " ");
                            if (temp instanceof MainBuilding)
                                o.print(9 + " ");

                            o.print(temp.getLifePoints() + " ");

                        }
                        o.println("");
                    } else o.println(0);
                }
            }

            if (player.getUniversity() == null) o.println(0);
            else o.println(1 + " " + player.getUniversity().getInfantaryLevel() + " "
                    + player.getUniversity().getCatapultLevel() + " "
                    + player.getUniversity().getCavalaryLevel());


        }













        o.close();
    }

    @Override
    public void save_binary(String fileName) {

        ObjectOutputStream o = null;
        try {
            o = new ObjectOutputStream(new FileOutputStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            o.writeObject(map);
            o.writeObject(players);
            o.writeObject(whoseTurn);
            o.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





    @Override
    public Player getPlayer(int x) {
        return players[x];
    }

    @Override
    public Map getMap() { return map; }

    public Player getWhoseTurn() {
        return whoseTurn;
    }

    public void changeWhoseTurn(Player whoseTurn) throws AgeOfEmpiresException {
        whoseTurn.increaseGold(2);
        whoseTurn.increaseStone(5);
        whoseTurn.increaseWood(10);

        if (whoseTurn.getMainBuilding() == null) {
            killPlayer(whoseTurn);
        }

        if (whoseTurn == players[players.length - 1]) {
            this.whoseTurn = players[0];
        } else for (int j = 0; j < players.length - 1; j++)
            if (whoseTurn == players[j]) this.whoseTurn = players[j + 1];

    }

    public void killPlayer(Player player) throws AgeOfEmpiresException {
        if (players.length == 1) throw new AgeOfEmpiresException();

        Player[] temp = new Player[players.length - 1];

        for (int a = 0; a < players.length; a++) {

            if (players[a] != player) {
                for (int b = 0; b < temp.length; b++) {
                    if (temp[b] == null) {
                        temp[b] = players[a];
                        b = players.length;
                    }
                }
            }
        }
        this.players = temp;

        if(players.length == 1){
            System.out.println("Oyun bitti");
        }
    }
}
