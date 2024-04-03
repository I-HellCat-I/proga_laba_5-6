import Classes.Context;
import Classes.Flat;
import CommandExecution.Interactor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) throws JsonProcessingException {
        Context.getInitDate();
        Interactor interactor = new Interactor();
        String input = "";
        Scanner scanner = new Scanner(System.in);
        while (!Objects.equals(input, "exit")){
            input = scanner.next();
            interactor.processInput(input);
        }
    }
}