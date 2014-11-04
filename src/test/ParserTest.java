package test;

import parser.Parser;
import tree.RedBlackTree;
import tree.Tree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Alexis Giraudet
 */
public class ParserTest {
    public static void Test0() throws IOException {
        File tmp = File.createTempFile("lorem", ".tmp");
        String s = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris semper purus est, sit amet condimentum justo rutrum et. Sed ac neque vitae magna pulvinar molestie. Morbi facilisis enim congue tortor ultricies, eget congue est placerat. Phasellus facilisis interdum ultricies. Aliquam sodales libero tortor, in facilisis ante interdum non. Mauris leo mi, cursus vitae porttitor sed, pellentesque sed lorem. Nullam id elementum orci. Suspendisse potenti. Vestibulum dignissim rutrum tincidunt. Curabitur nisl turpis, sollicitudin venenatis turpis id, lobortis commodo ante. Aenean ac libero bibendum, lacinia odio eget, laoreet lacus. Nulla et est leo.\n" +
                "\n" +
                "Quisque egestas lacus eget nunc feugiat interdum. In justo eros, accumsan et tristique sed, scelerisque hendrerit lectus. Proin egestas purus eget mi dapibus eleifend. Fusce ante nunc, lobortis ac libero nec, pellentesque elementum nunc. Aenean tincidunt mi convallis libero molestie, vel sollicitudin libero euismod. Aenean accumsan quam ac augue malesuada, quis efficitur purus interdum. Phasellus sapien purus, tempor eu pharetra et, varius eu mi. Nulla sed urna ut quam laoreet gravida. Vivamus semper eu magna nec molestie.\n" +
                "\n" +
                "Donec iaculis tempor semper. Donec bibendum lobortis sodales. Morbi tincidunt lacus vitae turpis egestas dapibus. Sed pulvinar magna nibh, non venenatis ante faucibus a. Fusce bibendum vestibulum congue. In hac habitasse platea dictumst. Aliquam eget nisi vehicula, interdum dolor ut, rhoncus ligula. Aenean ut faucibus odio, vitae iaculis ex. Nullam nec convallis lorem. Vivamus euismod in orci a lacinia. In hac habitasse platea dictumst. Ut ut urna sit amet nulla porta ullamcorper at ac lectus. Curabitur finibus congue risus, quis venenatis massa porta ac.\n" +
                "\n" +
                "Sed sagittis at purus sed iaculis. Donec vitae hendrerit elit, eget pellentesque nunc. Nam vitae iaculis tellus. Donec placerat ex vel posuere tristique. Donec lorem mi, accumsan vitae mi ut, gravida efficitur odio. In nibh erat, iaculis in neque id, sagittis gravida erat. Pellentesque tempor magna vel porttitor ullamcorper. Cras nec sodales orci. Mauris vel tortor ipsum. Nullam feugiat porta nisi vitae hendrerit.\n" +
                "\n" +
                "Proin eu risus sit amet nunc ornare bibendum. Vestibulum odio velit, scelerisque vel ultricies eget, pretium quis libero. In volutpat dignissim tempus. Ut sagittis cursus lorem, sit amet finibus turpis faucibus vitae. Nullam vestibulum est augue, eget consequat tortor volutpat vel. Ut pulvinar accumsan ultrices. Etiam ut ex feugiat, tincidunt urna quis, auctor diam. Cras efficitur interdum lectus, at placerat elit egestas ut. ";
        Files.write(tmp.toPath(), s.getBytes());
        Tree<String> t = new RedBlackTree<String>();
        Parser.parseTokens(tmp.getPath(), t);
        tmp.delete();
        TreeTest.printTree(t);
    }
}
