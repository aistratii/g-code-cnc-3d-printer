import dto.Instruction;
import dto.Position;

public class DictionaryLoader {

    private DictionaryLoader(){}

    public static Dictionary getDictionary(){
        final Dictionary dictionary = new Dictionary();

        dictionary.insert(Instruction.Type.SETUP, o -> {
            Position p = (Position)o;

            return "G0 " +
                    "X" + p.getX() + " " +
                    "Y" + p.getY() + " " +
                    "Z" + p.getZ();
        });

        dictionary.insert(Instruction.Type.MOVE, o -> {
            Position p = (Position)o;

            return "G0 " +
                    "X" + p.getX() + " " +
                    "Y" + p.getY() + " " +
                    "Z" + p.getZ();
        });

        return dictionary;
    }
}
