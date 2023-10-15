// Finish and comment me!

package edu.uw.tcss.model;

import java.util.Objects;


public final class StoreItemOrder implements ItemOrder {
    private final Item item;
    private final int quantity;


    public StoreItemOrder(final Item theItem, final int theQuantity) {
        super();
        item = Objects.requireNonNull(theItem, "The Item cannot be null.");

        if (theQuantity < 0) {
            throw new IllegalArgumentException();
        }
        quantity = theQuantity;
    }
    @Override
    public Item getItem() {
        return item;
    }
    

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Item: " + item + ", quantity: " + quantity;
    }

}
