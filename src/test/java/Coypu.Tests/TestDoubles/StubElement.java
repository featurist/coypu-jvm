package Coypu.Tests.TestDoubles;

import Coypu.ElementFound;

import java.util.Hashtable;

public class StubElement implements ElementFound
{
    private final Hashtable<String,String> attributes = new Hashtable<String,String>();
    private Object getNativeElement;
    private String id;
    private String text;
    private String value;
    private String name;
    private String selectedOption;
    private boolean selected;

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public boolean getSelected() {
        return selected;
    }

    public Object getNative() {
        return getNativeElement;
    }



    public void setId(String value) {
        id = value;
    }

    public void setText(String value) {
        text = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setName(String value) {
        name = value;
    }

    public void setSelectedOption(String value) {
        selectedOption = value;
    }

    public void setSelected(boolean value) {
        selected = value;
    }

    public void setNativeElement(Object value) {
        getNativeElement = value;
    }



    public boolean stale()
    {
        return false;
    }

    public String getAttribute(String attributeName)
    {
            return attributes.containsKey(attributeName) ? attributes.get(attributeName) : "";
    }

    public void stubAttribute(String attributeName, String value)
    { 
        attributes.put(attributeName,value); 
    }
}
