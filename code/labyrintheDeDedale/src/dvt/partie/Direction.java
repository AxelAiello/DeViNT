package dvt.partie;

public enum Direction {
    UP, DOWN, RIGHT, LEFT;

    public String directionToString(){
        String res;
        switch(this){
            case UP: res = "UP"; break;
            case LEFT: res = "LEFT"; break;
            case DOWN: res = "DOWN"; break;
            case RIGHT: res = "RIGHT"; break;
            default: res = "";
        }
        return res;
    }
}
