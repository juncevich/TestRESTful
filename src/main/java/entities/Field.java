package entities;

public class Field {
    String name;

    public Boolean getPrime() {
        return isPrime;
    }

    public void setPrime(String value) {
        if (value.equals("PRI")){
            isPrime = true;
        } else {
            isPrime = false;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Boolean isPrime;

}
