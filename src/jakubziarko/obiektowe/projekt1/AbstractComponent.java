package jakubziarko.obiektowe.projekt1;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractComponent <T>{
    private String number;
    private String text;

    private List<T> list=new ArrayList<>();

    public String getNumber() { return number; }
    public String getText() {
        return text;
    }
    public List<T> getList() {
        return list;
    }
    public void setText(String text){
        this.text=text;
    }
    public void setNumber(String number) { this.number=number;}
    public AbstractComponent(){
        text = "";
        number= "";
    }
    public void add(T component){
        list.add(component);
    }
}