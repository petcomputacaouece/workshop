package Exceptions;

public class userDontExists extends Exception{

    public userDontExists(){

    }

    public String toString(){
        return "Usuário nao existe";
    }

}
