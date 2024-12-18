package Gereja;

import Gereja.repository.JemaatRepositoryImpl;
import Gereja.service.JemaatServiceImpl;
import Gereja.view.AplikasiPerpuluhanPerbendaharaanViewImpl;
import Gereja.config.Database;
import Gereja.repository.JemaatRepository;
import Gereja.service.JemaatService;
import Gereja.view.AplikasiPerpuluhanPerbendaharaanView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        AplikasiPerpuluhanPerbendaharaanViewImpl jemaatView = context.getBean(AplikasiPerpuluhanPerbendaharaanViewImpl.class);
        jemaatView.prosesMenu();

    }

    @Bean
    Database database(){
        Database database = new Database("jemaat_db", "root", "", "localhost", "3306");
        database.setup();
        return database;
    }
}

