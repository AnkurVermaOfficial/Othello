package othello;

public class Player {
    private String name;
    public Player(String name){
        setName(name);
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
