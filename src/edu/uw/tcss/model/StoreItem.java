package edu.uw.tcss.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class StoreItem implements Item {
    final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
    private final String name;
    private final BigDecimal price;
    private final int bq;
    private final BigDecimal bp;

    public StoreItem(final String name, final BigDecimal price) {
        this(name, price, 0, new BigDecimal(0));
    }

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

    @Override
    public String toString(){
        String str = "";

        str += name + ", " + nf.format(price.doubleValue());
        if (bp.doubleValue() > 0)
        {
            str += (" (" + bq + " for " + nf.format(bp.doubleValue()) + ")");
        }
        return str;
    }

    @Override
    public boolean equals(final Object theItem){
        boolean result = false;

        if (theItem != null && theItem.getClass().equals(this.getClass())) {
            final StoreItem item = (StoreItem)theItem;
            result = (item.name.equals(this.name) && item.price.equals
                    (this.price) && item.bq == this.bq && item.bp.equals(this.bp));
        }
        return result;
    }
    @Override
    public int hashCode(){
        return Objects.hash(name, price, bq, bp);
    }
}
