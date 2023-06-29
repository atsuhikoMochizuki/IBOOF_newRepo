package fr.diginamic.service;

import fr.diginamic.mochizukiTools.Utils;
import fr.diginamic.service.Cleaner;
import fr.diginamic.service.Parser;
import org.junit.Test;

import java.util.ArrayList;

public class ParserTest extends Parser {

    private static ArrayList<String[]> rows = Cleaner.cleanFile(Cleaner.CSV_FILE_RELATIVE_PATH);

    @Test
    public void testInsertToDataBase() {
        Utils.msgInfo("Ecriture des données en base");
        Parser.insertToDataBase(rows);
        Utils.msgResult("Ecriture des données en base OK");
    }
}