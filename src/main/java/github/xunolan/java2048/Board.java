package github.xunolan.java2048;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

//盘面
public class Board {
    int size;
    public List<List<Element>> elementMap = new ArrayList<>();
    public Random random = new Random(2333);
    public Board(int size){
        assert size > 1;//size至少为2x2
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
     private List<Element> getColumn(int index){
        List<Element> temp = new ArrayList<>();
        for(int i=0;i<size;i++){
            temp.add(elementMap.get(i).get(index));
        }
        return temp;
     }
     private List<Element> move(List<Element> elements){//全部前移，但不进行合并。
        int originSize = elements.size();
        //删除所有为0的元素，并在最后append新元素？
         List<Element> newElementList = elements.stream().filter(element -> element.number>0 ).collect(Collectors.toList());
         for(int i= newElementList.size();i<originSize;i++){
             newElementList.add(new Element(0));
         }
         return newElementList;
     }
     private void putBackColumn(List<Element> elementList, int index){
         for(int i=0;i<size;i++){
            elementMap.get(i).set(index,elementList.get(i));
         }
     }

    public void up(){
        //从第二行开始，往前看有没有和自己一样的元素。
        //若前面为空，挪动到此列之前；
        //若前面有值且与自己相同，且之前未合并过（引入flag值）。合并。逻辑为将当前处理的元素值乘二，更新flag值；将被合并的元素删除；
        for(int columnIndex = 0; columnIndex < size; columnIndex++){
            List<Element> arrayList = getColumn(columnIndex);
            arrayList = move(arrayList);
            for(int i=1;i<size;i++){
                Element epre = arrayList.get(i-1);
                Element enow = arrayList.get(i);
                if(enow.number!=0 && !epre.isMerged && epre.number == enow.number) {
                    //需要合并
                    enow.number = enow.number*2;
                    arrayList.remove(i-1);
                    arrayList.add(new Element(0));
                }
            }
            arrayList = move(arrayList);
            putBackColumn(arrayList,columnIndex);
        }
        randomInit(2, 2);
    }
}
