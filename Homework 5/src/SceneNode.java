/*Anthony Pizzulli
111990335
R08
 */
public class SceneNode {

    private String title;
    private String sceneDescription;
    private int sceneID;
    private SceneNode left;
    private SceneNode middle;
    private SceneNode right;
    private static int numScenes;


    /**
     * Brief: Default constructor for SceneNode Class
     */
    public SceneNode(){
    }


    /**
     * Brief: Alternate constructor with two parameters for SceneNode Class
     * @param title String: Title of the SceneNode
     * @param desc String: Description of the SceneNode
     */
    public SceneNode( String title, String desc ){
        this.title = title;
        this.sceneDescription = desc;
    }

    /**
     * Brief: Alternate constructor with three parameters for SceneNode class
     * @param title String: Title of the SceneNode
     * @param desc String: Description of the SceneNode
     * @param n int: SceneID of the SceneNode
     */
    public SceneNode( String title, String desc, int n ){
        this.title = title;
        this.sceneDescription = desc;
        this.sceneID = n;
    }

    /**
     * Brief: Getter method for title instance variable
     * @return String: The given SceneNode's title
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * Brief: Setter method for title instance variable
     * @param s String: The updated value for the given SceneNode's title
     */
    public void setTitle(String s){
        this.title = s;
    }

    /**
     * Brief: Getter method for sceneDescription instance variable
     * @return String: The given SceneNode's sceneDescription
     */
    public String getSceneDescription(){
        return this.sceneDescription;
    }

    /**
     * Brief: Setter method for sceneDescription instance variable
     * @param s String: Updated value for the given SceneNode's sceneDescription
     */
    public void setSceneDescription( String s){
        this.sceneDescription = s;
    }

    /**
     * Brief: Getter method for sceneID instance variable
     * @return int: The given SceneNode's sceneID
     */
    public int getSceneID(){
        return this.sceneID;
    }

    /**
     * Brief: Setter method for sceneID instance variable
     * @param n int: Updated value for the given SceneNode's sceneID
     */
    public void setSceneID(int n){
        this.sceneID = n;
    }

    /**
     * Brief: Getter method for left child node
     * @return SceneNode: The left child of the given SceneNode
     */
    public SceneNode getLeft(){
        return this.left;
    }

    /**
     * Brief: Setter method for left child node
     * @param n SceneNode: The updated left child node of the given SceneNode
     */
    public void setLeft( SceneNode n){
        this.left = n;
    }

    /**
     * Brief: Getter method for middle child node
     * @return SceneNode: The middle child of the given SceneNode
     */
    public SceneNode getMiddle(){
        return this.middle;
    }

    /**
     * Brief: Setter method for middle child node
     * @param n SceneNode: The updated middle child node of the given SceneNode
     */
    public void setMiddle( SceneNode n){
        this.middle = n;
    }

    /**
     * Brief: Getter method for right child node
     * @return SceneNode: The right child of the given SceneNode
     */
    public SceneNode getRight(){
        return this.right;
    }

    /**
     * Brief: Setter method for right child node
     * @param n SceneNode: The updated right child node of the given SceneNode
     */
    public void setRight( SceneNode n){
        this.right = n;
    }

    /**
     * Brief: Getter method for static variable numScenes
     * @return int: numScenes static variable
     */
    public int getNumScenes(){
        return numScenes;
    }

    /**
     * Brief: Setter method for static variable numScenes
     * @param s int: Updated value for numScenes
     */
    public void setNumScenes( int s ){
        numScenes = s;
    }


    /**
     * Brief: Sets the current scene to the left-most empty child node
     * @param scene: SceneNode to be added
     * @throws FullSceneException: Thrown if there is no empty child node to add to
     */
    public void addSceneNode(SceneNode scene) throws FullSceneException{

        if( this.right != null ){
            throw new FullSceneException();
        }

        if( this.getLeft() == null ){
            this.setLeft(scene);
        }
        else if( this.middle == null ){
            this.setMiddle(scene);
        }
        else
            this.setRight(scene);


    }


    /**
     * Brief: This method is used to determine whether or not the given SceneNode is a leaf
     * @return: True if the given SceneNode has no children and false otherwise
     */
    public boolean isEnding(){ return( this.getLeft()==null && this.middle==null && this.right==null); }

    public void displayScene(){

    }

    /**
     * Brief: This method gives a visualization of the given SceneNode's information
     */
    public void displayFullScene(){
        String returnS = "";
        returnS += "Scene ID #" + this.sceneID + "\n";
        returnS += "Title: " + this.title + "\n";
        returnS += "Scene: " + this.sceneDescription + "\n";
        String loop = "";

        if( this.getLeft() != null ){
            loop += " '" + this.getLeft().sceneDescription + "' (#" + this.getLeft().sceneID + "),";
        }
        if( this.middle != null ){
            loop += " '" + this.middle.sceneDescription + "' (#" + this.middle.sceneID + "),";
        }
        if( this.right != null ){
            loop += " '" + this.right.sceneDescription + "' (#" + this.right.sceneID + "),";
        }
        else
            loop = " NONE";

        returnS += "Leads to:" + loop;
        System.out.println(returnS);
    }

    /**
     * Brief: This method is used to determine whether or not the given SceneNode is full, i.e. has 3 child nodes
     * @return: True if the given SceneNode has a left, middle and right child and false otherwise
     */
    public boolean isFull(){
        return (this.left != null && this.middle != null && this.right != null);
    }

    /**
     * Brief: This method compiles a brief summary of the given SceneNode into a String
     * @return String: Summary of given SceneNode
     */
    public String toString(){
        return this.getTitle() + " (#" + this.getSceneID() + ")";
    }

}

