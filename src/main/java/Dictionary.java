import dto.Instruction;
import dto.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Dictionary {
    private final Map<Instruction.Type, Function<Object, String>> gCodeDictionary = new HashMap<>();

    public void insert(Instruction.Type instructionType, Function<Object, String> converterFunction){
        this.gCodeDictionary.put(instructionType, converterFunction);
    }

    public List<String> convert(Instruction.Type type, Object command){
        final Object converterFunction = gCodeDictionary.get(type);

        if (type == Instruction.Type.SETUP) {
            Function<Position, List<String>> function = (Function<Position, List<String>>)converterFunction;

            //sets the speed
            return function.apply((Position)command);

        } else if (type == Instruction.Type.INIT){
            Function<Float, List<String>> function = (Function<Float, List<String>>)converterFunction;

            //executes the inital positioning of the drill
            return function.apply((Float)command);

        } else if (type == Instruction.Type.MOVE){
            Function<Position, List<String>> function = (Function<Position, List<String>>)converterFunction;

            //executes the movement speeds
            return function.apply((Position)command);
        } else {
            return null;
        }
    }
}
