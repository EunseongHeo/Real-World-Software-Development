package chapter_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.activation.ActivationGroup_Stub;
import java.util.List;

public class BankTransactionAnalyzerSimple {
    private static final String RESOURCES = "src/main/resources/";

    public static void main(final String... args) throws IOException {
        /*
            발생 가능한 문제 상황 예시
            1. 파일이 비어있다면?
            2. 데이터에 문제가 있어서 `amount` 변수를 파싱하지 못 한다면?
            3. 행의 데이터가 완벽하지 않다면?
         */

//        final Path path = Paths.get(RESOURCES + args[0]);
        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);

        if(lines.isEmpty()){    //1. What if the file is empty?
            System.out.println("The file is empty.");
        } else {
            double total = 0d;
            for (final String line : lines) {
                final String[] columns = line.split(",");

                if(columns.length >=2) {    //3. What if the data in the row is not complete?
                    try {   //2. What if the `amount` variable cannot be parsed because of an data issue?
                        final double amount = Double.parseDouble(columns[1]);
                        total += amount;
                    } catch (NumberFormatException e) {
                        System.err.println("NumberFormatException: parsing the `amount` variable ");
                        // Customize the error-handling logic based on your specific requirements.
                    }
                } else {
                    System.err.println("ArrayIndexOutOfBoundsException: Incomplete date for line");
                    // Handle the incomplete date further if needed.
                }
            }
            System.out.println("The total for all transactions is " + total);
        }
    }
}
