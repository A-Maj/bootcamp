package pl.pwn.rest.client;

import com.bootcamp.bootcamp.model.CourseEdition;
import com.bootcamp.bootcamp.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

public class CourseRestClient {

    public static void main(String[] args) {
        getCourses();
        getCourseById(2);

        User user = new User();
        user.setName("Marek");
        user.setSurname("Kroń");
        user.setMail("k@mpj.l");
        user.setPhone("111-111-111");
        user.setPassword("hhgjhghj");
        postCreateUser(user, 2);
    }

    private static void  postCreateUser(User user, int id) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<User> requestEntity = new HttpEntity<>(user, new HttpHeaders());
        ResponseEntity<CourseEdition> response = restTemplate.exchange("http://localhost:8081/client/kursy/zapisz/" + id,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<CourseEdition>() {
                });

        CourseEdition body = response.getBody();

        if(Objects.nonNull(body)) {
            if(HttpStatus.OK.equals(response.getStatusCode())) {

                System.out.println("Pojedyńczy kurs: \n" + body);
            } else {
                System.out.println("Error status: " + response.getStatusCode() + " body: " + response.getBody());
            }
        }
    }

    public static void getCourseById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CourseEdition> response = restTemplate.getForEntity("http://localhost:8081/client/kursy/zapisz/" + id, CourseEdition.class );

        Object body = response.getBody();

        if(Objects.nonNull(body)) {
            if(HttpStatus.OK.equals(response.getStatusCode())) {

                System.out.println("Pojedyńczy kurs: \n" + body);
            } else {
                System.out.println("Error status: " + response.getStatusCode() + " body: " + response.getBody());
            }
        }
    }

    private static void getCourses() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<CourseEdition>> response = restTemplate.exchange("http://localhost:8081/client/kursy", HttpMethod.GET, new HttpEntity<>(new HttpHeaders()),
                new ParameterizedTypeReference<List<CourseEdition>>(){
                });
        List<CourseEdition> courseEditions = response.getBody();

        if(Objects.nonNull(courseEditions) && !courseEditions.isEmpty()) {
            System.out.println("All Courses: \n" + courseEditions);
        } else {
            System.out.println("No course");
        }

    }
}
