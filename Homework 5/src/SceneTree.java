/*Anthony Pizzulli
111990335
R08
 */
public class SceneTree {
    
    private SceneNode root;
    private SceneNode cursor;

    /**
     * Brief: Default constructor for SceneTree Class
     */
    public SceneTree(){

    }

    /**
     * Brief: Getter method for the root of the SceneTree
     * @return SceneNode: Root of given SceneTree
     */
    public SceneNode getRoot() {
        return root;
    }

    /**
     * Brief: Setter method for root of SceneTree
     * @param n SceneNode: Updated root of given SceneTree
     */
    public void setRoot( SceneNode n){
        this.root = n;
    }

    /**
     * Brief: Getter method for cursor of SceneTree
     * @return SceneTree: Cursor of given SceneTree
     */
    public SceneNode getCursor() {
        return cursor;
    }

    /**
     * Brief: Setter method for cursor of SceneTree
     * @param n: Updated cursor of given SceneTree
     */
    public void setCursor( SceneNode n){
        this.cursor = n;
    }


    /**
     * Brief: Moves the cursor of the given SceneTree to the parent node
     * @throws NoSuchNodeException: Thrown if there is no parent node to move to
     */
    public void moveCursorBackwards() throws NoSuchNodeException {
        if (this.cursor == this.root) {
            throw new NoSuchNodeException();
        }
        this.cursor = findParent(this.root);
    }

    /**
     * Brief: recursive method used to search for the parent of the given node
     * @param n SceneNode: The place in the tree to begin searching
     * @return SceneNode: The parent of the cursor
     */
    public SceneNode findParent( SceneNode n){
        SceneNode temp = new SceneNode();
        SceneNode found = new SceneNode();
        if( n.getLeft() != null ) {
            if (n.getLeft() == this.cursor) {
                found = n;
                return found;
            }
            else
                findParent(n.getLeft() );
        }

        if( n.getMiddle() != null ){
            if( n.getMiddle() == this.cursor ){
                found = n;
                return found;
            }
            else
                findParent(n.getMiddle());
        }
        if( n.getRight() != null ){
            if( n.getRight() == this.cursor ){
                found = n;
                return found;
            }
            else
                findParent(n.getRight());
        }
        return found;
    }

    /**
     * Brief: Moves the cursor of the given SceneTree to the child node determined by user input
     * @param option String: Moves cursor to left child if "a," middle child if "b" and right child if "c"
     * @throws NoSuchNodeException: Thrown if the given child does not exist
     */
    public void moveCursorForward(String option) throws NoSuchNodeException{
        switch (option.toLowerCase()){
            case "a":
                if( this.cursor.getLeft() != null ){
                    this.cursor = this.cursor.getLeft();
                }
                else
                    throw new NoSuchNodeException();
                break;

            case "b":
                if( this.cursor.getMiddle() != null ){
                    this.cursor = this.cursor.getMiddle();
                }
                else
                    throw new NoSuchNodeException();
                break;
            case "c":
                if( this.cursor.getRight() !=null ){
                    this.cursor = this.cursor.getRight();
                }
                else
                    throw new NoSuchNodeException();
                break;
            default:
                break;
        }
    }

    /**
     * Brief: Removes the child of the cursor based on the input
     * @param option String: Removes left child if "a," middle child if "b" and right child if "c"
     * @throws NoSuchNodeException: Thrown if there is no child at given option
     */
    public void removeScene(String option) throws NoSuchNodeException{
        switch(option.toLowerCase()){
            case "a":
                if( this.getCursor().isFull() ){//there are 3 nodes
                    this.cursor.setLeft(this.cursor.getMiddle()) ;
                    this.cursor.setMiddle(this.cursor.getRight()) ;
                }
                else if( this.cursor.getLeft() != null && this.cursor.getMiddle() !=null && this.cursor.getRight() == null ) {//there are 2 nodes
                    this.cursor.setLeft(this.cursor.getMiddle()) ;
                }
                else if( this.cursor.getLeft() != null ){
                    this.cursor.setLeft(null) ;
                }
                else
                    throw new NoSuchNodeException();
                break;
            case "b":
                if( this.cursor.isFull() ){
                    this.cursor.setMiddle( this.cursor.getRight()) ;
                }
                else if( this.cursor.getLeft()!=null && this.cursor.getMiddle() != null && this.cursor.getRight() == null ){
                    this.cursor.setMiddle(null) ;
                }
                else
                    throw new NoSuchNodeException();
                break;
            case "c":
                if(this.cursor.isFull()){
                    this.cursor.setRight(null) ;
                }
                else
                    throw new NoSuchNodeException();
                break;
            default:
                break;
        }
    }

    /**
     * Brief: Used to add SceneNode with specified title and sceneDescription to SceneTree
     * @param title String: title instance variable of SceneNode to be added
     * @param sceneDescription String: sceneDescription instance variable of SceneNode to be added
     * @throws FullSceneException
     */
    public void addNewNode(String title, String sceneDescription) throws FullSceneException{

        SceneNode s = new SceneNode(title, sceneDescription);
        if( cursor.getRight() != null){
            throw new FullSceneException();
        }
        else
            s.addSceneNode(s);
    }


    /**
     * Brief: Used to move current SceneNode to SceneNode with given sceneID
     * @param sceneIDToMoveTo int: The sceneID of the SceneNode that the cursor is to be moved to
     * @throws NoSuchNodeException: Thrown if there is no SceneNode in the given SceneTree with the given sceneID
     * @throws FullSceneException: Thrown if the SceneNode with the given sceneID is full
     */
    public void moveScene(int sceneIDToMoveTo) throws NoSuchNodeException, FullSceneException{
        SceneNode goal = findSceneID(this.root, sceneIDToMoveTo);
        if( sceneIDToMoveTo == 1 )
            throw new NoSuchNodeException();
        if( goal != null){
            if( !(goal.isFull()) ){
                if( goal.getLeft() == null ){
                    goal.setLeft(this.cursor);
                }
                else if( goal.getMiddle() == null){
                    goal.setMiddle(this.cursor);
                }
                else if( goal.getRight() == null ){
                    goal.setRight(this.cursor);
                }
            }
            else
                throw new FullSceneException();
        }
        else
            throw new NoSuchNodeException();
    }

    /**
     * Brief: Used to traverse given SceneTree up to the cursor and construct a String of information based on the SceneNodes
     * @return String: Gives each SceneNode's title
     */
    public String getPathFromRoot(){
        String returnString = "";
        SceneNode tempCursor = this.root;
        if( !(this.root.isEnding()) ){
            while( tempCursor.getLeft() != this.cursor && tempCursor.getMiddle() != this.cursor && tempCursor.getRight() != this.cursor ){
                returnString += (tempCursor.getTitle() + ", ");
            }
        }
        returnString += (this.cursor.getTitle());
       return returnString;
    }

    /**
     * Brief: Used to compile a String representation of the given SceneTree
     * @return String: String representation of the given SceneTree
     */
    public String toString(){
        /*

         */
        return "";
    }

    /**
     *Brief: Recursive method used to locate SceneNode with given sceneID (if it exists)
     * @param node SceneNode: Used to call the method recursively
     * @param n int: The sceneID to be searched for
     * @return SceneNode: The SceneNode with the given sceneID (if found; otherwise the value will be null)
     */
    public SceneNode findSceneID( SceneNode node, int n ){
        SceneNode found = new SceneNode();
        if( node.getLeft() != null ) {
            if (node.getLeft().getSceneID() == n) {
                found = node.getLeft();
            }
            else
                findSceneID(node.getLeft(), n);
        }
        if( node.getMiddle() != null ){
            if( node.getMiddle().getSceneID() == n){
                found = node.getMiddle();
            }
            else
                findSceneID(node.getMiddle(), n);
        }
        if( node.getRight() != null ){
            if( node.getRight().getSceneID() == n){
                found = node.getRight();
            }
            else
                findSceneID(node.getRight(), n);
        }
        return found;
    }
}
