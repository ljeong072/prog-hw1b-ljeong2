package edu.uw.tcss.model;

import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class creates a "cart" for the client to put their items in before purchasing
 * those items.
 */
public class StoreCart implements Cart {
    /**
     * This instance field holds the itemorder objects in an ArrayList<StoreItemOrder>.
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
     * This method adds an item to the cart. Before adding, it will iterate through the cart and delete
     * the previous order to update the cart.
     *
     * @param theOrder reprsents the item order containing the
     * type of item and the quantity of such items.
     */
    @Override
    public void add(final StoreItemOrder theOrder) {
        final Iterator<StoreItemOrder> itr = cart.iterator();
        StoreItemOrder itemorder;
        StoreItem item;

        while(itr.hasNext())
        {
            itemorder = itr.next();

            if (itemorder.getItem().equals(theOrder.getItem()))
            {
                itr.remove();
            }
        }
        cart.add(theOrder);
    }

    /**
     * This method sets the client's membership to has or does not have.
     * @param theMembership is a boolean representing true if the client
     * has a membership and false if the client does not.
     */
    @Override
    public void setMembership(final boolean theMembership) {
        membership = theMembership;
    }

    /**
     * This method takes all the items in the cart and calculates
     * the total of all the items.
     * @return a bigdecimal object representing the total.
     */
    @Override
    public BigDecimal calculateTotal() {
        BigDecimal totalprice = new BigDecimal(0);
        for (final ItemOrder itemo : cart) {
            int itemquantity = itemo.getQuantity();
            final StoreItem item = (StoreItem) itemo.getItem();
            final int localbq = item.getBulkQuantity();

            if (membership && item.isBulk() && itemquantity >= localbq) {
                final BigDecimal total = new BigDecimal(itemquantity/localbq);
                itemquantity %= localbq;

                totalprice = totalprice.add(item.getBulkPrice().multiply(total));
            }
            totalprice = totalprice.add(item.getPrice().multiply(new BigDecimal(itemquantity)));
        }
        return totalprice.setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * This method clears all the items in the cart.
     */
    @Override
    public void clear() {
        cart.clear();
    }

    /**
     * This method returns the size of the cart
     * @return the size of the cart as an integer.
     */
    @Override
    public int getCartSize() {
        return cart.size();
    }

    /**
     * This toString takes the hascode and quantites of the storeitemorder object inside string.
     * Each item is separated by a space.
     * @return a string representing the contents of a cart
     */
    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder();
        for (int i = 0; i < (cart.size() - 1); i++) {
            str.append(cart.get(i).toString()).append(" ");
        }
        return "[" + str.append(cart.getLast().toString()) + "]";
    }
}
