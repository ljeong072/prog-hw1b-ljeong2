package edu.uw.tcss.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * This class represents a storeitem. It contains instance fields to store the name, price,
 * bulk quantity and bulk price (if applicable) along with getter methods. This class overrides hashcode and
 * the equals method.
 */
public class StoreItem implements Item, Comparable<StoreItem> {
    /**
     * This Numberformat instance field allows the price of each item to be formatted to two decimals.
     */
    private final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
    /**
     * This String contains the name of the StoreItem object.
     */
    private final String name;
    /**
     * This BigDecimal object contains the price of the StoreItem objcet.
     */
    private final BigDecimal price;
    /**
     * This BigDecimal object contains the bulk quanitty required to get a discount if the client
     * purchases bulkquanitty number of items while the membership is active (set to true).
     */
    private final int bq;
    /**
     * This BigDecimal object contains the bulk price which the client pays if he/she purchases the
     * necessary quantity of bulk items while their membership is active (set to true).
     */
    private final BigDecimal bp;

    /**
     * This StoreITem constructor creates a StoreItem object given the name and the price. The bulkquantity
     * and Bulkprice is set to 0 meaning that the user cannot purchase this item in "bulk quanitties" to recieve
     * a discount (in the event that the client's membership is active).
     *
     * @param name  is the name of the StoreItem object.
     * @param price is the price for the StoreItem object.
     */
    public StoreItem(final String name, final BigDecimal price) {
        this(name, price, 0, new BigDecimal(0));
    }

    /**
     * This overloaded StoreItem constructor creates a StoreItem object given the name, price, bulkquantity,
     * and bulkprice.
     *
     * @param name  is the name of the StoreItem object.
     * @param price is the price of the StoreItem object (individual quantities).
     * @param bq    is the quantity required to purchase in bulk and receive a discount (if membership is true and
     *              applicable).
     * @param bp    is the price in bulk if applicable.
     */
    public StoreItem(final String name, final BigDecimal price, final int bq,
                     final BigDecimal bp) {
        super();
        this.name = Objects.requireNonNull(name, "Name cannot be null.");
        this.price = Objects.requireNonNull(price, "price cannot be null.");
        this.bp = Objects.requireNonNull(bp, "Bulk price cannot be null.");

        if (name.isEmpty() || price.doubleValue() < 0
                || bq < 0 || bp.doubleValue() < 0) {
            throw new IllegalArgumentException();
        }
        this.bq = bq;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public int getBulkQuantity() {
        return bq;
    }

    @Override
    public BigDecimal getBulkPrice() {
        return bp;
    }

    @Override
    public boolean isBulk() {
        return bp.doubleValue() > 0;
    }

    /**
     * The toString method prints out the name and price of the StoreItem, but if the StoreItem has a bulk price and
     * bulk quantity, it will print out the name, price, bulkquantity, and bulkprice.
     *
     * @return a String representing the name and prices of the StoreItem (including the bulk quantity and bulk price
     * if applicable).
     */
    @Override
    public String toString() {
        String str = "";

        str += name + ", " + nf.format(price);
        if (bp.doubleValue() > 0) {
            str += (" (" + bq + " for " + nf.format(bp) + ")");
        }
        return str;
    }

    /**
     * Overriding equals method to compare whether the instance fields (values) of a StoreItem are equivalent
     * to one another.
     *
     * @param theItem is the StoreItem passed in.
     * @return a boolean which is true if the StoreItem objects are identical, and false if they are different
     * objects.
     */
    @Override
    public boolean equals(final Object theItem) {
        boolean result = false;

        if (theItem != null && theItem.getClass().equals(this.getClass())) {
            final StoreItem item = (StoreItem) theItem;
            result = (item.name.equals(this.name) && item.price.equals
                    (this.price) && item.bq == this.bq && item.bp.equals(this.bp));
        }
        return result;
    }

    /**
     * Overrides the hasCode to return the hashcode of the StoreItem object based on its instance fields.
     *
     * @return a hashcode based on the instance fields of the StoreItem object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, price, bq, bp);
    }

    /**
     * Overrides the compareTo method to sort the StoreItem objects alphabetically.
     *
     * @param o the object to be compared.
     * @return an 1 if the StoreItem object is "higher" alphabetically and -1 if the StoreItem object is
     * "lower" alphabetically and 0 if they are even.
     */
    @Override
    public int compareTo(final StoreItem o) {
        return this.name.compareTo(o.name);
    }
}