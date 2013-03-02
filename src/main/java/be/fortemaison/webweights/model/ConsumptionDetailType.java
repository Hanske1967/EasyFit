package be.fortemaison.webweights.model;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 13/02/13
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */
public class ConsumptionDetailType {

    public static final ConsumptionDetailType MATIN = new ConsumptionDetailType(1);

    public static final ConsumptionDetailType DIX_HEURE = new ConsumptionDetailType(2);

    public static final ConsumptionDetailType MIDI = new ConsumptionDetailType(3);

    public static final ConsumptionDetailType QUATRE_HEURE = new ConsumptionDetailType(4);

    public static final ConsumptionDetailType SOIR = new ConsumptionDetailType(5);

    public static final ConsumptionDetailType SOIREE = new ConsumptionDetailType(6);

    public static final ConsumptionDetailType[] ALL = new ConsumptionDetailType[]{MATIN, DIX_HEURE, MIDI, QUATRE_HEURE, SOIR, SOIREE};

    private final int type;

    /**
     * @param type
     */
    public ConsumptionDetailType (int type) {
        this.type = type;
    }

    public int getType () {
        return type;
    }

    @Override
    public boolean equals (Object object) {
        return object.getClass().equals(ConsumptionDetailType.class)
                && this.type == ((ConsumptionDetailType) object).getType();
    }

    /**
     * @param type
     */
    public static ConsumptionDetailType get (int type) {
        ConsumptionDetailType result = null;
        for (ConsumptionDetailType aType : ALL) {
            if (aType.getType() == type) {
                result = aType;
            }
        }

        return result;
    }


}
