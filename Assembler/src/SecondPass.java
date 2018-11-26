import com.sun.org.apache.bcel.internal.classfile.Code;

public class SecondPass {
    Instruction reader = new Instruction();
    private int MRIindex, RRIindex, IOIindex;
    private int ASTindex=0;

    private int isPseudo(String line){
        for(int i=0;i<4;i++){
            if(line.contains(reader.pseudoInstruction[i])) return 1;
        }
        return 0;
    }

    private int isMRI(String line){
        for(int i=0;i<7;i++){
            if(line.contains(reader.MRIinstruction[0][i])) {
                MRIindex=i;

                return 1;
            }
        }
        return 0;
    }

    private void ChangeDECtoBinary(String line){
        int index=line.indexOf("DEC");
        int value=Integer.parseInt(line.substring(index+4));
        String hexValue=String.format("%4s", Integer.toHexString(value)).replace(" ", "0");
        String result = hexValue.substring(hexValue.length()-4, hexValue.length());
        String binaryValue=String.format("%16s", Integer.toBinaryString(value)).replace(" ", "0");
        binaryValue=binaryValue.substring(binaryValue.length()-16, binaryValue.length());
        System.out.println(result + " " + binaryValue);
    }

    private  void ChangeHEXtoBinary(String line){
        int index=line.indexOf("HEX");
        int value=Integer.parseInt(line.substring(index+4), 16);
        String hexValue=String.format("%4s", Integer.toHexString(value)).replace(" ", "0");
        String result = hexValue.substring(hexValue.length()-4, hexValue.length());
        String binaryValue=String.format("%16s", Integer.toBinaryString(value)).replace(" ", "0");
        binaryValue=binaryValue.substring(binaryValue.length()-16, binaryValue.length());
        System.out.println(result + " " + binaryValue);

    }

    private void FindASTIndex(String line){
        String symbolName=line.substring(4, line.length());
        for(int i=0;i<CodeAndTable.symbolIndex;i++){
            if(CodeAndTable.AST[i][0].equals(symbolName)) ASTindex=i;
        }
    }

    private void ProcessingMRI(String line){
        FindASTIndex(line);
        String operationCode=Instruction.MRIinstruction[1][MRIindex];
        String addressCode=CodeAndTable.AST[ASTindex][1];
        String MRICodeHEX=operationCode+addressCode;

        int operationCodeDEC=Integer.parseInt(operationCode);
        operationCode=String.format("%3s", Integer.toBinaryString(operationCodeDEC)).replace(" ", "0");

        int addressCodeDEC = Integer.parseInt(addressCode, 16);
        addressCode=String.format("16%s", Integer.toBinaryString(addressCodeDEC)).replace(" ", "0");

        String FindI=line.substring(5, line.length());
        //addressCode=String.format("%3s", Integer.toBinaryString(addressCodeDEC)).replace(" ", "0");
        String ICode;
        if(FindI.contains("I")) ICode="1";
        else ICode="0";


        String MRICode=ICode+operationCode+addressCode;
        System.out.println(MRICodeHEX + " " + MRICode);

    }

    private void ProcessingnonMRI(String line){
        for(int i=0;i<18;i++){
            if(line.contains(Instruction.nonMRIinstruction[0][i])){
                String instructionHEX=Instruction.nonMRIinstruction[1][i];
                int instructionDEC=Integer.parseInt(instructionHEX, 16);
                String instruction=String.format("%16s", Integer.toBinaryString(instructionDEC)).replace(" ", "0");
                System.out.println(instructionHEX+" "+instruction);
            }
        }
    }

    public void SecondPassing(){
        int LC=0;
        while(CodeAndTable.code[LC].equals("END")==false){
            String line=CodeAndTable.code[LC];
            int PseudoSwitch=isPseudo(line);
            if(PseudoSwitch==1) {
                if (line.contains("ORG")) {
                    int a = line.indexOf("G");
                    LC = Integer.parseInt(line.substring(a + 2))-1;
                } else if (line.contains("END")) break;
                else{
                    if(line.contains("DEC")){
                        ChangeDECtoBinary(line);
                    }else if(line.contains("HEX")){
                        ChangeHEXtoBinary(line);
                    }
                }
            }else{
                int MRIswitch=isMRI(line);
                if(MRIswitch==1){
                    ProcessingMRI(line);
                }else{
                    ProcessingnonMRI(line);
                }
            }
            LC++;
        }
    }
}
