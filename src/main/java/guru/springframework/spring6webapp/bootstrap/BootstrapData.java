package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    private PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");
        Book sf = new Book();
        sf.setTitle("JEE Development without EJB");
        sf.setIsbn("54757585");

        Author ev = new Author();
        ev.setFirstName("Eric");
        ev.setLastName("Evans");
        Author rj = new Author();
        rj.setFirstName("Rod");
        rj.setLastName("Johnson");

        Publisher publisher = new Publisher();
        publisher.setPublisherName("McGrew Hill");
        publisher.setCity("Boston");
        publisher.setState("Massachusets");
        publisher.setZip("9245");
        publisher.setAddress("Commonwealth Avenue");

        Book dddSaved = bookRepository.save(ddd);
        Book sfSaved = bookRepository.save(sf);
        Author evSaved = authorRepository.save(ev);
        Author rjSaved = authorRepository.save(rj);
        Publisher publisherSaved = this.publisherRepository.save(publisher);

        evSaved.getBooks().add(dddSaved);
        rjSaved.getBooks().add(sfSaved);
        dddSaved.getAuthors().add(evSaved);
        sfSaved.getAuthors().add(rjSaved);

        dddSaved.setPublisher(publisherSaved);
        sfSaved.setPublisher(publisherSaved);

        authorRepository.save(evSaved);
        authorRepository.save(rjSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(sfSaved);

        System.out.println("In BootStrap ...");
        System.out.println("Authors count: " + authorRepository.count());
        System.out.println("Books count: " + bookRepository.count());
        System.out.println("Publishers count: " + publisherRepository.count());


    }
}
