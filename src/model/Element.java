package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Element {

    private String name;
    private List<Attribute> attributes = new ArrayList<>();
    private List<Element> childElements = new ArrayList<>();
    String content = "";

    public Element(){
        this.name = "";
    }

    public static class Builder {

        private Element newElement;

        public Builder() {
            newElement = new Element();
        }

        public Builder addName(String name){
            newElement.name = name;
            return this;
        }

        public Builder addAttributes(List<Attribute> attributes){
            newElement.attributes = attributes;
            return this;
        }

        public Builder addChildElements(List<Element> childElements){
            newElement.childElements = childElements;
            return this;
        }

        public Builder addContent(String content){
            newElement.content = content;
            return this;
        }

        public Element build(){
            return newElement;
        }

    }

    public String getName() {
        return name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public List<Element> getChildElements() {
        return childElements;
    }

    public String getContent() {
        return content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public void setChildElements(List<Element> childElements) {
        this.childElements = childElements;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return Objects.equals(name, element.name) &&
                Objects.equals(attributes, element.attributes) &&
                Objects.equals(childElements, element.childElements) &&
                Objects.equals(content, element.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, attributes, childElements, content);
    }

    @Override
    public String toString() {
        String result;
        result = "Element {" +
                "name = " + name;
        if(!attributes.isEmpty())
            result += ", attributes = " + attributes;
        if(!childElements.isEmpty())
            result += ", childElements = " + childElements;
        if(!content.isEmpty())
            result += ", content = " + content;
        result += "}";
        return result;
    }

}

