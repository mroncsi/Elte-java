package company;

import java.io.FileNotFoundException;

/**
 *
 * @author Török Renátó
 */
public class Main {

    public static void main(String[] args) {
        try {
            Company c = new Company("Aperture Science", "company.txt");
            System.out.println(c);
            System.out.println(c.employees());
            System.out.println(c.bestBoss());
            
        }catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
