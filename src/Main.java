import Classes.Flat;
import Classes.Interactor;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.Reference;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import static java.lang.System.exit;
import static java.lang.System.in;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {

        Interactor interactor = new Interactor();
//        byte[] bytes = new byte[100];
//        StringBuffer stringBuffer;
//        try (BufferedInputStream inStream = new BufferedInputStream(new FileInputStream("C:\\Users\\user\\IdeaProjects\\proga_laba_5-6\\src\\prikol.txt"))) {
//            //int amm = inStream.read(bytes);
//            Scanner scanner = new Scanner(inStream);
//            scanner.nextLine();
//            stringBuffer = new StringBuffer(new String(bytes));
//
//            System.out.println(stringBuffer.toString().split("\n")[2]);
//        }  catch (FileNotFoundException exc) {
//            System.out.println("Ваш файл не найден, введите имя существующего файла");
//            return;
//        } catch (IOException ignored) {
//        }
//        exit(0);
        String input = "";
        Scanner scanner = new Scanner(System.in);
        while (!Objects.equals(input, "exit")){
            input = scanner.next();
            interactor.processInput(input);
        }
    }
}