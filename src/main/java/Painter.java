import dto.Drill;
import dto.Instruction;
import dto.Position;
import dto.Size;

import java.util.List;

public class Painter {
    private final Dictionary dictionary;
    private final InstructionBuilder builder;

    public Painter(){
        this.dictionary = DictionaryLoader.getDictionary();
        builder = new InstructionBuilder(dictionary);
    }

    private List<String> build(Position beginPos,
                               Size plateSize,
                               Drill drill,
                               float patternRadius,
                               float innerWallWidth, float outerWallDistance){
        //setup
        builder.moveTo( Instruction.Type.SETUP, 0, 0, drill.getDrillTotalHeight() + drill.getDrillConeHeight() *2);
        builder.moveTo( Instruction.Type.INIT, 0, 0, drill.getDrillTotalHeight() + drill.getDrillConeHeight() *2);

        //init
        builder.moveTo( beginPos.getX() + outerWallDistance + patternRadius,
                        beginPos.getY() + outerWallDistance + patternRadius,
                        builder.getLast().getZ());

        final float innerHeight = plateSize.getHeight() - outerWallDistance * 2;
        final int segmentNumber = (int) (innerHeight / ((patternRadius * 2) + innerWallWidth/2));

        //draw all patterns
        for (int i = 0; i < segmentNumber; i++){
            if (hasSpaceToMove(outerWallDistance, plateSize.getHeight(), patternRadius)) {
                drawHorizontalLine(outerWallDistance, plateSize.getWidth(), patternRadius);
            }
            if (hasSpaceToMove(outerWallDistance, plateSize.getHeight(), patternRadius)){
                drawVerticalLine(innerWallWidth, plateSize.getHeight(), patternRadius);
            }
        }

        //finish
        builder.moveTo(builder.getLast().getX(), builder.getLast().getY(), drill.getDrillConeHeight() *2);
        builder.moveTo(0, 0, builder.getLast().getZ());

        return builder.build();
    }

    private boolean hasSpaceToMove(float outerWallDistance, float plateHeight, float patternRadius) {
        Position lastPosition = builder.getLast();

        return Math.abs(plateHeight - lastPosition.getY()) > Math.abs(plateHeight - outerWallDistance - patternRadius);
    }

    private void drawVerticalLine(float innerWallWidth, float plateHeight, float patternRadius) {
        Position lastPosition = builder.getLast();
        builder.moveTo(lastPosition.getX(), lastPosition.getY() + innerWallWidth + patternRadius*2, lastPosition.getZ());
    }

    private void drawHorizontalLine(float outerWallDistance, float plateWidth, float patternRadius) {
        Position lastPosition = builder.getLast();

        float leftDistance = lastPosition.getX();
        float rightDistance = Math.abs(plateWidth - lastPosition.getX());

        boolean isFromLeft = leftDistance < rightDistance;

        if (isFromLeft){
            builder.moveTo(plateWidth - outerWallDistance, lastPosition.getY(), lastPosition.getZ());
        } else {
            builder.moveTo(outerWallDistance, lastPosition.getY(), lastPosition.getZ());
        }
    }

    public List<String> paintHidrogenFlow(Position beginPos,
                                          Size plateSize,
                                          Drill drill,
                                          float drillDepth,
                                          float innerWallWidth, float outerWallDistance) {
        if (drill.getDrillConeHeight() < drillDepth){
            throw new RuntimeException("Wrong inputs. Drille cone height can't be smaller than the drillDepth");
        }

        float patternRadius = drillDepth * (drill.getDrillWidth() / 2) / drill.getDrillConeHeight();

        return build(beginPos, plateSize, drill, patternRadius, innerWallWidth, outerWallDistance);
    }

}
