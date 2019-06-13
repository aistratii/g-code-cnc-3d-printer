import dto.Drill;
import dto.Position;
import dto.Size;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Painter painter = new Painter();

        List<String> instructions = painter.paintHidrogenFlow(
                new Position(300, 300, 0),
                new Size(100, 100, 20),
                new Drill(5f, 2f, 100),
                5f,
                5f,
                10);

        //instructions.outputTo("D://gCode.gcode");
    }
}
