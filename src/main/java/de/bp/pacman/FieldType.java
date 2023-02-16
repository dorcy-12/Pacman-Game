package de.bp.pacman;

/**
 * Enumeration of types in a field
 */
public enum FieldType {
    /**
     * Noting eatable, passable fieldtype
     */
    EMPTY,
    /**
     * Eatable point (10 gamepoints per eaten point)
     */
    POINT,
    /**
     * not passable wall
     */
    BLOCK,
    /**
     * Eatable power_up, makes invincible for 10 secs
     */
    POWER_UP,
    /**
     * Colored empty, passable fieldtype
     */
    COLORED_EMPTY

}
