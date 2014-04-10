public class ListLoc {
    
	//
    // Public
    //
    public ListLoc(Locale z) {
        thisLocale = z;
    }
    public ListLoc getNorth() {
        return north;
    }
    public void setNorth(ListLoc north) {
        this.north = north;
    }
    public ListLoc getSouth() {
        return south;
    }
    public void setSouth(ListLoc south) {
        this.south = south;
    }
    public ListLoc getEast() {
        return east;
    }
    public void setEast(ListLoc east) {
        this.east = east;
    }
    public ListLoc getWest() {
        return west;
    }
    public void setWest(ListLoc west) {
        this.west = west;
    }
    public Locale getThisLocale() {
        return thisLocale;
    }
    public void setThisLocale(Locale d) {
        this.thisLocale = d;
    }
    private Locale thisLocale;
    private ListLoc north=null;
    private ListLoc south=null;
    private ListLoc east=null;
    private ListLoc west=null;
}