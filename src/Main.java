import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        RedBlackTree tree = new RedBlackTree();

        //tree.loadVocabs();
        //System.out.println(tree.findHeight());

        while (true) {
            loadMenu();

            Scanner scanner = new Scanner(System.in);
            String input;

            System.out.print("\nChoise: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("\nEnter Word: ");
                    input = scanner.nextLine();
                    tree.add(input);
                    break;
                case 2:
                    System.out.print("\nEnter Word: ");
                    input = scanner.nextLine();

                    System.out.println(tree.search(input));
                    break;
                case 3:
                    System.out.print("\nEnter Word: ");
                    input = scanner.nextLine();

                    tree.delete(input);
                    break;
                case 4:
                    System.out.print("\nHeight: " + tree.findHeight());
                    break;
                case 5:
                    System.out.print("\nSize: " + tree.size);
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
        }
    }

    private static void loadMenu()
    {
        System.out.println("---------");
        System.out.println("Main Menu");
        System.out.println("---------");

        System.out.println("1) Insert Word");
        System.out.println("2) Find Word");
        System.out.println("3) Delete Word");
        System.out.println("4) Print Height");
        System.out.println("5) Print Size");
        System.out.println("6) Exit");
    }
}
