package bazar;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import parser.Parser;
import tree.RedBlackTree;
import tree.Tree;

import java.io.File;
import java.io.FileFilter;
import java.util.Iterator;

/**
 * Classe Bazar
 * ensemble de pages initialement non trié
 *
 * @author Alexis Giraudet &amp; François Hallereau
 * @version 1.0
 */
public class Bazar {
    public static void main(String[] args) {
        int k = 0;
        Tree<Page> pages = new RedBlackTree<Page>();
        Tree<String> dico = null;

        try {
            String _k = null;
            String _dico = null;
            String[] _pages = null;

            CommandLineParser parser = new GnuParser();
            Options options = new Options();

            options.addOption(OptionBuilder
                    .hasArg()
                    .isRequired()
                    .create("dico"));
            options.addOption(OptionBuilder
                    .hasArg()
                    .isRequired()
                    .withType(Number.class)
                    .create("k"));

            CommandLine line = parser.parse(options, args);

            _k = line.getOptionValue("k");
            _dico = line.getOptionValue("dico");
            _pages = line.getArgs();

            k = Integer.parseInt(_k);
            dico = Parser.parseDico(new File(_dico));
            for (String fileName : _pages) {
                File dir = new File(FilenameUtils.getFullPath(fileName));
                FileFilter fileFilter = new WildcardFileFilter(FilenameUtils.getName(fileName));
                File[] files = dir.listFiles(fileFilter);
                for (File file : files) {
                    pages.add(Parser.parsePage(file));
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Usage : java -jar Bazar.jar -k [K] -dico [DICTIONARY] PAGE...");
            System.exit(1);
        }

        for (Page p : pages) {
            p.getWords().retainAll(dico); //on conserve uniquement les mots ayant un rapport avec le dico
        }

        Tree<Chapter> chapters = new RedBlackTree<Chapter>();
        int i = 1;
        for (Page p : pages) {
            Chapter current = null;
            Iterator<Chapter> it = chapters.iterator();
            while (it.hasNext()) {//parcours des chapitres existants
                Chapter c = it.next();
                if (c.addPage(p, k)) {//si la page a  k mots en communs avec une autre
                    if (current != null) {
                        current.getKeyWords().addAll(c.getKeyWords());
                        current.getPages().addAll(c.getPages());
                        it.remove();
                        break;
                    } else {
                        current = c;
                    }
                }

            }
            if (current == null) {//sinon on crée un nouveau chapitre
                chapters.add(new Chapter(i, p));
                i++;
            }
        }

        for (Chapter c0 : chapters) {
            for (Chapter c1 : chapters) {
                if (c0 != c1) {
                    c0.getKeyWords().removeAll(c1.getKeyWords());
                }
            }
        }

        System.out.println("k = " + k);
        System.out.println("Dictionary = " + dico);
        System.out.println();

        for (Chapter c : chapters) {
            System.out.println(c);
        }
    }
}
