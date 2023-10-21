package edu.uw.tcss.model;

import java.util.Objects;
/**
 * This class creates StoreItemOrder objects which contains the Item object and the quantity of said
 * Item objects being purchased.
 */
public final class StoreItemOrder implements ItemOrder {
    /**
     * This instance field holds an Item object.
     */
    private final Item item;
    /**
     * This instance field holds the quantity (amount of items that will be purchased).
     */
    private final int quantity;
    /**
     * This constructor creates a StoreItem object based on an item object and integer quantity passed in.
     * @param theItem is the Item object being passed in.
     * @param theQuantity is the quantity of Item bjects being purchased.
     */
    public StoreItemOrder(final Item theItem, final int theQuantity) {
        super();
        item = Objects.requireNonNull(theItem, "The Item cannot be null.");

        if (theQuantity < 0) {
            throw new IllegalArgumentException();
        }
        quantity = theQuantity;
    }
    /**
     * This getter method returns the Item.
     * @return the item object.
     */
    @Override
    public Item getItem() {
        return item;
    }
    /**
     * This getter method returns the quantity of item objects being purchased.
     * @return an integer quantity of item objects purchased.
     */
    @Override
    public int getQuantity() {
        return quantity;
    }
    /**
     * This toString method returns a string containing the item hashcode and quantity.
     * @return a String in the form \"Item: item hashcode, quantity: item quantity\"".
     */
    @Override
    public String toString() {
        return "Item HashCode: " + item.hashCode() + ", Item Quantity: " + quantity;
    }
}
