public class SearchTree implements NodeList {

    private ListItem root = null;

    public SearchTree(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem newItem) {
        if(this.root == null) {
            //the tree was empty, so our item becomes the head of the tree
            this.root = newItem;
            return  true;
        }
        //otherwise, start comparing from the head of the tree
        ListItem currentItem = this.root;
        while(true) {
            int comparison = currentItem.compareTo(newItem);
            if(comparison < 0) {
                //newItem is greater, move right if possible
                if(currentItem.next() != null) {
                    currentItem = currentItem.next();
                } else {
                    //there's no node to the right, so add at this point
                    currentItem.setNext(newItem);
                    return true;
                }
            } else if(comparison > 0) {
                //newItem is less, move left if possible
                if(currentItem.previous() != null) {
                    currentItem = currentItem.previous();
                } else {
                    //there's no node to the left, so add at this point
                    currentItem.setPrevious(newItem);
                    return true;
                }
            } else {
                //equal, so don't add
                System.out.println(newItem.getValue() + " is already present");
                return false;
            }
        }
    }

    @Override
    public boolean removeItem(ListItem item) {
        if(item != null) {
            System.out.println("Deleting item " + item.getValue());
        }
        ListItem currentItem = this.root;
        ListItem parentItem = currentItem;

        while(currentItem != null) {
            int comparison = currentItem.compareTo(item);
            if(comparison < 0) {
                parentItem = currentItem;
                currentItem = currentItem.next();
            } else if(comparison > 0) {
                parentItem = currentItem;
                currentItem = currentItem.previous();
            } else {
                //equal: found the item, so remove it
                performRemoval(currentItem, parentItem);
                return true;
            }
        }
        return false;

    }

    @Override
    public void traverse(ListItem root) {
        //recursive method
        if(root != null) {
            traverse(root.previous());
            System.out.println(root.getValue());
            traverse(root.next());
        }

    }
}
