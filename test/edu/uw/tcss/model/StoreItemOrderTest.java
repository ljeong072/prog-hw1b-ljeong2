package edu.uw.tcss.model;

import edu.uw.tcss.model.Item;
import edu.uw.tcss.model.StoreItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class StoreItemOrderTest
 {
    /**
     * The name of the item used in testing.
     */
    private static final String ITEM_NAME = "Item";

    /**
     * The name of the bulk item used in testing.
     */
    private static final String BULK_ITEM_NAME = "Bulk Item";

    /**
     * The price of the item used in testing.
     */
    private static final String ITEM_PRICE = ".50";

    /**
     * The bulk price of the item used in testing.
     */
    private static final String BULK_PRICE = "4.00";

    /**
     * The negative price of the item used in testing.
     */
    private static final String NEGATIVE_PRICE = "-0.01";

    /**
     * The bulk quantity of the item used in testing.
     */
    private static final int BULK_QUANTITY = 10;

     /**
      * The quantity of the item order used in testing.
      */
     private static final int TEST_QUANTITY = 10;

    /**
     * An object of class Item to use in testing.
     */
    private static final Item TEST_ITEM = new StoreItem(ITEM_NAME, new BigDecimal(ITEM_PRICE));

     /**
      * An object of class Item to use in testing.
      */
     private static final Item TEST_BULK_ITEM =
             new StoreItem(BULK_ITEM_NAME, new BigDecimal(ITEM_PRICE),
                     BULK_QUANTITY, new BigDecimal(BULK_PRICE));

     /**
      * AN object of class StoreItemOrder to be filled with a bulk item
      */
     private static StoreItemOrder STORE_ITEM_ORDER_TEST_BULK;

     /**
      * An object of class StoreItemOrder to use in testing
      */
     private static StoreItemOrder STORE_ITEM_ORDER_TEST;

     /**
      * This creates an StoreItemOrder with an item for testing.
      */
     @BeforeEach
     final void setUp() {
        STORE_ITEM_ORDER_TEST = new StoreItemOrder(TEST_ITEM, TEST_QUANTITY);
     }

     /**
      * This creates a StoreItemOrder with a bulk Item for testing.
      */
     @BeforeEach
     final void setUpBulk(){
         STORE_ITEM_ORDER_TEST_BULK = new StoreItemOrder(TEST_BULK_ITEM, TEST_QUANTITY);
     }

     @Test
     public final void testConstructorNullItemStoreItemOrder() {
         assertThrows(NullPointerException.class, () -> new StoreItemOrder(null, TEST_QUANTITY), "Two arg constructor does not handle null as an argument to item properly.");
     }

     @Test
     public final void testConstructorNegativeQuantityStoreItemOrder() {
         assertThrows(IllegalArgumentException.class, () -> new StoreItemOrder(TEST_ITEM, -1), "Two arg constructor does not handle negative numbers as an argument to StoreItem object properly.");
     }

     @Test
     public final void testTwoArgumentConstructor() {
         assertAll("Two argument constructor test.",
                 () -> {
                     setUp();
                     assertEquals(STORE_ITEM_ORDER_TEST.getItem().getClass(), STORE_ITEM_ORDER_TEST.getItem().getClass(),
                             "Item class should be: " + TEST_ITEM.getClass());
                 },
                 () -> {
                     setUp();
                     assertEquals(STORE_ITEM_ORDER_TEST.getItem().getName(), STORE_ITEM_ORDER_TEST.getItem().getName(),
                             "Item name should be: " + TEST_ITEM.getName());
                 },
                 () -> {
                     setUp();
                     assertEquals(STORE_ITEM_ORDER_TEST.getItem().getPrice(), STORE_ITEM_ORDER_TEST.getItem().getPrice(),
                             "Item price should be: " + ITEM_PRICE);
                 },
                 () -> {
                     setUpBulk();
                     assertEquals(STORE_ITEM_ORDER_TEST_BULK.getItem().getClass(), STORE_ITEM_ORDER_TEST_BULK.getItem().getClass(),
                             "Bulk Item class should be: " + TEST_BULK_ITEM.getClass());
                 },
                 () -> {
                     setUpBulk();
                     assertEquals(STORE_ITEM_ORDER_TEST_BULK.getItem().getName(), STORE_ITEM_ORDER_TEST_BULK.getItem().getName(),
                             "Bulk Item name should be: " + TEST_BULK_ITEM.getName());
                 },
                 () -> {
                     setUpBulk();
                     assertEquals(STORE_ITEM_ORDER_TEST_BULK.getItem().getPrice(), STORE_ITEM_ORDER_TEST_BULK.getItem().getPrice(),
                             "Bulk Item price should be: " + TEST_BULK_ITEM.getPrice());
                 },
                 () -> {
                     setUpBulk();
                     assertEquals(STORE_ITEM_ORDER_TEST_BULK.getItem().getBulkQuantity(), STORE_ITEM_ORDER_TEST_BULK.getItem().getBulkQuantity(),
                             "Bulk Item quantity should be: " + TEST_BULK_ITEM.getBulkQuantity());
                 },
                 () -> {
                     setUpBulk();
                     assertEquals(STORE_ITEM_ORDER_TEST_BULK.getItem().getBulkPrice(), STORE_ITEM_ORDER_TEST_BULK.getItem().getBulkPrice(),
                             "Bulk Item's bulk price should be: " + TEST_BULK_ITEM.getPrice());
                 });
     }
     @Test
     void testGetItem() {
         assertAll("GetItem test.",
                 () -> {
                     setUp();
                     assertEquals(TEST_ITEM.getClass(), STORE_ITEM_ORDER_TEST.getItem().getClass(), "Item retrieved should be an item class");
                 },
                 () -> {
                    setUp();
                    assertEquals("Item", STORE_ITEM_ORDER_TEST.getItem().getName(), "Item retrieved should be named item");
                 },
                 () -> {
                    setUpBulk();
                    assertEquals(TEST_ITEM.getClass(), STORE_ITEM_ORDER_TEST_BULK.getItem().getClass(), "Item retrieved should be an item class");
                 },
                 () -> {
                     setUpBulk();
                     assertEquals("Bulk Item", STORE_ITEM_ORDER_TEST_BULK.getItem().getName(), "Item retrieved should be named item");
                 });
        }

     @Test
     void testGetQuantity() {
         assertAll("GetQuantity test.",
                 () -> {
                     assertEquals(TEST_QUANTITY, STORE_ITEM_ORDER_TEST_BULK.getQuantity(), "Quantity of Item should be 10.");
                 },
                 () -> {
                    StoreItemOrder testitem2 = new StoreItemOrder(TEST_ITEM, 5);
                    assertEquals(5, testitem2.getQuantity(), "Quantity of Item should be 5");
                 });
     }
    @Test
    void testToString() {
        assertAll("toString test.",
                () -> {
                    assertEquals("Item HashCode: " + STORE_ITEM_ORDER_TEST.getItem().hashCode() + ", Item Quantity: " + STORE_ITEM_ORDER_TEST.getQuantity(), STORE_ITEM_ORDER_TEST.toString(), "toString should be: \"Item: \"HashCode\" + quantity: 10");
                });
    }
}

