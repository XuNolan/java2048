package github.xunolan.java2048;

//元素
public class Element {
    int number;
    boolean isMerged;//记录本次活动是否已进行过合并；
    public Element(int number){
        this.number = number;
    }
    public void setNumber(int number){
        this.number = number;
    }
}
