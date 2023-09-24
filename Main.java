public class Main {

    public static void main(String[] args) throws AgeOfEmpiresException
    {
        Game g = new Game(2);



        g.getPlayer(0).purchase(new Cavalry());
        g.getPlayer(1).purchase(new Spearman());

        for (int j = 1; j < 50; j++) {
            g.getPlayer(0).getSoldier(0).move1(2*j, 0);
            g.getPlayer(1).getSoldier(0).move1(99, 49-j);

        }
        g.getPlayer(0).getSoldier(0).attack1(g.getPlayer(1).getSoldier(0).getX1(), g.getPlayer(1).getSoldier(0).getY1());



        g.getPlayer(1).getWorker(0).move1(g.getPlayer(1).getWorker(0).getX1()-1,g.getPlayer(1).getWorker(0).getY1());
        g.getPlayer(0).pass();


        g.getPlayer(1).getWorker(0).build(new University());
        g.getPlayer(0).pass();


        g.getPlayer(1).getWorker(0).move1(g.getPlayer(1).getWorker(0).getX1(), g.getPlayer(1).getWorker(0).getY1() -1);
        g.getPlayer(0).pass();


        g.getPlayer(1).getWorker(0).move1(g.getPlayer(1).getWorker(0).getX1(), g.getPlayer(1).getWorker(0).getY1() -1);
        g.getPlayer(0).pass();


        g.getPlayer(1).getWorker(0).build(new Tower());
        g.getPlayer(0).pass();


        g.getPlayer(1).purchase(new Cavalry());
        g.getPlayer(0).pass();


        g.getPlayer(1).getSoldier(0).move1(g.getPlayer(1).getSoldier(0).getX1()-3, g.getPlayer(1).getSoldier(0).getY1());
        g.getPlayer(0).pass();


        g.getPlayer(1).getSoldier(0).move1(g.getPlayer(1).getSoldier(0).getX1()-3, g.getPlayer(1).getSoldier(0).getY1());
        g.getPlayer(0).pass();


        g.getPlayer(1).getUniversity().trainCavalry();

        g.getPlayer(0).pass();

        g.getPlayer(1).purchase(new Cavalry());
        g.getPlayer(0).pass();
        g.getPlayer(1).pass();


        g.getPlayer(0).getSoldier(0).move1(g.getPlayer(0).getSoldier(0).getX1()-2, g.getPlayer(0).getSoldier(0).getY1()+7);
        g.getPlayer(1).pass();


        g.getPlayer(0).getSoldier(0).move1(g.getPlayer(0).getSoldier(0).getX1(), g.getPlayer(0).getSoldier(0).getY1()+9);
        g.getPlayer(1).pass();


        g.getPlayer(0).getSoldier(0).move1(g.getPlayer(0).getSoldier(0).getX1(), g.getPlayer(0).getSoldier(0).getY1()+9);
        g.getPlayer(1).pass();


        g.getPlayer(0).getSoldier(0).move1(g.getPlayer(0).getSoldier(0).getX1(), g.getPlayer(0).getSoldier(0).getY1()+9);
        g.getPlayer(1).pass();


        g.getPlayer(0).getSoldier(0).move1(g.getPlayer(0).getSoldier(0).getX1(), g.getPlayer(0).getSoldier(0).getY1()+9);
        g.getPlayer(1).pass();


        g.getPlayer(0).getSoldier(0).move1(g.getPlayer(0).getSoldier(0).getX1(), g.getPlayer(0).getSoldier(0).getY1()+5);
        g.getPlayer(1).pass();


        g.getPlayer(0).getSoldier(0).move1(g.getPlayer(0).getSoldier(0).getX1()+1, g.getPlayer(0).getSoldier(0).getY1()+1);
        g.getPlayer(1).pass();


        g.getPlayer(0).getSoldier(0).attack1(g.getPlayer(0).getSoldier(0).getX1() + 1, g.getPlayer(0).getSoldier(0).getY1());
        g.getPlayer(1).pass();


        g.getPlayer(0).getSoldier(0).attack1(g.getPlayer(0).getSoldier(0).getX1() + 1, g.getPlayer(0).getSoldier(0).getY1());
        g.getPlayer(1).pass();


        g.getPlayer(0).getSoldier(0).attack1(g.getPlayer(0).getSoldier(0).getX1() + 1, g.getPlayer(0).getSoldier(0).getY1());
        g.getPlayer(1).pass();


        g.getPlayer(0).getSoldier(0).attack1(g.getPlayer(0).getSoldier(0).getX1() + 1, g.getPlayer(0).getSoldier(0).getY1());
        g.getPlayer(1).pass();


        g.getPlayer(0).getSoldier(0).attack1(g.getPlayer(0).getSoldier(0).getX1() + 1, g.getPlayer(0).getSoldier(0).getY1());
        g.getPlayer(1).pass();


        g.getPlayer(0).getSoldier(0).attack1(g.getPlayer(0).getSoldier(0).getX1() + 1, g.getPlayer(0).getSoldier(0).getY1());
        g.getPlayer(1).pass();


        g.getPlayer(0).getSoldier(0).move1(g.getPlayer(0).getSoldier(0).getX1()+1,g.getPlayer(0).getSoldier(0).getY1());
        g.getPlayer(1).pass();
         

         
         
         

        g.save_text("deneme1");
        Game a = new Game("deneme1", false);
        a.getMap().print();
        System.out.println(a.getPlayer(0).getGold() + " " + a.getPlayer(0).getWood() + " " + a.getPlayer(0).getStone());
        a.getPlayer(0).pass();
        a.getPlayer(1).pass();
        a.getPlayer(0).getSoldier(0).move1(a.getPlayer(0).getSoldier(0).getX1(),a.getPlayer(0).getSoldier(0).getY1()-1);
        a.getPlayer(1).pass();
        System.out.println(a.getPlayer(1).getMainBuilding().getLifePoints());
        for (int k = 0; k < 20; k++) {
            a.getPlayer(0).getSoldier(0).attack1(a.getPlayer(0).getSoldier(0).getX1() + 1,a.getPlayer(0).getSoldier(0).getY1()+ 1);
            a.getPlayer(1).pass();
        }
        //System.out.println(a.getPlayer(1).getMainBuilding().getLifePoints());
        a.getMap().print();
        System.out.println(a.getPlayer(0).getMainBuilding().getX() + " " + a.getPlayer(0).getMainBuilding().getY());





         
         
    }
}

