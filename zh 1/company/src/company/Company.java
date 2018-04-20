package company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Török Renátó
 */
public class Company {
    private final String name;
    private List<Contract> contracts;
    
    public Company(String name, String fileName) throws FileNotFoundException {
        this.name = name;
        contracts = new LinkedList<>();
        
        Scanner sc = new Scanner(new File(fileName));
        
        while(sc.hasNextLine()) {
            Contract result = Contract.make(sc.nextLine());       
            if(result != null) contracts.add(result);
        }
    }
    
    public List<String> employeesOf(String employer) {
        return new LinkedList<>(contracts.stream()
                .filter(c -> c.hasEmployer(employer))
                .map(Contract::getEmployee)
                .collect(Collectors.toList()));       
    }
    
    @Override
    public String toString() {
        return contracts.stream()
                .map(contract -> contract.toString())
                .collect(Collectors.joining(",", "Company(" + name + ",[", "])"));
    }
    
    public List<String> employees() {
        return new LinkedList<>(contracts.stream()
                .flatMap(c -> Stream.of(c.getEmployee(), c.getEmployer()))
                .distinct()
                .collect(Collectors.toList()));
    }
    
    public List<String> bosses() {
        return new LinkedList<>(contracts.stream()
            .map(Contract::getEmployer)
            .distinct()
            .collect(Collectors.toList()));
    }
    
    public String bestBoss() {
        return bosses().stream()
                .max((a, b) -> Integer.compare(employeesOf(a).size(), employeesOf(b).size()))
                .orElse(null);
    }
}
