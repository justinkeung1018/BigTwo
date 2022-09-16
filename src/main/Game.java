public class Game {
    UI ui;
    public Game(UI ui) {
        this.ui = ui;
    }

    public static void main(String[] args) {
        System.out.println("Game started");
        BigTwo bigTwo = new BigTwo();
        System.out.println("Created BigTwo instance");
        UI ui = new CommandLineUI(bigTwo);
        System.out.println("Created new UI instance");
        Game game = new Game(ui);
        System.out.println("Created new Game instance");
        game.start();
    }

    private void start() {
        ui.start();
    }
}
