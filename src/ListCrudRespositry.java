// return List instead of iterables
// author Dan Vega
// https://www.youtube.com/watch?v=lDbE0uYlYgk
// require spring 3.00 sanpshot

package dev.pacheco.demoiterables;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableJdbcRepositories
public class DemoIterablesApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoIterablesApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner (EventRepository r) {
		return args -> {
			r.save(new Event(null, "Chicago", LocalDate.of(2022,04,26), LocalDate.of(2022,04,28)));
			r.save(new Event(null, "Minnesota", LocalDate.of(2022,04,26), LocalDate.of(2022,04,28)));
			r.save(new Event(null, "New York", LocalDate.of(2022,04,26), LocalDate.of(2022,04,28)));
			r.save(new Event(null, "New Jersey", LocalDate.of(2022,04,26), LocalDate.of(2022,04,28)));
			r.save(new Event(null, "Alabama", LocalDate.of(2022,04,26), LocalDate.of(2022,04,28)));
			r.save(new Event(null, "Denver", LocalDate.of(2022,04,26), LocalDate.of(2022,04,28)));
		};
	}
}


@RestController
@RequestMapping("/events")
class EventController {

	private final EventRepository repository;

	public EventController(EventRepository repository) {
		this.repository = repository;
	}


	@GetMapping
	public List<Event> findAll() {
		return repository.findAll();
	}
}

interface EventRepository extends ListCrudRepository<Event, Long> {
}

record Event (@Id Long id, String city, LocalDate startDate, LocalDate endDate) {
}
