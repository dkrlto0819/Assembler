import java.io.IOException;

public class Main {
    public static String[][] instructionData;

    public static void getInstructionData(){
        Instruction reader = new Instruction();
        instructionData=reader.Instruction();
    }

    public static void main(String[] args) throws IOException{
        FirstPass gotoFirstPass=new FirstPass();
        SecondPass gotoSecondPass=new SecondPass();

        getInstructionData();
        gotoFirstPass.FirstPassing();
        gotoSecondPass.SecondPassing();
    }
}
