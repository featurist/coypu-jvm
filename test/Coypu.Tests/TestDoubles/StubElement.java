package Coypu.Tests.TestDoubles;

import Coypu.ElementFound;

import java.util.Hashtable;

public class StubElement implements ElementFound
{
    private final Hashtable<String,String> attributes = new Hashtable<String,String>();
    private Object nativeElement;
    private String id;
    private String text;
    private String value;
    private String name;
    private String selectedOption;
    private boolean selected;

    public String Id() {
        return id;
    }

    public String Text() {
        return text;
    }

    public String Value() {
        return value;
    }

    public String Name() {
        return name;
    }

    public String SelectedOption() {
        return selectedOption;
    }

    public boolean Selected() {
        return selected;
    }

    public Object Native() {
        return nativeElement;
    }



    public void setId(String value) {
        id = value;
    }

    public void setText(String value) {
        text = value;
    }

    public void value(String value) {
        value = value;
    }

    public void name(String value) {
        name = value;
    }

    public void selectedOption(String value) {
        selectedOption = value;
    }

    public void selected(boolean value) {
        selected = value;
    }

    public void nativeElement(Object value) {
        nativeElement = value;
    }



    public boolean Stale()
    {
        return false;
    }

    public String Attribute(String attributeName)
    {
            return attributes.containsKey(attributeName) ? attributes.get(attributeName) : "";
    }

    public void StubAttribute(String attributeName, String value)
    { 
        attributes.put(attributeName,value); 
    }
}
