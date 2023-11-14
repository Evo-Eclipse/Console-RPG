import java.util.Scanner;
import java.util.Random;
import java.util.Timer;

public class Util {

    private static final String ANSI_RESET = "\u001B[0m";

    private static String[] KnightNames = {"Garrosh", "Grommash", "Thrall", "Varok", "Saurfang", "Cairne", "Baine",
            "Vol'jin", "Sylvanas", "Valeera", "Anduin", "Jaina", "Malfurion", "Tyrande", "Illidan", "Arthas", "Uther",
            "Rexxar", "Gul'dan", "Genn", "Gelbin", "Mekkatorque", "Tinkmaster", "Teron", "Edwin", "Velen", "Tirion",
            "Grom", "Mannoroth", "Kil'jaeden", "Archimonde", "Kael'thas", "Anub'arak", "Kel'Thuzad", "Sapphiron",
            "Malygos", "Nozdormu", "Ysera", "Alexstrasza", "Nefarian", "Onyxia", "Deathwing", "Al'Akir", "Neptulon",
            "Therazane", "Cho'gall", "C'Thun", "Yogg-Saron", "Algalon", "Lich King", "Murozond", "Sinestra",
            "Ragnaros", "Majordomo"};
    private static String[] WizardNames = {"Gandalf", "Saruman", "Radagast", "Alatar", "Pallando", "Merlin", "Morgana",
            "Nimue", "Prospero", "Morgoth", "Sauron", "Melkor", "Voldemort", "Dumbledore", "Harry", "Hermione",
            "Ron", "Snape", "Dobby", "Gimli", "Legolas", "Frodo", "Sam", "Gollum", "Bilbo", "Thorin", "Balin",
            "Gloin", "Bofur", "Bombur", "Bifur", "Dwalin", "Dori", "Nori", "Ori", "Fili", "Kili", "Bard", "Beorn",
            "Bolg", "Boromir", "Elrond", "Galadriel", "Gloin", "Gollum", "Grima", "Haldir", "Isildur", "Lurtz",
            "Radagast", "Saruman", "Sauron", "Shelob", "Smaug", "Theoden", "Thranduil", "Treebeard", "Ugluk",
            "Witch-King", "Wormtongue", "Wraith", "Nazgul"};
    private static String[] Terminators = {"T-1000", "T-800", "T-850", "T-888", "T-900", "T-950", "T-1000", "T-1001",
            "T-600", "T-700", "T-720", "T-750", "T-800", "T-808", "T-810", "T-820", "T-825", "T-835", "T-850", "T-888",
            "T-900", "T-950", "T-1000", "T-1001", "T-1002", "T-1003", "T-1004", "T-1005", "T-1006", "T-1007", "T-1008",
            "T-1009", "T-1010", "T-1011", "T-1012", "T-1013", "T-1014", "T-1015", "T-1016", "T-1017", "T-1018", "T-1019",
            "T-2000", "T-3000", "T-4000", "T-5000", "T-6000", "T-7000", "T-8000", "T-9000", "T-10000", "T-20000"};

    private static String[][] Apex = {{"Партия подошла к концу", "Восихитительную победу одержал", "над поверженным"},
            {"Игра окончена", "", "расправился над"},
            {"История была написана", "Потрясающий", "сокрушил"}};


    protected static String getRandomKnightName() {
        return KnightNames[(int) (Math.random() * KnightNames.length)];
    }
    protected static String getRandomWizardName() {
        return WizardNames[(int) (Math.random() * WizardNames.length)];
    }
    protected static String getRandomTerminator() {
        return Terminators[(int) (Math.random() * Terminators.length)];
    }

    protected static void parryEvent(String attacker, String defender) {
        System.out.printf("~%s ловко увернулся от %s~\n", defender, attacker);
    }
    protected static void critEvent(String attacker, String defender, int damage) {
        System.out.printf("КРИТ от %s по %s на %d урона!\n", attacker, defender, damage);
    }
    protected static void attackEvent(String attacker, String defender, int damage) {
        System.out.printf("%s нанес %d урона %s\n", attacker, damage, defender);
    }

    protected static void greeting() {
        System.out.printf("Добро пожаловать в игру %s, где ваши заветные баталии могут стать реальной симуляцией!\n" +
                "- - - - - - - - - - - -\n\n", Game.getname());
    }

    protected static void gmchoose() {
        System.out.println("Выберите тип партии:\n  A -- автоматический бой\n  I -- бой против ИИ\n  P -- PVP");
        Scanner in = new Scanner(System.in);
        char c = in.next().charAt(0);

        if (c >= 'A' && c <= 'Z') {
            c += 32;
        }

        switch (c) {
            case 'a':
                System.out.println("Выбран автоматический бой");
                Game.setGM('A');
                break;
            case 'i':
                System.out.println("Выбран бой против ИИ");
                Game.setGM('I');
                break;
            case 'p':
                System.out.println("Выбран PVP");
                Game.setGM('P');
                break;
            default:
                System.out.println("Неверный тип партии");
                gmchoose();
        }
    }

//    protected static void namer(Player player) {
//        System.out.println("Как вы желаете чтобы Вас звали?");
//        Scanner in = new Scanner(System.in);
//        String name = in.nextLine();
//        player.setname(name);
//    }

    protected static String namer() {
        System.out.println("Как Вы желаете, чтобы Вас звали?");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        return name;
    }

    protected static Unit[] uchoose(int ulen) {
        System.out.println("Выберите вашу группировку юнитов, к примеру KWT или twk:");
        Scanner in = new Scanner(System.in);
        String[] iunits = in.nextLine().split("");
        Unit[] units = new Unit[ulen];

        for (int i = 0; i < iunits.length; i++) {
            if (iunits[i].equals("K") || iunits[i].equals("k")) {
                units[i] = new Knight(Util.getRandomKnightName());
            } else if (iunits[i].equals("W") || iunits[i].equals("w")) {
                units[i] = new Wizard(Util.getRandomWizardName());
            } else if (iunits[i].equals("T") || iunits[i].equals("t")) {
                units[i] = new Terminator(Util.getRandomTerminator());
            } else {
                message(2);
                uchoose(ulen);
            }
        }
        return units;
    }

    protected static void displayu(Player player) {
        System.out.println(player.getname() + "' юниты:");
        for (int i = 0; i < player.getunits().length; i++) {
            Unit unit = player.getunits()[i];
            String status = unit.pulse() ? "Жив" : "Мертв";
            System.out.printf("У юнита %d: %s - Здоровье: %d - Общее состояние: %s\n",
                    i, unit.getname(), unit.getHealth(), status);
        }
    }
    protected static int chooseAU(Player player) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(player.getname() + ", выберите атакующего юнита (0, 1, 2):");
            int choice = scanner.nextInt();
            if (player.isValidUnitChoice(choice)) {
                return choice;
            }
            message(3);
        }
    }
    protected static int chooseTU(Player opponent) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите цель аттаки (0, 1, 2):");
            int choice = scanner.nextInt();
            if (opponent.isValidUnitChoice(choice)) {
                return choice;
            }
            System.out.println("Неверный выбор ↺");
        }
    }

    protected static void message(int i) {
        switch (i) {
            case 1: System.out.println("Неверный тип игры ↺"); break;
            case 2: System.out.println("Неверный тип юнита ↺"); break;
            case 3: System.out.println("Неверный выбор ↺"); break;
        }
    }
    protected static void awmessage(String w, String l) {
        int i = (int) (Math.random() * Apex.length);
        System.out.printf("\n\n%s\n- - - - - - - - - - - - - - - -\n%s %s %s %s",
                Apex[i][0], Apex[i][1], w, Apex[i][2], l);
    }
}
