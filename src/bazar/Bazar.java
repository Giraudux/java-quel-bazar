package bazar;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import parser.Parser;
import tree.RedBlackTree;
import tree.Tree;

import java.io.IOException;

/**
 * Classe Bazar
 * ensemble de pages initialement non trié
 * @author Alexis Giraudet &amp; François Hallereau
 * @version 1.0
 */
public class Bazar {

    /**
     * constante qui indique le nombre de mots en commun avec le dictionnaire pour faire partie d'un chapitre
     */
    private int __k;

    /**
     * ensemble de pages
     * @see bazar.Page
     */
    private Tree<Page> __pages;

    /**
     * dictionnaire de mots
     */
    private Tree<String> __dico;

    /**
     * Constructeur de la classe
     * @param k la constante
     * @param dico le dictionnaire
     * @param pages les pages
     * @throws IOException lorsque le parser échoue
     */
    public Bazar(int k, String dico, String[] pages) throws IOException {
        __k = k;
        __pages = new RedBlackTree<Page>();
        __dico = null;
        load(dico, pages);
    }

    /**
     * Méthode qui parse le dico et les pages
     * @param dico le dictionnaire
     * @param pages les pages
     * @throws IOException lorsque le parser échoue
     */
    private void load(String dico, String[] pages) throws IOException {
        __dico = Parser.parseDico(dico);
        for (String s : pages) {
            __pages.add(Parser.parsePage(s));
        }
    }

    /**
     * Méthode qui trie les pages et les classe par chapitre
     * @see bazar.Chapter
     */
    public void resolve() {
        for (Page p : __pages) {
            p.getWords().retainAll(__dico); //on conserve uniquement les mots ayant un rapport avec le dico
        }

        Tree<Chapter> chapters = new RedBlackTree<Chapter>();
        int i = 1;
        for (Page p : __pages) {
            boolean added = false;
            for (Chapter c : chapters) {//parcours des chapitres existants
                if (c.addPage(p, __k)) {//si la page a  k mots en communs avec une autre
                    added = true;//on l'ajoute au chapitre correspondant
                    break;
                }
            }
            if (!added) {//sinon on crée un nouveau chapitre
                Chapter c = new Chapter(i);
                i++;
                c.addPage(p);//on ajoute la page au chapitre
                chapters.add(c);
            }
        }

        //TODO: redondant
        for (Chapter c0 : chapters) {
            for (Chapter c1 : chapters) {
                if (c0 != c1) {
                    c0.getKeyWords().removeAll(c1.getKeyWords());
                    //c1.getKeyWords().removeAll(c0.getKeyWords());
                }
            }
        }

        for (Chapter c : chapters) {
            System.out.println(c);
        }
    }

    public static void main(String[] args) {
        Bazar bazar;
        String k = null;
        String dico = null;
        String[] pages = null;

        CommandLineParser parser = new GnuParser();
        Options options = new Options();
        options.addOption(OptionBuilder.withArgName("d")
                .withLongOpt("dico")
                .withDescription("dictionnary file path")
                .hasArg(true)
                .withArgName("DICO")
                .isRequired(true)
                .create());
        options.addOption(OptionBuilder.withArgName("k")
                .withLongOpt("k")
                .withDescription("minimal words difference between two page to form a chapter")
                .hasArg(true)
                .withArgName("K")
                .isRequired(true)
                        //.withType(Number.class)
                .create());
        try {
            CommandLine line = parser.parse(options, args);
            k = line.getOptionValue("k");
            dico = line.getOptionValue("dico");
            pages = line.getArgs();
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            System.out.println("Usage : bazar -k [K] -d [DICO] [PAGE]...");
            System.exit(1);
        }

        try {
            bazar = new Bazar(Integer.parseInt(k), dico, pages);
            bazar.resolve();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
