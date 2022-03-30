package com.sg.springscheduler.service;
import com.sg.springscheduler.entity.Customer;
import com.sg.springscheduler.enumerator.Language;
import com.sg.springscheduler.enumerator.Status;
import com.sg.springscheduler.repo.CustomerRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.*;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

@Component
public class SchedulerService {

    @Autowired
    Connection connection;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CustomerRepository customerRepository;

//    @PersistenceContext
//    EntityManager entityManager;

//    @SneakyThrows
    @Scheduled(cron = "*/10 * * * * *")
    public void importFile() {
        File file = new File("src/main/resources/customer.csv");

        FileSystemResource fileSystemResource = new FileSystemResource(file);
        InputStream inputStream = null;

        try {
            inputStream = fileSystemResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        fileSystemResource.getInputStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String[] row;
        int flag = 0;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (flag == 0) {
                    flag++;
                    continue;
                }

                row = line.split(",");

                dataEntry(Arrays.stream(row).toList());

//                List<String> data = new ArrayList<>();
//                data.add(row[0].trim());
//                data.add(row[1].trim());
//                data.add(row[2].trim());
//                data.add(row[3].trim());
//                data.add(row[4].trim());
//                data.add(row[5].trim());
//                data.add(row[6].trim());
//                dataEntry(data);
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dataEntry(List<String> data) {

        int fetchSize = jdbcTemplate.getFetchSize();
        System.out.println("fetch size=" + fetchSize);
        Customer customer = new Customer();
        customer.setUserName(data.get(0));
        customer.setFirstName(data.get(1));
        customer.setLastName(data.get(2));
        customer.setPhoneNumber(data.get(3));
        customer.setEmail(data.get(4));
        customer.setStatus(Status.valueOf(data.get(5)));
        customer.setPreferredLanguage(Language.valueOf(data.get(6)));

//        String insertQuery = "insert into customer(id,user_name, first_name, last_name ,phone_number, email, status, preferred_language ) VALUES (?,?, ?, ?, ?, ?, ?,?)";
//        jdbcTemplate.batchUpdate(insertQuery, new BatchInsertWrapper(customer));
        customerRepository.save(customer);
    }
}
