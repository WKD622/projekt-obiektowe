package jakubziarko.obiektowe.projekt1;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDocument implements Component{
    protected int number;
    protected String text;
    protected List<Component> list=new ArrayList<>();

    public int getNumber() {
        return number;
    }
    public String getText() {
        return text;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setText(String text) {
        this.text = text;
    }
    public List<Component> getList() {
        return list;
    }
    public void add(Component component){
        list.add(component);
    }
    public void addText(String text){
        this.text=this.text.concat(text);
    }
}