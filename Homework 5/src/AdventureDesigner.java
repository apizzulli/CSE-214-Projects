/*Anthony Pizzulli
111990335
R08
 */
import java.util.Scanner;
public class AdventureDesigner {

    public static AdventureDesigner aD = new AdventureDesigner();
    public static SceneTree tree = new SceneTree();

    /**
     * Brief: Driver for SceneTree, including user input and console printing
     * @param args
     */
    public static void main(String[] args) {

        String input = "";
        Scanner userIn = new Scanner(System.in);
        System.out.println("Creating a story...");

        System.out.println("Please enter a title: ");
        String title = userIn.nextLine();
        System.out.println("Please enter a scene: ");
        String scene = userIn.nextLine();
        System.out.println("\nScene #1 added.");
        tree.setRoot(new SceneNode(title, scene, 1));
        tree.setCursor(tree.getRoot());
        int numScene = 2;

        while( !(input.toLowerCase().equals("q")) ){
            System.out.println("\nA) Add Scene\n" +
                    "R) Remove Scene\n" +
                    "S) Show Current Scene\n" +
                    "P) Print Adventure Tree\n" +
                    "B) Go Back A Scene\n" +
                    "F) Go Forward A Scene\n" +
                    "G) Play Game\n" +
                    "N) Print Path To Cursor\n" +
                    "M) Move scene\n" +
                    "Q) Quit");


            System.out.println("Please enter a selection: ");
            input = userIn.nextLine().toLowerCase();
            switch( input ){
                case "a":
                    System.out.println("Please enter a title: ");
                    title = userIn.nextLine();
                    System.out.println("Please enter a scene: ");
                    scene = userIn.nextLine();
                    SceneNode s = new SceneNode( title, scene, numScene);
                    try{
                        tree.getCursor().addSceneNode(s);
                        tree.getCursor().setNumScenes(tree.getCursor().getNumScenes()+1);
                        System.out.println("Scene #" + numScene + " added." );
                        numScene++;
                        tree.getCursor().setNumScenes(numScene);

                    }catch( FullSceneException f){
                        System.out.println("You cannot add another scene!");
                    }
                    break;
                case "r":
                    System.out.println("Please enter an option: ");
                    String o = userIn.nextLine();
                    try{
                        tree.removeScene(o);
                    }catch(NoSuchNodeException no){
                        System.out.println("That option does not exist.");
                    }
                    break;
                case "s":
                    tree.getCursor().displayFullScene();
                    break;
                case "p":
                    System.out.println(tree.toString());
                    break;
                case "b":
                    try{
                        tree.moveCursorBackwards();
                        System.out.println("Successfully moved back to " + tree.getCursor().getTitle() + ".");
                    }catch( NoSuchNodeException ne ){
                        System.out.println("That option does not exist.");
                    }
                    break;

                case "f":

                    System.out.println("Which option do you wish to go to: ");
                    String option = userIn.nextLine();
                    try{
                        tree.moveCursorForward(option);
                        System.out.println("Successfully moved to " + tree.getCursor().getTitle() + ".");
                    }catch( NoSuchNodeException n ){
                        System.out.println("That option does not exist.");
                    }
                    break;

                case "g":
                    SceneNode tempCursor = tree.getCursor();
                    aD.playGame();
                    tree.setCursor(tempCursor);
                    break;

                case "n":
                    String t = tree.getPathFromRoot();
                    System.out.println(t);
                    break;
                case "m":
                    System.out.println("Move currrent scene to: ");
                    int n = Integer.parseInt(userIn.nextLine());
                    try{
                        tree.moveScene(n);
                        System.out.println("Successfully moved scene.");
                    }catch( NoSuchNodeException ne ){
                        System.out.println("That option does not exist.");
                    }
                    catch( FullSceneException f){
                        System.out.println("You cannot add another scene!");
                    }
                    break;

                case "q":
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Brief: Allows the user to play the game that has been created by traversing the tree
     */
    public static void playGame(){
        Scanner in = new Scanner(System.in);
        System.out.println("Now beginning game...\n");
        tree.setCursor(tree.getRoot());
        while( tree.getCursor() != null ){
            System.out.println(tree.getCursor().getTitle() + "\n" + tree.getCursor().getSceneDescription() + "\n" );
            if( tree.getCursor().getLeft() != null ){
                System.out.println("A) " + tree.getCursor().getLeft().getTitle() );
            }
            if( tree.getCursor().getMiddle() != null ){
                System.out.println("B) " + tree.getCursor().getMiddle().getTitle() );
            }
            if( tree.getCursor().getRight() != null ){
                System.out.println("C) " + tree.getCursor().getRight().getTitle() );
            }
            if( tree.getCursor().isEnding() )
                break;
            System.out.println("\nPlease enter an option: ");
            String input = in.nextLine();
            try{
                tree.moveCursorForward(input);
            }catch( NoSuchNodeException n){

            }
        }

        System.out.println("\nThe End\n");
        System.out.println("Returning back to creation mode...");
        //while ( cursor.get)
    }
}
