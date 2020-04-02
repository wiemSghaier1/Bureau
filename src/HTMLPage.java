
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class HTMLPage {

    private  BufferedWriter bw;
    private String nom, prenom, pseudo,difficulte;
    private int  nbecateg;
    private Boolean son, score, pe, ombre;

    public HTMLPage(String nom1,String prenom1,String pseudo1,String difficulte1,int nbecateg1,Boolean son1,Boolean score1,Boolean pe1,Boolean ombre1) throws IOException {

        File htmlTemplateFile = new File("pages/config.html");
        String htmlString = FileUtils.readFileToString(htmlTemplateFile,"utf-8");

        this.nom = nom1;
        this.prenom = prenom1;
        this.pseudo = pseudo1;
        this.difficulte = difficulte1;
        this.nbecateg = nbecateg1;
        this.son = son1;
        this.score = score1;
        this.pe = pe1;
        this.ombre = ombre1;
        htmlString = htmlString.replace("$nom", nom);
        htmlString = htmlString.replace("$prenom", prenom);
        htmlString = htmlString.replace("$pseudo", pseudo);
        htmlString = htmlString.replace("$diff", difficulte);
        htmlString = htmlString.replace("$categorie", remplaceCat(nbecateg));
        htmlString = htmlString.replace("$son", " "+son+" ");
        htmlString = htmlString.replace("$score", " "+score+" ");
        htmlString = htmlString.replace("$pe", " "+pe+" ");
        htmlString = htmlString.replace("$ombre", " "+ombre+" ");
        File newHtmlFile = new File("pages/confiigg.html");
        FileUtils.writeStringToFile(newHtmlFile, htmlString,"utf-8");

        try {
            Desktop.getDesktop().open(new File("pages/confiigg.html"));
        }catch (IOException f) {
            // TODO Auto-generated catch block
            f.printStackTrace();
        }
    }

    public String remplaceCat(int nbecateg){

        String text = " ";

        for (int i= 1 ; i <= nbecateg ; i++){
            text += "categorie: "+i+"<html><br></html>";
        }
        return text;
    }
}
