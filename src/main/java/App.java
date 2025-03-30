import models.Dataloader;
import models.Service;

import javax.xml.crypto.Data;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Dataloader data = new Dataloader();

        data.loadFromJson();

        data.print();

    }
}
