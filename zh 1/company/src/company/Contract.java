package company;

/**
 * 
 * @author Török Renátó
 */
public class Contract {
    public static final Contract VADER = new Contract("Vader", "Emperor", 5000);
    
    private final String employee;
    private final String employer;
    private final int wage;
    
    public Contract(String employee, String employer, int wage) {
        this.employee = employee;
        this.employer = employer;
        this.wage = wage;
    }

    public String getEmployee() {
        return employee;
    }

    public String getEmployer() {
        return employer;
    }

    public int getWage() {
        return wage;
    }
    
    public static Contract make(String line) { // Gomboc Artur,Picur,200
        String[] data = line.split(",");
        
        if(data.length != 3
                || data[0].trim().isEmpty()
                || data[1].trim().isEmpty()
                || !data[2].chars().allMatch(Character::isDigit)
                || Integer.parseInt(data[2]) < 0)
            return null;
        
        return new Contract(data[0], data[1], Integer.parseInt(data[2]));
    }
    
    public boolean hasEmployer(String employer) {
        return this.employer.equals(employer);
    }
    
    @Override
    public String toString() {
        return "Contract(" + employee + "," + employer + "," + wage + ")";
    }
}
