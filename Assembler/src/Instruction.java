public class Instruction {
    public static String[][] MRIinstruction = {{"AND", "ADD", "LDA", "STA", "BUN", "BSA", "ISZ"},
    {"0", "1", "2", "3", "4", "5", "6"}};
//    public static String[][] RRIinstruction = {{"CLA", "CLE", "CMA", "CME", "CIR", "CIL", "INC", "SPA", "SNA", "SZA", "SZE", "HLT"},
//            {"7800", "7400", "7200", "7100", "7080", "7040", "7020", "7010", "7080", "7040", "7020", "7010", "7008", "7004", "7002", "7001"}};
//    public static String[][] IOIinstruction={{"INP", "OUT", "SKI", "SKO", "ION", "IOF"},
//            {"F800", "F400", "F200", "F100", "F080", "F040"}};
    public static String[][] nonMRIinstruction={{"CLA", "CLE", "CMA", "CME", "CIR", "CIL", "INC", "SPA", "SNA", "SZA", "SZE", "HLT", "INP", "OUT", "SKI", "SKO", "ION", "IOF"},
        {"7800", "7400", "7200", "7100", "7080", "7040", "7020", "7010", "7008", "7004", "7002", "7001", "F800", "F400", "F200", "F100", "F080", "F040"}};
    public static String[] pseudoInstruction = {"ORG", "END", "DEC", "HEX"};

}
