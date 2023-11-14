public class Game {

    private static String Name = "Game";
    private static char GM = 'N';

    public static void setname(String name) {
        Name = name;
    }

    public static String getname() {
        return Name;
    }

    public static void setGM(char gm) {
        GM = gm;
    }

    public static void start() {
        Util.greeting();
        Util.gmchoose();

        switch (GM) {
            case 'A':
                startAuto();
                break;
            case 'I':
                startVsAI();
                break;
            case 'P':
                startPvP();
                break;
            default:
                Util.message(1);
                start();
                break;
        }
    }

    private static void startAuto() {
        Player AI1 = new Player("Лорд Скайнет", 3, true);
        Player AI2 = new Player("Межгалактическое ИИ", 3, true);
        AI1.setunits();
        AI2.setunits();

        while (true) {
            AI1.aiAttack(AI2);
            if (!AI2.hasAliveUnits()) {
                Util.awmessage(AI1.getname(), AI2.getname());
                break;
            }

            AI2.aiAttack(AI1);
            if (!AI1.hasAliveUnits()) {
                Util.awmessage(AI2.getname(), AI1.getname());
                break;
            }
        }
    }

    // AI mode: Player vs AI
    private static void startVsAI() {
        Player human = new Player("Человек", 3, false);
        Player ai = new Player("Скайнет", 3, true);
        human.setunits();
        ai.setunits();

        while (true) {
            human.playerAttack(ai);
            if (!ai.hasAliveUnits()) {
                Util.awmessage(human.getname(), ai.getname());
                break;
            }

            ai.aiAttack(human);
            if (!human.hasAliveUnits()) {
                Util.awmessage(ai.getname(), human.getname());
                break;
            }
        }
    }

    // PvP mode: Player vs Player
    private static void startPvP() {
        Player player1 = new Player("Игрок 1", 3, false);
        Player player2 = new Player("Игрок 2", 3, false);
        player1.setunits();
        player2.setunits();

        while (true) {
            player1.playerAttack(player2);
            if (!player2.hasAliveUnits()) {
                Util.awmessage(player1.getname(), player2.getname());
                break;
            }

            player2.playerAttack(player1);
            if (!player1.hasAliveUnits()) {
                Util.awmessage(player2.getname(), player1.getname());
                break;
            }
        }
    }

//    private static void Util.awmessage(Player human, Player AI) {
//        if (!human.hasAliveUnits()) {
//            System.out.println("AI wins!");
//        } else {
//            System.out.println("Human wins!");
//        }
//    }
}