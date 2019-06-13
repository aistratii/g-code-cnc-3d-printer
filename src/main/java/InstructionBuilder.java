import dto.Instruction;
import dto.Position;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class InstructionBuilder {
    private List<Instruction> instructions;
    private Dictionary dictionary;

    public InstructionBuilder(Dictionary dictionary){
        this.dictionary = dictionary;
        this.instructions = new ArrayList<>();
    }

    public InstructionBuilder moveTo(Instruction.Type type, Position newPosition){
        this.instructions.add(new Instruction(type, newPosition));
        return this;
    }

    /**
     * Default instruction type is move
     * @param newPosition
     * @return
     */
    public InstructionBuilder moveTo(Position newPosition){
        this.instructions.add(new Instruction(Instruction.Type.MOVE, newPosition));
        return this;
    }


    public InstructionBuilder moveTo(Instruction.Type type, float x, float y, float z){
        this.instructions.add(new Instruction(type, new Position(x, y, z)));
        return this;
    }

    public InstructionBuilder moveTo(float x, float y, float z){
        this.instructions.add(new Instruction(Instruction.Type.MOVE, new Position(x, y, z)));
        return this;
    }

    public Position getLast(){
        return instructions.get(instructions.size()-1).getPosition();
    }

    public List<String> build(){
//        final List<List<String>> firstInstructions = new ArrayList<>();
//
//        firstInstructions.add(
//                dictionary.convert(
//                        Instructions.Type.SETUP,
//                        new Position(0, 0, positions.get(0).getZ())));
//
//        firstInstructions.add(
//                dictionary.convert(
//                        Instructions.Type.INIT,
//                        positions.get(1)));
//
//
//        final List<String> followingInstructions = positions.stream()
//                .map(pos -> dictionary.convert(Instructions.Type.MOVE, pos))
//                .flatMap(List::stream)
//                .collect(toList());
//
//        final List<String> gCode = firstInstructions.stream().flatMap(List::stream).collect(toList());
//        gCode.addAll(followingInstructions);

        final List<String> gCodeList = new ArrayList<>();

        //set the speed
        dictionary.convert(Instruction.Type.INIT, 60F).stream().forEach(instructionLine -> {
            gCodeList.add(instructionLine);
            gCodeList.add("\n");
        });

        instructions.stream()
                .map(instr -> dictionary.convert(instr.getType(), instr.getPosition()))
                .flatMap(List::stream)
                .forEach(instructionLine -> {
                    gCodeList.add(instructionLine);
                    gCodeList.add("\n");
                });

        return gCodeList;
    }
}
