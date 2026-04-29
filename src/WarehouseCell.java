public class WarehouseCell {

    public static int id = 0;
    private Object CellType;

    private enum CellType;
    private char symbol;
    private int x;
    private int y;
    
    WarehouseCell(){
        id += 1;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        WarehouseCell.id = id;
    }

    public void setCellType(enum Constants.CellType Constants.CellType wall) {
        this.CellType = Constants.CellType;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
