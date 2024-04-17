import Classes.Context;
import Classes.Flat;
import CommandExecution.Interactor;
import Enums.Furnish;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        //TIP О чём тут можно говорить?
        Context.getInitDate(); // Инициализирует Контекст и его содержимое
        Interactor interactor = new Interactor();
        String input = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Путь, для сохранения коллекции: " + Context.getPath());
        while (!Objects.equals(input, "exit")) {
            input = scanner.nextLine();
            if (!Objects.equals(input, "")) interactor.masterProcessInput(input);
        }
    }
}