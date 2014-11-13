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
 * @author Alexis Giraudet
 */
public class Bazar {
    private int __k;
    private Tree<Page> __pages;
    private Tree<String> __dico;

    public Bazar(int k, String dico, String[] pages) throws IOException {
        __k = k;
        __pages = new RedBlackTree<Page>();
        __dico = null;
        load(dico, pages);
    }

    private void load(String dico, String[] pages) throws IOException {
        __dico = Parser.parseDico(dico);
        for (String s : pages) {
            __pages.add(Parser.parsePage(s));
        }
    }

    public void resolve() {
        for (Page p : __pages) {
            p.getWords().retainAll(__dico);
        }

        Tree<Chapter> chapters = new RedBlackTree<Chapter>();
        int i = 0;
        for (Page p : __pages) {
            boolean added = false;
            for (Chapter c : chapters) {
                if (c.addPage(p, __k)) {
                    added = true;
                    break;
                }
            }
            if (!added) {
                Chapter c = new Chapter(i);
                i++;
                c.addPage(p);
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
