package github.xunolan.java2048;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//盘面
public class Board {
    int size;
    public List<List<Element>> elementMap = new ArrayList<>();
    public Random random = new Random(1234);
    public Board(int size){
        this.size = size;
    }
    public Board boardInit(){
        elementMap.clear();
        for(int i=0; i<size; i++) {
            List<Element> list = new ArrayList<>();
            for(int j=0; j<size; j++) {
                list.add(new Element(0));
            }
            elementMap.add(list);
        }
        return this;
    }

    public Board randomInit(int number, int initValue){
        List<Element> flatMap = new ArrayList<>();
        for(int i = 0; i<size; i++) {
            for(int j=0; j<size; j++) {
                Element e = elementMap.get(i).get(j);
                if(e.number==0){ //只在空元素中随机挑选一个；
                    flatMap.add(e);
                }
            }
        }
        for(int i=0;i<number;i++){
            int index = random.nextInt(flatMap.size());
            flatMap.get(index).setNumber(initValue);
            flatMap.remove(index);
        }
        return this;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i<size; i++) {
            for(int j=0; j<size; j++) {
                Element e = elementMap.get(i).get(j);
                stringBuilder.append(e.number);
                stringBuilder.append("\t");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }


}
