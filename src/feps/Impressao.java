package feps;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import javax.print.PrintService;

import javax.print.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
// Essa classe � a responsavel pela impressao. Ela detecta a impressora
// instalada, recebe o texto e o imprime.
public class Impressao {
    // variavel estatica porque ser� utilizada por inumeras threads
    private static PrintService impressora;
    public Impressao() {
        detectaImpressoras();
    }
    // O metodo verifica se existe impressora conectada e a
    // define como padrao.
    public void detectaImpressoras() {
        try {
            DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
            PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);
            for (PrintService p: ps) {
                System.out.println("Impressora encontrada: " + p.getName());
                if (p.getName().contains("Text") || p.getName().contains("Generic"))  {
	                System.out.println("Impressora Selecionada: " + p.getName());
                    impressora = p;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public synchronized boolean imprime(String texto) {
        // se nao existir impressora, entao avisa usuario
    
        // senao imprime texto
        if (impressora == null) {
            String msg = "Nennhuma impressora foi encontrada. Instale uma impressora padr�o \r\n(Generic Text Only) e reinicie o programa.";
	       	System.out.println(msg);
        } else {
            try {
                DocPrintJob dpj = impressora.createPrintJob();
                InputStream stream = new ByteArrayInputStream(texto.getBytes());
                DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                Doc doc = new SimpleDoc(stream, flavor, null);
                dpj.print(doc, null);
                return true;
            } catch (PrintException e) {
                e.printStackTrace();
            }
        }
        
//        JasperPrintManager.printReport(sourceFileName, false);
        
        return false;
    }
}