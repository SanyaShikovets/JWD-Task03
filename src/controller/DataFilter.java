package controller;

import model.Attribute;
import model.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DataFilter {

    private final String SPACE = " ";
    private final String EQUALLY = "=";
    private final Integer START_OF_STRING = 0;

    public List<Attribute> findAttributes(String elementWithAttributes){
        List<Attribute> attributes = new ArrayList<>();
        String [] arrayAttributes = elementWithAttributes.split(SPACE);
        for(String attribute : arrayAttributes){
            String nameAttribute = attribute.substring(START_OF_STRING, attribute.indexOf(EQUALLY));
            String valueAttribute = attribute.substring(attribute.indexOf(EQUALLY) + 2, attribute.length() - 1);
            attributes.add(new Attribute(nameAttribute, valueAttribute));
        }
        return attributes;
    }

    public Element openingAndClosingAnElement(String elementWithContent){
        final String START_OF_TAG = "<";
        final String END_OF_TAG = ">";
        int endOfNameElement = elementWithContent.indexOf(END_OF_TAG);
        String nameElementWithAttribute = elementWithContent.substring(START_OF_STRING + 1, endOfNameElement);
        String contentOfElement = elementWithContent.substring(endOfNameElement + 1);
        String nameElement;
        List<Attribute> attributes;
        Element element = new Element();
        if(nameElementWithAttribute.contains(EQUALLY)){
            nameElement = nameElementWithAttribute.substring(START_OF_STRING, nameElementWithAttribute.indexOf(EQUALLY));
            attributes = findAttributes(nameElementWithAttribute.substring(nameElementWithAttribute.indexOf(SPACE) + 1));
            element.setAttributes(attributes);
        } else {
            nameElement = nameElementWithAttribute;
        }
        element.setName(nameElement);
        int endContent = contentOfElement.indexOf(START_OF_TAG);
        String content = contentOfElement.substring(START_OF_STRING, endContent);
        element.setContent(content);
        return element;
    }

    public Element filter(List<String> data){// сложноватый по управлению метод

        Stack<Element> stack = new Stack<>();
        Element rootElement = new Element();

        stack.push(rootElement);

        for(String string : data){
            if(rootElement.getName().isEmpty()){
                rootElement.setName(string.substring(START_OF_STRING + 1, string.length() - 1));
                continue;
            }
            if(string.matches("<(.*)>(.*)</(.*)>")) {
                Element newElement = openingAndClosingAnElement(string);
                Element prevElement = stack.pop();
                List<Element> childElementsChange = prevElement.getChildElements();
                childElementsChange.add(newElement);
                prevElement.setChildElements(childElementsChange);
                stack.push(prevElement);
            }
            else {
                if (string.matches("</(.*)>")) {
                    Element lastElement = stack.pop();
                    if(!string.substring(START_OF_STRING + 2, string.length() - 1).equals(rootElement.getName())) {
                        Element prevElement = stack.pop();
                        List<Element> childElementsChange = prevElement.getChildElements();
                        childElementsChange.add(lastElement);
                        prevElement.setChildElements(childElementsChange);
                        stack.push(prevElement);
                    }
                }
                else {
                    if (string.matches("<(.*)>")) {
                        String buffer = string.substring(START_OF_STRING + 1, string.length() - 1);
                        if (buffer.contains(EQUALLY)) {
                            String name = buffer.substring(START_OF_STRING, buffer.indexOf(SPACE));
                            String bufferAttributes = buffer.substring(buffer.indexOf(SPACE) + 1);
                            List<Attribute> attributes = findAttributes(bufferAttributes);
                            stack.push(new Element.Builder().addName(name).addAttributes(attributes).build());
                        } else {
                            stack.push(new Element.Builder().addName(buffer).build());
                        }
                    }
                }
            }
        }
        return rootElement;
    }
}
