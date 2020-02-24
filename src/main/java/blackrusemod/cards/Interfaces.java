package blackrusemod.cards;

public interface Interfaces {
    // Interface for cards using the Knives mechanic.
    // Cards implementing this interface will have their damage affected by Silver Blades.
    public interface KnivesCard {}

    // Interface for cards using the Protection mechanic.
    // Cards implementing this interface will have their magic number affected by Elegance.
    public interface ProtectionCard {}
}