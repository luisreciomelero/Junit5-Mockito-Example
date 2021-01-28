package mockito;

/*
 * Esta clase tendra dependecia directa con la clase ValidNumber
 * Usaremos mockito para testear no mi clase add, sino la dependencia de esta
 * Con mockito haremos los test unitarios sin tener que preocuparnos por las clases de las que dependemos.
 */

public class Add {

    private ValidNumber validNumber;

    public Add(ValidNumber validNumber){
        this.validNumber = validNumber;
    }

    public int add(Object a, Object b){
        if(validNumber.check(a) && validNumber.check(b)){
            return (Integer)a + (Integer)b;
        }
        return 0;
    }

    public int addInt(Object a){
        return validNumber.doubleToInt(a) + validNumber.doubleToInt(a);
    }

}
