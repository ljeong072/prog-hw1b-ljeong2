// Finish and comment me!

package edu.uw.tcss.model;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.math.BigDecimal;

/**
 * This class creates a "cart" for the client to put their items in before purchasing
 * those items.
 */
public class StoreCart implements Cart {
    /**
     * This instance field holds the itemorder objects
     */
    private final ArrayList<StoreItemOrder> cart;
    /**
     * This instance field represents whether the client has a membership or not.
     */
    private boolean membership;

    /**
     * This constructor creates a cart object and sets its values
     * to "default". The cart is empty and the client does not
     * have a membership.
     */
    public StoreCart() {
        super();
        cart = new ArrayList<>();
        membership = false;
    }

    /**
     * This method adds an item to the cart.
     *
     * @param theOrder reprsents the item order containing the
     * type of item and the quantity of such items.
     * @Override
     */
    @Override
    public void add(final StoreItemOrder theOrder) {

        for (int i = 0; i < cart.size(); i++)
        {
            if (theOrder.getItem().equals(cart.get(i).getItem()))
            {
                cart.remove(i);
            }
        }
        cart.add(theOrder);
    }

    /**
     * This method sets the client's membership to has or does not have.
     * @param theMembership is a boolean representing true if the client
     * has a membership and false if the client does not.
     * @Override
     */
    @Override
    public void setMembership(final boolean theMembership) {
        membership = theMembership;
    }

    /**
     * This method takes all the items in the cart and calculates
     * the total of all the items.
     * @return a bigdecimal object representing the total.
     * @Override
     */
    @Override
    public BigDecimal calculateTotal() {

            BigDecimal totalprice = new BigDecimal(0);

            for (final ItemOrder itemo : cart) {
                final int itemquantity = itemo.getQuantity();
                final StoreItem item = (StoreItem) itemo.getItem();
                if (membership && item.isBulk() &&
                        itemquantity >= item.getBulkQuantity()) {
                    final int localbq = item.getBulkQuantity();

                    final int remainder = itemquantity%localbq;
                    final int total = itemquantity/localbq;

                    final BigDecimal finprice = new BigDecimal(item.getBulkPrice().doubleValue() * total
                            + remainder * item.getPrice().doubleValue());

                    final BigDecimal round = finprice.setScale(2, RoundingMode.HALF_EVEN);
                    totalprice = totalprice.add(round);
                } else {
                    final BigDecimal finprice = new BigDecimal(item.getPrice().doubleValue() * itemquantity);
                    final BigDecimal round = finprice.setScale(2, RoundingMode.HALF_EVEN);
                    totalprice = totalprice.add(round);
                }
            }
            return totalprice;
        }

    /**
     * This method clears all the items in the cart.
     * @Override
     */
    @Override
    public void clear() {
        cart.clear();
    }

    /**
     * This method returns the size of the cart
     * @return the size of the cart as an integer.
     * @Override
     */
    @Override
    public int getCartSize() {
        return cart.size();
    }

    /**
     * This creates the cart's contents and formats them into a string.
     * @return a String representing the cart.
     * @Override.
     */
    public String toString() {
        return cart.toString();
    }
}
