public class Stacks {


    //
    // Public
    //
    public Stacks() {
        init();
    }

    public void push(ListLoc item) throws Exception{
        // Check for stack overflow.
        if (topPtr > 0) {
            topPtr = topPtr - 1;
            arr[topPtr] = item;
        } else {
            // TODO: Throw an overflow exception.
        	throw new Exception();
        }
    }

    public ListLoc pop() throws Exception{
        ListLoc retVal = null;
        // Check for stack underflow.
        if (topPtr < CAPACITY) {
            retVal = arr[topPtr];
            topPtr = topPtr + 1;
        } else {
            // In case of underflow, return -1.
            // TODO: Throw an underflow exception.
        	throw new Exception();
            
        }
        return retVal;
    }

    public boolean isEmpty() {
        boolean retVal = false;
        if (topPtr == CAPACITY) {
            retVal = true;
        }
        return retVal;
    }

   /* public boolean isEmptyMo() {
        return (topPtr == CAPACITY);
    }*/


    //
    // Private
    //
    private final int CAPACITY = 45;
    private ListLoc[] arr = new ListLoc[CAPACITY];
    private int topPtr = 0;

    private void init() {
       for (int i = 0; i < CAPACITY; i++) {
           arr[i] = null;
       }
       topPtr = CAPACITY;
    }


}
