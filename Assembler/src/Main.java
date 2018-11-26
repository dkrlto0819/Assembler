import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
        FirstPass gotoFirstPass=new FirstPass();
        SecondPass gotoSecondPass=new SecondPass();

        gotoFirstPass.FirstPassing();
        gotoSecondPass.SecondPassing();
    }
}
