
import com.sun.org.apache.bcel.internal.classfile.Code;

import java.io.*;

public class FirstPass {
    private void getcode(){
        try{
            File file = new File("/Users/ddang/Assembler/Assembler/inputCode.txt");
            FileReader filereader = new FileReader(file);
            BufferedReader bufReader=new BufferedReader(filereader);
            String line="";

            while((line=bufReader.readLine())!=null){
                CodeAndTable.code[CodeAndTable.index++]=line;
                if(line.contains("ORG")){
                    int LC=line.indexOf("G");
                    CodeAndTable.index=Integer.parseInt(line.substring(LC+2));
                }

            }
            bufReader.close();
        }catch (FileNotFoundException e){

        }catch (IOException e){
            System.out.println(e);
        }

    }

    public void FirstPassing(){
        getcode();
        int LC=0;

        while(CodeAndTable.code[LC].equals("END")==false){
            String line=CodeAndTable.code[LC];
            if(line.contains(",")){
                int symbolIndex=line.indexOf(",");
                int TableIndex=CodeAndTable.symbolIndex;
                //System.out.println(symbolIndex);
                CodeAndTable.AST[TableIndex][0]=line.substring(0, symbolIndex);
                CodeAndTable.AST[TableIndex][1]=Integer.toString(LC);
                CodeAndTable.symbolIndex++;
            }else if(line.contains("G")){
                int a=line.indexOf("G");
                LC=Integer.parseInt(line.substring(a+2));
            }
            LC++;
        }
    }
}
