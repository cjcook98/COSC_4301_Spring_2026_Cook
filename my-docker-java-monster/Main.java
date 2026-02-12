public class Main {
    public static void main(String[] args) {
        // Create a monster object
        Monster myMonster = new Monster("Emberclaw", "Fire");

        // Print output
        System.out.println("Your monster has been created.");
        System.out.println("Description: " + myMonster.getDescription());
    }
}