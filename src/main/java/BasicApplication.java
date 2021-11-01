
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.io.FileReader;
import java.util.Base64;

/**
 * @author Kingsley Ezenwere
 * @since 10/02/2019
 */
@SpringBootApplication
public class BasicApplication implements CommandLineRunner
{
    public static void main(String[] args)
    {
        SpringApplication.run(BasicApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("start");
//        final byte[] file = FileUtils.readFileToByteArray(new File("C:\\Users\\Home\\Downloads\\Beavers Material List 09062021.xlsx"));
//        final String encodeToString = Base64.getEncoder().encodeToString(file);
//        FileDTO fileDTO =  new FileDTO();
//        fileDTO.setEncodedData(encodeToString);
//        fileDTO.setOriginalFileName("xlsx");
//        configureService.create(fileDTO);
    }
}